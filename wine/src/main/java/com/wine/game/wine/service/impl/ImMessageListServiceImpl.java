package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ImMessageListDao;
import com.wine.game.wine.entity.ImMessageListEntity;
import com.wine.game.wine.service.ImMessageListService;


@Service("imMessageListService")
public class ImMessageListServiceImpl extends ServiceImpl<ImMessageListDao, ImMessageListEntity> implements ImMessageListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ImMessageListEntity> page = this.page(
                new Query<ImMessageListEntity>().getPage(params),
                new QueryWrapper<ImMessageListEntity>()
        );

        return new PageUtils(page);
    }

}