package com.spring.micrometer;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        String[] toDo =new String[]{"A","C","D","B"};
        Arrays.sort(toDo);

        System.out.println(Arrays.binarySearch(toDo,"B"));

    }

    public static int countWordsUsingSplit(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] words = input.split("\\s+");
        return words.length;
    }
}
