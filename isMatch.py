# Time Complexity : O(n)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        parts = p.replace('?', '.').split('*')

        if len(parts) == 1:
            return bool(re.match(parts[0] + '$', s))

        if not re.match(parts[0], s):
            return False

        s = s[len(parts.pop(0)):]

        if not re.search(parts[-1] + '$', s):
            return False

        s = s[:len(s) - len(parts.pop())]

        for part in parts:
            m = re.search(part, s)
            if not m:
                return False
            s = s[m.end():]
        return True
