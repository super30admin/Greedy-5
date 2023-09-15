class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        '''
        Time complexity: O(S * P), S --> length of the input string s, and P --> length of the pattern p.
        Space complexity: O(1)
        '''
        s_ptr, p_ptr = 0, 0  # Pointers for the input string and pattern

        s_star, p_star = -1, -1  # Pointers to remember the last '*' positions
        while s_ptr < len(s):
            if p_ptr < len(p) and (p[p_ptr] == '?' or p[p_ptr] == s[s_ptr]):
                # Move both pointers for '?' or matching characters
                s_ptr += 1
                p_ptr += 1
            elif p_ptr < len(p) and p[p_ptr] == '*':
                # Remember the '*' position in pattern and input string
                p_star = p_ptr
                s_star = s_ptr
                p_ptr += 1
            elif p_star != -1:
                # Use '*' to match more characters in input string
                p_ptr = p_star + 1
                s_star += 1
                s_ptr = s_star
            else:
                return False

        # Check for any remaining '*' in pattern
        while p_ptr < len(p) and p[p_ptr] == '*':
            p_ptr += 1

        return p_ptr == len(p)
