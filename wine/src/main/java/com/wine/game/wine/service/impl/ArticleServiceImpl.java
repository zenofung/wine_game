package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.*;
import com.wine.game.wine.service.*;
import com.wine.game.wine.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ArticleDao;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Resource
    private CommentService commentService;
    @Resource
    private ComComService comComService;

    @Resource
    private ArticlePraiseService articlePraiseService;

    @Resource
    private CommentPraiseService commentPraiseService;

    @Resource
    private ComComPraiseService comComPraiseService;

    @Resource
    private UserService userService;

    @Resource
    private ArticleDao articleDao;
    @Resource
    private ArticleLabelService articleLabelService;
    @Resource
    private WineService wineService;
    @Resource
    private AttentionService attentionService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("user_id"))){
            new TimeoutException("没有用户id");
        }
        IPage<ArticleEntity> page = null;
        if (StringUtils.isEmpty(params.get("attention"))){
            page = this.page(
                    new Query<ArticleEntity>().getPage(params),
                    new QueryWrapper<ArticleEntity>()
            );
        }else {
           page= articleDao.findByAttentionID(new Query<ArticleEntity>().getPage(params),params.get("user_id").toString());
        }
        getPageArticle(params, page);
        return new PageUtils(page);
    }

    private void getPageArticle(Map<String, Object> params, IPage<ArticleEntity> page) {
        page.getRecords().stream().forEach(m -> {
            QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("art_id", m.getId());
            List<CommentEntity> list = commentService.list(queryWrapper);
            m.setCommentEntity(list);
            UserEntity byId = userService.getById(m.getUserId());
            UserVo userVo=new UserVo();
            BeanUtils.copyProperties(byId,userVo);
            m.setUserVo(userVo);

            List<ArticlePraiseEntity> articleId = articlePraiseService.list(new QueryWrapper<ArticlePraiseEntity>().eq("article_id", m.getId()));
            m.setPraises(articleId.size());
            List<ArticlePraiseEntity> articleId2 = articlePraiseService.list(new QueryWrapper<ArticlePraiseEntity>().eq("article_id", m.getId()).eq("user_id", params.get("user_id")));
            m.setPraiseStatus(articleId2.size() > 0 ? true : false);
            // 获取是否关注
            // attentionService
            List<AttentionEntity> list1 = attentionService.list(new QueryWrapper<AttentionEntity>().eq("me_id", params.get("user_id")).eq("follower_id",m.getUserId()));
            boolean flag= list1.size() > 0 ? true:false;
            m.setAttentionStatus(flag);
            //标签
            m.setLabelEntities(articleLabelService.listByArticleName(m.getId()));
            //获取酒局
            m.setWineEntity(wineService.getByIdAll(m.getWineId()));

        });
    }

    @Override
    public ArticleEntity getByIdAndContent(String id, String userId) {
        ArticleEntity articleEntity = articleDao.selectById(id);
        if (StringUtils.isEmpty(articleEntity)){
            throw new RuntimeException("没有文章");
        }
        UserEntity byId = userService.getById(articleEntity.getUserId());
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(byId,userVo);
        articleEntity.setUserVo(userVo);
        //点赞
        List<ArticlePraiseEntity> articleId = articlePraiseService.list(new QueryWrapper<ArticlePraiseEntity>().eq("article_id",id));
        articleEntity.setPraises(articleId.size());
        List<ArticlePraiseEntity> articleId2 = articlePraiseService.list(new QueryWrapper<ArticlePraiseEntity>().eq("article_id",id).eq("user_id", userId));
        articleEntity.setPraiseStatus(articleId2.size() > 0 ? true : false);
        // 获取是否关注
        // attentionService
        List<AttentionEntity> entityList = attentionService.list(new QueryWrapper<AttentionEntity>().eq("me_id", userId).eq("follower_id",articleEntity.getUserId()));
        boolean flag= entityList.size() > 0 ? true:false;
        articleEntity.setAttentionStatus(flag);
        //标签
        articleEntity.setLabelEntities(articleLabelService.listByArticleName(articleEntity.getId()));
        //获取酒局
        articleEntity.setWineEntity(wineService.getByIdAll(articleEntity.getWineId()));

        //查询二级评价
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("art_id", articleEntity.getId());
        List<CommentEntity> list = commentService.list(queryWrapper);
        list.stream().forEach(f -> {
            //查询二级评价
            QueryWrapper<ComComEntity> comId = new QueryWrapper<ComComEntity>().eq("com_id", f.getId());
            List<ComComEntity> list1 = comComService.list(comId);

            //设置用户信息
            UserEntity byId1 = userService.getById(f.getUserId());
            UserVo userVo2=new UserVo();
            BeanUtils.copyProperties(byId1,userVo2);
            f.setUserVo(userVo2);
            //查找多级评论
            list1.stream().forEach(t -> {
                UserEntity userEntity = userService.getById(t.getUserId());
                UserVo userVo3=new UserVo();
                BeanUtils.copyProperties(userEntity,userVo3);
                t.setUserVo(userVo3);
                UserEntity byId2 = userService.getById(t.getUserIdTwo());
                UserVo userVo4=new UserVo();
                BeanUtils.copyProperties(byId2,userVo4);
                t.setTwoUserVo(userVo4);
                QueryWrapper<ComComEntity> comId2 = new QueryWrapper<ComComEntity>().eq("com_id_two", t.getId());
                List<ComComEntity> list3 = comComService.list(comId2);
                if (list3.size() > 0) {
                    getListComCom(list3, userId);
                }
                t.setComComEntityList(list3);
            });
            //评论点赞

            List<CommentPraiseEntity> commentPraiseEntities2 = commentPraiseService.list(new QueryWrapper<CommentPraiseEntity>().eq("comment_id",f.getId()));
            f.setPraises(commentPraiseEntities2.size());
            List<CommentPraiseEntity> commentPraiseEntities3 = commentPraiseService.list(new QueryWrapper<CommentPraiseEntity>().eq("comment_id", f.getId()).eq("user_id", userId));
            f.setPraiseStatus(commentPraiseEntities3.size() > 0 ? true : false);
            f.setComComEntityList(list1);

        });
        articleEntity.setCommentEntity(list);
        return articleEntity;
    }

    @Override
    public CommentEntity getByIdCom(String id, String userId) {
        CommentEntity byId = commentService.getById(id);
        //查询二级评价
        QueryWrapper<ComComEntity> comId = new QueryWrapper<ComComEntity>().eq("com_id", byId.getId());
        List<ComComEntity> list1 = comComService.list(comId);

        //设置用户信息
        UserEntity byId1 = userService.getById(byId.getUserId());
        UserVo userVo2=new UserVo();
        BeanUtils.copyProperties(byId1,userVo2);
        byId.setUserVo(userVo2);
        //查找多级评论
        list1.stream().forEach(t -> {
            UserEntity userEntity = userService.getById(t.getUserId());
            UserVo userVo3=new UserVo();
            BeanUtils.copyProperties(userEntity,userVo3);
            t.setUserVo(userVo3);
            UserEntity byId2 = userService.getById(t.getUserIdTwo());
            UserVo userVo4=new UserVo();
            BeanUtils.copyProperties(byId2,userVo4);
            t.setTwoUserVo(userVo4);
            QueryWrapper<ComComEntity> comId2 = new QueryWrapper<ComComEntity>().eq("com_id_two", t.getId());
            List<ComComEntity> list3 = comComService.list(comId2);
            if (list3.size() > 0) {
                getListComCom(list3, userId);
            }
            t.setComComEntityList(list3);
            List<ComComPraiseEntity> com_com_id = comComPraiseService.list(new QueryWrapper<ComComPraiseEntity>().eq("com_com_id", t.getId()));
            t.setPraises(com_com_id.size());
            List<ComComPraiseEntity> list = comComPraiseService.list(new QueryWrapper<ComComPraiseEntity>().eq("com_com_id", t.getId()).eq("user_id", userId));
            t.setPraiseStatus(list.size() > 0 ? true : false);
        });
        //评论点赞

        List<CommentPraiseEntity> commentPraiseEntities2 = commentPraiseService.list(new QueryWrapper<CommentPraiseEntity>().eq("comment_id",byId.getId()));
        byId.setPraises(commentPraiseEntities2.size());
        List<CommentPraiseEntity> commentPraiseEntities3 = commentPraiseService.list(new QueryWrapper<CommentPraiseEntity>().eq("comment_id", byId.getId()).eq("user_id", userId));
        byId.setPraiseStatus(commentPraiseEntities3.size() > 0 ? true : false);
        byId.setComComEntityList(list1);

        return byId;

    }

    public void getListComCom(List<ComComEntity> comComEntityList, String userId) {
//       List<ComComEntity> =new ArrayList<>();

        for (ComComEntity co : comComEntityList) {
            List<ComComEntity> id = comComService.list(new QueryWrapper<ComComEntity>().eq("com_id_two", co.getId()));
            UserEntity byId = userService.getById(co.getUserId());
            UserVo userVo2=new UserVo();
            BeanUtils.copyProperties(byId,userVo2);
            co.setUserVo(userVo2);
//            co.setUserEntity(byId);
            UserEntity byId2 = userService.getById(co.getUserIdTwo());
//            co.setUserEntityTwo(byId2);
            UserVo userVo3=new UserVo();
            BeanUtils.copyProperties(byId2,userVo3);
            co.setUserVo(userVo2);

            co.setComComEntityList(id);
            List<ComComPraiseEntity> commentPraiseEntities = comComPraiseService.list(new QueryWrapper<ComComPraiseEntity>().eq("com_com_id", co.getId()));
            co.setPraises(commentPraiseEntities.size());
            List<ComComPraiseEntity> commentPraiseEntities1 = comComPraiseService.list(new QueryWrapper<ComComPraiseEntity>().eq("com_com_id", co.getId()).eq("user_id", userId));
            co.setPraiseStatus(commentPraiseEntities1.size() > 0 ? true : false);
            if (id.size() > 0) {
                getListComCom(id, userId);
            }
//            list.addAll(id);
        }
//        return list;
    }
}