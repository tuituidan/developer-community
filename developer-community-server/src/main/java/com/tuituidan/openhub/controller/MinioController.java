package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.consts.Consts;
import com.tuituidan.openhub.service.MinioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * MinioController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/15
 */
@Api(tags = "Minio相关")
@RestController
@RequestMapping(Consts.API_V1)
public class MinioController {

    @Resource
    private MinioService minioService;

    /**
     * 文件上传
     *
     * @param file 上传文件
     * @param type 文件类型
     * @return 文件ID
     */
    @ApiOperation("文件上传")
    @PostMapping("/file/actions/upload")
    public String uploadFile(MultipartFile file, String type) {
        return minioService.uploadFile(file, type);
    }

}
