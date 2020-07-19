package com.spring.micrometer.test;

public class StringConcatinations {

    public static int findSmallestDivisor(String s, String t) {
        // Write your code here
        if(s==null || t==null || s.isEmpty() || t.isEmpty()){
            return -1;
        }
        boolean isFirstCondition =checkFirstCondition(new String(s),new String(t));
        if(isFirstCondition){
            if(t.length()%2 !=0){
                return -1;
            }else{
                int val =checkSecondCondition(t);
                return  val==-1 ? t.length(): val;
            }

        }
        return -1;

    }

    private static int checkSecondCondition(String t) {
        int length=-1;
        int start=0;
        int end=2;
        boolean isFound=false;
        while(!isFound && end<=t.length()){
            int diff =end-start;
            String first =t.substring(start,end);
            if(end+diff>t.length()){
                break;
            }
            String second =t.substring(end,end+diff);
            if(first.equalsIgnoreCase(second)){
                System.out.println(first);
                length=first.length();
                isFound=true;
                break;
            }else {
                end++;
            }
        }
        return length;
    }

    private static boolean checkFirstCondition(String s, String t) {
        if(!s.contains(t)){
            return  false;
        }else{
            while(s.length()>t.length()){
                t=t+t;
            }
            if(s.length()==t.length() && s.equals(t)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int val =findSmallestDivisor("bcdbcdbcdbcd","bcdbcd");
        System.out.println(val);
        int val1 =findSmallestDivisor("lrbblrbb","lrbb");
        System.out.println(val1);
    }
}
