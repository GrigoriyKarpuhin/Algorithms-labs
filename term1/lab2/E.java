import java.util.ArrayList;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner scan1 = new Scanner(System.in);
        String in = scan1.nextLine();
        Scanner scan2 = new Scanner(in);
        ArrayList<String> stackIn = new ArrayList<>();
        ArrayList<Integer> stackMem = new ArrayList<>();
        while (scan2.hasNext()) {
            stackIn.add(scan2.next());
        }
        while (stackIn.size() > 0) {
            String next = stackIn.remove(0);
            if (next.charAt(0) == '+') {
                int a = stackMem.remove(0);
                int b = stackMem.remove(0);
                stackMem.add(0, a + b);
            } else if (next.charAt(0) == '-') {
                int a = stackMem.remove(0);
                int b = stackMem.remove(0);
                stackMem.add(0, b - a);
            } else if (next.charAt(0) == '*') {
                int a = stackMem.remove(0);
                int b = stackMem.remove(0);
                stackMem.add(0, a * b);
            } else if (next.charAt(0) == '/') {
                int a = stackMem.remove(0);
                int b = stackMem.remove(0);
                stackMem.add(0, b / a);
            } else {
                stackMem.add(0, Integer.parseInt(next));
            }
        }
        System.out.println(stackMem.get(0));
    }
}