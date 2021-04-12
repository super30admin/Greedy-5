#Time Complexity:O(n)
#Space Complexity:O(1)
#if the input strings are same or if p string is * we return True. Use 4 pointers , two of which keep track of *'s in S and p strings and 
#two others which parse the two strings. As long as the pointer of s has not completed parsing, if the pointers of p and s are within the string lengths
#and match or if pointer p matches a ?, we increment both pointers, if the p pointer is pointing at *,we place the star pointers of respective
#strings at current string pointer position and incerement p string pointer.If pStar is not yet assigned we return False else we increment
#star pointer of s string, assign s pointer to same position as sStar pointer and p pointer to the next position of pStar pointer. IF the 
#parsing of s string is completed,p string is parsed until the end if * is encountered else false is returned. if both s and p strings are parsed
#completly we return True.
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s==p or p=="*":
            return True
        slen=len(s)
        plen=len(p)
        sp=0
        pp=0
        sStar=-1
        pStar=-1
        while sp<slen:
            if pp<plen and (s[sp]==p[pp] or p[pp]=="?"):
                sp+=1
                pp+=1
            elif pp<plen and p[pp]=="*":
                sStar=sp
                pStar=pp
                pp+=1
            elif pStar==-1:
                return False
            else:
                sStar+=1
                sp=sStar
                pp=pStar+1
        while pp<plen:
            if p[pp]!="*":
                return False
            pp+=1
        return True