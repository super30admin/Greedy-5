class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or s == '*':
            return True
        # if p=='' or s=='':
        #     return False
        m = [[False for i in range(len(s) + 1)] for j in range(len(p) + 1)]
        m[0][0] = True
        # if p[0]=='*':
        #     m[1][0]=True
        # print(m)
        for i in range(1, len(m)):
            if p[i - 1] == '*':
                m[i][0] = m[i - 1][0]
                for j in range(1, len(m[0])):
                    m[i][j] = m[i - 1][j] or m[i][j - 1]
            elif p[i - 1] == '?':
                for j in range(1, len(m[0])):
                    m[i][j] = m[i - 1][j - 1]
            else:
                for j in range(1, len(m[0])):
                    if s[j - 1] == p[i - 1]:
                        m[i][j] = m[i - 1][j - 1]

        return m[len(m) - 1][len(m[0]) - 1]