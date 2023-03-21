class Solution {

    //Approach 2
    //Time Complexity: O(m*log(n)), m = length of s, n = length of p
    //Space Complexity: O(1)

    public boolean isMatch(String s, String p) {

        if(s.equals(p) || p.equals("*")) return true;           //check if s.equals(p) or p.equals("*"), then return true

        int sl = s.length();                                    //store s and p length
        int pl = p.length();

        int sStar = -1, pStar = -1;                             //create a sStar and pStar pointer in which we are storing the index positions of s and p when we reaches * at p string, so that we can go back and iterating from that

        int sp = 0, pp = 0;                                     //sp and pp is s pointer and p pointer

        while(sp<sl){                                           //iterate till sp reaches sl

            if(pp<pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){     //check if pp<pl and if charAt pp and sp is equal or charAt pp is ?
                pp++;                       //then increament the pp and sp
                sp++;
            }
            else if(pp<pl && p.charAt(pp) == '*'){          //check if pp<pl and charAt pp is equal '*'
                pStar = pp;                                 //then we store the pp, sp location into pStar and sp
                sStar = sp;
                pp++;                                       //and increament the pp, as we are taking the 0 case, so if 0 case didn't work out, then we just come back to pStar and sStar and take one case and then iterate again
            }
            else if(pStar == -1){                           //check if pStar == -1, means either pp pointer reaches to the end or both sp and pp points different character and we haven't encountered any *, then we have to return false
                return false;
            }
            else{                                           //it means that, sp and pp points different characters or pp reaches to the end and we have encountered * before
                pp = pStar + 1;                             //then update pp to pStar+1
                sp = sStar + 1;                             //sp to sStar + 1
                sStar = sStar + 1;                          //sStar to sStar + 1, means we are associating the * to character at sStar and that's why we are increamenting the sStar, and check if we can match, if not then we come here again and assign current sStar character to the * and increament the sStar, that's how we are doing everything
            }
        }

        while(pp<pl){                                       //iterate till pp<pl
            if(p.charAt(pp) != '*'){                        //check if charAt pp is not *
                return false;                               //if so, means sp reach to the end, and pp isn't and one of the remaining characters is not *, so we have to return false
            }
            pp++;                                           //increament the pp
        }
        return true;                                        //return true
    }



    //Approach 1
    //Time Complexity: O(m*n), m = p.length() and n = s.length()
    //Space Complexity: O(m*n), we can reduce space Complexity to: min(m,n)

    // public boolean isMatch(String s, String p) {

    //     if(s.equals(p) || p.equals("*")) return true;           //check if s.equals(p) or p.equals("*"), then return true

    //     int sl = s.length();                                    //store the s length
    //     int pl = p.length();                                    //store the p length

    //     boolean[][] dp = new boolean[pl+1][sl+1];               //create a boolean dp array to keep track if the string is matched or not
    //     dp[0][0] = true;                                        //dp[0][0] is true because _ is matching with _

    //     for(int i=1; i<=pl; i++){                               //iterate till i reaches to pl
    //         for(int j=0; j<=sl; j++){                           //for each i, iterate till j reaches to sl

    //             if(p.charAt(i-1) == '*'){                       //check if charAt(i-1) is *, here we are taking _ character at 0 index, so we have to take i-1 whenever we are doing charAt
    //                 if(j==0){                                   //check if j==0 case,
    //                     dp[i][j] = dp[i-1][j];                  //zero case
    //                 }
    //                 else{
    //                     dp[i][j] = dp[i-1][j] || dp[i][j-1];       //zero case or one case
    //                 }
    //             }
    //             else{
    //                 if( j>0 && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?')){       //check if j>0 and if charAt p and s is same or charAt p is ?, means current character at both string is equal, then we have to check whats the earlier characters, and assign that value
    //                     dp[i][j] = dp[i-1][j-1];                //assign the earlier value to the current dp
    //                 }
    //             }
    //         }
    //     }
    //     return dp[pl][sl];                                      //return the dp[pl][sl]
    // }
}



public class WildcardMatchingLC44 {
}
