
package com.exam.portal;


/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
    public int solution(int[] A, int k, int l) {
        int n = A.length;
        int[] prefix_sum = new int[n + 1];
        prefix_sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + A[i - 1];
        }
        int ans = -1;
        for (int i = k; i <= n; i++) {
            int tmp1 = prefix_sum[i] - prefix_sum[i - k];
            for (int j = i + l; j <= n; j++) {
                int tmp2 = prefix_sum[j] - prefix_sum[j - l];
                ans = Math.max(ans, tmp1 + tmp2);
            }
        }
        for (int i = l; i <= n; i++) {
            int tmp1 = prefix_sum[i] - prefix_sum[i - l];
            for (int j = i + k; j <= n; j++) {
                int tmp2 = prefix_sum[j] - prefix_sum[j - k];
                ans = Math.max(ans, tmp1 + tmp2);
            }
        }
        return ans;
    }
}


public class Test {
    public static void main(String[] args) {


        int[] arr = new int[]{10, 9, 11, 7, 6, 5, 3, 2};

        new Ideone().solution(arr, 3, 4);

    }
}
