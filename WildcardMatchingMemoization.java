import java.util.Scanner;

public class WildcardMatchingMemoization {

        // Dynamic Programming - Memoization - Time O(m*n) and Space O(m*n)

        public boolean isMatch(String s, String p) {

            // pattern and string lengths
            int pl = p.length();
            int sl = s.length();

            boolean[][] dp = new boolean[pl+1][sl+1];
            dp[0][0] = true;

            // nested loop to build dp array
            for(int i = 1; i <= pl; i++) {
                for(int j = 0; j <= sl; j++) {

                    // * in p
                    if(p.charAt(i-1) == '*') {

                        // first column
                        if(j == 0) {

                            // up
                            dp[i][j] = dp[i-1][j];
                        }

                        else {

                            // up or left
                            dp[i][j] = dp[i-1][j] || dp[i][j-1];
                        }
                    }

                    // matching characters or ? in p
                    else if(j > 0 && (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?')) {
                        // diagonal up left
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
            // output
            return dp[pl][sl];
        }

        public static void main(String[] args) {

            WildcardMatchingMemoization obj = new WildcardMatchingMemoization();

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
Time Complexity = O(m*n)
Space Complexity = O(m*n)
*/

