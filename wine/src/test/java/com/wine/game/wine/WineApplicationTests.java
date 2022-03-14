package com.wine.game.wine;

import cn.binarywang.wx.miniapp.api.WxMaSchemeService;
import cn.binarywang.wx.miniapp.api.WxMaSecCheckService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class WineApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxMaService wxMaService;

    @Test
    void contextLoads() {
        // redisTemplate.opsForValue().set("1","3");
        try {
            // String accessToken = wxMaService.getAccessToken();
            boolean aa = wxMaService.getSecCheckService().checkMessage("特3456书yuuo莞6543李zxcz蒜7782法fgnv级");
            boolean bb = wxMaService.getSecCheckService().checkMessage("草拟吗");
            System.out.println("aa---->"+aa+"bb---->"+bb);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }

}
