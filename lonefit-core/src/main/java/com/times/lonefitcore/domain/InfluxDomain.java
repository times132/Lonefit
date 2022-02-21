package com.times.lonefitcore.domain;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "lonefit")
public class InfluxDomain {
    @Column(tag = true)
    String name;
    @Column
    Double price;
    @Column(timestamp = true)
    Instant time;
}
