import java.util.Arrays;

class Solution {
    public int[][] generateMatrix(int n) {
    /* 
     * TASK:   print n ^ 2 numbers in a spining way, see attached image
     * 
     * METHOD: maintain three important variables - loop: control round, start: starting point,
     *         count: stepped value. see code in detail
     * 
     * NOTE:   every loop assumes that initial point is included but final point is not included
     * 
     * TIME/
     * SPACE:  O(N ^ 2) / O(N ^ 2)
     */
        int loop = 0;      // round control
        int start = 0;     // start point of assignments
        int count = 1;     // from 1 to n ^ 2
        int i, j;
        int[][] res = new int[n][n]; //create a 2d array with n x n size

        while (loop ++ < n / 2) { // control rounds: start with loop 1
            // top: from left to right
            for (j = start; j < n - loop; j ++) {
                res[start][j] = count ++;
            }

            // right: from top to bottom
            for (i = start; i < n - loop; i ++) {
                res[i][j] = count ++;
            }

            // bottom: from right to left
            for (; j > start; j --) {
                res[i][j] = count ++;
            }

            // left: from bottom top
            for (; i > start; i --) {
                res[i][j] = count ++;
            }
            
            ++ start;
        }

        // assign center value if n is odd
        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        Solution obj = new Solution();
        System.out.println(Arrays.deepToString(obj.generateMatrix(n)));
    }
}