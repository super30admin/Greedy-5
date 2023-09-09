#include<vector>
#include<iostream>

using namespace std;

//dp solution O(M*N) space and time

class Solution {
public:
    bool isMatch(string s, string p) {
        int source_len = s.size();
        int pattern_len = p.size();
        vector<vector<bool>> dp(source_len+1,vector<bool>(pattern_len+1,false));
        dp.at(0).at(0) = true;
        for(int i{1};i<=source_len;++i){
            dp.at(i).at(0) = false;
        }
        for(int j{1};j<=pattern_len;++j){
            char p_char = p.at(j-1);
            if(p_char =='*'){
                dp.at(0).at(j) = dp.at(0).at(j-1);
            }
            else{
                dp.at(0).at(j) = false;
            }
        }
        for(int i{1};i<=source_len;++i){
            char s_char = s.at(i-1);
            for(int j{1};j<=pattern_len;++j){
                char p_char = p.at(j-1);
                if(p_char == '*'){
                    dp.at(i).at(j) = dp.at(i).at(j-1) || dp.at(i-1).at(j); 
                }
                else{
                    if(p_char == '?' || p_char == s_char){
                        dp.at(i).at(j) = dp.at(i-1).at(j-1);
                    }
                    else{
                        dp.at(i).at(j) = false;
                    }
                }
            }
        }
        return dp.at(source_len).at(pattern_len);
    }
};

//above can be optimized using one array and diagup element;

//two pointer solution

// in worst case O(m*N)
// in amortized it is O(mnlog(mn))

class Solution {
public:
    bool isMatch(string s, string p) {

        int source_len = s.size();
        int pattern_len = p.size();
        int s_ptr{};
        int p_ptr{};
        int p_star{-1};
        int s_star{-1};

        while(s_ptr<source_len){
            if(p_ptr<pattern_len && (p.at(p_ptr) == s.at(s_ptr) || p.at(p_ptr) == '?')){
                ++s_ptr;
                ++p_ptr;
            }
            else if(p_ptr<pattern_len && p.at(p_ptr) == '*'){
                p_star = p_ptr;
                s_star = s_ptr;
                ++p_ptr;//zero case
            }
            //basically char not matching
            else{
                if(p_star == -1) return false; // not encountered a star and mismatch before.
                else {
                    //here condition for
                    s_star++;
                    s_ptr = s_star;
                    p_ptr = p_star+1;
                }

            }
        }
        while(p_ptr<pattern_len){
            if(p.at(p_ptr) != '*') return false;
            ++p_ptr;
        }
        return true;
    }
};
