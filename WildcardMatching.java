// Time Complexity : O(slogp)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class WildcardMatching {
    class Solution {
        public boolean isMatch(String s, String p) {

            int sLen = s.length(), pLen = p.length();
            if(s.equals(p) || p.equals("*"))
                return true;
            /*Dp similar to expression matching */

        /*Optimal: 2 ptr
        if both char match ptr++
        if *: store s* and p* , pp++
        if p* = -1, return false //chars dont match
        skip the s* // saying s* char is matched with *
        */

            int sPtr = 0, pPtr = 0, sStar = -1, pStar = -1;

            while(sPtr < sLen){
                if(pPtr < pLen && (s.charAt(sPtr) == p.charAt(pPtr) || p.charAt(pPtr) == '?')){
                    sPtr++;
                    pPtr++;
                }
                else if(pPtr < pLen && p.charAt(pPtr) == '*'){
                    sStar = sPtr;
                    pStar = pPtr;
                    pPtr++;
                }
                else if(pStar == -1){
                    return false;
                }
                else{
                    sPtr = sStar + 1;
                    sStar = sPtr;
                    pPtr = pStar + 1;
                }
            }

            while(pPtr < pLen){
                if(p.charAt(pPtr) != '*'){
                    return false;
                }
                pPtr++;
            }

            return true;
        }
    }
}
