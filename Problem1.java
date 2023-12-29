// Time Complexity : O(nlogm)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;
        while(sp < sl){
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                pp++;
                sp++;
            }
            else if(pp < pl && p.charAt(pp) == '*'){
                sStar = sp;
                pStar = pp;
                pp++; // 0 case
            }
            else if(pStar == -1)
                return false;
            else{
                sp = sStar+1;
                pp = pStar + 1;
                sStar = sp; // 1 case
            }
        }
        while(pp < pl){
            if(p.charAt(pp) != '*')
                return false;
            pp++;
        }
        return true;
    }
}