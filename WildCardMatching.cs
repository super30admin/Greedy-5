using System;
using System.Collections.Generic;
using System.Text;

namespace DynamicProgramming
{
   public  class WildCardMatching
    {
        /*
         * T.C: O(m*n)
         * S.C: O(m*n)
         */
        public bool IsMatch(string s, string p)
        {
            if (s.Equals(p)) return true;

            int m = p.Length;
            int n = s.Length;

            bool[,] dp = new bool[m + 1, n + 1];

            dp[0, 0] = true;

            for (int i = 1; i < m + 1; i++)
            {
                for (int j = 0; j < n + 1; j++)
                {
                    if (p[i - 1] != '*')
                    {
                        if (j > 0)
                        {
                            if (p[i - 1] == s[j - 1] || p[i - 1] == '?')
                            {
                                dp[i, j] = dp[i - 1, j - 1];
                            }
                        }
                    }
                    else
                    {
                        if (j > 0)
                        {
                            dp[i, j] = dp[i - 1, j] || dp[i, j - 1];
                        }
                        else
                        {
                            dp[i, j] = dp[i - 1, j];
                        }
                    }
                }
            }

            return dp[m, n];
        }
    }
}
