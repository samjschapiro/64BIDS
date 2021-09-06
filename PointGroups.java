import java.util.*;
import java.lang.Math;

public class PointGroups {
    public static void main(String[] args) {
        /* Generate test data */
        ArrayList<Integer> firstPoints = new ArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            firstPoints.add( (int) (100 * Math.random()) + 1);
        }

        // System.out.println(firstPoints);

        ArrayList<Integer> secondPoints = new ArrayList<Integer>();

        for (int i = 0; i < 1000; i++) {
            secondPoints.add( (int) (100 * Math.random()) + 1);
        }

        // System.out.println(secondPoints);

        findGroups(firstPoints, secondPoints);

    }
    public static List<Integer> findGroups(List<Integer> firstPoints, List<Integer> secondPoints) {
        
        int firstSize = firstPoints.size();

        int secondSize = secondPoints.size();
        
        ArrayList<Integer> groups = new ArrayList<Integer>();
        
        ArrayList<ArrayList<Double>> input = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Double>> output = new ArrayList<ArrayList<Double>>();

        for (int i = 0; i < (firstSize / 2) - 1; i++) {
            ArrayList<Double> point = new ArrayList<Double>();
            point.add(0, Double.valueOf(firstPoints.get(i))); /* x component */
            point.add(1, Double.valueOf(firstPoints.get(i+1))); /* y component */
            point.add(2, Math.sqrt( Math.pow(firstPoints.get(i), 2) + Math.pow(firstPoints.get(i+1), 2) )); /* magnitude */
            point.add(3, Math.atan(firstPoints.get(i+1) / Double.valueOf(firstPoints.get(i)))); /* direction */
            input.add(point);
            i++;
        }

        for (int i = 0; i < (secondSize / 2) - 1; i++) {
            ArrayList<Double> point = new ArrayList<Double>();
            point.add(0, Double.valueOf(secondPoints.get(i))); /* x component */
            point.add(1, Double.valueOf(secondPoints.get(i+1))); /* y component */
            point.add(2, Math.sqrt( Math.pow(secondPoints.get(i), 2) + Math.pow(secondPoints.get(i+1), 2) )); /* magnitude */
            point.add(3, Math.atan( secondPoints.get(i+1) / Double.valueOf(secondPoints.get(i)))); /* direction */
            output.add(point);
            i++;
        }

        System.out.println(input);
        System.out.println(output);

        /* This gets us .1 % */
        for (int i = 1; i <= firstSize / 2; i++) {
            groups.add(i);
        }
        for (int i = 1; i <= firstSize / 2; i++) {
            groups.add(i);
        }


        return groups;
    }
}

