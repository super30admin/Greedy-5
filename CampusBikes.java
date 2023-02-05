//Time complexity is NM(LogM) M is number of workers, N is number of bikes
//Space complexity is O(MN)
class Solution {
    List<List<Pair<Integer, Integer>>> workerToBikeList = new ArrayList<>();
    int closestBikeIndex[] = new int[1001];
    
    class WorkerBikePair {
        int workerIndex;
        int bikeIndex;
        int distance;   
        
        WorkerBikePair(int workerIndex, int bikeIndex, int distance) {
            this.workerIndex = workerIndex;
            this.bikeIndex = bikeIndex;
            this.distance = distance;
        }
    }
    
    Comparator<WorkerBikePair> WorkerBikePairComparator 
        = new Comparator<WorkerBikePair>() {
        @Override
        public int compare(WorkerBikePair a, WorkerBikePair b) {
            if (a.distance != b.distance) {
                return a.distance - b.distance;
            } else if (a.workerIndex != b.workerIndex) {
                return a.workerIndex - b.workerIndex;
            } else {
                return a.bikeIndex - b.bikeIndex;
            }
        }
    };

    int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
    
    void addClosestBikeToPq(PriorityQueue<WorkerBikePair> pq, int worker) {
        Pair<Integer, Integer> closestBike = workerToBikeList.get(worker)
            .get(closestBikeIndex[worker]);
        closestBikeIndex[worker]++;
        
        WorkerBikePair workerBikePair 
            = new WorkerBikePair(worker, closestBike.getValue(), closestBike.getKey());
        pq.add(workerBikePair);
    }
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<WorkerBikePair> pq = new PriorityQueue<>(WorkerBikePairComparator);
        
        for (int worker = 0; worker < workers.length; worker++) {
            List<Pair<Integer, Integer>> bikeList = new ArrayList<>();
            for (int bike = 0; bike < bikes.length; bike++) {
                int distance = findDistance(workers[worker], bikes[bike]);
                bikeList.add(new Pair(distance, bike));
            }
            Collections.sort(bikeList, Comparator.comparing(Pair::getKey));
            
            workerToBikeList.add(bikeList);
            
            closestBikeIndex[worker] = 0;
            
            addClosestBikeToPq(pq, worker);    
        }
        
        boolean bikeStatus[] = new boolean[bikes.length];
        
        int workerStatus[] = new int[workers.length];
        Arrays.fill(workerStatus, -1);
        
        while (!pq.isEmpty()) {
            WorkerBikePair workerBikePair = pq.remove();
            
            int worker = workerBikePair.workerIndex;
            int bike = workerBikePair.bikeIndex;
            
            if (workerStatus[worker] == -1 && !bikeStatus[bike]) {
                bikeStatus[bike] = true;
                workerStatus[worker] = bike;
                
            } else {
                addClosestBikeToPq(pq, worker);
            }
        }
    
        return workerStatus;
    }
}