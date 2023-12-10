import java.util.Scanner;
public class D {



    private static void Insert(int leaf, int[] heap){
        while ((leaf != 0) && (heap[leaf] > heap[(leaf - 1)/2])) {
            int buffer;
            buffer = heap[leaf];
            heap[leaf] = heap[(leaf-1)/2];
            heap[(leaf-1)/2] = buffer;
            leaf = (leaf-1)/2;
        }
    }
    private static void Extract(int[] heap){
        int value = 0;
        while (2 * value + 2 < heap.length){
            if ((heap[2 * value + 2] > heap[2 * value + 1]) && heap[2 * value + 2] > heap[value]){
                int c = heap[value];
                heap[value] = heap[2 * value + 2];
                heap[2 * value + 2] = c;
                value = 2 * value +2;
            } else if (heap[2 * value + 1] > heap[value]){
                int c;
                c = heap[value];
                heap[value] = heap[2 * value + 1];
                heap[2 * value + 1] = c;
                value = 2 * value +1;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; i++) {
            heap[i] = 0;
        }
        for (int j = 0; j < n; j++) {
           int operation = scan.nextInt();
            if (operation == 0) {
                heap[j] = scan.nextInt();
                Insert(j, heap);
            }

            if (operation == 1) {
                System.out.println(heap[0]);
                heap[0] = heap[n-1];
                heap[n-1] = 0;
                Extract(heap);
            }
        }
    }
}