package Stack;

import Stack.Lecture_01.Stack;

public class Lecture_03 {

    public static class DynamicStack extends Stack {
        @Override
        public void push(int data) throws Exception {
            if (isFull()) {
                int[] old_array = arr;
                int[] new_array = new int[2 * old_array.length];
                for (int i = 0; i < arr.length; i++)
                    new_array[i] = old_array[i];
                arr = new_array;
            }
            super.push(data);
        }
    }

}
