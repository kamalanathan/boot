package com.kamal.dp;

import java.util.ArrayList;
import java.util.List;

public class SubSequence {

	public static List<Integer> subSequence(int[] arr, int start, List<Integer> list) {

		if (start >= arr.length) {
			return list;
		}
		if (start > 0 && arr[start] != arr[start - 1] + 1) {
			list = new ArrayList();
		}
		list.add(arr[start]);
		List<Integer> integers = subSequence(arr, start + 1, list);
		if (integers.size() > list.size()) {
			return integers;
		} else {
			return list;
		}
	}

	public static void subSeq(int[] arr) {
		List<Integer> list = new ArrayList<Integer>();
		List<List<Integer>> list2 = new ArrayList<List<Integer>>();

		list.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1] + 1) {
				list.add(arr[i]);
			} else {
				list2.add(list);
				list = new ArrayList<Integer>();
				list.add(arr[i]);
			}
		}
		int max = 0;
		for (List<Integer> list3 : list2) {
			max = Math.max(max, list3.size());
		}
		for (List<Integer> list3 : list2) {
			if (list3.size() == max) {
				System.out.println(list3);
			}
		}
	}

	public static void main(String[] args) {
		int arr[] = { 5, 2, 3, 6, 8, 1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> integers = new ArrayList<>();
		integers = subSequence(arr, 0, integers);
		System.out.println(integers);
		subSeq(arr);
	}
}
