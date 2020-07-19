package com.spring.micrometer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class ProfitWinningStreak {

    public int processArray(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return 0;
        }
        if (inputArray.length == 1) {
            return inputArray[0];
        }
        HashMap<Integer, List<List<Integer>>> mapOfValues = new HashMap<>();
        int prev = Integer.MIN_VALUE;
        int count = 0;
        List<Integer> list = new LinkedList<>();
        for (int curr : inputArray) {
            if (curr >= prev) {
                count++;
                list.add(curr);
            } else {
                List<List<Integer>> listList = mapOfValues.get(count);
                if (listList == null || listList.isEmpty()) {
                    listList = new LinkedList<>();
                }
                listList.add(list);
                mapOfValues.put(count, listList);
                list = new LinkedList<>();
                list.add(curr);
                count = 1;
            }
            prev=curr;
        }
        Integer maxKey = mapOfValues.keySet().stream().sorted().max(Integer::compareTo).get();
        List<List<Integer>> listList = mapOfValues.get(maxKey);
        int max =Integer.MIN_VALUE;
        for(List<Integer> currList : listList){
             max =Math.max(max,currList.get(currList.size()-1)-currList.get(0));
        }
        System.out.println(max);
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,
                6,
                36,
                32,
                32,
                121,
                66,
                24,
                26,
                371,
                661,
                6,
                4,
                8,
                -1};

        new ProfitWinningStreak().processArray(arr);
    }
}
