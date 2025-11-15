import java.util.*;

class Greedy01 {

    public record Item(int weight, int value, double ratio) implements Comparable<Item> {
        @Override
        public int compareTo(Item otherItem) {
            // Descending ratios
            return Double.compare(otherItem.ratio(), this.ratio());
        }
    }

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
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i], (double) values[i] / weights[i]);
        }

        // Sort ratios of items in descending
        Arrays.sort(items);

        int remainingWeight = c; // Remaining weight in knapsack
        int totalValue = 0; // Value in knapsack after filling it up

        for (int i = 0; i < n; i++) {
            // Base case
            if (remainingWeight == 0) break;

            Item currentItem = items[i];

            // If item with the highest ratio can fit in knapsack, then grab it
            if (currentItem.weight() <= remainingWeight) {
                // Take item and update variables
                remainingWeight -= currentItem.weight();
                totalValue += currentItem.value();
            }
        }

        return totalValue;
    }
}