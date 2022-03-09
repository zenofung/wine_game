package com.wine.game.wine.controller;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.zenofung.common.utils.ImageUtil;
import com.zenofung.common.utils.R;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @description:
 * @author: zeno
 * @create: 2022-03-09 13:43
 */

@RestController
@RequestMapping("wine/image")
public class ImageController {

    @Value("${image.targetPath}")
    private String targetPath;
    @Value("${image.imageUrl}")
    private String imageUrl;

    @RequestMapping("saveImage")
    public R saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        String s = ImageUtil.writeImage(file.getBytes(), file.getOriginalFilename(), targetPath, imageUrl);
        return R.ok(s);
    }

}