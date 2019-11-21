package com.spring.micrometer;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class MicrometerApplicationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrometerApplicationsApplication.class, args);
    }

    /*
    Meter filter will going to filter out the mtrics you want to observer,there are number of ways to filter out the metrics
    Like below filter will going to accept only metrics which starts with web every other will not be display
     */
//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
//        return registry -> registry.config().commonTags("region", "us-east-1").meterFilter(MeterFilter.acceptNameStartsWith("web")).meterFilter(MeterFilter.deny(id -> {
//            String uri = id.getTag("uri");
//            return uri != null && uri.startsWith("/actuator");
//        })).meterFilter(MeterFilter.deny());
//    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
