package com.times.lonefitbatch.influx;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.Query;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.influxdb.query.internal.FluxResultMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.fail;

@SpringBootTest
public class InfluxTest {

    private String token = "mt1_PG7XP9l_EJukSR_j8fB0OLW1Scxvgzq5CYXLrs3CPAf3wv0DLnGfht86QbBS-UIOzs4SFKTs7rRiO2Yd4Q==";
    private String bucket = "lonefit";
    private String org = "Lonefit";
    private InfluxDBClient client;

    @Before
    public void InfluxConnectionTest() {
        Connection();
    }

    @Test
    public void save() {
        Point point = Point
                .measurement("lonefit")
                .addTag("name", "명돌")
                .addField("price", 24D)
                .time(Instant.now(), WritePrecision.MS);

        WriteApiBlocking writeApi = client.getWriteApiBlocking();
        writeApi.writePoint(bucket, org, point);
    }

    @Test
    public void read() {
        String query = "from(bucket: \"lonefit\") |> range(start: -1h)";
        List<FluxTable> tables = client.getQueryApi().query(query, org);

        for (FluxTable table : tables) {
            for (FluxRecord record : table.getRecords()) {
                System.out.println(record);
            }
        }
    }

    private void Connection(){
        client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());

        if (client.equals(null)) {
            fail("Influx 연결 실패");
        }
    }
}
