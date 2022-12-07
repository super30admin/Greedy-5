//space - O(1)
// time - O(slogp)
class Solution {
    public boolean isMatch(String s, String p) {
        
        int sp = 0;
        int pp = 0;
        int sstar = -1;
        int pstar = -1;
        int sl =s.length();
        int pl = p.length();
        if(s.equals(p) || p.equals("*")) return true; 
        while(sp<sl){
            if(pp<pl && (p.charAt(pp)=='?'|| p.charAt(pp) == s.charAt(sp))){
                pp++;
                sp++;
            }else if(pp<pl && p.charAt(pp) =='*'){
                //zero case
                sstar = sp;
                pstar = pp;
                pp++;
            } else if(pstar == -1){
                return false;
            }else{
                sp = sstar+1;
                pp = pstar+1;
                sstar = sp;
            }
        }
        while(pp<pl){
            if(p.charAt(pp)!='*') return false;
            pp++;
        }
        return true;


    }
}