"""
Scan s and p with two pointers; remember the last * position and the match start in s
On mismatch: if we saw a *, backtrack: treat that * as matching one more char from s
When s ends, the rest of p must be all *
"""
"""
Time Complexity: O(len(s) + len(p))
Space Complexity: O(1)
"""


class wildcardMatch:
    def isMatch(self, s: str, p: str) -> bool:
        i = j = 0               
        star = -1      
        match = 0      
        n, m = len(s), len(p)

        while i < n:
            if j < m and (p[j] == s[i] or p[j] == '?'):
                i += 1; j += 1
            elif j < m and p[j] == '*':
                star = j
                match = i
                j += 1       
            elif star != -1:     
                j = star + 1
                match += 1
                i = match
            else:
                return False

        while j < m and p[j] == '*':
            j += 1
        return j == m

if __name__ == "__main__":
    obj = wildcardMatch()
    tests = [
        ("aa", "a", False),
        ("aa", "*", True),
        ("cb", "?a", False),
        ("adceb", "*a*b", True),
        ("acdcb", "a*c?b", False),
        ("", "*", True),
        ("", "?", False),
        ("abc", "****a**?**c*", True),
    ]
    for s, p, exp in tests:
        print(s, p, obj.isMatch(s, p), "exp:", exp)


