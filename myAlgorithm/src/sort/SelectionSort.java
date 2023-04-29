package sort;

import java.util.Arrays;

public class SelectionSort {
	
	public static void main(String[] args) {
		int[] arr = new int[] {5,4,2,4,5,6};
		for(int i = 0; i < arr.length; i++) {
			int min = i;
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
			}
			
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
			
		}
		System.out.println(Arrays.toString(arr));

	}
}
