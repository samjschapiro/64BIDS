
import java.util.*;

public class FlatCallsAndPuts {
    public int calculateProfit(List<Integer> callStrikePrices, List<Integer> callVolumes, List<Integer> putStrikePrices, List<Integer> putVolumes) {
        
        int call_set_size = callStrikePrices.length();
        int call_powerSet_size = Math.pow(2, call_set_size);
        List<List<Integer>> call_powerSet = new List<List<Integer>> ();

        /* Power set for calls */
        for (int counter = 0; counter < call_powerSet_size; i++) {
            for (int j = 0; j < call_set_size; j++) {
                if ( (counter & (1 << j)) > 0) {
                    call_powerSet.add()
                }
            }

        }

        /* Power set for puts */

    }
}

