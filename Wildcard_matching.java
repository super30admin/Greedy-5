class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*')) return true;
        
        int sl = s.length();
        int pl = p.length();
        
        int sp = 0;
        int pp = 0;
        
        int pStar = -1;
        int sStar = -1;
        
        while(sp < sl){
            
            //case 1
            if(pp < pl && (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '?')){
                sp++; pp++;
            
            } else if(pp < pl && p.charAt(pp) =='*'){ // zero case
                sStar = sp;
                pStar = pp;
                pp++;
            }else if(pStar == -1) return false;
            
            else{ // one case
                sStar++;
                sp = sStar;
                pp = pStar+1;
                
            }
        }
        while(pp < pl){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        
        return true;
    }
}


//TC: O(s lop p)
//SC: O(1)
