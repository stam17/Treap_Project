import java.util.Random;

public class Experiment {

    public static final int RANGE = 6500;
    private static final int SIZE = 1000000;
    private static final int STARTCOUNT = 10000;

    private static double[] treapTimes;
    private static double[] aftreapTimes;
    private static int counter=0;

    public static void main(String[] args) {
        treapTimes = new double[20];
        aftreapTimes = new double[20];
        for (int i = 0; i < 20; i++) {
            run();
            counter++;
        }
        double treapSumTrials = 0;
        double aftreapSumTrials = 0;
        for (int trial = 0; trial < 20; trial++) {
            if (trial > 4) {
                treapSumTrials += treapTimes[trial];
                aftreapSumTrials += aftreapTimes[trial];
            }
        }
        double avgTreapLookup = treapSumTrials / 15;
        double avgAFTreapLookup = aftreapSumTrials / 15;

        System.out.println("Distribution: Zipf");
        System.out.println("Range: " + RANGE);
        System.out.println("Average treap lookup: " + avgTreapLookup);
        System.out.println("Average access frequency treap lookup: " + avgAFTreapLookup);
    }
    private static int[] fisherYatesShuffle(int[] array) {
        Random r = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = r.nextInt(i);
            // swap
            int tmp = array[index];
            array[index] = array[i];
            array[i] = tmp;
        }
        return array;
    }

    public static void run() {
        // fill the two treaps with numbers from 0 to RANGE (shuffled)
        Treap treap = new Treap();
        AFTreap aftreap = new AFTreap();

        int[] arr = new int[RANGE];
        for (int j = 1; j <= RANGE; j++) {
            arr[j-1]=j;
        }
        arr = fisherYatesShuffle(arr);
        for (int i : arr) {
            treap.insert(i,i);
            aftreap.insert(i,i);
        }

        long treapSum = 0;
        long aftreapSum = 0;
        long treapStartTime;
        long treapEndTime;
        long aftreapStartTime;
        long aftreapEndTime;

        RequestGenerator req = new RequestGenerator();

        for (int i = 0; i<SIZE; i++) {
            int x = req.getZipf();
            //int x = req.getUniform();

            if (i >= STARTCOUNT) {
                aftreapStartTime = System.nanoTime();
                aftreap.search(x);
                aftreapEndTime = System.nanoTime();

                treapStartTime = System.nanoTime();
                treap.search(x);
                treapEndTime = System.nanoTime();
                aftreapSum += (aftreapEndTime - aftreapStartTime);
                treapSum += (treapEndTime - treapStartTime);
            } else {
                aftreap.search(x);
                treap.search(x);
            }
        }
        double avgTreapTime = treapSum / (double) (SIZE - STARTCOUNT);
        double avgAFTreapTime = aftreapSum / (double) (SIZE - STARTCOUNT);
        System.out.print(avgTreapTime + " ");
        System.out.println(avgAFTreapTime);

        treapTimes[counter] = avgTreapTime;
        aftreapTimes[counter] = avgAFTreapTime;
    }
}
