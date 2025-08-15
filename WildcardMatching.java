// Time Complexity : O(m*n) worst case, O(min(m, n)) in best case and O(mlogn) avg case where m = len(s), n=len(p)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : Use Carl Barton's algorithm. Use two poitners at beginning of both strings pp and sp. Id characters match
/// increment both, else if patern's current character is *, we check all possibilities of substrings including 0. Remember
/// where we found the most recent * in pStar and corresponding position of sp in sStar. If char at sp and pp don't match, check 
/// if we have seen a * before. If we did, try one char substring for *, move sStar, sp and pp to next positions. Repeat until we reach end of source string
/// If we reach end of s string and remaining substring after pp is all *s then return true, else return false.
class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0, pp = 0, sStar = -1, pStar = -1;
        int sl = s.length(), pl = p.length();
        while(sp<sl){
            if(pp<pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }
            else if(pp<pl && p.charAt(pp) == '*'){
                pStar = pp;
                sStar = sp;
                pp++;
            }
            else if(pStar == -1){
                return false;
            }
            else{
                pp = pStar + 1;
                sp = sStar + 1;
                sStar++;
            }
        }
        while(pp < pl){
            if(p.charAt(pp) != '*'){
                return false;
            }
            pp++;
        }
        return true;
    }
}