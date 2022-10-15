package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.consts.Consts;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/9/25
 */
@RestController
@RequestMapping(Consts.API_V1)
public class IndexController {

    /**
     * index
     *
     * @return List
     */
    @GetMapping("/test")
    public List<String> index() {
        return Arrays.asList("1", "2");
    }

}
