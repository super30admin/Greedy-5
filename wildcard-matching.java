//TC: O(mlogn)
//SC:O(1)
//running on leetcode: yes

//TWO POINTER APPROACH
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;
        //get lengths of each string
        int m = p.length();
        int n = s.length();
        int sp=0;
        int pp=0;
        int sstar = -1;
        int pstar = -1;
        while(sp<n) {
            if(pp<m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            else if(pp<m && (p.charAt(pp) == '*')) {
                sstar = sp;
                pstar = pp;
                pp++;
            }
            else if(sstar == -1) {
                return false;
            }
            else {
                sstar++;
                sp=sstar;
                pp=pstar+1;
            }
        }
        while(pp < m) {
            if(p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}
