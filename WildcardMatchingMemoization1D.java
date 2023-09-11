import java.util.Scanner;

public class WildcardMatchingMemoization1D {

        // Dynamic Programming - Memoization 1D - Time O(m*n) and Space O(n)

        public boolean isMatch(String s, String p) {

            // pattern and string lengths
            int pl = p.length();
            int sl = s.length();

            boolean[] dp = new boolean[sl+1];   // O(n) space
            dp[0] = true;

            // nested loop to build dp array
            for(int i = 1; i <= pl; i++) {      // O(m*n)

                //
                boolean diagUpLeft = false;

                for(int j = 0; j <= sl; j++) {

                    // store current dp[j] in temp before it is updated in this iteration on columns
                    boolean temp = dp[j];

                    // * in p
                    if(p.charAt(i-1) == '*') {

                        // first column
                        if(j == 0) {

                            // up
                            dp[j] = dp[j];
                        }

                        else {

                            // up or left
                            dp[j] = dp[j] || dp[j-1];
                        }
                    }

                    // matching characters or ? in p
                    else if(j > 0 && (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?')) {
                        // diagonal up left
                        dp[j] = diagUpLeft;
                    }

                    else {

                        // make it false, default false in 2D dp array case
                        dp[j] = false;
                    }

                    // temp will be diagonal up left next row and next column dp element
                    diagUpLeft = temp;
                }
            }
            // output
            return dp[sl];
        }

        public static void main(String[] args) {

            WildcardMatchingMemoization1D obj = new WildcardMatchingMemoization1D();

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
Space Complexity = O(n)

n = string length
m = pattern length
*/