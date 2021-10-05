public class OptionCombinations {
    public static String countCombinations(int strikes, int expiries) {
        int combinations = strikes * expiries * 2;
        int profit = 0;
        for (int i = 2; i <= combinations; i++) {
            int num = 1;
            int denomL = 1;
            int denomR = 1;
            for (int c = 2; c <= combinations; c++) {
             	num *= c;   
            }
            for (int d = 2; d <= i; d++) {
                denomL *= d;
            }
            for (int l = 2; l <= (combinations - i); l++) {
                denomR *= l;
            }
            profit += num / (denomL * denomR);
        }
        System.out.println(profit);
        return Integer.toString(profit);
    }
    public static void main(String[] args) {
        countCombinations(2, 3);
        countCombinations(2, 1);
        countCombinations(1, 1); 
    }
}
