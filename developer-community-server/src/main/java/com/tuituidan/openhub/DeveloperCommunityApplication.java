package com.tuituidan.openhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * DeveloperCommunityApplication
 *
 * @author tuituidan
 */
@EnableCaching
@SpringBootApplication
public class DeveloperCommunityApplication {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(DeveloperCommunityApplication.class, args);
    }

}
