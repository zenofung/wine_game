package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.*;
import com.wine.game.wine.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                new QueryWrapper<ArticleEntity>()
        );
        page.getRecords().stream().forEach(m -> {
            QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("art_id", m.getId());
            List<CommentEntity> list = commentService.list(queryWrapper);
            m.setCommentEntity(list);
            m.setUserEntity(userService.getById(m.getUserId()));
            List<ArticlePraiseEntity> articleId = articlePraiseService.list(new QueryWrapper<ArticlePraiseEntity>().eq("article_id", m.getId()));
            m.setPraises(articleId.size());
            List<ArticlePraiseEntity> articleId2 = articlePraiseService.list(new QueryWrapper<ArticlePraiseEntity>().eq("article_id", m.getId()).eq("user_id", params.get("user_id")));
            m.setPraiseStatus(articleId2.size() > 0 ? true : false);
            //TODO 获取是否关注
            // attentionService


            //标签
            m.setLabelEntities(articleLabelService.listByArticleId(m.getId()));
            //获取酒局
            m.setWineEntity(wineService.getById(m.getWineId()));

        });
        return new PageUtils(page);
    }

    @Override
    public ArticleEntity getByIdAndContent(Integer id, Integer userId) {
        ArticleEntity articleEntity = articleDao.selectById(id);
        UserEntity byId = userService.getById(articleEntity.getUserId());
        articleEntity.setUserEntity(byId);
        List<CommentPraiseEntity> commentPraiseEntities = commentPraiseService.list(new QueryWrapper<CommentPraiseEntity>().eq("comment_id", articleEntity.getId()));
        articleEntity.setPraises(commentPraiseEntities.size());
        List<CommentPraiseEntity> commentPraiseEntities1 = commentPraiseService.list(new QueryWrapper<CommentPraiseEntity>().eq("comment_id", articleEntity.getId()).eq("user_id", userId));
        articleEntity.setPraiseStatus(commentPraiseEntities1.size() > 0 ? true : false);
        //查询二级评价
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("art_id", articleEntity.getId());
        List<CommentEntity> list = commentService.list(queryWrapper);
        list.stream().forEach(f -> {
            //查询二级评价
            QueryWrapper<ComComEntity> comId = new QueryWrapper<ComComEntity>().eq("com_id", f.getId());
            List<ComComEntity> list1 = comComService.list(comId);
            f.setComComEntityList(list1);
            //查找多级评论
            list1.stream().forEach(t -> {
                QueryWrapper<ComComEntity> comId2 = new QueryWrapper<ComComEntity>().eq("com_id_two", t.getId());
                List<ComComEntity> list3 = comComService.list(comId2);
                if (list3.size() > 0) {
                    getListComCom(list3, userId);
                }
                t.setComComEntityList(list3);
            });


        });
        articleEntity.setCommentEntity(list);
        return articleEntity;
    }

    public void getListComCom(List<ComComEntity> comComEntityList, Integer userId) {
//       List<ComComEntity> =new ArrayList<>();

        for (ComComEntity co : comComEntityList) {
            List<ComComEntity> id = comComService.list(new QueryWrapper<ComComEntity>().eq("com_id_two", co.getId()));
            UserEntity byId = userService.getById(co.getUserId());
            co.setUserEntity(byId);
            UserEntity byId2 = userService.getById(co.getUserIdTwo());
            co.setUserEntityTwo(byId2);
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