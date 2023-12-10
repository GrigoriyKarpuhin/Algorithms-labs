import java.util.Scanner;

public class MergeSort {
    public void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] arrL = new int[n1];
        int[] arrR = new int[n2];
        for (int i = 0; i < n1; i++)
            arrL[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            arrR[j] = arr[mid + 1 + j];
        int i = 0;
        int j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (arrL[i] <= arrR[j]) {
                arr[k] = arrL[i];
                i++;
            }
            else {
                arr[k] = arrR[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = arrL[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = arrR[j];
            j++;
            k++;
        }
    }
    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++){
            array[i] = scan.nextInt();
        }
        new MergeSort().sort(array, 0, size - 1);
        for (int j = 0; j < size; j++){
            System.out.print(array[j] + " ");
        }
    }
}