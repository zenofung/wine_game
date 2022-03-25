package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.AttentionEntity;
import com.wine.game.wine.service.AttentionService;
import com.wine.game.wine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.UserRankingDao;
import com.wine.game.wine.entity.UserRankingEntity;
import com.wine.game.wine.service.UserRankingService;


@Service("userRankingService")
public class UserRankingServiceImpl extends ServiceImpl<UserRankingDao, UserRankingEntity> implements UserRankingService {
    @Autowired
    private UserService userService;
    @Autowired
    private AttentionService attentionService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<UserRankingEntity> userRankingEntityQueryWrapper = new QueryWrapper<>();
        userRankingEntityQueryWrapper.orderByDesc(params.get("type").toString());
        IPage<UserRankingEntity> page = this.page(
                new Query<UserRankingEntity>().getPage(params),
                userRankingEntityQueryWrapper
        );
        page.getRecords().stream().forEach(m->{
           m.setUserVo(userService.getByIdUserVo(m.getUserId()));
           m.setMe(m.getUserId().equals(params.get("userId").toString())?true:false);
            List<AttentionEntity> list1 = attentionService.list(new QueryWrapper<AttentionEntity>().eq("me_id", params.get("userId")).eq("follower_id",m.getUserId()));
            m.setMeAttention(list1.size() > 0 ? true:false);

        });

        return new PageUtils(page);
    }

    @Override
    public List<UserRankingEntity> getListRanking(String type) {
        return null;
    }

}