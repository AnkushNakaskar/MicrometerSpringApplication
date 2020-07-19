package com.spring.micrometer;

public class ProblemY {

    public int numberOfFriendsToInvite(int sushiRolls){
        if(sushiRolls<=0){
            return 0;
        }

        return (int)Math.random()*sushiRolls;
    }
}
