//TC: O(n)
//SC: O(1)

public class wild_card_matching {
   
class Solution {
    public static boolean isMatch(String s, String p) {
      
      if (s == null || p == null) {
        return false;
      }
      int sIndex = 0;
      int pIndex = 0;
      int starIndex = -1;
      int match = 0;
      while (sIndex < s.length()) {
        // If the current pattern is a match
        if (pIndex < p.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
          sIndex++;
          pIndex++;
        }
        // If the current pattern is a star
        else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
          starIndex = pIndex;
          match = sIndex;
          pIndex++;
        }
        // If the current pattern is not a match and there is no star
        else if (starIndex == -1) {
          return false;
        }
        // If the current pattern is not a match and there is a star
        else {
          pIndex = starIndex + 1;
          sIndex = match + 1;
          match++;
        }
      }
      // Check if the pattern is a match
      while (pIndex < p.length() && p.charAt(pIndex) == '*') {
        pIndex++;
      }
  
      return pIndex == p.length();
    }
  }
}
