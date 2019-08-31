package com.kamal.dp;

public class RecursionMaximum {

	public static int findMaximum(int arr[], int index, int value) {
		if (index >= arr.length) {
			return value;
		}
		if (arr[index] > value) {
			return arr[index];
		}
		return findMaximum(arr, index + 1, value);
	}

	public static int findMax2(int[] nums, int i) {
		if (i == 0) {
			return nums[0];
		}
		return Math.max(nums[i], findMax2(nums, i - 1));
	}

	public static void main(String[] args) {
		int arr[] = { 1200, 20, 3, -1, 5, 6, 700, 8, 9, 10 };
		System.out.println(findMaximum(arr, 0, arr[0]));
		System.out.println(findMax2(arr, arr.length - 1));
	}
}
