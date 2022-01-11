/**
 * Average example
 * @author Aarthi
 */
public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public static float average(int[] nums) {
            float result = 0;
            for (int i = 0; i < nums.length; i++) {
        		result += nums[i];
            }
            result = result / nums.length;
            return result;
        }

        public static void main(String[] args) {
            int [] array = {1,2,3,4,5,6};
            float answer = average(array);
            System.out.println(answer);
        }
}
