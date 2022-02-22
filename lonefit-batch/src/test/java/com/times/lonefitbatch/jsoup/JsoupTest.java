package com.times.lonefitbatch.jsoup;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsoupTest {

    @Autowired
    private LoaDataService loaDataService;

    @Test
    public void JsoupTest() {
        loaDataService.getMarket();
    }
}
