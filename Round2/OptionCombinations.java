package Round2;

public class OptionCombinations {
    public String countCombinations(int strikes, int expiries) {
        int combinations = strikes * expiries * 2;
        // (strikes * expires * 2) C (n) where n starts at 2 and ends at 12
        int profit = 0;
        for (int i = 2; i <= combinations; i++) {
            // for each iteration, find the number of combinations
            int factorial = 1;
            for (int c = 2; c <= combinations; c++) {
             	factorial *= c;   
            }
            profit += factorial;
        }
        return "" + profit;
    }
    public void main(String[] args) {
        countCombinations(1, 3);        
    }
}
