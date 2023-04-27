//TC: Avg - O(slog p) --worst - O(s*p)
//SC: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        int sl = s.length();
        int pl = p.length();
        int sp = 0; 
        int pp = 0;
        int sStar = -1; 
        int pStar = -1;
        while(sp < sl){
            if(pp < pl && (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }else if(pp < pl && p.charAt(pp) == '*'){
                pStar = pp;
                sStar = sp;
                pp++;
            }else if(pStar == -1){
                return false;
            }else{
                 pp = pStar + 1;
                sStar++;
                sp = sStar;
            }
        }
        while(pp < pl){
            if(p.charAt(pp) != '*')return false;
            pp++;
        }
        return true;
    }
}
