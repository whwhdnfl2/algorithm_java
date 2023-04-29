package sort;

import java.util.Arrays;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = new int[] {5,4,2,4,5,6};
		
		for(int i = 0; i < arr.length; i++) {
			int temp = arr[i];
			int prev = i - 1;
			while(prev >= 0 && temp < arr[prev]) {
				arr[prev + 1] = arr[prev];
				prev --;
			}
			arr[prev + 1] = temp;
		}
		
		System.out.println(Arrays.toString(arr));

	}
}
