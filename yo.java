//Time complexity is O(M+N)
//Space complexity is O(1)
//This solution is submitted on leetcode

public class BigN164WildCardMatching {
	class Solution {
	    public boolean isMatch(String s, String p) {
	        //egde case
	        if(s.equals(p) || p.equals("*"))
	            return true;
	        if(s.isEmpty() || p.isEmpty())
	            return false;
	        int sp = 0;
	        int pp = 0;
	        int spStar = -1;
	        int ppStar = -1;
	        int sl = s.length();
	        int pl = p.length();
	        while(sp<sl){
	            if(pp<pl && (p.charAt(pp) == s.charAt(sp) || p.charAt(pp) == '?')){
	                sp++;
	                pp++;
	            }
	            else if ( pp<pl && p.charAt(pp) == '*'){
	                spStar = sp;
	                ppStar = pp;
	                pp++;
	            }
	            else if (ppStar == -1)
	                return false;
	            else{
	                pp = ppStar +1;
	                sp = spStar+1;
	                spStar = sp;
	            }
	        }
	        while(pp< pl){
	            if(p.charAt(pp)!='*')
	                return false;
	            pp++;
	        }
	        return true;
	    }
	}
}