import java.util.*;



public class FlatCallsAndPuts {
    public static void main(String[] args) {
        ArrayList<Integer> callStrikePrices = new ArrayList<Integer>();
            callStrikePrices.add(3);
            callStrikePrices.add(9);
            callStrikePrices.add(7);

        ArrayList<Integer> callVolume = new ArrayList<Integer>();
            callVolume.add(1);
            callVolume.add(3);
            callVolume.add(2);

        ArrayList<Integer> putStrikePrices = new ArrayList<Integer>();
            putStrikePrices.add(10);
            putStrikePrices.add(10);            

        ArrayList<Integer> putVolumes = new ArrayList<Integer>();
            putVolumes.add(2);
            putVolumes.add(2);

        System.out.println(calculateProfit(callStrikePrices, callVolume, putStrikePrices, putVolumes));
    }
    public static int calculateProfit(List<Integer> callStrikePrices, List<Integer> callVolumes, List<Integer> putStrikePrices, List<Integer> putVolumes) {
        
        /* Power set for calls amount*/
        int call_set_size = callStrikePrices.size();
        int call_powerSet_size = (int) Math.pow(2, call_set_size);
        int[] call_powerSet = new int[call_powerSet_size];

        for (int counter = 0; counter < call_powerSet_size; counter++) {
            for (int j = 0; j < call_set_size; j++) {
                if ( (counter & (1 << j)) > 0) {
                    call_powerSet[counter] += callStrikePrices.get(j) * callVolumes.get(j);
                }
            }
        }

        /* Power set for puts amount */
        int put_set_size = putStrikePrices.size();
        int put_powerSet_size = (int) Math.pow(2, put_set_size);
        int[] put_powerSet = new int[put_powerSet_size];

        for (int counter = 0; counter < put_powerSet_size; counter++) {
            for (int j = 0; j < put_set_size; j++) {
                if ( (counter & (1 << j)) > 0) {
                    put_powerSet[counter] += putStrikePrices.get(j) * putVolumes.get(j);
                }
            }
        }

        /* Power set for calls volume */
        int call_set_size_volume = callStrikePrices.size();
        int call_powerSet_size_volume = (int) Math.pow(2, call_set_size_volume);
        int[] call_powerSet_volume = new int[call_powerSet_size_volume];

        for (int counter = 0; counter < call_powerSet_size; counter++) {
            for (int j = 0; j < call_set_size; j++) {
                if ( (counter & (1 << j)) > 0) {
                    call_powerSet_volume[counter] += callVolumes.get(j);
                }
            }
        }

        /* Power set for puts volume */
        int put_set_size_volume = putStrikePrices.size();
        int put_powerSet_size_volume = (int) Math.pow(2, put_set_size_volume);
        int[] put_powerSet_volume = new int[put_powerSet_size_volume];

        for (int counter = 0; counter < put_powerSet_size_volume; counter++) {
            for (int j = 0; j < put_set_size_volume; j++) {
                if ( (counter & (1 << j)) > 0) {
                    put_powerSet_volume[counter] += putVolumes.get(j);
                }
            }
        }

        int max = 0;
        
        for(int i=0; i < call_powerSet_size_volume; i++){
            for(int j=0; j < put_powerSet_size_volume; j++){
                if(call_powerSet_volume[i] == put_powerSet_volume[j]){
                    int tem = put_powerSet[j] - call_powerSet[i];
                    if(tem > max) max = tem;
                }
            }
        }

        return max;

    }
}

