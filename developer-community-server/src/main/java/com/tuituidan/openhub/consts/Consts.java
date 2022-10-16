package com.tuituidan.openhub.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Consts.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2020/10/9
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Consts {

    /**
     * API 版本1的前缀.
     */
    public static final String API_V1 = "/api/v1";

    /**
     * ATTACH_FILE
     */
    public static final String ATTACH_FILE = "attach-file";

    /**
     * 与jar同级目录，jar如果放在系统根目录，比如docker环境
     * System.getProperty("user.dir")获取到的是"/"，直接去掉.
     */
    public static final String ROOT_DIR = "/".equals(System.getProperty("user.dir")) ? ""
            : System.getProperty("user.dir");

}
