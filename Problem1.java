// Time Complexity - O(Max(m,n)) where m is the length of String s and n is the length of String p
// Space Complexity - O(1)
// This Solution worked on LeetCode


class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*"))    return true;
        if(s.isEmpty() || p.isEmpty())  return false;
        int sl = s.length();
        int pl = p.length();
        int sp = 0;
        int pp =0;
        int sStar = -1;
        int pStar = -1;
        while(sp < sl){
            // case 1 which has a match
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) =='?')){
                sp++;
                pp++;
            }
            else if(pp < pl && p.charAt(pp) == '*'){
                sStar = sp;
                pStar = pp;
                pp++;
            }
            else if(pStar == -1){
                return false;
            }
            else{
                sp = sStar + 1;
                pp = pStar +1;
                sStar = sp;
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
