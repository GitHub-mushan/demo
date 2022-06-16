package com.example.demo;

import com.example.demo.util.Similarity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Similarity.getSimilarity("程序猿","头发呢"));
    }

}
