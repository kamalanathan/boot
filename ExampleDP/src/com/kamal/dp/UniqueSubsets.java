package com.kamal.dp;

import java.util.ArrayList;
import java.util.Arrays;

public class UniqueSubsets {
	public static void generateSubsets(int[] arr, int k, ArrayList<Integer> arrayList, boolean[] used) {
		if (arrayList.size() == k) {
			System.out.println(Arrays.toString(arrayList.toArray()));
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (!used[i]) {
				used[i] = true;
				arrayList.add(arr[i]);
				generateSubsets(arr, k, arrayList, used);
				used[i] = false;
				arrayList.remove(arrayList.size() - 1);
			}
		}
	}

	public static void generateSubsets2(int[] arr, int k, ArrayList<Integer> arrayList, int start) {
		if (arrayList.size() == k) {
			System.out.println(Arrays.toString(arrayList.toArray()));
			return;
		}

		for (int i = start; i < arr.length; i++) {
			arrayList.add(arr[i]);
			generateSubsets2(arr, k, arrayList, i + 1);
			arrayList.remove(arrayList.size() - 1);
		}
	}

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4 };
		int k = 2;
		ArrayList<Integer> integers2 = new ArrayList<>();
		boolean[] b = new boolean[arr.length];
		generateSubsets(arr, k, integers2, b);
		System.out.println();
		System.out.println();
		integers2 = new ArrayList<>();
		generateSubsets2(arr, k, integers2, 0);
	}
}
