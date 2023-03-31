// Approach 1: DP matrix
// TC: O(sp)
// SC: O(sp)

// DP Boolean matrix
// Column 1 (-) has no 1 case only 0 case
// Start from 1st row
// For the first column if the character is a *, get its value from above
// Else
// If your character is a *, 0 case is from the top, 1 case is from the left, OR condition
// If your character is ? or matching, get the value from diagonal left up
// Else your character is not matching so FALSE
// In the end return dp[pl][sl]

class Solution {
    public boolean isMatch(String s, String p) {
        int sl=s.length();
        int pl=p.length();
        boolean[][] dp = new boolean[pl+1][sl+1];
        
        dp[0][0]=true;
        
        for(int i=1;i<=pl;i++){
            for(int j=0;j<=sl;j++){
                if(j==0){
                    if(p.charAt(i-1)=='*'){
                        dp[i][0]=dp[i-1][0];
                    } 
                } else {
                    if(p.charAt(i-1)=='*'){
                        dp[i][j]=dp[i-1][j] || dp[i][j-1];
                    }
                    else if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?'){
                        dp[i][j]=dp[i-1][j-1];
                    } else
                    {
                        dp[i][j]=false;
                    } 
                    //THIS ALSO WORKS INSTEAD OF ABOVE ELSEIF AND ELSE LOOP
                    // else{ 
                    //     if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?'){
                    //         dp[i][j]=dp[i-1][j-1];
                    //     }
                    // } 
                }
            }
        }
        return dp[pl][sl];
    }
}



// Approach 2: Two-Pointer
// TC: O(sp) average case O(s+p) best case
// SC: O(1)


// We have 2 pointers and additional 2 pointers for marking where * occurs
// 2  additional pointers are STAR pointers, they are only used when * occurs
// star pointers start at -1 (uninitialized) and pp and sp are at index 0 
// Going over String s & string p
// If pp is not out of bounds and if sp and pp values are same OR pp value is '?'
// Thats a match, move sp and pp ahead
// If pp is in bounds and pp is a '*', we take the 0 case of not matching * with sp
// Here sStar is assiged sp value and pStar, pp value, and we ONLY move pp ahead (so that we can
// come back to pp later)
// If pStar never occured i.e. its -1 means * never happened, return false directly
// Else pStar did happen, so we need to use the * to match other characters
// So for this condition, pp=pStar+1, move sStar by 1 place and assign sp=sStar
// if Pattern is longer than string and the pattern still has chars left but they are only *,
// go till the end of pattern else return false

class Solution {
    public boolean isMatch(String s, String p) {
        int sl=s.length();
        int pl=p.length();
        int sStar=-1; int pStar=-1;int sp=0; int pp=0;
        
        while(sp<sl){
            // pp is not out of bounds and ..
            if(pp<pl && (s.charAt(sp)==p.charAt(pp) || p.charAt(pp)=='?')){
                sp++;pp++;
            } else if(pp<pl && p.charAt(pp)=='*'){
                sStar=sp;pStar=pp;
                //0 case - dont match sp
                pp++;
                
            } else if(pStar==-1){
                // star never happened
                return false;
            } else {
                // pStar did occur before
                // go back and go over the characters again
                pp=pStar+1;
                sStar++;
                sp=sStar;
            }
            
        }
        // String s is over and p still remains
        while(pp<pl){
            if(p.charAt(pp)!='*') return false;
            pp++;
        }
        
        return true;
    }
}