import java.util.Arrays;

public class hw3 {
    public static void main(String[] args) {
        System.out.println(findMaxNumberOfIntervals(new double[][]{{1, 3}, {4, 5}, {6, 9}}));

        //test();
    }

    public static void test() {
        assert findMaxNumberOfIntervals(new double[][]{{1, 3}}) == 1;
        assert findMaxNumberOfIntervals(new double[][]{{1, 3}, {3, 5}, {5, 9}}) == 1;
        assert findMaxNumberOfIntervals(new double[][]{}) == 0;
        assert findMaxNumberOfIntervals(new double[][]{{1, 2}, {1, 2}, {1, 2}}) == 3;
    }

    private static int findMaxNumberOfIntervals(final double[][] intervals) {

        final double[] arrivals = new double[intervals.length];
        final double[] lefts = new double[intervals.length];

        // takes n time to put everything into two separate arrays
        for (int i = 0; i < intervals.length; i++) {
            arrivals[i] = intervals[i][0];
            lefts[i] = intervals[i][1];
        }

        // spend (2)nlogn time sorting arrivals and lefts time stamps arrays
        mergesort(arrivals);
        mergesort(lefts);

        // currently, no one is at the meeting
        int currentNumOfPersonsAtMeeting = 0;
        // so the initial max number of people at the meeting is 0
        int maxNumOfPersonsAtMeeting = 0;

        // index that indicate the current checking position in two time stamps lists
        int arrivalsIndex = 0;
        int leftsIndex = 0;

        // go through all time stamps and find out the most amount of persons at a meeting
        while (arrivalsIndex < arrivals.length && leftsIndex < lefts.length) {
            // if the person's arrivals time stamps is smaller than lefts time, then it indicates a new arrivals
            if (arrivals[arrivalsIndex] < lefts[leftsIndex]) {
                // increment the number of persons that are currently at meeting
                currentNumOfPersonsAtMeeting++;
                // if number of persons that are currently at meeting reaches a new high, then mark it down
                if (maxNumOfPersonsAtMeeting < currentNumOfPersonsAtMeeting) {
                    maxNumOfPersonsAtMeeting = currentNumOfPersonsAtMeeting;
                }
                // now go and check next arrivals time stamp
                arrivalsIndex++;
            }
            // oh no, it seems like someone arrive earlier lefts the room
            else {
                // decrement the number of persons that are currently at meeting
                currentNumOfPersonsAtMeeting--;
                // lefts check how many people are still in the room for next lefts time stamp
                leftsIndex++;
            }
        }

        // return the result
        return maxNumOfPersonsAtMeeting;
    }

    public static void mergesort(final double[] data) {
        mergesortRecursive(data, 0, data.length - 1);
    }

    /**
     * The recursive part of 2-way mergesort.
     * Given an array of integers (data), a low index, high index
     * sort the subarray data[low..high] (ascending order) using 2-way mergesort.
     *
     * @param data an array of integers
     * @param low  low index
     * @param high high index
     */
    public static void mergesortRecursive(final double[] data, final int low, final int high) {
        if (low < high) {

            final int middle = low + (high - low) / 2;

            mergesortRecursive(data, low, middle);
            mergesortRecursive(data, middle + 1, high);

            merge(data, low, middle, high);
        }
    }

    public static void merge(final double[] data, final int low, final int middle, final int high) {
        // make copy of lefts and right arrays thus we can override the original dataset directly
        final double[] arrL = Arrays.copyOfRange(data, low, middle + 1);
        final double[] arrR = Arrays.copyOfRange(data, middle + 1, high + 1);
        // index of lefts array
        int arrivalsIndex = 0;
        // index of right array
        int leftsIndex = 0;
        // index of next written position in the original data set
        int k = low;

        // merging
        while (arrivalsIndex < arrL.length && leftsIndex < arrR.length) {
            if (arrL[arrivalsIndex] <= arrR[leftsIndex]) {
                data[k] = arrL[arrivalsIndex];
                arrivalsIndex++;
            } else {
                data[k] = arrR[leftsIndex];
                leftsIndex++;
            }
            k++;
        }

        // if there is anything lefts in the two arrays, then write them into the data array
        while (arrivalsIndex < arrL.length) {
            data[k] = arrL[arrivalsIndex];
            arrivalsIndex++;
            k++;
        }
        while (leftsIndex < arrR.length) {
            data[k] = arrR[leftsIndex];
            leftsIndex++;
            k++;
        }
    }
}
