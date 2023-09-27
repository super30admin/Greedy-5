// Time Complexity :
// best case -> O(min(m,n))
// average case -> O(m log n)
// worst case -> O(m*n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes

public class WildCardMatchingUsingGreedy {
    public boolean isMatch(String s, String p) {

        int sp = 0; int pp = 0;
        int sStarP = -1; int pStarP = -1;

        while(sp < s.length()){

            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?'))
            {
                sp++; pp++;
            }
            else if(pp < p.length() && p.charAt(pp) == '*')
            {
                sStarP = sp; pStarP = pp;
                pp++;
            }
            else
            {
                if(pStarP == -1)
                {
                    return false;
                }
                else
                {
                    sStarP++; sp = sStarP;
                    pp = pStarP+1;
                }
            }
        }

        while(pp < p.length()){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }

        return true;
    }
}
