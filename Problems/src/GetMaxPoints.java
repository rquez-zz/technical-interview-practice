import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Given N tasks, find the maximal points that can be achieved by finishing them

 Problem Constraints
 There are T minutes for completing N tasks.
 Solutions can be submitted at any time, including exactly T minutes after the start
 i-th task submitted t minutes after the start, will get maxPoints[i] - t * pointsPerMinute[i] points
 i-th task takes requiredTime[i] minutes to solve

 Input Format
 Line 1: T, total minutes available to finish
 Line 2: Comma separated list of maxPoints
 Line 3: Comma separated list of pointsPerMinute
 Line 4: Comma separated list of requiredTime

 Sample Input
 75
 250,500,1000
 2,4,8
 25,25,25

 Sample Output
 1200

 Explanation
 First, solve the third task 25 minutes after the start of the contest. Get 1000 - 8 * 25 = 800 points
 Second, solve the second task 50 minutes after the start of the contest. Get 500 - 4 * 50 = 300 points
 Third, solve the first task 75 minutes after the start of the contest. Get 250 - 2 * 75 = 100 points
 In total, get 800 + 300 + 100 = 1200 points

 */
public class GetMaxPoints {
    public static void main(String args[]) {
        int[] maxPoints = {250, 500, 1000};
        int[] pointsPerMinute = {2,4,8};
        int[] requiredTime = {25,25,25};
        System.out.println(getMaxPoints(maxPoints, requiredTime, pointsPerMinute));
    }
    public static int getMaxPoints(int[] maxPoints, int[] requiredTime, int[] pointsPerMinute) {
        int sumMaxPoints = 0;
        for (int i = 0; i < maxPoints.length; i++) {
            sumMaxPoints = sumMaxPoints + maxPoints[i];
            if (i != 0)
                requiredTime[i] += requiredTime[i-1];
        }
        return (sumMaxPoints - findMin(requiredTime,pointsPerMinute, 0, 0, sumMaxPoints));
    }

    public static int findMin(int[] requiredTime, int[] pointsPerMinute, int timeIndex, int sum, int minSum) {
        if(timeIndex == requiredTime.length - 1) {
            sum+= requiredTime[timeIndex] * pointsPerMinute[0];
            if (sum< minSum) return sum;
            else return minSum;
        }
        for (int j = 0; j < pointsPerMinute.length; j++) {
            int sumResult = findMin(requiredTime,
                    removeFromArray(j, pointsPerMinute),
                    timeIndex + 1,
                    sum + requiredTime[timeIndex] * pointsPerMinute[j],
                    minSum);
            if ( sumResult < minSum)
                minSum = sumResult;
        }
        return minSum;
    }
    public static int[] removeFromArray(int itemIndexToRemove, int[] array) {
        List<Integer> result = new LinkedList<Integer>();
        for( int item : array) {
            if (array[itemIndexToRemove] != item) result.add(item);
        }
        int[] newArray = new int[array.length - 1];
        for (int i = 0; i < newArray.length; i++) { newArray[i] = result.get(i); }
        return newArray;
    }
}
