import java.util.Locale;
import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Locale.setDefault(Locale.UK);
        Scanner scan = new Scanner(System.in);
        double C = scan.nextDouble();
        double low = 0;
        double high = C;
        double bin;
        double X;
        while ((high - low) > 0.00000001){
            bin = (high + low) / 2;
            X = Math.pow(bin, 2) + Math.sqrt(bin);
            if (X > C) {
                high = bin;
            } else {
                low = bin;
            }
        }
        System.out.printf("%f", low);
    }
}