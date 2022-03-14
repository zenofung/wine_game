package com.zenofung.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

import java.io.File;

/**
 * @description: 
 *          
 * @author: zeno fung
 *
 * @create: 2022-03-09 14:14
 */
public class ImageUtil {

    public static String writeImage(byte[] imageBytes,String fileName,String targetPath,String imageUrl){
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.'));
        if(!fileSuffix.equals(".mp3") && !fileSuffix.equals(".jpg") && !fileSuffix.equals(".jpeg") && !fileSuffix.equals(".png") && !fileSuffix.equals(".gif")&& !fileSuffix.equals(".mp4")){
            throw new RuntimeException("请上传图片或视频");
        }
        String s = IdUtil.fastUUID();
        FileUtil.writeBytes(imageBytes, targetPath+s+fileSuffix);
        return imageUrl+s+fileSuffix;
    }


}