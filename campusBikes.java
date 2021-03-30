/*
method1: using treemap to maintain sorted order of distance

TC: O(mn * log m), m and n is length of workers and bikes
SC:O(n)


method2: can make use of number of max bikes given in which is 1000;
TC:O(mn)
SC: O(2000)

*/
class Solution {
    
    //method 1: maintain a map of distance as key and list of worker to bike pairs as value
    
    //TreeMap<Integer, List<int[]>> map;
//    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
//         this.map = new TreeMap<>();
        
//         for(int i = 0; i < workers.length;i++){
//             for(int j = 0;j < bikes.length;j++){
//                 //calc the distance
//                 int distance = getDistance(workers[i], bikes[j]);
//                 if(!map.containsKey(distance)){
//                     map.put(distance, new ArrayList<>());
//                 }
//                 map.get(distance).add(new int[]{i,j}); //adding pair of worker, bike ie i,j
//             }
//         }
        
//         int[]checkWorker = new int[workers.length];
//         Arrays.fill(checkWorker,-1);
        
//         boolean[]checkBikes = new boolean[bikes.length];
//         int count = 0;
        
//         for(Integer distance: map.keySet()){
//             List<int[]> list = map.get(distance);
//             for(int[]pair : list){
//                 int w = pair[0];
//                 int b = pair[1];
//                 //check if worker is assigned bike and bike is avaiable
//                 if(checkWorker[w] == -1 && checkBikes[b] == false){
//                     checkWorker[w] = b;
//                     checkBikes[b] = true;
//                     count++;
//                 }
//                 if(count == workers.length){
//                     return checkWorker;
//                 }
//             }
//         }
//         return checkWorker;
//     }
    
    private int getDistance(int[]worker, int[]bike){
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]);
    }

//method 2:
    public int[] assignBikes(int[][] workers, int[][] bikes){
        
        List<int[]>[] list = new List[2000];
        
        for(int i =0; i < workers.length;i++){
            for(int j = 0;j < bikes.length;j++){
                int distance = getDistance(workers[i],bikes[j]);
                if(list[distance] == null){
                    list[distance] = new ArrayList<>();
                }
                list[distance].add(new int[]{i,j});
            }
        }
        
        int[]result = new int[workers.length];Arrays.fill(result,-1);
        boolean []checkBikes = new boolean[bikes.length];
        int count = 0;
        
        for(int i = 0; i < list.length;i++){
            List<int[]> curr = list[i];
            if(curr != null){
                for(int[] pair : curr){
                    int w = pair[0];
                    int b= pair[1];
                    if(result[w] == -1 && checkBikes[b] == false){
                        result[w] = b;
                        checkBikes[b] = true;
                        count++;
                    }
                    if(count == workers.length){
                        return result;
                    }
                }
            }
        }
        return result;
    }
}