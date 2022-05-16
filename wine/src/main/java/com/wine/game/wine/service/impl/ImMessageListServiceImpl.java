package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.ImMessageEntity;
import com.wine.game.wine.service.ImMessageService;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ImMessageListDao;
import com.wine.game.wine.entity.ImMessageListEntity;
import com.wine.game.wine.service.ImMessageListService;
import org.springframework.util.StringUtils;


@Service("imMessageListService")
public class ImMessageListServiceImpl extends ServiceImpl<ImMessageListDao, ImMessageListEntity> implements ImMessageListService {

    @Autowired
    private UserService userService;
    @Autowired
    private ImMessageService imMessageService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("userId"))){
            throw new RuntimeException("没有用户ID");
        }
        IPage<ImMessageListEntity> page = this.page(
                new Query<ImMessageListEntity>().getPage(params),
                new QueryWrapper<ImMessageListEntity>().eq("user_id",params.get("userId")).or().eq("friend_id",params.get("userId")).orderByAsc("create_time")
        );
        page.getRecords().stream().forEach(m->{
            //获取用户头像昵称

            //获取好友聊天内容
            ImMessageEntity imMagListId = imMessageService.getOneByListId(m.getId());
            if (StringUtils.isEmpty(imMagListId)){
                ImMessageEntity imMessageEntity=new ImMessageEntity();
                m.setImMessageEntityLast(imMessageEntity);
            }else {
                m.setImMessageEntityLast(imMagListId);
            }

            List<ImMessageEntity> list = imMessageService.list(new QueryWrapper<ImMessageEntity>().eq("im_mag_list_id", m.getId()).ne("user_id",params.get("userId")).eq("message_status", 0));
            m.setUnread(list.size());
            String userId = params.get("userId").toString();
            getUserProt(m, userId);

        });

        return new PageUtils(page);
    }

    private void getUserProt(ImMessageListEntity m, String userId) {
        if (m.getUserId().equals(userId)){
            UserVo byIdUserVo2 = userService.getByIdUserVo(m.getFriendId());
            m.setOnLine(byIdUserVo2.getLoginStatus());
            m.setUserVo(byIdUserVo2);
        }else {
            UserVo byIdUserVo = userService.getByIdUserVo(m.getUserId());
            m.setUserVo(byIdUserVo);
            m.setOnLine(byIdUserVo.getLoginStatus());
        }
    }
    private void getUserProt2(ImMessageListEntity m, String userId) {
        if (m.getUserId().equals(userId)){
            UserVo byIdUserVo2 = userService.getByIdUserVo(m.getUserId());
            m.setOnLine(byIdUserVo2.getLoginStatus());
            m.setUserVo(byIdUserVo2);
        }else {
            UserVo byIdUserVo = userService.getByIdUserVo(m.getFriendId());
            m.setUserVo(byIdUserVo);
            m.setOnLine(byIdUserVo.getLoginStatus());
        }
    }



    @Override
    public String getFriendList(String imList) {
        // ImMessageListEntity byId = this.getById(imList);
        // List<ImMessageListEntity> list = this.list(new QueryWrapper<ImMessageListEntity>().eq("user_id", byId.getFriendId()).eq("friend_id", byId.getUserId()));
        return "";
    }

    @Override
    public ImMessageListEntity getByIdAndUserVo(String id, String userId) {
        ImMessageListEntity byId = this.getById(id);
        ImMessageEntity imMagListId = imMessageService.getOneByListId(byId.getId());
        byId.setImMessageEntityLast(imMagListId);
        List<ImMessageEntity> list = imMessageService.list(new QueryWrapper<ImMessageEntity>().eq("im_mag_list_id", byId.getId()).eq("message_status", 0));
        getUserProt2(byId,userId);
        byId.setUnread(list.size());
        return byId;

    }


}