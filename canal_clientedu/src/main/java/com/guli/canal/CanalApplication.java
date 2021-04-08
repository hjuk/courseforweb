package com.guli.canal;

import com.guli.canal.untils.Canal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication

public class CanalApplication implements CommandLineRunner {
    @Resource
    private Canal canal;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        canal.run();
    }
}
