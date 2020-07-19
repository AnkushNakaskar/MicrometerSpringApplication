package com.spring.micrometer;

import java.util.Stack;

public class ProblemX {

    public int countBoringDays(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        Stack<Character> stack = new Stack<>();
        int i = 0;
        stack.add(input.charAt(i));
        int count = 0;
        i++;
        while (i < input.length()) {
            Character currChar = input.charAt(i);
            if(currChar.equals(stack.peek())){
                count++;
            }else{
                stack.push(currChar);
            }
            i =i+1;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(new ProblemX().countBoringDays("ABCD"));
        System.out.println(new ProblemX().countBoringDays("AABBBCDD"));
    }
}
