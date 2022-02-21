package com.times.lonefitbatch.influx;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;



@SpringBootTest
public class InfluxTest {

    private String token = "0Baxl6p7P1bV8eKRpsQrLTynpKzAtp8fu87-Mf-_JTr104HUyafw6swh5maEsmjFe_8SaiWQK3mYgxgpe-l4-g==";
    private String bucket = "lonefit";
    private String org = "times132@gmail.com";
    private InfluxDBClient client;

    @Before
    public void InfluxConnectionTest() {
        assertDoesNotThrow(Exception.class, () -> {
            Connection();
        });
    }

    @Test
    public void save() {
        Point point = Point
                .measurement("lonefit")
                .addTag("name", "명파")
                .addField("price", 300.5)
                .time(Instant.now(), WritePrecision.MS);

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writePoint(bucket, org, point);
    }

    private void Connection() throws Exception{
        client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());

        if (client.equals(null)) {
            throw new Exception();
        }
    }
}
