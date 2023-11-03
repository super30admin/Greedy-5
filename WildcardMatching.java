// Time Complexity: O(s*p) 
// Space Complexity: O(1)

// Approach: Use two pointers to go through the source string and pattern string.


public class WildcardMatching {

    public static void main(String[] args) {
        WildcardMatching wm = new WildcardMatching();
        
        System.out.println(wm.isMatch("aa", "*"));
        System.out.println(wm.isMatch("aa", "a*"));
        System.out.println(wm.isMatch("ab", "?*"));
        System.out.println(wm.isMatch("aab", "c*a*b"));
        System.out.println(wm.isMatch("aab", "a*b"));
        
    }

    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sstar = -1;
        int pstar = -1;
        int sp = 0;
        int pp = 0;
        //Go through all the characters in source string
        while(sp < sl){

            char schar = s.charAt(sp);
            //If we run out of char in p string and there is no star in p string, return false
            if(pp >= pl && pstar == -1){
                return false;
            }
            //If there is a star
            if(pp >= pl && pstar != -1){
                pp = pstar + 1;
                sstar++;
                sp = sstar;
                continue;
            }
            char pchar = p.charAt(pp);
            if(pchar == '*'){
                pstar = pp;
                pp++;
                sstar = sp;
            }
            else if(pchar == schar || pchar == '?'){
                pp++;
                sp++;
            }
            else if(pchar != schar && pstar != -1){
                sstar++;
                sp = sstar;
                pp = pstar + 1;
            } else if(pchar != schar && pstar == -1){
                return false;
            }
        }

        //check the rmeinaing char in pattern string
        while(pp < pl){

            char pchar = p.charAt(pp);
            if(pchar != '*'){
                return false;
            }
            pp++;
        }

        return true;

    }
}