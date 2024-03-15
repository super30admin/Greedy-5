/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(workers*bikes + (maxDistance - minDistance))
* 
* Space Complexity: O((maxDistance - minDistance) + bikes)
* 
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> hmap = new HashMap<>();

        int minValue = Integer.MAX_VALUE;

        int maxValue = Integer.MIN_VALUE;

        for (int workerIndex = 0; workerIndex < workers.length; workerIndex++) {
            for (int bikeIndex = 0; bikeIndex < bikes.length; bikeIndex++) {
                int[] worker = workers[workerIndex];

                int[] bike = bikes[bikeIndex];

                int distance = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);

                minValue = Math.min(minValue, distance);

                maxValue = Math.max(maxValue, distance);

                if (!hmap.containsKey(distance)) {
                    hmap.put(distance, new ArrayList<>());
                }

                hmap.get(distance).add(new int[] { workerIndex, bikeIndex });
            }
        }

        int[] workerAssignments = new int[workers.length];

        Arrays.fill(workerAssignments, -1);

        boolean[] bikeAssignments = new boolean[bikes.length];

        for (int val = minValue; val <= maxValue; val++) {
            if (hmap.containsKey(val)) {
                for (int[] pair : hmap.get(val)) {
                    int workerIndex = pair[0];

                    int bikeIndex = pair[1];

                    if (workerAssignments[workerIndex] == -1 && !bikeAssignments[bikeIndex]) {
                        workerAssignments[workerIndex] = bikeIndex;
                        bikeAssignments[bikeIndex] = true;
                    }
                }
            }
        }

        return workerAssignments;
    }
}