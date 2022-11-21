//Time Complexity: O(m*n)
//Space Complexity: O(n)
class solution{
public int[] assignBikes(int[][] workers, int[][] bikes) {
    //this array will hold pairs sorted by distance - index in array is distance and list of ints
    //is the list of pairs of worker-bike that have this distance
    ArrayList<int[]>[] dist = new ArrayList[2001];
    int wlen = workers.length;
    int blen = bikes.length;
    //this will hold the resutling array
    int[] res = new int[wlen];
    //this is for us to check if bike has been used
    boolean[] seenBikes = new boolean[blen];
    //fill result array with -1 we can identify worker that hasn't been processed
    Arrays.fill(res, -1);

    //fill array of distances with pairs of bike-worker. Our "for" loops start with bike and then
    //with worker. Because of this the condition of the problem has been satisfied - worker with smaller index 
	//will be always first, then bikes
    for (int w = 0; w < wlen; w++) {
      for (int b = 0; b < blen; b++) {
        int d = Math.abs(workers[w][0] - bikes[b][0]) + Math.abs(workers[w][1] - bikes[b][1]);
        if (dist[d] == null) {
          dist[d] = new ArrayList();
        }
        dist[d].add(new int[] {b, w});
      }
    }

    //this counts how many workers has been assigned so far
    int c = 0;

    for (int d = 0; d <= 2000; d++) {
      if (c == blen)
        break;
      //if we haven't met any pair with this distance
      if (dist[d] == null) continue;
      for (int[] pair : dist[d]) {
        //check if worker hasn't been assigned yet and if bike hasn't been used
        if (res[pair[1]] == -1 && !seenBikes[pair[0]]) {
          res[pair[1]] = pair[0];
          seenBikes[pair[0]] = true;
          //increment count of workers with bikes and check if need to break from loop
          c++;
          if (c == blen)
            break;
        }
      }
    }
    return res;
  }
}