package com.kamal.dp;

import java.util.ArrayList;
import java.util.List;

public class UCST {

	public static void combi(int[] arr, int start, int target, List<Integer> arrayList) {
		int count = 0;
		for (Integer integer : arrayList) {
			count += integer;
			if (count == target) {
				System.out.println(arrayList);
				return;
			}
			// else if (count > target) {
			// return;
			// }
		}

		for (int i = start; i < arr.length; i++) {
			if (i > start && arr[i] == arr[i - 1]) {
				continue;
			}
			arrayList.add(arr[i]);
			combi(arr, i + 1, target, arrayList);
			arrayList.remove(new Integer(arr[i]));
		}
	}

	public static void main(String[] args) {
		int arr[] = { 10, 1, 2, 5, 6, 7, 1 };
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		combi(arr, 0, 10, new ArrayList<Integer>());
	}
}
