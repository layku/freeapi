package cn.layku.freeapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @packageName: cn.layku.freeapi
 * @name: App
 * @description:
 * @author: 董定卓
 * @dateTime: 2020/03/07 10:40
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... strings) {
        logger.info("项目启动完成,当前环境->{},当前端口->{}", active, port);
    }
}
