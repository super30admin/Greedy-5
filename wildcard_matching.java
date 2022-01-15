// Time complexity :O(m+n)
// Space complexity : O(1)

class Solution {
    public boolean isMatch(String s, String p) {
        
        // Two indices used to iterate through pp and sp
        int pp = 0;
        int sp = 0;
        
        // length of the two strings
        int pl = p.length();
        int sl = s.length();
        
        System.out.println(pl);
        
        // Use them to store to come back
        int sStar = -1;
        int pStar = -1;
        
        while ( sp < sl ) {
            
            // If character matches
            if ( pp < pl && ( s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?' ) ){
                
                // Increment the pointer of the two strings
                sp++;
                pp++;
                
            }
            
            // If * is present in 2nd string
            else if ( pp < pl && p.charAt(pp) == '*'){
                
                // store it to use the next iteration if the result is proved wrong
                sStar = sp;
                pStar = pp;
                
                // Move to the next pointer (consider * as empty)
                pp++;
            
            }
        
            else if (pStar == -1){
                
                return false;   
            }
            
            else{
                
                // if character does not match
                
                // Go back to the sp stored , pp stored.
                sp = sStar + 1;
                pp = pStar + 1;
                
                // move it to the next sStar as the * will take one character
                sStar++;
                
            }
            
            
        }
        
        // extra characters in second string
        while ( pp < pl ){
            
            // If character is found
            if ( p.charAt(pp) != '*' ) {
                
                return false;
            }
            
            pp++;
        }
        
        return true;
    }
}