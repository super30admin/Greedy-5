class Solution {

    public void print(boolean[] arr, char c){
        System.out.print(c + " ");
        for(boolean b: arr){
            System.out.print((b ? 'T' : 'F') + " ");
        }
        System.out.println();
    }

    public boolean isMatch(String s, String p) {
        
        boolean[] prev = new boolean[s.length()+1];
        //System.out.print("  - ");
        //for(char c : s.toCharArray()){
           //System.out.print(c + " ");
        //}
        //System.out.println();
        prev[0] = true;
        //print(prev,'-');
        
        for(int i = 0; i < p.length(); i++){
            char patt = p.charAt(i);
            boolean[] curr = new boolean[s.length()+1];
            for(int j = -1; j < s.length(); j++){
                char str = j == -1 ? '-' : s.charAt(j);

                if(patt == '?'){
                    curr[j+1] = j > -1 && prev[j];
                } else if(patt == '*'){
                    curr[j+1] = prev[j+1] || (j > -1 && curr[j]);
                } else {
                    curr[j+1] = patt == str && (j > -1 ? prev[j] : true);
                }

            }
            prev = curr;
            //print(prev,patt);
        }

        return prev[s.length()];

    }
}
