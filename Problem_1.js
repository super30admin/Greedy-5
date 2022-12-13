// Problem1: Wildcard Matching (https://leetcode.com/problems/wildcard-matching/)

// dp
// Time Complexity : O(mn)
// Space Complexity : O(mn)

// 2 pointers
// Time Complexity : O(mn)
// Space Complexity : O(1)

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {

    // 4 pointers
    let plen = p.length;
    let slen = s.length;
    let sp = 0;
    let pp = 0;
    let sstar = -1;
    let pstar = -1;
    while (sp < slen) {
        if (pp < plen && (s[sp] === p[pp] || p[pp] === "?")) {
            sp++;
            pp++;
        } else if (pp < plen && p[pp] === "*") {
            pstar = pp;
            sstar = sp;
            pp++;
        } else if (pstar === -1) {
            return false;
        } else {
            sp = sstar + 1;
            pp = pstar;
            sstar = sp;
        }
    }
    while (pp < plen) {
        if (p[pp] !== "*")
            return false;
        pp++;
    }
    return true;

    // let plen = p.length; //m rows
    // let slen = s.length; //n cols
    // let dp = new Array(plen+1);
    // for(let i=0; i<dp.length; i++){
    //     dp[i] = new Array(slen+1);
    //     dp[i].fill(false);
    // }
    // dp[0][0] = true;
    // console.log(dp)
    // for(let i=1; i<plen+1; i++){
    //     for(let j=0; j<slen+1; j++){
    //         if(p[i-1]!=="*"){
    //             if(j>0){
    //                 if(p[i-1] === s[j-1] || p[i-1]==="?"){
    //                     dp[i][j] = dp[i-1][j-1];
    //                 }
    //             }

    //         } else {
    //             if(j>0){
    //                 dp[i][j] = dp[i-1][j] || dp[i][j-1];
    //             } else {
    //                 dp[i][j] = dp[i-1][j];
    //             }
    //         }
    //     }
    // }
    // return dp[plen][slen];
};

