'''
44 Wildcard Matching
https://leetcode.com/problems/wildcard-matching/description/

Solution:
1. Recursion w/ memoization:
We use recursion with memoization to check wildcard match. It tries all possible paths for * (match 0 or more characters). It saves overlapping results in a 2D matrix to avoid recomputation.
https://youtu.be/kgKcuTIv6wk?t=315
https://youtu.be/ZmlQ3vgAOMo?t=898 (most important step (expand or collapse *), 14:58-26:00)
Time: O(MN), Space: O(MN)

2. DP (bottom-up):
We use a 2D DP table to check if pattern p matches string s using wildcard rules - * can match zero or more characters, and ? can match any single character. We fill the table by checking all combinations of s and p, and return if the last cell is true.
https://youtu.be/kgKcuTIv6wk?t=471 (dry run 1)
https://youtu.be/kgKcuTIv6wk?t=1444 (dry run 2)
Time: O(MN), Space: O(MN)

3. Greedy:
Define 4 pointers
i  = pointer pointing to the current char in string s
j  = pointer pointing to the current char in pattern p
prevMatchPos = position of pointer (i) in string s where the last match started
prevStarPos  = position of pointer (j) in pattern where the last * was seen

At first, we initialize two pointers i and j to the beginning of the string and the pattern respectively. We also initialize two variables prevMatchPos=-1 and prevStarPos=-1.

We then loop through the string until we reach the end or find a character in the pattern that doesn't match the corresponding character in the string. While looping through the string, we have 4 possibilities:

a) If the current characters match, we simply move to the next characters in both the pattern and the string.
Or, if the pattern has a '?', we can match '?' with any character in the string.Hence, we simply move to the next characters in both the pattern and the string.

b) If the pattern has a '*' character, then we mark the current position in the pattern and the string as a proper match by setting prevStarPos to the current position (pointing to '*') in the pattern and prevMatchPos to the current position in the string.

c) If there is no match beween the current char of string and pattern (s[i] != p[j]) AND we haven't encountered a * before (i.e., prevStarPos is still -1), then it means we cannot fallback on a previously encountered * to match s[i]. Hence, it is not possible for the pattern to match the string and we return False.

d) Most confusing step: If there was no match beween the current char of string and pattern (s[i] != p[j]) AND the pattern p[j] is not a '*' character, then we understand we need to go through a different route. Hence, we fallback/backtrack to the previously encountered '*' character position (prevStarPos>-1) and try to establish a different match by setting j to prevStarPos + 1, prevMatchPos to prevMatchPos + 1, and i to prevMatchPos.

Once we have looped over the string, we consume any remaining '*' characters in the pattern, and if we have reached the end of both the pattern and the string, the pattern matches the string.

https://youtu.be/kgKcuTIv6wk?t=2736 (dry run 1)
https://youtu.be/kgKcuTIv6wk?t=3082 (most confusing step of dry run 1, expansion of *)
https://youtu.be/kgKcuTIv6wk?t=3293 (dry run 2)
https://youtu.be/kgKcuTIv6wk?t=3369 (most confusing step of dry run 2, expansion of *)
'''
from pprint import pprint

def isMatchWC(s: str, p: str) -> bool:
        def recurse(i: int, j: int, depth) -> bool:
            if i == m and j == n:
                return True
            if j == n:
                return False
            if i == m:
                for k in range(j, n):
                    if p[k] != '*':
                        return False
                return True
            if memo[i][j] is not None:
                return memo[i][j]

            result = False
            # Uncomment the print statements for debugging
            # 'depth' used only for checking recursion depth
            # print(f"\ndepth = {depth}")
            # print(f"i = {i}, s[0:i] = {s[0:i+1]}")
            # print(f"j = {j}, p[0:j] = {p[0:j+1]}")
            if s[i] == p[j] or p[j] == '?':
                result = recurse(i + 1, j + 1, depth + 1)
            elif p[j] == '*':
                case_1 = recurse(i + 1, j, depth + 1) # expand * by 1 char
                case_0 = recurse(i, j + 1, depth + 1) # collapse *
                result = case_0 or case_1


            memo[i][j] = result
            return result

        m, n = len(s), len(p)
        memo = [[None for _ in range(n)] for _ in range(m)]
        return recurse(0, 0, 0)

def isMatchWC_dp(s: str, p: str) -> bool:
    M = len(s)
    N = len(p)
    # Create DP
    dp = [ [False]*(N+1) for _ in range(M+1)]

    # Set all columns in row 0 of dp to False except when:
    # a) row[0][0]
    # b) if you encounter a * in p, then set dp[0][col] = dp[0][col-1]
    dp[0][0] = True
    for j in range(1, N+1):
        if p[j-1] == '*':
            dp[0][j] = dp[0][j-1]
        else:
            dp[1][j] = False

    # fill the remaining rows (1,...,M) and cols (1,...,N)
    for i in range(1, M+1):
        for j in range(1, N+1):

            # if curr char of string and pattern match,
            # then dp[i][j] = dp of left diagonal cell
            if s[i-1] == p[j-1] or p[j-1] == '?.':
                dp[i][j] = dp[i-1][j-1]
            else:
                # if curr char of pattern = *,
                # then dp[i][j] = dp on the left cell ||
                #               = dp on the cell above
                if p[j-1] == '*':
                    # * expands to nothing
                    case_0 = dp[i][j-1] # left cell
                    # * expands to -> '*' + 's[i-1]'
                    case_1 = dp[i-1][j] # cell above
                    dp[i][j] = case_0 or case_1
                else:
                    # if curr chars of string and pattern don't match,
                    # then dp[i][j] = false
                    dp[i][j] = False
    return dp

def isMatchWC_greedy(s: str, p: str) -> bool:
    ''' Time: O(max(M,N)), Space: O(1) '''
    if s == p:
        return True

    if not p:
        return False

    M = len(s)
    N = len(p)

    i, j = 0, 0
    prevMatchPos, prevStarPos = -1, -1

    while i < M:
        if (j < N) and (s[i] == p[j] or p[j] == '?'):
            i += 1
            j += 1
        elif (j < N) and p[j] == "*":
            prevMatchPos = i
            prevStarPos = j
            # case 0: since * expands to nothing, we move j forward by 1 to
            # point to the character after *
            j += 1
        else: # s[i] and p[j] do not match or j out of bounds
            if prevStarPos == -1: # no * before j to fallback
                return False
            else: # this is the most confusing step
                # there is a * before j to fallback
                # case 1: expand * to s[prevMatchPos] (meaning try to match
                # p[prevStarPos] to s[prevMatchPos]) and move prevMatchPos to
                # the next char in s
                j = prevStarPos + 1
                prevMatchPos = prevMatchPos + 1
                i = prevMatchPos

    # If we have gone past the end of s, then we have no chars left in s to
    # match (meaning, we just have "" to match). But p has some trailing chars
    while j < N:
        # if trailing char is a *, we collapse * so that it can match ""
        # if trailing char is not a *, we cannot match that to the """
        if p[j] != "*":
            return False
        j += 1
    return True

def run_isMatchWC():
    tests = [("adceb", "*a*b",
                [[True, True, False, False, False],
                [False, True, True, True, False],
                [False, True, False, True, False],
                [False, True, False, True, False],
                [False, True, False, True, False],
                [False, True, False, True, True]]
                ),
                ("acdcb", "a*c?b",
                [[True, False, False, False, False, False],
                [False, True, True, False, False, False],
                [False, False, True, True, False, False],
                [False, False, True, False, False, False],
                [False, False, True, True, False, False],
                [False, False, True, False, False, False]]
                ),
                ("aa", "a",
                [[True, False],
                [False, True],
                [False, False]]
                ),
                ("aa", "*",
                [[True, True],
                 [False, True],
                 [False, True]]
                ),
                ("cb", "?a",
                [[True, False, False],
                 [False, False, False],
                 [False, False, False]]
                ),
    ]
    for test in tests:
        s, p, ans = test[0], test[1], test[2]
        print(f"\nstring = {s}")
        print(f"pattern = {p}")
        for method in ['recursion', 'dp', 'greedy']:
            if method == 'recursion':
                result = isMatchWC(s,p)
                print(f"Method {method}: Wildcard match = {result}")
                success = (ans[-1][-1] == result)
            elif method == 'dp':
                result = isMatchWC_dp(s,p)
                print(f"Method {method}: Wildcard match = {result[-1][-1]}")
                print(f"DP:")
                pprint(result)
                success = (ans == result)
            elif method == 'greedy':
                result = isMatchWC_greedy(s,p)
                print(f"Method {method}: Wildcard match = {result}")
                success = (ans[-1][-1] == result)
            print(f"Pass: {success}")
            if not success:
                print(f"Failed")
                return

run_isMatchWC()