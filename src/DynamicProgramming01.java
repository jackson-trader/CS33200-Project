import java.util.*;

class DynamicProgramming01 {
    static int knapsack(int W, int[] weights, int[] values) {
        int[] dp = new int[W + 1];

        for (int i = 1; i <= weights.length; i++) {
            for (int j = W; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }

        return dp[W];
    }

    public static void main(String[] args) {
        /*  Design an algorithm using dynamic
            programming (if applicable). Pseudocode is needed. Will this algorithm
            achieve optimal solution?
        */

//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt(); // Number of items
//        int W = scanner.nextInt(); // Max weight capacity
//        int[] weights, values; // weight[i], value[i]
        int[] values = {1, 2, 3};
        int[] weights = {4, 5, 1};
        int W = 4;

        System.out.println(knapsack(W, values, weights));
    }
}