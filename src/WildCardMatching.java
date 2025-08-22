//time complexity- O(lens * lenp)
//space complexity- O(1)
public class WildCardMatching {
    static boolean isMatch(String s, String p) {
        int lens = s.length();
        int lenp = p.length();

        int pp = 0;
        int sp = 0;
        int pstar = -1;
        int sstar = -1;

        while(sp < lens) {
            if(pp < lenp && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                pp++;
                sp++;
            } else if(pp < lenp  && p.charAt(pp) == '*') {
                pstar = pp;
                sstar = sp;
                pp++;
            } else if(pstar == -1) {
                return false;
            } else {
                sstar = sstar + 1;
                sp = sstar;
                pp = pstar + 1;
            }
        }
        while(pp < p.length()){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "badceb";
        String p = "b*a*b";

        System.out.println("Can source \"" + s + "\" and pattern \"" + p + "\" can be same ? => " + isMatch(s, p));
    }
}
