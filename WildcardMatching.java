class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sp= 0;
        int pp=0;
        int sstar=-1;
         int pstar=-1;
         while(sp<sl)
         {
            if( pp < pl && (s.charAt(sp) == p.charAt(pp) ||  p.charAt(pp)=='?'))
            {
                sp++;
                pp++;
            }
            else if(pp< pl &&  p.charAt(pp)=='*')
            {
                  sstar=sp;
                   pstar=pp;
                   pp++; //assume no empty or 0 match
            }
            else if(pstar==-1)
            {
                return false;
            }
            else{
                sstar++;
                sp=sstar;
                pp=pstar+1;
            }
         }
        while(pp<pl)
        {
            if( p.charAt(pp) =='*')
            pp++;
            else
            return false;
        }
        return true;
    }
}
/*Time complexity: O(min(S,P)) for the best case and better than O(SlogP) for the average case,
 where S and P are lengths of the input string and the pattern correspondingly.
  in the worst-case scenario,
this algorithm requires O(S⋅P) time.

Space complexity: O(1).
*/
