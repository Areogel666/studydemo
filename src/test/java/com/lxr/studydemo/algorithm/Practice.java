package com.lxr.studydemo.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Practice {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    @Test
    public void testTwoSum() {
        int[] nums = {2,7,11,15};
        int target = 9;
        Arrays.stream(twoSum(nums, target)).forEach(System.out::println);
    }
}
