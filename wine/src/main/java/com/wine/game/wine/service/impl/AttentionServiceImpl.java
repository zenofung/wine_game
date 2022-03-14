package com.wine.game.wine.service.impl;

import com.wine.game.wine.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.AttentionDao;
import com.wine.game.wine.entity.AttentionEntity;
import com.wine.game.wine.service.AttentionService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("attentionService")
public class AttentionServiceImpl extends ServiceImpl<AttentionDao, AttentionEntity> implements AttentionService {

    @Resource
    private UserService userService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AttentionEntity> attentionEntityQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(params.get("me_id"))){
            attentionEntityQueryWrapper.eq("me_id",params.get("me_id"));
        }
        IPage<AttentionEntity> page = this.page(
                new Query<AttentionEntity>().getPage(params),
                attentionEntityQueryWrapper
        );
        page.getRecords().stream().forEach(m->{
           m.setUserEntity(userService.getById(m.getFollowerId()));
        });
        return new PageUtils(page);
    }

}