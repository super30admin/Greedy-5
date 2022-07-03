//DP approach
class Solution {
    //Time Complexity: 0(m*n) where m is the length of p string and n is the length of s string
    //Space Complexity : 0(m*n)
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }

        int m = p.length();
        int n = s.length();
        boolean [][] dp = new boolean [m + 1][n + 1];   //dp array taking length of m+1 and n+1 dor empty strings
        dp[0][0] = true;    //initializing 1st index as true as empty string can form empty string

        for(int i = 1; i <= m; i++){
            for(int j =0; j <= n; j++){
                if(p.charAt(i-1) != '*'){   //if the character in p string is not a star
                    if(j > 0){  //also  not computing for 1st column
                        if(p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?'){ //if the character match or there is a ? mark
                            dp[i][j] = dp[i-1][j-1];    //then the value comes from diagonal
                        }
                    }
                }
                else{   //if the character is a star
                    if(j > 0){  //and j is greater than 0
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];    //or the value comes between top or left
                    }
                    else{
                        dp[i][j] = dp[i-1][j];  //computing for 1st column
                    }
                }
            }
        }
        return dp[m][n];//returning the last index
    }
}

//Two pointer approach
class Solution {

    //Time Complexity: 0(m+n)
    //Space Complexity : 0(1)

    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        }
        int pp = 0; //pointer on p string
        int sp = 0;//pointer on s string
        int sstar = -1; //pointer on s string and captures the last position of s
        int pstar = -1; //pointer on p string and captures of saves the last location where * is encountered
        int sl = s.length();
        int pl = p.length();
        while(sp < sl){ //when pointer on s reaches its end
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){   //if pointer in p is in bounds and the character in s and p matches
                sp++;   //then move both pointers forward
                pp++;
            }
            else if(pp < pl && p.charAt(pp) == '*'){    //if the character in p is a star
                pstar = pp; //then pstar records the current position of pointer in p string
                sstar = sp; //sstar records the current position of pointer in s string
                pp++;   //and i move my pointer in p forward
            }
            else if(pstar == -1){   //if pstar is still -1, means that there is no * in p string and the strings do not match
                return false;   //so I return false
            }
            else{
                pp = pstar; //else, i reset my pp to pstar
                sstar = sstar + 1;  //move my sstar forward
                sp = sstar; //reset my sp to sstar
            }
        }
        while(pp < pl){ //after the s string is traversed, elements might be left in p string to be processed
            if(p.charAt(pp) != '*'){    //if the character in pstring is not a star, then there will be a mismatch as I have already processed s string completely
                return false;//so I return false
            }
            pp++;   //else, I move my p pointer ahead
        }
        return true;    //finally return true
    }
}