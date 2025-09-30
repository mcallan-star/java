public class InsertionSortExample {
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // Pick the element to insert
            int j = i - 1;

            // Shift elements to the right to create space for the key
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // Insert the key in the correct position
        }
    }

    public static void main(String[] args) {
        int[] numbers = {7, 3, 5, 2};
        insertionSort(numbers);
        
        // Print sorted array
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}