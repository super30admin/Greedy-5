// given an array return the sum of product of all sublsets
// input = {1,2,3} Output = 23
// Time complexity = O(n)
// Space Complexity = O(1)

// logic formula
// a + b + c + ab + bc+ ca + abc
// 1 + a + b + c + ab + bc + ca + abc - 1
// 1 + a + b ( 1 + a) + c( 1 + a) + bc( 1 + a) -1
//(1 + a) ( 1+ b + c + bc) -1
// ( 1 + a) ( 1 + b) (1 + c) - 1

public class SumProductSubsets {
    public static long sumOFProducts(int[] arr){
        long result = 1;

        // calculate the product of ( 1 + arr[i]) for all elements
        for ( int num : arr){
            result *= ( 1 + num );
        }
        // subtract one to include empty subset
        return result -1;
    }

    public static void main (String[] args){
        int [] arr = {1,2,3};
        System.out.println("Sum of product of all subsets" +" "+sumOFProducts(arr));
    }

}
