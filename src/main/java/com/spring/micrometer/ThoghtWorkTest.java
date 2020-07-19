package com.spring.micrometer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ThoghtWorkTest {

    public  static  ObjectMapper objectMapper =new ObjectMapper();


    public static void main(String[] args) {
        String url = "https://http-hunt.thoughtworks-labs.net/challenge/";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("userId", "6yJSBdiNU"); // optional - in case you auth in headers

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String text ="";
        try {
//            ResponseEntity<Map> respEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
//            Map<String,Map<String,String>> sampleInput = (Map<String, Map<String,String>>) respEntity.getBody().get("sampleInput");
//            System.out.println(respEntity.toString());
//            System.out.println(sampleInput);
//            Map<String,String> mapOfString = (Map<String,String>)sampleInput.get("input");
//            text = mapOfString.get("text");
//            System.out.println(text);
//            System.out.println(text.split(" ").length);
//            String[] words = text.split("\\s+");
//            System.out.println(words.length);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        long first = System.currentTimeMillis();
        try{
//            //Channllege One
            System.out.println();

            System.out.println(">>>>>>First challenge..!!!  "+ first);
            ResponseEntity<Map> respEntity = restTemplate.exchange("https://http-hunt.thoughtworks-labs.net/challenge/input", HttpMethod.GET, entity, Map.class);
            text = (String) respEntity.getBody().get("text");
            text =text.toLowerCase();
            System.out.println(text);

            Map<String,Integer> output =new HashMap<>();
            output.put("a",StringUtils.countOccurrencesOf(text,"a"));
            output.put("e",StringUtils.countOccurrencesOf(text,"e"));
            output.put("i",StringUtils.countOccurrencesOf(text,"i"));
            output.put("o",StringUtils.countOccurrencesOf(text,"o"));
            output.put("u",StringUtils.countOccurrencesOf(text,"u"));
            String jsonString =objectMapper.writeValueAsString(output);
            System.out.println(jsonString);
            HttpEntity<String> postEntity = new HttpEntity<String>(jsonString, headers);
            ResponseEntity<Map> postResponseEntity = restTemplate.exchange("https://http-hunt.thoughtworks-labs.net/challenge/output", HttpMethod.POST, postEntity, Map.class);
            System.out.println(">>>>");
            System.out.println(postResponseEntity.getBody());
            System.out.println(">>>>>>First challenge..!!!  "+ System.currentTimeMillis());



        }catch (Exception exception){
            exception.printStackTrace();
        }
        long last =System.currentTimeMillis();
        System.out.println(">>>>>>First challenge..!!!  " + "  DIFF  "+ (last-first));

    }

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }
}
