/*
//DP Solution-
//Time - O(m*n) where m and n are the size of the strings s & p
//Space -O(m*n)
class Solution {
public:
    bool isMatch(string s, string p) {
        int pl = p.length();
        int sl = s.length();
        vector<vector<bool>> dp(pl+1,vector<bool>(sl+1,false));
        dp[0][0] = true;
        for(int i = 1;i<=pl;i++){
            for(int j = 0;j<=sl;j++){
                if(p[i-1] == '*'){
                    //zero case - one row up & one case one col behind
                    if(j==0) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = dp[i-1][j] or dp[i][j-1];
                }
                else if((j > 0 and p[i-1] == s[j-1]) or p[i-1] == '?' ){
                    if(j>0) dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[pl][sl];
    }
};
*/
//Time: O(slogp) Looks like S*P in worse case but mathematically comes to be slogp
//Space: O(1) no extra space
class Solution {
public:
    bool isMatch(string s, string p) {
        int sStar = -1,pStar = -1;
        int sp =0,pp = 0;
        int pl = p.length();
        int sl = s.length();
        if(s == p || p == "*") return true;
        while(sp < sl){
            //Case 1 : Check if the char are equal if yes increment pointers
            if(pp < pl and (p[pp] == s[sp] or p[pp] == '?')){
                sp++;
                pp++;
            }
            //Case 2 : In case star set sStar and pStar
            else if(p[pp] == '*'){
                pStar = pp;
                pp++;
                sStar = sp;
            }
            else if(pStar == -1) return false;
            else{
                sStar++; sp = sStar; //Keep matching to star until the char match to letter after *
                pp = pStar+1;
            }
        }
        while(pp < pl){
            if(p[pp] != '*') return false;
            pp++;
        }
        return true;
    }
};