# Greedy-5

## Problem1: Wildcard Matching (https://leetcode.com/problems/wildcard-matching/)

//Time - Complexity = O( M \* N )
//Space - Complexity = O(1)

class Solution {
public boolean isMatch(String s, String p) {
if(s.equals(p) || p.equals("\*")) {
return true;
}
int slength = s.length();
int plength = p.length();

// boolean[][] dp = new boolean[plength + 1][slength + 1];
// dp[0][0] = true;

// for(int i = 1; i < dp.length;i++) {
// for(int j = 0;j < dp[0].length; j++) {
// if(p.charAt(i-1) == '\*') {
// dp[i][j] = dp[i-1][j];
// if(j > 0) {
// dp[i][j] = dp[i][j] || dp[i][j-1];
// }
// } else if(j>0 && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?')){
// dp[i][j] = dp[i-1][j-1];
// }
// }
// }
// return dp[plength][slength];

        int smem = -1;
        int pmem = -1;

        int sp = 0;
        int pp = 0;

        while(sp < slength) {
            if(pp < plength && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                pp++;
                sp++;
            } else if (pp < plength && p.charAt(pp) == '*') {
                smem = sp;
                pmem = pp;
                pp++;
            } else if(pmem == -1 ) {
                return false;
            } else {
                smem++;
                sp = smem;
                pp = pmem +1;
            }
        }

        while(pp < plength) {
            if(p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }

}

## Problem2: Bikes in a Campus (https://leetcode.com/problems/campus-bikes/)

//Time - Complexity = O(M*N)
//Space - Complexity = O(M*N)

class Solution {
public int[] assignBikes(int[][] workers, int[][] bikes) {
if(workers == null || workers.length == 0 || bikes == null || bikes.length == 0) {
return new int[0];
}

        Map<Integer, List<int[]>> map = new HashMap<>();
        int wlen = workers.length;
        int blen = bikes.length;
        int[] result = new int[wlen];
        int max = 2001;
        int min = 0;

        for(int i = 0 ; i < wlen; i++) {
            for(int j = 0 ; j < blen; j++) {
                int dist = manhattanDistance(workers[i],bikes[j]);

                max = Math.max(dist,max);
                min = Math.min(dist,min);

                if(!map.containsKey(dist)) {
                    map.put(dist, new ArrayList<>());
                }
                map.get(dist).add(new int[]{i,j});
            }
        }
        int count = 0;
        boolean[] assignedWorkers = new boolean[workers.length];
        boolean[] assignedBikes = new boolean[bikes.length];

        for(int dist = min; dist <= max; dist++) {

            if(map.containsKey(dist)) {
                List<int[]> distList = map.get(dist);
                for(int[] pair : distList) {
                    int worker = pair[0];
                    int bike = pair[1];

                    if(!assignedBikes[bike] && !assignedWorkers[worker]) {
                        count++;
                        result[worker] = bike;
                        assignedBikes[bike] = true;
                        assignedWorkers[worker] = true;
                    }
                    if(count == wlen) {
                        return result;
                    }
                }
            }
        }
        return result;





    }

    private int manhattanDistance(int[] worker,int[] bikes) {
        return Math.abs(worker[0] - bikes[0]) + Math.abs(worker[1] - bikes[1]);
    }

}
