class Solution {
    public boolean isMatch(String s, String p) {
        int sp, pp, pstar, sstar;
        sp = pp = 0;
        pstar = sstar = Integer.MAX_VALUE;

        while(true){
            if(sp >= s.length() && pp >= p.length())
                return true;
            if(sp >= s.length())
                if(p.charAt(pp) == '*'){
                    pp++;
                    continue;
                }
                else
                    return false;



            if(pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }
            else if(pp < p.length() && p.charAt(pp) == '*'){
                pstar = pp++;
                sstar = sp;
            }
            else if(sstar != Integer.MAX_VALUE){
                sp = ++sstar;
                pp = pstar+1;
            }
            else
                return false;
        }
    }
}