"""
TC: O((m+n)  best case. O(m*n) worst case
SP: O(1)
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        i, j = 0, 0  # Pointers for s and p
        star_idx, match = -1, -1  # Last '*' position in p, last match position in s

        while i < len(s):
            if j < len(p) and (p[j] == '?' or s[i] == p[j]):
                i += 1
                j += 1
            elif j < len(p) and p[j] == '*':
                star_idx = j  # Mark the position of '*'
                match = i  # Store the matched position in s
                j += 1  # Move pattern pointer forward
            elif star_idx != -1:
                # Backtrack: Assume '*' matches an extra character
                j = star_idx + 1
                match += 1
                i = match
            else:
                return False  # No match

        # Ensure remaining characters in p are all '*'
        while j < len(p) and p[j] == '*':
            j += 1

        return j == len(p)
