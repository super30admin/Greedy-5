class Solution {

    boolean[][] dp;

    // This problem is similar to Regular Expression Matching
    public boolean isMatch(String s, String p) {
        
        dp = new boolean[s.length() + 1][p.length() + 1];

        // This is bcoz we are optimizing false calls
        for(boolean[] x : dp) Arrays.fill(x, true);

       return helper(s, p, s.length() - 1, p.length() - 1); 
    }

    private boolean helper(String s, String p, int index1, int index2)
    {
        // Both the strings are exhausted
        if(index1 < 0 && index2 < 0) return true;

        // String p has exhausted so we can't match anything from p to s
        if(index2 < 0) return false;

        // Now this means that string s is exhausted and string p is not. Now the only way these two strings could match is 
        // if string p has all *'s remaining in it. Bcoz * can even match with an empty string
        if(index1 < 0)
        {
            for(int i = 0; i <= index2; i++)
            {
                // We have some other characters in p
                if(p.charAt(i) != '*') return false;
            }

            // This happens when all the remaining characters were * in p
            return true;
        }

        // This is a straight forward string matching problem
        if(s.charAt(index1) == p.charAt(index2) || p.charAt(index2) == '?')
        {
            if(helper(s, p, index1 - 1, index2 - 1)) return true;
        }

        if(dp[index1][index2] == false) return false;

        // The above if case failed bcoz both the characters didn't match. Check if the character is turned out to be *
        if(p.charAt(index2) == '*')
        {
            // We will have 2 choices either to choose it or not choose it
            // Eg : If we have 2 strings abc* and abdef
            // When we are at * I don't choose * it means * is matching to an empty character so remaining strings are abc and abdef
            // When we choose *, we have multiple possibilities that * could match 1 character, 2 characters, 3 characters.......
            // Let's assume * matches 1 character so we have abc* and abde bcoz one * is utilized for f
            // As it is a 0-1 recursion this choose not choose happens always and * gets matched to 1,2,3,... characters

            // The 1st function is to not choose * and 2nd one is to choose *
            return dp[index1][index2] = helper(s, p, index1 - 1, index2) || helper(s, p, index1, index2 - 1);
        }

        // This means none of the cases were above. Eg : If we have string aba and bca, the last characters were matching which are a
        // but now the characters b and c are not matching there is no way we are going forward
        return dp[index1][index2] = false;
    }
}