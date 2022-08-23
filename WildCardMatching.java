//Time O(n)
// Space O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.equals("*")|| s.equals(p)) 
            return true;
        int sStar = -1 , pStar = -1;
        int sl =s.length(); 
        int pl = p.length();
        int sPointer= 0 , pPointer=0;
        while(sPointer<sl)
        {
            if(pPointer<pl && ( s.charAt(sPointer) == p.charAt(pPointer) || p.charAt(pPointer) =='?'))
            {sPointer++; pPointer++;}
            else if(pPointer<pl && p.charAt(pPointer)=='*')
            {
                sStar = sPointer; 
                pStar= pPointer;
                pPointer++;
            }
            else if(pStar==-1)
                return false;
            else
            {
            sPointer = sStar+1;
            sStar= sPointer;
            pPointer =pStar+1;    
                
            }
        }
        
        while(pPointer<pl)
        {
            if(p.charAt(pPointer)!='*')
                return false;
            pPointer++;
        }
        return true;
    }
}