import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Svyatoslav ILINSKIY on 5/7/2014.
 */
public class FibTester {
    private static final int FIB_START = 0;
    private static final int FIB_END = 100;
    private static final int FIB_TO_FIND = 100000;

    public static void main(String[] args) {
        Stopwatch s = new Stopwatch();
        System.out.println("This program runs tests on different implementations of fibonacci numbers.");
        System.out.print("Enter an integer to get a fibonacci number. Enter anything else to run default tests: ");
        Scanner keyboard = new Scanner(System.in);
        if(keyboard.hasNextInt()) {
            readInput(s, keyboard);
        } else {
            automatic(s);
        }
        keyboard.close();
    }

    /**
     * Only call this method if user has entered an int!
     * @param s stopwatch
     * @param keyboard console input
     */
    private static void readInput(Stopwatch s, Scanner keyboard) {
        do {
            int i = keyboard.nextInt();
            s.start();
            BigInteger result = fib(i);
            s.stop();
            System.out.printf("fib(%d) = %d, time: %f\n", i, result, s.time());
            System.out.printf("Enter an integer to get a fibonacci number. Enter anything else to exit the program: ");
        } while(keyboard.hasNextInt());
    }

    /**
     * Method that automatically finds some values
     * @param s stopwatch
     */
    private static void automatic(Stopwatch s) {
        for(int i = FIB_START; i < FIB_END + 1;i++) {
            s.start();
            BigInteger result = fib(i);
            s.stop();
            System.out.printf("fib(%d) = %d, time: %f\n", i, result, s.time());
        }

        s.start();
        BigInteger result = fib(FIB_TO_FIND);
        s.stop();
        System.out.printf("fib(%d) = %d, time: %f\n", FIB_TO_FIND, result, s.time());
    }

    /**
     * method that implements the closed form solution to fibonacci sequence.
     * It starts getting round errors after 72nd element
     * @param n num to find
     * @return nth fibonacci number
     */
    public static BigInteger fib(int n) {
        BigDecimal alpha = new BigDecimal(1/Math.sqrt(5));
        BigDecimal root1 = new BigDecimal((1 + Math.sqrt(5))/2);
        BigDecimal root2 = new BigDecimal((1 - Math.sqrt(5))/2);
        return (root1.pow(n).multiply(alpha)).subtract(alpha.multiply(root2.pow(n))).toBigInteger();
    }
}
