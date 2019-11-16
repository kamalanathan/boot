package com.kamal.dp;

public class HouseProblem {
	static int k = 0;

	public static int calculate(int[] arr, int index, int[] cache) {
		k++;
		if (index >= arr.length) {
			return 0;
		}
		if (cache[index] != 0) {
			return cache[index];
		}
		int total = 0;
		for (int i = index; i < arr.length; i++) {
			total = Math.max(total, arr[i] + calculate(arr, i + 2, cache));
			cache[index] = total;
		}
		return total;
	}

	public static int calculate2(int[] arr) {
		int[] cache = new int[arr.length];
		cache[0] = arr[0];
		cache[1] = arr[1];

		for (int i = 2; i < arr.length; i++) {
			cache[i] = arr[i] + cache[i - 2];
		}
		return Math.max(cache[arr.length - 1], cache[arr.length - 2]);
	}

	public static void main(String[] args) {
		int arr[] = { 20, 125, 20, 15, 10 };
		int[] cache = new int[arr.length];
		System.out.println(calculate(arr, 0, cache));
		System.out.println(k);
		System.out.println(calculate2(arr));
	}
}

// 20 -> 30 -> 10