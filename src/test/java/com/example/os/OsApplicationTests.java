package com.example.os;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class OsApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }

}
