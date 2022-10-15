package com.tuituidan.openhub.util;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * MarkdownUtils.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/12
 */
@UtilityClass
@Slf4j
public class MarkdownUtils {

    private static final List<String> MARKDOWN_IDENTIFIERS = Arrays.asList("\\*", "#", "\\+\\+", "~", "==",
            "^", ":::", "hljs-left", "hljs-center", "hljs-right", ">", "- ", "```", " ");

    private static final int INTRODUCTION_LEN = 250;

    private static final int SUIT_WIDTH = 130;

    private static final int SUIT_HEIGHT = 80;

    /**
     * getIntroduction
     *
     * @param markdown markdown
     * @return String
     */
    public static String getIntroduction(String markdown) {
        if (StringUtils.isBlank(markdown)) {
            return "";
        }
        for (String identifier : MARKDOWN_IDENTIFIERS) {
            markdown = markdown.replaceAll(identifier, "");
        }
        return markdown.length() > INTRODUCTION_LEN
                ? markdown.substring(0, INTRODUCTION_LEN) + "..." : markdown;
    }

    /**
     * 从html中选出大小合适的封面图
     *
     * @param html html
     * @return String
     */
    public static String getFirstImage(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }
        Elements elements = Jsoup.parse(html).body().getElementsByTag("img");
        if (CollectionUtils.isEmpty(elements)) {
            return "";
        }
        List<String> imgUrls = elements.stream().map(item -> item.attr("src"))
                .filter(url -> !StringUtils.contains(url, ".svg"))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(imgUrls)) {
            return "";
        }
        // 先默认赋值第一张图片，然后从第一张开始检查图片大小
        // 只要找到宽度大于130，高度大于80的终止循环返回该图片
        // 如果到最后都没找到，即使第一张不符合大小，也会使用第一张
        String result = imgUrls.get(0);
        for (String url : imgUrls) {
            String tempUrl = checkImageSize(url);
            if (StringUtils.isNotBlank(tempUrl)) {
                result = tempUrl;
                break;
            }
        }
        return result;
    }

    private String checkImageSize(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            if (image.getWidth() > SUIT_WIDTH && image.getHeight() > SUIT_HEIGHT) {
                return url;
            }
        } catch (Exception ex) {
            log.error("获取图片发生错误-{}", url, ex);
        }
        return "";
    }

}
