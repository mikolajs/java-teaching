import java.util.Arrays;

class Solution {
    // public int solution(int A, int B) {
    // String a = String.valueOf(A);
    // String b = String.valueOf(B);
    // if(a.length() > b.length() ) return -1;
    // if(a.length() == b.length() && b.equals(a)) return 0;
    // else {
    // for(int i = 0; i <= b.length() - a.length(); i++) {
    // if(b.substring(i, i+a.length()).equals(a)) return i;
    // }
    // }
    //
    // return -1;
    // }

    int solution(int[] A) {
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        System.out.println("Start: " + result);
        int r = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count++;
                else
                    count--;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count++;
                else
                    count--;
            }
            System.out.print(count + "   ");
            r = Math.max(r, count);
        }
        ///r 2 gdy są z obu stron różne, 0 gdy z jednej różne z drugiej to samo, -2 gdy z obu identyczne, 
        // -1 gdy na początku lub na końcu takie same, 
        System.out.println();
       if(r ==  2) return result + r;
       else return result + r; ///????
    }

    public static void main(String[] agrs) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] {0,0,1,1,1,0,0}));

    }
}