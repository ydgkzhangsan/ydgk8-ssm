package com.ydgk.springboot;

import com.ydgk.springboot.SpringBootHelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-14 15:26
 */
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(SpringBootHelloWorld.class);

    @RequestMapping("/hello")
    String home() {
        logger.info("hello~~");
        return "Hello World!";
    }

}
