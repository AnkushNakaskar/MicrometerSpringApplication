* This project explain about the micro meter in spring boot
    * Most of the time,All the functionality will be used in Anotation wise instead of bean defined in MicroMeterApplication
    * check the TestController
        * To add the region tag,We are overriding the behiour with asia region.
        * common tags are defined in application.properties files with "management.metrics.tags."
        * To enable the method with @Timed with need to have TimesAspect  bean in classpath then only the would work
        * Read the properties from Spring configuration for more details 
            * Properties start with "management.metrics.*"
     * What is 
     Configurations
        * below code check for function execution with the help of time 
         
     `register.record(() -> {
                  try {
                      int timeToSleep = Math.abs(new Random().nextInt(6000));
                      System.out.println("Timer record....!!! ::: "+count.incrementAndGet() +"  sleep :: "+timeToSleep);
                      Thread.sleep(timeToSleep);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              });
             `
    *   Below code specify the creation of Timer with histogram  
     ` register = Timer.builder("my.timer.ankush.histogram")
                       .publishPercentiles(0.5, 0.95) // median and 95th percentile
                       .publishPercentileHistogram()
                       .sla(Duration.ofMillis(5000))
                       .minimumExpectedValue(Duration.ofMillis(100))
                       .maximumExpectedValue(Duration.ofSeconds(10)).register(meterRegistry);
     `       
   * SLA : it is the time for which request are serve in 
      * In the picture ,you can see the 223 requests are server in 5 second
    * We are defining the minimum expected value and maximum expected value as parameter in bucket
            * you can see the buckets starts with 0.1 to 10.0 second
   * publishPercentiles 
            * 0.5 and 0.95 specify the 95% of request server in 5.87 second and 50% of request server in 2.91 second
            
   * you can have alters on request crossing the SLA count should be more than something,
        * which can be calculated as below 
             * Get difference of request server on 10 second and SLA specifies 
                  *  here is 260-223 =37
        * you can record the function just i did in above mention code.
         * you can calculate the latency as below :
            * my_timer_ankush_histogram_seconds_sum/my_timer_ankush_histogram_seconds_count
                * total time taken(sum)/total number of request                       
* Most important ,EVery meter is registered with unique value as 
    * name of meter and tags of meter  and type of meter.
    * hence calling the counter.builder frequestly with common tags and name ,Will not going to create the new counter.
    * It will going to increment the counter(which is already there)                             