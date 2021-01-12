# time complexity: O(sp)
# space complexity: O(sp)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:

        # if pattern is just * then return true
        if s == p or p == "*":
            return True
        # if any one of they is null then return false
        if not s or not p:
            return False

        source = len(s)
        pattern = len(p)

        # create a dp array with one more space
        dp = [[False for i in range(source + 1)] for j in range(pattern + 1)]
        # make first element true
        dp[0][0] = True

        # for each element in pattern, do an loop on string
        for p_idx in range(1, pattern + 1):
            s_idx = 1
            # if * is encountered then find the first true value in previous row and make all the colums true from that column in present row
            if p[p_idx - 1] == "*":
                while not dp[p_idx - 1][s_idx - 1] and (s_idx < source + 1):
                    s_idx += 1

                dp[p_idx][s_idx - 1] = dp[p_idx - 1][s_idx - 1]

                while s_idx < source + 1:
                    dp[p_idx][s_idx] = True
                    s_idx += 1
            # otherewise check if there is a match  ot ? is there then the replace the value with the diagonal value
            else:
                while s_idx < source + 1:
                    if p[p_idx - 1] == "?" or p[p_idx - 1] == s[s_idx - 1]:
                        dp[p_idx][s_idx] = dp[p_idx - 1][s_idx - 1]
                    s_idx += 1

        return dp[pattern][source]

