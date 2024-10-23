# Time complexity - O(1) since the number of brackets are constant
class Solution:
    def calculateTax(self, brackets: List[List[int]], income: int) -> float:
        result = 0
        lower = 0 
        i = 0
        while income:
            upper, percent = brackets[i]
            taxable = min(income, upper - lower)
            result +=  (taxable*percent)/100
            income -= taxable
            lower = upper
            i += 1
        return result
