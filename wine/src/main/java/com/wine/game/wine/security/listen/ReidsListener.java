package com.wine.game.wine.security.listen;

import cn.hutool.json.JSONUtil;
import com.wine.game.wine.common.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @description: 
 *          ________
 *          ___  __/______________
 *          __  /_ ___  __ \  ___/
 *          _  __/ __  /_/ / /__
 *          /_/    _  .___/\___/
 *                 /_/
 * @author: www.fpcs.top
 *
 * @create: 2021-05-24 15:22
 */
@Slf4j
public class ReidsListener extends KeyExpirationEventMessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired

    /**
     * Creates new {@link MessageListener} for {@code __keyevent@*__:expired} messages.
     *
     * @param listenerContainer must not be {@literal null}.
     */
    public ReidsListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("redis键过期{}",message.toString());
        String s = message.toString();
        if (!StringUtils.isEmpty(s)){
            if (s.contains("Auth2:")){
                String s1 = (String)redisTemplate.opsForValue().get("s");
                // AuthVo authVo = JSONUtil.toBean(s1, AuthVo.class);
                // marketingAuth2Service.refreshToken(s,authVo.getRefreshToken());
            }
        }
    }
}