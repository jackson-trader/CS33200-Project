import java.util.*;

class DynamicProgramming01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // number of items
        int c = scanner.nextInt(); // knapsack capacity
        int[] weights = new int[n];
        int[] values = new int[n];

        // Read weight and value for each item
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }

        System.out.println(knapsack(n, c, weights, values));
    }

    static int knapsack(int n, int c, int[] weights, int[] values) {
        int[][] dp = new int[n + 1][c + 1];

        // Generating the 2D dp array to solve the problem
        for (int i = 1; i <= n; i++) { // i = how many items considered
            for (int w = 1; w <= c; w++) { // w = current capacity
                int currentItem = i - 1; // to make indexing easier

                // If current item doesn't fit in current capacity considered
                if (weights[currentItem] > w) {
                    dp[i][w] = dp[i - 1][w]; // just copy from previous row, item doesn't fit
                } else {
                    // Current item fits in knapsack, decide to skip or grab it
                    int remaining = w - weights[currentItem]; // remaining capacity

                    // Previous row (don't grab item)
                    int skipItem = dp[i - 1][w];
                    // Previous row at remaining capacity + value of current item (grab item)
                    int grabItem = dp[i - 1][remaining] + values[currentItem];

                    dp[i][w] = Math.max(skipItem, grabItem);
                }
            }
        }

        ArrayList<Integer> itemsGrabbed = new ArrayList<>();
        int w = c;

        // Backtrack the dp array in order to figure out which items were grabbed
        for (int i = n; i > 0; i--) {
            // i = current item
            if (dp[i][w] != dp[i - 1][w]) {
                // Grab item
                itemsGrabbed.add(i);
                w -= weights[i - 1];
            }
        }

        System.out.println("Items grabbed: " + itemsGrabbed);

        return dp[n][c];
    }
}
