//TimeComplexity:O(s.length()*p.length())
//SpaceComplexity : O(1)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) {
                return true;
            }
        int sp=0;
        int pp=0;
        int sStar = -1;
        int pStar = -1;
        while(sp < s.length()) {
            if(pp< p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                 sp++;
                 pp++;
            } else if(pp< p.length()  && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
                pp++;
            }else if(pStar == -1) {
                return false;
            }else {
                sStar= sStar+1;
                sp = sStar;
                pp =pStar+1;
            }
        }
        while(pp < p.length()) {
            if(p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
     return true;   
    }
}