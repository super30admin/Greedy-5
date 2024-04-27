package week14.day2;
//TC - O(m*n)
//SC - O(m*n)
class Solution3 {
  public boolean isMatch(String s, String p) {
      if (s == null || p == null)
          return true;
      if (s.equals(p) || p.equals("*"))
          return true;

      int row = s.length();
      int col = p.length();
      boolean[][] dp = new boolean[row + 1][col + 1];
      dp[0][0] = true;

      for (int i = 0; i < dp.length; i++) {
          for (int j = 1; j < dp[0].length; j++) {
              if (i == 0) { // first row
                  if (p.charAt(j - 1) == '*') {
                      dp[i][j] = dp[i][j - 1];
                  } else
                      dp[i][j] = false;
              } else {
                  if (p.charAt(j - 1) == '*') {
                      dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                  } else if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
                      dp[i][j] = dp[i - 1][j - 1];
                  } else {
                      dp[i][j] = false;
                  }
              }
          }
      }
      return dp[row][col];
  }
}
public class WildcardMatching_Soln1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
