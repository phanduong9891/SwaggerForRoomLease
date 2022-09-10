package com.axonactive.roomLeaseManagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class DemoApplicationTest {
    @Test
    void contextLoads() {
    }


    @Test
    void testEncryptPassword(){

        String encrtypedPassWord = new BCryptPasswordEncoder().encode("123");
        System.out.println(encrtypedPassWord);
}
}
