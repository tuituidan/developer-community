package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.consts.Consts;
import com.tuituidan.openhub.pojo.entity.AttachFile;
import com.tuituidan.openhub.service.AttachFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MinioController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/15
 */
@Api(tags = "附件相关")
@RestController
@RequestMapping(Consts.API_V1)
public class AttachFileController {

    @Resource
    private AttachFileService attachFileService;

    /**
     * selectByBelongId
     *
     * @param belongId belongId
     * @return List
     */
    @ApiOperation("根据所属ID查询")
    @GetMapping("/{belongId}/attach/file")
    public List<AttachFile> selectByBelongId(@PathVariable("belongId") String belongId) {
        return attachFileService.selectByBelongId(belongId);
    }

}
