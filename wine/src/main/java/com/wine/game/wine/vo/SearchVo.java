package com.wine.game.wine.vo;


import com.wine.game.wine.entity.SearchEntity;
import com.wine.game.wine.entity.WineBarEntity;
import com.wine.game.wine.entity.WineEntity;
import lombok.Data;

import java.util.List;

@Data
public class SearchVo {
    // 用户历史搜索
    List<UserVo> userVos;
    //热门关键词
//    List<String> keyList;
    //酒吧显示
    List<WineBarEntity> wineBarEntities;

}
