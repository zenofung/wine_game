package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.entity.WineBarEntity;
import com.wine.game.wine.entity.WineEntity;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.service.WineBarService;
import com.wine.game.wine.service.WineService;
import com.wine.game.wine.vo.SearchVo;
import com.wine.game.wine.vo.UserVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.SearchDao;
import com.wine.game.wine.entity.SearchEntity;
import com.wine.game.wine.service.SearchService;
import org.springframework.util.StringUtils;


@Service("searchService")
public class SearchServiceImpl extends ServiceImpl<SearchDao, SearchEntity> implements SearchService {

    @Autowired
    private WineBarService wineBarService;
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("userId"))){
            throw new RuntimeException("没有用户ID");
        }
        IPage<SearchEntity> page = this.page(
                new Query<SearchEntity>().getPage(params),
                new QueryWrapper<SearchEntity>().eq("user_id",params.get("userId"))
        );
        //先查历史搜索

        return new PageUtils(page);
    }

    @Override
    public List<String> getListKeyWord() {
        List<String> list = searchDao.getListKeyWord();
        return list;
    }

    @Override
    public SearchVo getListSearch(String key) {
        List<WineBarEntity> bar_tittle = wineBarService.list(new QueryWrapper<WineBarEntity>().like("bar_tittle", key));
        List<UserEntity> user_nikename = userService.list(new QueryWrapper<UserEntity>().like("user_nikename", key));
        List<UserVo> collect = user_nikename.stream().map(m -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(m, userVo);
            return userVo;
        }).collect(Collectors.toList());

        SearchVo searchVo=new SearchVo();
        searchVo.setUserVos(collect);
        searchVo.setWineBarEntities(bar_tittle);
        return searchVo;
    }

}