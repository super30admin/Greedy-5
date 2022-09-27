
//Time: O(sl*pl)
//Space: O(sl*pl)

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*')){
            return true;
        }
        
        int sl = s.length();
        int pl = p.length();
        
        boolean [][] dp = new boolean[pl+1][sl+1];
        dp[0][0]= true;
        for (int i = 1; i <= pl; i++){
            for (int j = 0; j <= sl; j++){
                if (p.charAt(i-1) != '*'){
                    if (j>0 && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?')){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    if (j == 0){
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }
                }
            }
        }
        return dp[pl][sl];
    }
}


/////////////

//Time: O(sl*pl)
//Space: O(sl)

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*')){
            return true;
        }
        
        int sl = s.length();
        int pl = p.length();
        
        boolean [] dp = new boolean[sl+1];
        dp[0]= true;
        
        for (int i = 1; i <= pl; i++){
            boolean diag = dp[0];
            for (int j = 0; j <= sl; j++){
                boolean temp = dp[j];
                if (p.charAt(i-1) != '*'){
                    if (j>0 && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?')){
                        dp[j] = diag;
                    }else{
                        dp[j] = false;
                    }
                }else{
                    if (j == 0){
                        dp[j] = dp[j];
                    }else{
                        dp[j] = dp[j] || dp[j-1];
                    }
                }
            diag = temp;
            }
        }
        return dp[sl];
    }
}




///////////

//Time complexity: O(slogp)
//Space complexity: O(1)

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        
        int sp = 0;
        int pp = 0;
        int sstar = -1;
        int pstar = -1;
        
        while (sp < sl){
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }else if (pp < pl && p.charAt(pp) == '*'){
                sstar = sp;
                pstar = pp;
                pp++;
            }else if (pstar  == -1){
                return false;
            }else{
                pp = pstar + 1;
                sp = sstar + 1;
                sstar = sp;
            }
        }
    while (pp < pl){
        if (p.charAt(pp) != '*') return false;
        pp++;
    }
        
    return true;  
    }
}
