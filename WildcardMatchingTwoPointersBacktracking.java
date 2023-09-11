import java.util.Scanner;

public class WildcardMatchingTwoPointersBacktracking {

        // Two pointers Backtracking - Time O(S*P) and Space O(1)

        public boolean isMatch(String s, String p) {

            //
            if(s.equals(p) || p.equals("*")) {
                return true;
            }

            // pattern and string lengths
            int pl = p.length();
            int sl = s.length();

            // two main pointers at zeroth indices
            int pp = 0;       int sp = 0;
            // start pointers negative initially
            int pStar = -1;    int sStar = -1;

            // cover string s fully
            while(sp < sl) {

                // move both pointers ahead if characters match or ? in p
                if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                    //
                    sp++;
                    pp++;
                }

                // zeroth case of *
                else if(pp < pl && p.charAt(pp) == '*') {

                    // place star pointers
                    pStar = pp;
                    sStar = sp;
                    // move p pointer ahead
                    pp++;
                }

                // if no star found and no match
                else if(pStar == -1) {
                    // output
                    return false;
                }

                // backtracking step
                else {
                    // place main pointers one character ahead of star pointers
                    sp = sStar + 1;
                    pp = pStar + 1;

                    // move star pointer of string to its main pointer
                    sStar = sp;
                }
            }

            // cover pattern is still incomplete, after covering string
            while(pp < pl) {

                // if not * in p
                if(p.charAt(pp) != '*') {
                    // mismatch
                    return false;
                }

                // move main pointer of p ahead
                pp++;
            }

            // output
            return true;
        }

        public static void main(String[] args) {

            WildcardMatchingTwoPointersBacktracking obj = new WildcardMatchingTwoPointersBacktracking();

            Scanner scanner = new Scanner(System.in);

            System.out.println("string s");
            String s = scanner.nextLine();

            System.out.println("pattern p");
            String p = scanner.nextLine();

            boolean answer = obj.isMatch(s,p);
            System.out.println("Does wildcard pattern match? " + answer);
        }

}

/*
Time Complexity = O(S*P) - worst case
Best Case = O(min(S,P))
Average case = O(SlogP)

Space Complexity = O(1)
*/