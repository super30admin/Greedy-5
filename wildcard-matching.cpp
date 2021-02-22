class Solution {
public:
    bool isMatch(string s, string p) {
        int slen = s.size(), plen = p.size();
        int sptr = 0, pptr = 0, sstar = -1, pstar = -1;
        
        while(sptr<slen){
            
            if(pptr<plen && (s[sptr] == p[pptr] || p[pptr] == '?')){
                sptr++;
                pptr++;
            }else if(pptr<plen && p[pptr] == '*'){
                sstar = sptr;
                pstar = pptr;
                pptr++;
            }else {
                if(pstar == -1) return false;
                sstar++;
                sptr = sstar;
                pptr = pstar+1;
            }
            
        }
        while(pptr<plen){
            if(p[pptr]!='*') return false;
            if(p[pptr] == '*') pptr++;
        }
        
        return true;
        
    }
};