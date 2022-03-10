package com.times.lonefitbatch.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoaDataService {

    private static String LOA_MARKET_URL = "https://lostark.game.onstove.com/Market/List_v2";
    private static String LOA_LOGIN_URL = "https://member.onstove.com/auth/signin";
    @Value("${loa.userId}")
    private String userId;
    @Value("${loa.password}")
    private String password;

    //@PostConstruct
    public void getMarket() {
        Connection conn = Jsoup.connect("https://member.onstove.com/auth/login");

        try {
            Connection.Response loginResponse = conn
                    .timeout(5000)
                    .method(Connection.Method.GET)
                    .execute();

            Connection.Response response = Jsoup.connect(LOA_LOGIN_URL)
                    .data("user_id", userId, "user_pwd", password, "forever", "false")
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .timeout(5000)
                    .execute();

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

            Document doc = Jsoup.connect(LOA_MARKET_URL)
                    .cookies(response.cookies())
                    .data(reqData)
                    .post();

            //System.out.println(doc);

            Element itemElement = doc.getElementById("tbodyItemList");
            String item1Name = itemElement.getElementsByClass("name").get(0).text();
            String itemPrice1 = itemElement.getElementsByTag("em").get(2).text();
            String item2Name = itemElement.getElementsByClass("name").get(1).text();
            String itemPrice2 = itemElement.getElementsByTag("em").get(5).text();
            String item3Name = itemElement.getElementsByClass("name").get(2).text();
            String itemPrice3 = itemElement.getElementsByTag("em").get(8).text();
            System.out.println(item1Name + " : " + itemPrice1);
            System.out.println(item2Name + " : " + itemPrice2);
            System.out.println(item3Name + " : " + itemPrice3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
