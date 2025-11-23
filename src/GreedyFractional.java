import java.util.*;

class GreedyFractional {

    public record Item(int weight, int value, double ratio, int originalIndex) implements Comparable<Item> {
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

        System.out.format("%.2f\n", knapsack(n, c, weights, values));
    }

    static double knapsack(int n, int c, int[] weights, int[] values) {
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i], (double) values[i] / weights[i], i);
        }

        // Sort ratios of items in descending
        Arrays.sort(items);

        int remainingWeight = c; // Remaining weight in knapsack
        double totalValue = 0.0; // Value in knapsack after filling it up

        ArrayList<String> itemsGrabbed = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Base case
            if (remainingWeight == 0) break;

            Item currentItem = items[i];

            // If item with the highest ratio can fit in knapsack, then grab it
            if (currentItem.weight() <= remainingWeight) {
                // Take item and update variables
                remainingWeight -= currentItem.weight();
                totalValue += currentItem.value();
                itemsGrabbed.add(String.format("1 of item %d", currentItem.originalIndex()));
            } else {
                // Cut highest-ratio item into fraction, so you can still grab it (only occurs at end)
                double fraction = (double) remainingWeight / currentItem.weight();
                totalValue += fraction * currentItem.value();
                remainingWeight = 0;
                itemsGrabbed.add(String.format("%.2f of item %d", fraction, currentItem.originalIndex()));
            }
        }

        System.out.println("Items grabbed (0-index based): " + itemsGrabbed);

        return totalValue;
    }
}