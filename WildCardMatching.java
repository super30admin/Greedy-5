//TC : O(|s| . log |p| ) length of s and p
//Sc : O(1) // No extra space used here 

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*'))    return true;
        
        int lenS = s.length();
        int lenP = p.length();
        
        int cs = 0, cp = 0, ss = -1, ps = -1;
        //cs = counter on s and cp = counter on p 
        //ss and ps will be updated whenever we get '*' in pattern string. 
        
        while(cs < lenS){
            if(cp < lenP && (s.charAt(cs) == p.charAt(cp) || p.charAt(cp) == '?') ){
                cs++;
                cp++;
            } else if(cp < lenP && p.charAt(cp) == '*'){
                ss = cs;
                ps = cp;
                
                //Null/Zero case
                cp++;
            } else if(ps == -1) return false;
            else{
                cs = ss + 1;
                cp = ps + 1;
                ss = cs;
            }
        }
        
        
        while(cp < lenP){
            if(p.charAt(cp) != '*') return false;
            cp++;
        }
        
        return true;
    }
}


/*

// TC: O(m * n)
//SC : O(m * n)

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*'))    return true;
        
        int lenS = s.length();
        int lenP = p.length();
        
        boolean[][] dp = new boolean[lenP + 1][lenS + 1];
        
        dp[0][0] = true; //Empty string matches with empty string, which is true;
        
        for(int i = 1; i<= lenP; i++){
            for(int j = 0; j <= lenS; j++){
                if(p.charAt(i-1) != '*'){
                    if(j > 0 && (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?') ){
                        dp[i][j] = dp[i-1][j-1];
                    }   
                }else {
                    // NUll/Zero case
                    dp[i][j] = dp[i-1][j];
                    
                    if(j > 0){
                        dp[i][j] = dp[i][j] || dp[i][j-1];
                    }
                }
            }
        }
        
        
        return dp[lenP][lenS];
    }
}


*/