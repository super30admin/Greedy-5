# TC: O(2^n)
# SC: O(n)


class Solution:
    def solve(self, index1, index2, text, pattern):
        if index1 < 0 and index2 < 0:
            return True
        if index2 < 0 and index1 >= 0:
            return False
        if index1 < 0 and index2 >= 0:
            for i in range(index2 + 1):
                if pattern[i] != '*':
                    return False
            return True

        if text[index1] == pattern[index2] or pattern[index2] == '?':
            return self.solve(index1 - 1, index2 - 1, text, pattern)

        if pattern[index2] == '*':
            return self.solve(index1 - 1, index2, text, pattern) or self.solve(index1, index2 - 1, text, pattern)

        return False

    def isMatch(self, s: str, p: str) -> bool:
        n1 = len(s)
        n2 = len(p)
        return self.solve(n1 - 1, n2 - 1, s, p)