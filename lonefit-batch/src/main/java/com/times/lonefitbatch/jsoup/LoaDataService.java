package com.times.lonefitbatch.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

@Service
public class LoaDataService {

    private static String LOA_MARKET_URL = "https://lostark.game.onstove.com/Market/List_v2";
    private static String LOA_LOGIN_URL = "https://member.onstove.com/auth/login";
    @Value("{loa.userId}")
    private String userId;
    @Value("{loa.password}")
    private String password;

    @PostConstruct
    public void getMarket() {
        Connection conn = Jsoup.connect(LOA_LOGIN_URL);

        try {
//            Connection.Response response = conn
//                    .method(Connection.Method.GET)
//                    .execute();
//
//            Connection.Response login = conn
//                    .cookies(response.cookies())
//                    .data("user_id", userId, "user_pwd", password)
//                    .method(Connection.Method.POST)
//                    .timeout(5000)
//                    .ignoreContentType(true)
//                    .execute();
//
//            System.out.println(login);
            HashMap<String, String> reqData = new HashMap<>();
            reqData.put("firstCategory", "50000");
            reqData.put("secondCategory", "50010");
            reqData.put("characterClass", "");
            reqData.put("tier", "0");
            reqData.put("grade", "99");
            reqData.put("itemName", "명예의 파편");
            reqData.put("pageNo", "1");
            reqData.put("isInit", "false");
            reqData.put("sortType", "7");

            String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJleHBpcmVfdGltZSI6MTY0NjY4MjIzNjc0MiwibWVtYmVyX25vIjoxOTQ1NTg0NjIsImFwcGxpY2F0aW9uX25vIjoxMDAwMn0.eK6eoYXKQN2ZRixuXSSqY1qFi9_uCK6sMyHUcFx9Nyhb2gz1a4KeApt-obgSy2FDMoQ61bdaeeHlGKHs-0EpLU1z6Ivssb8ykIL6N8Wbu0by5eKB_kiPbxwyWHqTJyOkMoEP6zZnxBMtZMTf_rGUkyyQ9nPZBeTXEgPtAoZe_w_AHG2JYVtAiMDfRd_uqfsed9EbxA_RzvJLKPNbSx3T8qomj15O7tZvxo2WNNdmjkbPiKNNUoh4vQSc2Oim68Nr";

            Connection.Response doc = Jsoup.connect(LOA_MARKET_URL)
                    .cookie("SUAT", jwt)
                    .data(reqData)
                    .method(Connection.Method.POST)
                    .execute();

            System.out.println(doc.parse().html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
