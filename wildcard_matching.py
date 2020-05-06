// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
This problem is similar to regular expression matching except there is a difference in the pattern matching.
eg: pattern : c*a*b
    in regular expression matching * represents the zero or more repetitions of the preceding character.
    in wildcard matching * represents the zero or more repetitions of any character including blank space.
 In this problem,
 a) if the pattern is * we check the or between two cases where the first case is considering * while comparing and the second case is without considering * while comparing.
 b) if the character in string and pattern are equal or the pattern is '?' then we take the diagonal cell value.


# Time complexity --> o(mn) m--> len(string) n--> len(pattern)
# space complexity --> o(mn) m--> len(string) n--> len(pattern)
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        m=len(s)
        n=len(p)
        #create a matrix using string on x-aixs and pattern on the y-axis
        out=[[False for i in range(n+1)]for j in range(m+1)]
        out[0][0]=True
        for i in range(1,n+1):
            #in the first row when '*' it is true as it is comparing * with blank which is always true but on the way if we encounter characters then it is False all the way till the end.
            if p[i-1]=='*':
                out[0][i]=True
            else:
                break
        for i in range(1,m+1):
            for j in range(1,n+1):
            #If the pattern is * then we take the case when * is included and the case when * is not included.i.e the vakue from the top row and the prev col value.If any of this cells give you a True then the current cell is True
                if p[j-1]=='*':
                    out[i][j]=out[i-1][j] or out[i][j-1]
            If both the characters in string and pattern are equal or pattern is '?' then we take the diagonal cell value.
                elif s[i-1]==p[j-1] or p[j-1]=='?':
                    out[i][j]=out[i-1][j-1]
        # print(out)
        return out[m][n]
                
                
            
                
        