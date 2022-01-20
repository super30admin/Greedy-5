class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        TreeMap<Integer, ArrayList<Pair<Integer, Integer>>> map = new TreeMap<>();
        ArrayList<Pair<Integer, Integer>> temp;
        int distance;
        boolean w[] = new boolean[workers.length];
        boolean b[] = new boolean[bikes.length];
        int result[] = new int[workers.length];
        int count = 0;

        for(int i=0;i<workers.length;i++){
            for(int j=0;j<bikes.length;j++){
                distance = manhattanDistance(workers[i],bikes[j]);
                if(!map.containsKey(distance))
                    map.put(distance, new ArrayList<Pair<Integer, Integer>>());
                map.get(distance).add(new Pair<Integer, Integer>(i, j));
            }
        }

        for(int i:map.keySet()){
            temp = map.get(i);
            for(Pair<Integer, Integer> p:temp){
                if(!w[p.getKey()] && !b[p.getValue()]){
                    result[p.getKey()] = p.getValue();
                    w[p.getKey()] = true;
                    b[p.getValue()] = true;
                    count++;
                    if(count == workers.length)
                        return result;
                }
            }
        }
        return result;
    }

    int manhattanDistance(int worker[], int bike[]){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}