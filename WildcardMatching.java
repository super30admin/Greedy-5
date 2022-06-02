//Time Complexity : O(m.n); where m and n are lengths of pattern and input strings.
//Space Complexity : O(1)
public class WildcardMatching { 
	/**Approach: Two Pointers **/
	public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        int m = p.length();
        int n = s.length();
        int sp= 0; int pp =0;
        int sStar = -1; int pStar = -1;
        
        while(sp < n){
            //chars match
            if(pp < m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){ 
                sp++; 
                pp++;                
            }else if (pp < m && p.charAt(pp) == '*'){ 
                sStar = sp;
                pStar = pp;
                //zero case
                pp++;
            }else if(pStar == -1){ //if char do not match and no '*' appeared so far
                return false;
            }else{ 
                sp = sStar + 1;
                pp = pStar + 1;
                //one case
                sStar = sp;
            }
        }
        
        //if s finished but p still has chars, check if all remaining chars are '*'
        while(pp < m){
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        
        return true;
    }
    
	// Driver code to test above
	public static void main (String[] args) {	
		WildcardMatching ob  = new WildcardMatching();	
		String s = "adceb";
		String p = "*a*b";
		System.out.println("Does pattern match the string? "+ ob.isMatch(s,p));         
	}
}
