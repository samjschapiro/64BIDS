import java.math.*;

public class OptionCombinations {
    public static int[] factorials = new int[100001];
    public static int mod = 1000000007;
    public static BigInteger MOD = BigInteger.valueOf(1000000007);

    public static void calculateFactorials() {

        long f = 1;

        for (int i = 1; i < factorials.length; i++) {
            f = (f * i) % mod;
            factorials[i] = (int) f;
        }

    }

    // Choose(n, k) = n! / (k! * (n-k)!)
    public static long nCk(int n, int k) {

        if (n < k) {
            return 0;
        }

        long a = BigInteger.valueOf(factorials[k]).modInverse(MOD).longValue();
        long b = BigInteger.valueOf(factorials[n - k]).modInverse(MOD).longValue();

        // Left to right associativity between * and %
        return factorials[n] * a % mod * b % mod;

    }

    public static void main(String[] args) {

        calculateFactorials();
        System.out.println(countCombinations(2, 1));
        System.out.println(countCombinations(1, 1));
        System.out.println(countCombinations(3, 2));
        System.out.println(countCombinations(2, 3));
        System.out.println(countCombinations(50, 1000));
        System.out.println(countCombinations(0, 3));
    }
        
    public static String countCombinations(int strikes, int expiries) {
        BigInteger profit = new BigInteger("0");
        for (int i = 1; i < (strikes * expiries * 2); i++) {
            BigInteger val = new BigInteger(""+nCk(strikes * expiries * 2, i));
            profit = profit.add(val);
            profit = profit.subtract(new BigInteger("1"));
        }
        return profit.toString();
    }
}
