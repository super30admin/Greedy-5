//Time Complexity-O(m+n)--->'m' is length of string and 'n' is length of pattern
//Space Complexity-O(1)
//Did the code execute on Leetcode? Yes

class Solution {
public:
    bool isMatch(string s, string p) {
        if(s==p || p=="*")
        {
            return true;   
        }
        if(s.length()==0 || p.length()==0)
        {
            return false;
        }
        int s1=0;
        int p1=0;
        int s11=-1;
        int p11=-1;
        while(s1<s.length())
        {
            if(p1<p.length() && s[s1]==p[p1] || p[p1]=='?')
            {
                s1++;
                p1++;
            }
            else if(p1<p.length() && p[p1]=='*')
            {
                s11=s1;
                p11=p1;
                p1++;
            }
            else if(p11==-1)
            {
                return false;
            }
            else
            {
                s1=s11+1;
                p1=p11+1;
                s11=s1;
            }
        }
        while(p1<p.length())
        {
            if(p[p1]!='*')
            {
                return false;
            }
            p1++;
        }
        return true;
    }
};