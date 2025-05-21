/*
TC : O(m*n)
SC : O(1)
*/
class Solution
{
public:
    bool isMatch(string s, string p)
    {
        if (s == p)
        {
            return true;
        }

        if (p.size() == 0 || p.empty())
        {
            return false;
        }

        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;

        while (sp < s.size())
        {
            // if the both the chars match or have a '?'
            if (pp < p.size() && (s[sp] == p[pp] || p[pp] == '?'))
            {
                sp++;
                pp++;
            }
            // if string p has a '*', take case 0
            else if (pp < p.size() && p[pp] == '*')
            {
                sStar = sp;
                pStar = pp;
                pp++;
            }
            // if no wild card matching available
            else if (pStar == -1)
            {
                return false;
            }
            // if string p has a '*', take case 1
            else
            {
                pp = pStar;
                sp = sStar;
                pp++;
                sp++;
                sStar = sp;
            }
        }

        // if did not reach end of string p
        while (pp < p.size())
        {
            if (p[pp] != '*')
            {
                return false;
            }
            pp++;
        }

        return true;
    }
};