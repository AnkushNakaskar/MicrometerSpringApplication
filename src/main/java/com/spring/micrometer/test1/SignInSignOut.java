package com.spring.micrometer.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SignInSignOut {

    static class User {
        public Long userId;
        public Long timeStamp;
        public Action action;

        public User(String userId, String timeStamp, String action) {
            this.userId = Long.parseLong(userId);
            this.timeStamp = Long.parseLong(timeStamp);
            if ("sign-in".equalsIgnoreCase(action)) {
                this.action = Action.SignIn;
            } else {
                this.action = Action.SignOut;
            }
        }
    }

    static enum Action {
        SignIn, SignOut;
    }


    public static List<String> processLogs(List<String> logs, int maxSpan) {
        // Write your code here

        if (logs == null || logs.isEmpty()) {
            return null;
        }
        //99 1 sign-in
        List<User> userList = logs.stream().map(s -> {
            String[] userString = s.split(" ");
            User user = new User(userString[0], userString[1], userString[2]);
            return user;
        }).collect(Collectors.toList());

        Map<Long, List<User>> listMap = userList.stream().collect(Collectors.groupingBy(user -> user.userId));
        Map<Long, Long> mapOfDifference = new HashMap<>();
        for (Long key : listMap.keySet()) {
            List<User> users = listMap.get(key);
            Optional<User> signInUser = users.stream().filter(user -> {
                return Action.SignIn.equals(user.action);
            }).findFirst();
            Optional<User> signOutUser = users.stream().filter(user -> Action.SignOut.equals(user.action)).sorted((f1, f2) -> Long.compare(f2.timeStamp, f1.timeStamp)).findFirst();

            if (signOutUser.isPresent() && signInUser.isPresent()) {
                mapOfDifference.put(signInUser.get().userId, (signOutUser.get().timeStamp - signInUser.get().timeStamp));
            }


        }

        List<Long> userIds = new LinkedList<>();
        mapOfDifference.forEach((k, v) -> {
            if (v <= maxSpan) {
                userIds.add(k);
            }
        });
        return userIds.stream().sorted((o1, o2) -> o1.compareTo(o2)).map(o->o.toString()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("60 12 sign-in", "80 20 sign-out", "10 20 sign-in", "60 20 sign-out");




        List<String> processlist = processLogs(list, 100);
        System.out.println(processlist);
    }
}
