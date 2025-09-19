//Time Complexity: best case – O(min(m, n)) (early mismatch or quick match without '*')
// Space Complexity: O(1) – constant extra space for a few pointers and star anchors.

// Use two pointers (sp for s, pp for p) and remember the last seen '' positions (sStar, pStar); advance both on match or '?'.
// When encountering '', record anchors (pStar=pp, sStar=sp) and move pp forward to let '' match zero+ chars.
// On mismatch: if a '' was seen, backtrack to pStar+1 and extend the '' match by incrementing sStar (sp=sStar); otherwise fail; finally ensure leftover pattern chars are all ''.

class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;

        while(sp < s.length()){
            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }else if(pp < p.length() && p.charAt(pp) == '*'){
                pStar = pp;
                sStar = sp;
                pp++;
            }else{
                if(pStar == -1) return false;
                sStar++;
                sp = sStar;
                pp = pStar + 1;
            }
        }

        while(pp < p.length()){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }

        return true;
    }
}
