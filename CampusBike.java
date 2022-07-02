//TC : O(MN) where m is number of workers and n is number of bikes
//SC : O(MN) where m is number of workers and n is number of bikes
class Solution {
    HashMap<Integer,List<int[]>> hm;
    public int[] assignBikes(int[][] workers, int[][] bikes) {

        hm = new HashMap();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i=0;i<workers.length;i++)
        {
            int[] worker = workers[i];
            for(int j=0;j<bikes.length;j++){
                int[] bike = bikes[j];
                int dis = getManhattanDistance(worker,bike);
                //System.out.println("dis : "+dis);
                min = Math.min(min,dis);
                max = Math.max(max,dis);
                if(!hm.containsKey(dis))
                    hm.put(dis,new ArrayList());
                //System.out.println("adding i : "+i+" j: "+j);
                hm.get(dis).add(new int[]{i,j});
            }
        }

        int[] wAssign = new int[workers.length];
        Arrays.fill(wAssign,-1);
        int[] bAssign = new int[bikes.length];
        System.out.println("min : "+min);
        System.out.println("max : "+max);
        for(int i=min;i<=max;i++)
        {
            //System.out.println("i : "+i);
            List<int[]> l = hm.get(i);
            //System.out.println("List : "+l);
            if(l!=null){
                for(int j=0;j<l.size();j++)
                {
                    int[] temp = l.get(j);
                    //System.out.println("worker : "+temp[0]);
                    //System.out.println("bike : "+temp[1]);
                    if(wAssign[temp[0]]==-1 && bAssign[temp[1]]==0)
                    {
                        //System.out.println("Setting worker : "+temp[0]);
                        //System.out.println("Setting bike : "+temp[1]);
                        wAssign[temp[0]] = temp[1];
                        bAssign[temp[1]] = 1;
                    }
                }
            }
        }
        return wAssign;
    }

    private int getManhattanDistance(int[] worker,int[] bike){
        return (Math.abs(worker[0]-bike[0]) + Math.abs(worker[1]-bike[1]));
    }
}