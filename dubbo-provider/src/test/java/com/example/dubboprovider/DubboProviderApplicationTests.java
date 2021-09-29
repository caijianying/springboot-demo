package com.example.dubboprovider;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
//@SpringBootTest
class DubboProviderApplicationTests {
    private volatile int i=0;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("demo");
        log.info(ClassLayout.parseInstance(user).toPrintable());
    }

    @Data
    class User{
        private String name;
    }

}
