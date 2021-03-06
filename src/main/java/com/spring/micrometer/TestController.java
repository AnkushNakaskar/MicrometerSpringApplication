package com.spring.micrometer;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Timed
public class TestController {

    @Autowired
    private MeterRegistry meterRegistry;

    private Counter counter;

    private Timer register;

    private DistributionSummary distribution;

    /*
    1.To Use the extra tags,We need to have the common tag configured with Meter registry in prometheus
    2.Try invoking  http://localhost:8080/actuator/metrics/web.server
    3.After test api is invoked, and then invoke actuator api

    For filtering : http://localhost:8080/actuator/metrics/http.server.requests?tag=method:GET&tag=status:404

     */
    @Timed(extraTags = {"region", "asia-east-1"})
    @GetMapping(value = "/test")
    public ResponseEntity<String> getTimed() {
//        process();
        counter.increment();
        processHistograme();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


/*
To enable the @Timed on methods,We need to has TimeAspect bean initialised which in MicrometerApplicationsApplication.java class
 */

    public void processHistograme() {
        final AtomicInteger count = new AtomicInteger(0);
        long startTime = new Date().getTime();
        try {
            int timeToSleep = Math.abs(new Random().nextInt(6000));
            System.out.println("Timer record....!!! ::: " + count.incrementAndGet() + "  sleep :: " + timeToSleep);
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endDate = new Date().getTime();


        register.record(endDate - startTime, TimeUnit.MILLISECONDS);
//        register.record(() -> {
//        });

    }

    @PostConstruct
    public void raiseCounter() {

        register = Timer.builder("my.timer.ankush.histogram")
                .publishPercentiles(0.5, 0.95) // median and 95th percentile
                .publishPercentileHistogram()
                .sla(Duration.ofMillis(5000))
                .minimumExpectedValue(Duration.ofMillis(100))
                .maximumExpectedValue(Duration.ofSeconds(10)).register(meterRegistry);

    }

    @PostConstruct
    public void counter() {
        counter = Counter.builder("counter_micro_meter_app").description("a description of what this counter does").baseUnit("ankush").tag("name", "value").register(meterRegistry);
    }

    @PostConstruct
    public void distributionSummery() {
        distribution = DistributionSummary.builder("my.ratio")
                .scale(100)
                .sla(70, 80, 90)
                .register(meterRegistry);
    }
}
