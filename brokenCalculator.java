/*
Problem:
*/

// Approach 1: BFS - TLE
class Solution {
    public int brokenCalc(int startValue, int target) {
        if (startValue == target)
            return 0;
        
        Queue<Integer> queue = new LinkedList<>();
        int minOps = 0;
        
        queue.add(startValue);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int curValue = queue.poll();
                
                if (curValue * 2 == target || curValue - 1 == target) {
                    return minOps + 1;
                }
                
                if (curValue > target) {
                    queue.add(curValue - 1);
                } else {
                    queue.add(curValue * 2);
                    queue.add(curValue - 1);
                }
            }
            ++minOps;
        }
        
        return minOps;
    }
}

// Approach 2 : Greedy
class Solution {
    public int brokenCalc(int startValue, int target) {
        if (startValue == target)
            return 0;
        
        int minOps = 0;
        
        while (target > startValue) {
            if (target % 2 == 0) {
                target = target / 2;
            } else {
                target = target + 1;
            }
            ++minOps;
        }
        
        return minOps + (startValue - target);
    }
}