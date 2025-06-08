// Problem: Wilcard Matching
// Time complexity: O(n + m), where n is the length of string s and m is the length of string p.
// Space complexity: O(1), as we are using only a constant amount of extra space.
// Did this code run successfully on LeetCode: Yes

// Approach: 
// We will use two pointers to traverse both strings.
// The first pointer will traverse the string s and the second pointer will traverse the pattern p.
// We will also keep track of the last position of '*' in p and the position in s where we matched the last '*'.
// If we encounter a character in p that matches the current character in s or is a '?', we move both pointers forward.
// If we encounter a '*', we record its position and move the pointer in p forward.
// If we encounter a mismatch, we check if we have previously encountered a '*'. If so, we backtrack to the last '*' and try to match the next character in s.
// If we reach the end of s, we check if the remaining characters in p are all '*'.
// If they are, we return true; otherwise, we return false.

#include <string>
using namespace std;
class Solution {
public:
    bool isMatch(string s, string p) {
        int sl = s.size();
        int pl = p.size();
        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;
        
        while(sp < sl) {
            if(pp < pl && (s[sp] == p[pp] || p[pp] == '?')) {
                sp++;
                pp++;
            }else if(pp < pl && p[pp] == '*') {
                sStar = sp;
                pStar = pp;
                pp++;
            }else if(pStar == -1) {
                return false;
            }else{
                pp = pStar + 1;
                sp = sStar + 1;
                sStar = sp;
            }
        }
        while(pp < pl) {
            if(p[pp] != '*'){
                return false;
            }
            pp++;
        }
        return true;
    }
};