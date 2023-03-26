class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*')) return true;
        int sl = s.length();
        int pl = p.length();
        int sp = 0;
        int pp = 0;
        int ss = -1;
        int ps = -1;
        while(sp<sl){
        if(pp<pl && (s.charAt(sp) == p.charAt(pp) ||p.charAt(pp)==('?'))){
            sp++;
            pp++;
        }
        else if(pp<pl && p.charAt(pp) =='*'){
            ss=sp;
            ps=pp;
            pp++;
        }
        else if(ps==-1){
            return false;
        }
        else{
            pp=ps+1;
            sp=ss+1;
            ss=sp;
        }}
        while(pp<pl){
            if (p.charAt(pp) != '*') return false;
            pp++;
        }
    return true;
    }
}