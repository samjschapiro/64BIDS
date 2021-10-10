import java.util.*;
import java.util.Arrays;

public class DataCenterTraversal {


    public static int findShortestDistance(int n, List<List<Integer>> microwaveConnections, List<List<Integer>> fiberConnections) {
       // Iterate through each list, when we find a node that has 1 in it, we call recursive function on that node
       HashMap<Integer, List<Integer>> microwave = createHashMap(microwaveConnections);
       HashMap<Integer, List<Integer>> fiber = createHashMap(fiberConnections);
       int value = pathSearch(microwave, fiber, false, 1, -1, 0, Integer.MAX_VALUE, n);
       if (value == Integer.MAX_VALUE) {
           return -1;
       } else {
           return value;
       }
    }

    public static HashMap<Integer, List<Integer>> createHashMap(List<List<Integer>> connections) {
        HashMap<Integer, List<Integer>> retMap = new HashMap<>();
        for (List<Integer> connection : connections) {
            if (!retMap.containsKey(connection.get(0))) {
                retMap.put(connection.get(0), new ArrayList<Integer>());
            }
            if (!retMap.containsKey(connection.get(1))) {
                retMap.put(connection.get(1), new ArrayList<Integer>());
            }
            retMap.get(connection.get(0)).add(connection.get(1));
            retMap.get(connection.get(1)).add(connection.get(0));
       }
       return retMap;
    }

    public static int pathSearch(HashMap<Integer, List<Integer>> availableMicrowave, 
                                HashMap<Integer, List<Integer>> availableFiber, 
                                boolean microwaveFlag, 
                                int location,
                                int previousLocation,
                                int count,
                                int currentBest,
                                int n) 
    {
        System.out.println();
        System.out.println("COUNT: " + count + ", Location: " + location);
        System.out.println("Current best: " + currentBest);
        System.out.println("Microwave: " + availableMicrowave);
        System.out.println("Fiber: " + availableFiber);
        if (count + 1 >= currentBest) {
            return currentBest;
        }
        int minPathLength = currentBest;
        if (availableFiber.get(location) != null)
        {
            for (int destination = 0; destination < availableFiber.get(location).size(); destination++) {
                int destinationValue = availableFiber.get(location).get(destination);
                if (destinationValue == n) {
                    return count + 1;
                }
                if (destinationValue == previousLocation && !microwaveFlag) {
                    continue;
                }
                availableFiber.get(location).remove(destination);
                int value = pathSearch(availableMicrowave, availableFiber, false, destinationValue, location, count + 1, minPathLength, n);
                if (minPathLength > value) {
                    minPathLength = value;
                }
                availableFiber.get(location).add(destination, destinationValue);
            }
        }
        if (!microwaveFlag && availableMicrowave.get(location) != null)
        {
            for (int destination = 0; destination < availableMicrowave.get(location).size(); destination++) {
                int destinationValue = availableMicrowave.get(location).get(destination);
                if (destinationValue == n) {
                    return count + 1;
                }
                availableMicrowave.get(location).remove(destination);
                int value = pathSearch(availableMicrowave, availableFiber, true, destinationValue, location, count + 1, minPathLength, n);
                if (minPathLength > value) {
                    minPathLength = value;
                }
                availableMicrowave.get(location).add(destination, destinationValue);
            }
        }
        return minPathLength;
    }

    public static void main(String[] args) {
    /* Test case 1 */
        List<Integer> m0 = new ArrayList<>();
        m0.add(1);
        m0.add(4);
        List<Integer> m1 = new ArrayList<>();
        m1.add(4);
        m1.add(5);
        List<Integer> f0 = new ArrayList<>();
        f0.add(2);
        f0.add(3);
        List<Integer> f1 = new ArrayList<>();
        f1.add(2);
        f1.add(5);
        List<Integer> f2 = new ArrayList<>();
        f2.add(3);
        f2.add(4);
        List<Integer> f3 = new ArrayList<>();
        f3.add(1);
        f3.add(3);
        List<List<Integer>> microwave = new ArrayList<>();
        microwave.add(m0);
        microwave.add(m1);
        List<List<Integer>> fiber = new ArrayList<>();
        fiber.add(f3);
        fiber.add(f0);
        fiber.add(f1);
        fiber.add(f2);

        System.out.println(findShortestDistance(5, microwave, fiber)); 
    /* Test case 2 
        List<Integer> m0 = new ArrayList<>();
        m0.add(1);
        m0.add(4);
        List<Integer> m1 = new ArrayList<>();
        m1.add(4);
        m1.add(5);
        List<Integer> f0 = new ArrayList<>();
        f0.add(1);
        f0.add(2);
        List<Integer> f1 = new ArrayList<>();
        f1.add(3);
        f1.add(4);
        List<Integer> f2 = new ArrayList<>();
        f2.add(4);
        f2.add(5);
        List<List<Integer>> microwave = new ArrayList<>();
        microwave.add(m0);
        microwave.add(m1);
        List<List<Integer>> fiber = new ArrayList<>();
        fiber.add(f0);
        fiber.add(f1);
        fiber.add(f2);

        System.out.println(findShortestDistance(5, microwave, fiber)); */
        
    }
}
/* Example
n = 5
microwaveConnections = [[1, 4], [4, 5]]
fiberConnections = [[2, 3], [2, 5], [3, 4]]

megaList = [[1, 4], [2, 3], [2, 5], [3, 4], [4, 5]]
O(nlogn) megaPowerListSorted = [[1, 4], [2, 3], [2, 5], [3, 2], [3, 4], [4, 1], [4, 3], [4, 5], [5, 2], [5, 4]] */

/* Algorithm
    if (index 1 is startVal)
        startHere
        startCondition = false
    for each element in megaPowerListSorted
    */