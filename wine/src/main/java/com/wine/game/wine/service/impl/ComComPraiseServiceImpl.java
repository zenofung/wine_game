package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.ComComEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ComComPraiseDao;
import com.wine.game.wine.entity.ComComPraiseEntity;
import com.wine.game.wine.service.ComComPraiseService;


@Service("comComPraiseService")
public class ComComPraiseServiceImpl extends ServiceImpl<ComComPraiseDao, ComComPraiseEntity> implements ComComPraiseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComComPraiseEntity> page = this.page(
                new Query<ComComPraiseEntity>().getPage(params),
                new QueryWrapper<ComComPraiseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void removeByUserId(ComComPraiseEntity comComPraiseEntity) {
        this.remove(new QueryWrapper<ComComPraiseEntity>().eq("user_id",comComPraiseEntity.getUserId())
                .eq("com_com_id",comComPraiseEntity.getComComId()));
    }

}