package com.spring.micrometer.test1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class MiniMumUniQueArraySum {

    public static int getMinimumUniqueSum(List<Integer> arr) {
        // Write your code here


        if (arr == null || arr.isEmpty()) {
            return 0;
        }



        Collections.sort(arr);
        Set<Integer> set =new HashSet<>();
        for(Integer curr : arr){
            while(set.contains(curr)){
                curr=curr+1;
            }
            set.add(curr);
        }

        int countSumOld =0;
        for(Integer val : set){
            countSumOld+=val;
        }

        Map<Integer, List<Integer>> mapOfValues = arr.stream().collect(Collectors.groupingBy(Integer::intValue));
        final List<Integer> finalList =new LinkedList<>();
        mapOfValues.forEach((k,v) ->{
            if(v.size()>1){
                int count =0;
                for(Integer val : v){
                    finalList.add(val+count);
                    count++;
                }
            }else if(v.size()==1){
                finalList.add(v.get(0));
            }
        });

        Map<Integer, List<Integer>> newmapOfValues = finalList.stream().collect(Collectors.groupingBy(Integer::intValue));

        int countSum =0;
        for(Integer val : finalList){
            countSum+=val;
        }
        System.out.println(countSum);
        System.out.println(" >>>"+countSumOld + "  >> 620880");


        return countSumOld;

    }

    public static void main(String[] args) {


        List<Integer> list = Arrays.asList(20,
                        1659,
                        710,
                        710,
                        1730,
                        1808,
                        2043,
                        1613,
                        1841,
                        1328,
                        504,
                        1730,
                        2545,
                        493,
                        879,
                        1441,
                        2043,
                        1613,
                        710,
                        1613,
                        1250);

        List<Integer> listNew = Arrays.asList(400, 820, 2035, 1267, 201, 2557, 503, 2259, 1095, 2009, 2961, 2378, 1566, 561, 2899, 2535, 2749, 2899, 1602, 1622, 151, 2332, 2608, 1257, 2992, 1241, 1866, 2992, 1055, 1672, 1626, 1916, 2211, 2974, 1626, 2845, 12, 875, 777, 2517, 728, 1595, 2225, 1177, 649, 2332, 1396, 2974, 1826, 300, 2212, 721, 2930, 1690, 2420, 1179, 196, 1177, 935, 561, 1468, 784, 1880, 1153, 548, 1798, 1221, 241, 2125, 1248, 2930, 1353, 1828, 2104, 1066, 2401, 1828, 1617, 756, 167, 1552, 1798, 2826, 2230, 1738, 756, 1521, 1880, 240, 1546, 2551, 1602, 819, 1353, 1323, 2353, 2557, 1792, 1724, 2125, 305, 2930, 1414, 721, 2878, 325, 2543, 2961, 2407, 1201, 1156, 316, 2016, 2543, 2554, 843, 971, 2230, 2065, 2015, 825, 109, 2408, 168, 2930, 2125, 2448, 133, 1537, 300, 1617, 1777, 109, 1055, 1177, 104, 2145, 2772, 512, 2386, 2517, 1130, 548, 826, 2781, 2912, 2115, 2125, 114, 1231, 2518, 16, 2211, 17, 586, 2145, 181, 1468, 1626, 526, 663, 593, 2505, 2984, 526, 819, 1631, 2108, 1318, 2930, 916, 1959, 46, 1016, 2420, 1712, 691, 721, 103, 1239, 898, 558, 558, 419, 1948, 2752, 1521, 856, 1534, 575, 2590, 586, 1738, 1231, 2812, 2912, 2554, 2974, 2711, 819, 2878, 2557, 2951, 2065, 1306, 2590, 1413, 598, 2543, 2134, 1413, 561, 731, 16, 2899, 121, 2386, 2361, 1712, 2332, 1446, 1798, 2543, 1566, 1306, 240, 1546, 2789, 756, 2118, 772, 1546, 2873, 564, 935, 2108, 2565, 547, 1916, 1095, 2349, 774, 581, 1550, 681, 1552, 2036, 2532, 2752, 2035, 1826, 1413, 1747, 121, 2115, 1941, 1566, 1985, 667, 1899, 843, 1177, 667, 2245, 2108, 1353, 651, 2145, 420, 1484, 1134, 879, 2212, 1768, 2670, 919, 1667, 564, 2974, 2035, 1602, 114, 2261, 2592, 2351, 1095, 784, 1693, 386, 211, 1385, 1414, 2772, 1221, 2951, 440, 856, 2700, 1534, 302, 2680, 1318, 1275, 2592, 1201, 719, 1179, 1566, 2225, 325, 992, 561, 1770, 2745, 581, 2378, 1353, 1551, 977, 2534, 2128, 1404, 1800, 443, 2608, 782, 196, 2796, 357, 1537, 121, 2956, 170, 1385, 1318, 2772, 1404, 1484, 731, 1534, 2336, 730, 386, 1659, 1066, 2408, 874, 748, 2972, 1721, 2828, 1468, 2519, 1206, 2379, 581, 419, 575, 2041, 667, 168, 2118, 1602, 1693, 2592, 756, 2624, 103, 2878, 2415, 547, 2974, 575, 1016, 2255, 784, 2261, 1937, 2912, 1396, 2065, 1308, 1677, 201, 652, 1231, 2700, 2927, 1747, 2828, 2930, 561, 662, 1677, 196, 1210, 1690, 46, 2361, 2259, 598);
        int sum =getMinimumUniqueSum(listNew);

        System.out.println();
        System.out.println();
        System.out.println(sum);

    }
}
