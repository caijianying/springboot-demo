package com.test.jol;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
@Slf4j
public class JolTest {

    @Test
    public void test(){
        User user = new User();
        user.setName("demo");
        user.setAge(10);
        log.info(ClassLayout.parseInstance(user).toPrintable());
    }
}
