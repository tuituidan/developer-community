package com.tuituidan.openhub.exception;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.tuituidan.tresdin.util.StringExtUtils;

/**
 * MinioUploadException.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/15
 */
public class MinioException extends RuntimeException {

    private static final long serialVersionUID = 6691259735796864272L;

    /**
     * HttpRequestErrorException.
     *
     * @param message message
     * @param args args
     */
    public MinioException(String message, Object... args) {
        super(StringExtUtils.format(message, args), EventArgUtil.extractThrowable(args));
    }

}
