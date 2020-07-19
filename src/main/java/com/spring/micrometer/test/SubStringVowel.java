package com.spring.micrometer.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SubStringVowel {

    public static Map<Character, Integer> mapOfVowel = new HashMap<>();
    public static int count =0;
    public  static HashSet<String> set =new HashSet<>();

    static {
        mapOfVowel.put('a', 0);
        mapOfVowel.put('e', 0);
        mapOfVowel.put('i', 0);
        mapOfVowel.put('o', 0);
        mapOfVowel.put('u', 0);
    }

    public static void vowelsubstring(String input,int start,int end) {
        // Write your code here
        if (input == null) {
            return ;
        }
        if(Math.abs(start-end)<5){
            return;
        }
        if(end>input.length() || start<0){
            return ;
        }

        String subString =input.substring(start,end);
        if(checkStringNotContainVowel(subString)){
            if(!set.contains(""+start+""+end) && !set.contains(""+end+""+start)){
                set.add(""+start+""+end);
                set.add(""+end+""+start);
                count++;
            }
        }
        vowelsubstring(input,start,end-1);
        vowelsubstring(input,start+1,end);
        return;
    }


    private static boolean checkStringNotContainVowel(String subString) {
        boolean isPresent = true;
        if (subString == null || subString.length() == 0) {
            return false;
        }
        boolean isA = false;
        boolean isE = false;
        boolean isI = false;
        boolean isO = false;
        boolean isU = false;




        for (int i = 0; i < subString.length(); i++) {
            Character curr = subString.charAt(i);
            if (!mapOfVowel.keySet().contains(curr)) {
                isPresent = false;
                break;
            }
            if ("a".equalsIgnoreCase("" + curr)) {
                isA = true;
            }
            if ("e".equalsIgnoreCase("" + curr)) {
                isE = true;
            }

            if ("i".equalsIgnoreCase("" + curr)) {
                isI = true;
            }

            if ("o".equalsIgnoreCase("" + curr)) {
                isO = true;
            }

            if ("u".equalsIgnoreCase("" + curr)) {
                isU = true;
            }
        }

        return isPresent && isA && isE && isI && isO && isU;
    }

    public static void main(String[] args) {

//        String input ="aeioaexaaeuiou";
        String input = "axyzaeiou";

        String input1="aaeiouxa";

        vowelsubstring(input,0,input.length());
//        System.out.println(val);
        System.out.println(count);
    }
}
