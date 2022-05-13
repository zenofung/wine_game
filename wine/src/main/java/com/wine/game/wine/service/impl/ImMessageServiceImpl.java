package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.ImMessageListEntity;
import com.wine.game.wine.service.ImMessageListService;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.util.StringUtils;


@Service("imMessageService")
public class ImMessageServiceImpl extends ServiceImpl<ImMessageDao, ImMessageEntity> implements ImMessageService {

    @Autowired
    private ImMessageDao imMessageDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ImMessageListService imMessageListService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        if (StringUtils.isEmpty(params.get("imList"))){
            throw new RuntimeException("管道id 不存在");
        }
        // String friendList= imMessageListService.getFriendList(params.get("imList").toString());
        IPage<ImMessageEntity> page = this.page(
                new Query<ImMessageEntity>().getPage(params),
                new QueryWrapper<ImMessageEntity>().eq("im_mag_list_id",params.get("imList").toString()).orderByDesc("create_time")
        );
        if (StringUtils.isEmpty(params.get("userId"))){
            throw new RuntimeException("id 不存在");
        }
        String userId = params.get("userId").toString();
        page.getRecords().stream().forEach(m->{
            getUserVo(userId, m);
        });

        return new PageUtils(page);
    }

    private void getUserVo(String userId, ImMessageEntity m) {
        if (m.getUserId().equals(userId)){
            m.setMessageUser(0);
            m.setUserVo(userService.getByIdUserVo(m.getUserId()));
        }else {
            m.setMessageUser(1);
            m.setUserVo(userService.getByIdUserVo(m.getUserId()));
        }
    }


    @Override
    public ImMessageEntity getOneByListId(Integer id) {
       return imMessageDao.getOneByListId(id);
    }

}