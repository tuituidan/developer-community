package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.consts.Consts;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/9/25
 */
@Controller
@RequestMapping(Consts.API_V1)
public class IndexController {

    /**
     * index
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, String returnUrl) {
        request.getSession().setAttribute("returnUrl", returnUrl);
        return "redirect:/login";
    }

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
