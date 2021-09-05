import java.util.*;
import java.lang.Math;

public class PointGroups {
    public static void main(String[] args) {
        /* Generate test data */
        ArrayList<Integer> firstPoints = new ArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            firstPoints.add( (int) (100 * Math.random()) + 1);
        }

        ArrayList<Integer> secondPoints = new ArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            secondPoints.add( (int) (100 * Math.random()) + 1);
        }

        System.out.println(findGroups(firstPoints, secondPoints));

    }
    public static List<Integer> findGroups(List<Integer> firstPoints, List<Integer> secondPoints) {
        
        int firstSize = firstPoints.size();
        int secondSize = secondPoints.size();
        
        ArrayList<Integer> values = new ArrayList<Integer>();
        
        /* This gets us .1 % */
        for (int i = 1; i <= firstSize / 2; i++) {
            values.add(i);
        }
        for (int i = 1; i <= firstSize / 2; i++) {
            values.add(i);
        }


        return values;
    }
}

