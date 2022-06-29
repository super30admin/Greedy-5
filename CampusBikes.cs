using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Greedy
{
    public class CampusBikes
    {
        /*
         * T.C: O(M*N) where m is number of workers and n is number of bikes
         * S.C: O(M*N) where m is number of workers and n is number of bikes
         */
        public int[] AssignBikes(int[][] workers, int[][] bikes)
        {
            Dictionary<int, List<int[]>> map = new Dictionary<int, List<int[]>>();
            int min = int.MaxValue;
            int max = int.MinValue;

            for(int i =0; i < workers.Length; i++)
            {
                for(int j = 0; j < bikes.Length; i++)
                {
                    int[] worker = workers[i];
                    int[] bike = bikes[j];

                    int x = Math.Abs(worker[0] - bike[0]);
                    int y = Math.Abs(worker[1] - bike[1]);

                    int distance = x + y;

                    min = Math.Min(min, distance);
                    max = Math.Max(max, distance);

                    if (!map.ContainsKey(distance))
                    {
                        map.Add(distance, new List<int[]>());
                    }

                    map[distance].Add(new int[] { i, j });
                }
            }

            bool[] allocatedBikes = new bool[bikes.Length];
            int[] result = new int[workers.Length];
            Array.Fill(result, -1);
           

            for(int i = min; i < max; i++ )
            {
                List<int[]> allocation = map[i];
                if (allocation == null) continue;

                for(int j = 0; j < allocation.Count; j++)
                {
                    int worker = allocation[i][0];
                    int bike = allocation[i][1];

                    if(result[worker] == -1 && allocatedBikes[bike] == false)
                    {
                        result[worker] = bike;
                        allocatedBikes[bike] = true;
                    }
                }
            }

            return result;
        }
    }
}
