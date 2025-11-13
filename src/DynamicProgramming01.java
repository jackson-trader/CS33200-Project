import java.util.*;

class DynamicProgramming01 {
    static int knapsack(int n, int m, int[] weights, int[] values) {
        // Initialize 2D dp array (Already initialized to 0 by Java)
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) { // Gradually increase num of items considered
            for (int w = 1; w <= m; w++) { // Gradually increase capacity
                // If current item fits in knapsack decide to grab or skip it
                if (weights[i] <= w) {
                    int skipItem = dp[i - 1][w];
                    int grabItem = dp[i - 1][w - weights[i]] + values[i];
                    dp[i][w] = Math.max(skipItem, grabItem);
                } else {
                    // Can't include item in bag because too much weight, just copy from row above (i.e. Don't add item)
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Num of items
        int m = in.nextInt(); // Weight capacity
        int[] weights = new int[n + 1]; // Weights of each item
        int[] values = new int[n + 1]; // Values of each item

        // Obtain weight and value of each item x_i
        for (int i = 1; i <= n; i++) {
            weights[i] = in.nextInt();
            values[i] = in.nextInt();
        }

        System.out.println(knapsack(n, m, weights, values));
    }
}