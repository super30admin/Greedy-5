/*
TC - O(kN + M), assuming we backtrack a certain number of times for the number of stars present due to mismatches, but even then we are always moving forward and can backtrack only n times
SC - O(1), no extra space used
Issues Faced - this one was very challenging. I used a for loop which made state tracking more tricky, was overengineering edge-cases from the get-go, and completely missed the idea of backtracking here because I was so focused on making it a pure greedy, math kind of solution. Intuition building for such problems needs to improve.
Leetcode Run - yes!


*/

class Solution {
    public:
        bool isMatch(string s, string p) {
    
            int n = s.size();
    
            int m = p.size();
    
            int i = 0; //this will move through string s
    
            int j = 0; //this will move through pattern p
    
            int starIdx = -1; //points to the most recent star I have seen (initialized to -1)
    
            int matchIdx = -1; //the index in string s where that * started matching
    
            while (i < n) {
                if (j < m && (p[j] == '?' || p[j] == s[i])) {
                    j += 1;
                    i += 1;
                } else if (j < m && p[j] == '*') {
                    starIdx = j;
                    matchIdx = i; //I start matching the star from here
                    j += 1;
                } else {
                    //if no checkpoint, THEN return false
                    if (matchIdx == -1) {
                        return false;
                    }
                    //rollback to checkpoint
                    i = matchIdx + 1;
                    matchIdx = i;//save it as one step forward that we matched
                    j = starIdx + 1;//skip the star, because this time also we have matched and finished with it, just one more char has been taken but we are done with it
                }
    
            }
    
            while (j < m && p[j] == '*') {
                j += 1;
            }
    
            return j == m;
            
        }
    };
