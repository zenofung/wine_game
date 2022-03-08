package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.ComComEntity;
import com.wine.game.wine.entity.CommentEntity;
import com.wine.game.wine.service.ComComService;
import com.wine.game.wine.service.CommentService;
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
import com.wine.game.wine.entity.ArticleEntity;
import com.wine.game.wine.service.ArticleService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Resource
    private CommentService commentService;
    @Resource
    private ComComService comComService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                new QueryWrapper<ArticleEntity>()
        );
        page.getRecords().stream().forEach(m->{
            QueryWrapper<CommentEntity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("art_id",m.getId());
            List<CommentEntity> list = commentService.list(queryWrapper);
            list.stream().forEach(f->{
                //查询二级评价
                QueryWrapper<ComComEntity> comId = new QueryWrapper<ComComEntity>().eq("com_id", f.getId());
                List<ComComEntity> list1 = comComService.list(comId);
                f.setComComEntityList(list1);
                //查找多级评论
                list1.stream().forEach(t->{
                        QueryWrapper<ComComEntity> comId2 = new QueryWrapper<ComComEntity>().eq("com_id_two", t.getId());
                        List<ComComEntity> list3 = comComService.list(comId2);
                        if (list3.size()>0){
                            getListComCom(list3);
                        }

                    f.setComComEntityList(list3);
                });


            });
            m.setCommentEntity(list);
        });
        return new PageUtils(page);
    }

   public void getListComCom(List<ComComEntity> comComEntityList){
//       List<ComComEntity> =new ArrayList<>();

        for (ComComEntity co:comComEntityList){
            List<ComComEntity> id = comComService.list(new QueryWrapper<ComComEntity>().eq("com_id_two", co.getId()));
            co.setComComEntityList(id);
            if (id.size()>0){
                getListComCom(id);
            }
//            list.addAll(id);
        }
//        return list;
    }
}