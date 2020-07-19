package com.spring.micrometer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Media {

    public int solve(ArrayList<Integer> A) {

        List<Integer> inputList = A.stream().sorted((o1, o2) -> o2.compareTo(o1)).collect(Collectors.toList());

        return 0;
    }
}
