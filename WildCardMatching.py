'''
Solution:
1.  Perform DP as there are repeated subproblems.
2.  Filling DP cells is almost similar to regular expresion DP problem.
3.  When string is empty and until we find anything other than * => fill True
    When pattern is * => check for one char before in string OR check for one char before in pattern
    When corresponding chars are same or pattern has ? => fill with diagonal prev bool value.

Time Complexity:    O(m x n) for both approaches
Space Complexity:   O(m x n) for approach - 1 and O(m) for approach - 2

--- Passed all testcases successfully on leetcode for both the solutions.
'''

class WildCardMatchI:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(p)
        n = len(s)

        #   initializations
        dpMatches = [[False for i in range(m + 1)] for j in range(n + 1)]
        dpMatches[0][0] = True

        #   base case
        for i in range(1, m + 1):
            if (p[i - 1] != '*'):
                break
            dpMatches[0][i] = True

        #   fill the table
        for r in range(1, n + 1):
            for c in range(1, m + 1):

                #   if pattern is * => check for one char before in string OR check for one char before in pattern
                if (p[c - 1] == '*'):
                    dpMatches[r][c] = dpMatches[r - 1][c] or dpMatches[r][c - 1]
                #   if corresponding chars are same or pattern has ? => fill with diagonal prev bool value.
                elif (p[c - 1] == s[r - 1] or p[c - 1] == '?'):
                    dpMatches[r][c] = dpMatches[r - 1][c - 1]

        #   return last cell
        return dpMatches[n][m]

class WildCardMatchII:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(p)
        n = len(s)

        dpMatches = [[False for i in range(m + 1)] for j in range(2)]
        dpMatches[0][0] = True

        #   base case
        for i in range(1, m + 1):
            if (p[i - 1] != '*'):
                break
            dpMatches[0][i] = True

        for r in range(1, n + 1):
            dpMatches[1] = [False]
            for c in range(1, m + 1):

                #   if pattern is * => check for one char before in string OR check for one char before in pattern
                if (p[c - 1] == '*'):
                    dpMatches[1].append(dpMatches[0][c] or dpMatches[1][c - 1])

                #   if corresponding chars are same or pattern has ? => fill with diagonal prev bool value.
                elif (p[c - 1] == s[r - 1] or p[c - 1] == '?'):
                    dpMatches[1].append(dpMatches[0][c - 1])

                #   default value
                else:
                    dpMatches[1].append(False)

            dpMatches[0] = dpMatches[1]

        #   return last cell
        return dpMatches[0][m]