import java.util.ArrayList;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<Integer> stackA = new ArrayList<>();
        ArrayList<Integer> stackB = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        boolean isPossible = true;
        int need = 1;
        for (int i = 0; i < n; i++) {
            stackA.add(scan.nextInt());
        }
        while (stackA.size() > 0 || stackB.size() > 0) {
             if (stackB.size() > 0 && stackB.get(stackB.size() - 1) == need) {
                stackB.remove(stackB.size() - 1);
                result.add("pop");
                need++;
            } else if (stackA.size() == 0 && stackB.get(stackB.size() - 1) != need) {
                System.out.println("impossible");
                isPossible = false;
                break;
            } else {
                stackB.add(stackA.get(0));
                stackA.remove(0);
                result.add("push");
            }
        }
        if (isPossible) {
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}