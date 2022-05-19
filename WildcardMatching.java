/*
Time Complexity: O(m*n), worst case (m is the length of string s and n is the length of pattern)
Space Complexity: O(1), constant space
Run on leetcode: yes
Any difficulties: no

Approach:
1. Attempted once Discussed in the class
 */
public class WildcardMatching {
    public static boolean wildcardMatching(String s, String pattern){
        int pIndex = 0;
        int sIndex = 0;
        int startIndex = -1;
        int index = -1;

        while(sIndex<s.length()){
            if(pIndex<pattern.length() && (s.charAt(sIndex)==pattern.charAt(pIndex) || pattern.charAt(pIndex) == '?')){
                pIndex++;
                sIndex++;
            }else if(pIndex<pattern.length() && pattern.charAt(pIndex) == '*'){
                startIndex = pIndex;
                index = sIndex;
                pIndex++;
            }else if(startIndex!= -1){
                pIndex = startIndex+1;
                sIndex = index+1;
                index++;
            }else{
                return false;
            }
        }
        while(pIndex<pattern.length() && pattern.charAt(pIndex) == '*'){
            pIndex++;
        }

        return pIndex == pattern.length();
    }
    public static void main(String[] args){
        System.out.println("Wildcard Matching: "+ wildcardMatching("aa", "a"));
        System.out.println("Wildcard Matching: "+ wildcardMatching("aa", "*"));
        System.out.println("Wildcard Matching: "+ wildcardMatching("cb", "?a"));
    }
}
