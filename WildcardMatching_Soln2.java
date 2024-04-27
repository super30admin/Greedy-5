package week14.day2;
//TC - Worst - O(m*n), best - O(min(m,n)), average - O(m log n)
//SC - O(1)
class Solution2 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return true;
        //if (s.equals(p) || p.equals("*")) --not mandatory
           // return true;
        int sl = s.length();
        int pl = p.length();
        int sp = 0;
        int pp = 0;
        int sStar = -1;
        int pStar = -1;
        // while(sp<sl && pp<pl){ -- will fail when s=aa p=a
        while (sp < sl) {
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp)=='?')) {
                sp++;
                pp++;
            } else if (pp < pl && p.charAt(pp) == '*') { // 0 match
                sStar = sp;
                pStar = pp;
                pp++;
            } else if (pStar == -1) { // mismatch scenario
                return false;
            } else { // mismatch scenario
                sStar = sStar + 1;
                sp = sStar;
                pp = pStar + 1;
            }
        }
        // could be possible that char in p is still remanining
        while (pp < pl) {
            if (p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}
public class WildcardMatching_Soln2 {

	public static void main(String[] args) {
		Solution2 s= new Solution2();
		System.out.println(s.isMatch("aa", "*"));

	}

}
/**
Whiteboard below cases
1. s= "acdcb" p ="a*c?b"
2. s= "adceb" p ="*a*b"
3. s="aa" p="a"
4. s="aa" p="*"
 */
