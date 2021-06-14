import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * 
 */
public class HowSumTabulation {


    /**
     * Write a function`howSum(targetSum, numbers)` that takes in a
     * targetSum and an array of numbers as arguments.
     * 
     * The function should return an array containing any combination of
     * elements that add up to exactly the targetSum.
     * 
     * If there is no combination that adds up to the targetSum, then
     * return null.
     * 
     * If there are multiple combinations possible, you may return any
     * single one.
     * 
     * m = targetSum
     * n = numbers.length
     * 
     * Time: O(m^2 * n)  -  Space: O(m^2)
     */
    static int[] howSum(int targetSum, int[] numbers) {

        // **** sanity checks ****
        if (targetSum == 0) return null;

        // **** initialization (Java(16777748)) ****
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] table = new ArrayList[targetSum + 1];

        table[0] = new ArrayList<Integer>();

        for (int i = 0; i < numbers.length; i++) {
            ArrayList<Integer> al = new ArrayList<Integer>();
            int nums = numbers[i];
            al.add(nums);
            table[nums] = al;
        }

        // **** iterate through the table ****
        for (int i = 1; i <= targetSum; i++) {

            // ???? ????
            // System.out.println("<<< i: " + i);

            // **** skip this entry (if needed) ****
            if (table[i] == null)
                continue;

            // **** loop through the numbers array ****
            for (int j = 0; j < numbers.length; j++) {

                // **** for ease of use ****
                int num = numbers[j];

                // ???? ????
                // System.out.println("<<< num: " + num);

                // **** compute target index ****
                int ndx = i + num;

                // **** skip this index (out of range) ****
                if (ndx > targetSum)
                    continue;

                // ???? ????
                // System.out.println("<<< ndx: " + ndx);

                // **** initialize list (if needed) ****
                if (table[ndx] == null)
                    table[ndx] = new ArrayList<Integer>();

                // **** copy all elements from table[i] to table[ndx] ****
                ArrayList<Integer> src = table[i]; 
                ArrayList<Integer> dst = table[ndx];
                dst.clear();
                dst.addAll(src);

                // **** add current element to table[ndx] ****
                dst.add(num);

                // ???? ????
                // System.out.println("<<< dst: " + dst.toString());

                // **** check if done ****
                if (ndx == targetSum) {

                    // **** convert List<Integer> to int[] ****
                    int[] arr = dst.stream().mapToInt( x -> x).toArray();

                    // **** return int[] ****
                    return arr;
                }
            }

            // ???? ????
            // System.out.println("<<< table: " + Arrays.toString(table));
        }

        // **** check if no ansswer was found ****
        if (table[targetSum] == null) return null;

        // **** get last list in the table ****
        ArrayList<Integer> lst = table[targetSum];

        // **** convert List<Integer> to int[] ****
        int[] arr = lst.stream().mapToInt( x -> x).toArray();

        // **** return int[] ****
        return arr;
    }
     

    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** initialization ****
        long start  = 0;
        long end    = 0;
        int[] ans  = null;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read target sum ****
        int targetSum = Integer.parseInt(br.readLine().trim());

        // **** read numbers ****
        int[] numbers = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< targetSum: " + targetSum);
        System.out.println("main <<< numbers: " + Arrays.toString(numbers));

        // **** call function of interest ****
        start = System.nanoTime();
        ans = howSum(targetSum, numbers);
        end = System.nanoTime();

        // ???? ????
        System.out.println("main <<< howSum: " + Arrays.toString(ans));
        System.out.println("main <<< time: " + ((end - start) / 1000) + " micro seconds");
    }
}