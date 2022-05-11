package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ImMessageDao;
import com.wine.game.wine.entity.ImMessageEntity;
import com.wine.game.wine.service.ImMessageService;


@Service("imMessageService")
public class ImMessageServiceImpl extends ServiceImpl<ImMessageDao, ImMessageEntity> implements ImMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ImMessageEntity> page = this.page(
                new Query<ImMessageEntity>().getPage(params),
                new QueryWrapper<ImMessageEntity>()
        );

        return new PageUtils(page);
    }

}