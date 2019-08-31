package com.kamal.dp;

public class SequenceRecurssion {
	public static boolean isSquence(int[] arr, int index) {
		if (index >= arr.length) {
			return true;
		}
		return (arr[index] == arr[index - 1] + 1) && isSquence(arr, index + 1);
	}

	public static void main(String[] args) {
		int[] arr = { 22, 23, 24, 25, 26, 27, 28 };
		System.out.println(isSquence(arr, 1));
	}
}
