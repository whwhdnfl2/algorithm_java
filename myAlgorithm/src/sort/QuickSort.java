package sort;

import java.util.Arrays;

public class QuickSort {
	
	public static void quick(int[] arr, int start, int end) {
		if(start >= end) return;
		int pivot = start;
		int left = start + 1;
		int right = end;
		while(left <= right) {
			while(left <= end && arr[left] <= arr[pivot]) left++;
			while(right > start && arr[right] >= arr[pivot]) right--;
			if(left > right) {
				int temp = arr[pivot];
				arr[pivot] = arr[right];
				arr[right] = temp;
			}
			else {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
			}
		}
		quick(arr, right + 1, end);
		quick(arr, start, right - 1);
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {5,4,2,4,5,6};
		
		quick(arr, 0, arr.length - 1);
		
		System.out.println(Arrays.toString(arr));
	}

}
