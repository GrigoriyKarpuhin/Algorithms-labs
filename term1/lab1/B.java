import java.util.Scanner;
 
public class B {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = new int[size];
        for (int k = 0; k < size; k++){
            array[k] = scan.nextInt();
        }
        int min = 101;
        int[] arrCount = new int[101];
        for (int i = 0; i < array.length; i++) {
            arrCount[array[i]] += 1;
            if (array[i] < min) {
                min = array[i];
            }
        }
        for (int j = min; j < arrCount.length; j++) {
           int stop = 0;
            while (arrCount[j] > 0) {
                System.out.print(j + " ");
                arrCount[j]--;
                stop++;
            }
            if (stop == array.length){
                break;
            }
        }
    }
}