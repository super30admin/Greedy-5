// Time Complexity : O(min(s, p)) where s and p are lengths of string and pattern
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Take two pointers one on string and other on pattern, run while loop till string pointer reaches end
// If char at pattern and string match or string has '?' increment pointers
// In case of star we will match nothing with string char and continue
// We will backtrack if characters do not match and increment the string pointer by one char than prev
// After the string is covered, we will check the remaining characters in the pattern and exhaust it and finally return true. 
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        int sIndex  = 0;
        int pIndex = 0;
        int starIndex = -1;
        int sTmpIndex = -1;
        while(sIndex < sLen){
            if(pIndex < pLen && (p.charAt(pIndex) == '?' || p.charAt(pIndex) == s.charAt(sIndex))){
                pIndex++;
                sIndex++;
            }
            else if(pIndex < pLen && (p.charAt(pIndex) == '*')){
                starIndex = pIndex;
                sTmpIndex = sIndex;
                pIndex++;
            }
            else if(starIndex == -1){
                return false;
            }
            else{
                pIndex = starIndex + 1;
                sIndex = sTmpIndex + 1;
                sTmpIndex = sIndex;
            }      
        }
        for(int i = pIndex; i < pLen; i++){
            if(p.charAt(i) != '*')
                return false;
        }
        return true;
    }
}