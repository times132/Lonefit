package com.times.lonefitbatch.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class LoaDataService {

    private static String LOA_MARKET_URL = "https://lostark.game.onstove.com/Market";

    @PostConstruct
    public void getMarket() {
        Connection conn = Jsoup.connect(LOA_MARKET_URL);

        try {
            Document doc = conn.get();
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
