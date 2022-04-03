package com.wine.game.wine.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WineUserVo {
    private List<UserVo> userVo;
    private List<UserVo>  userReady;
    private boolean meStatus;
}
