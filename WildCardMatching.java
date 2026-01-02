// Time Complexity : Best case = O(min(m,n)) . Worst case = O(m*n) . Average case = O(m log n) where m and n are lengths of s and p.
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Approach : This problem can be solved with DP tabulation, but time complexity will be m*n and space will be O(n) if 1D dp array is used.
// We can do it better by using the below method, we maintain two pointers on two string s and p along with sStar and pStar pointers to point to * character.
// We start by matching characters in both strings, if matched we increment both pointers and continue. IF * is encounter we update the sStar position to the corresponding
// sp but move pp assuming * is for empty sequence. Now when characters doesn't match, we move back the pp to recently found pStar+1 and sp also to sStar+1 and update sStar
// to sp. There is edge case, where sp is reached out of bounds but pp hasn't. So we check if p has only * in rest of the string and return the result.

class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0; //pointer on String s
        int pp = 0; //pointer on String p
        int sStar = -1; //pointer for * on s
        int pStar = -1; //pointer for * on p
        int sl = s.length();
        int pl = p.length();
        while(sp < sl){ //while sp didnt cross the bound of s length
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){ //characters matched
                sp++;
                pp++;
            }
            else if(pp < pl && p.charAt(pp) == '*'){ //* case
                sStar = sp;
                pStar = pp;
                pp++; //assuming 0 case
            }
            else if(pStar == -1){ //no *
                return false;
            } else{ //character doesn't match or other cases
                pp = pStar + 1;
                sp = sStar + 1;
                sStar = sp;
            }
        }
        while(pp < pl){ //pp still didn't reach end even after sp is out of bounds
            if(p.charAt(pp) != '*'){ //when a alphabetic characters is encountered
                return false;
            }
            else{
                pp++;
            }
        }
        return true;
    }
}