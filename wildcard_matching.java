class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true; //if string s equals p or p is having * then return true
        int m = p.length(); //m stores the length of p
        int n = s.length(); //n stores the length of s
        int sp = 0, pp = 0; //2 variables sp, pp pointing to 0th index of string s and p
        int sStar = -1, pStar = -1; //2 variables sStar and pStart initialized to -1 and will be updated when we see a *
        while(sp < n) { //while sp < n
            if(pp < m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) { //if the character at sp and pp are same or char at pp in p string is equal to ?
                sp++; //we increment sp
                pp++; //we increment pp
            }
            else if(pp < m && p.charAt(pp) == '*') { //if char at pp in string p is equal to *,
                sStar = sp; //we update sStar to sp
                pStar = pp; //we update pStar to pp
                pp++; //we increment pp
            }
            else if(sStar == -1) { //if we havent found a start, then sStar will still be as -1 which means there is no matching until now
                return false; //so we return false
            }
            else { //if we reached the end and if the last before element in
                sp = sStar + 1; //we increment sp
                pp = pStar + 1; //we increment pp
                sStar = sp; //we update sStar
            }
        }
        while(pp < m) { //while pp is less than m
            if(p.charAt(pp) != '*') return false; //if char at pp in p String is not equal to *
            pp++; //we increment pp
        }
        return true;//we return true
    }
}
//tc and sc - O(min(s,p)) and O(1)