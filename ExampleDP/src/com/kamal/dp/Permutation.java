package com.kamal.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutation {

	public static void permutation(List<int[]> integers, Set<String> set) {
		if (integers.isEmpty()) {
			return;
		}
		int[] arr = integers.get(0);
		int[] arr2 = new int[integers.get(0).length];
		for (int i = 0; i < arr.length; i++) {
			int k = 0;
			String temp = arr[i] + "";
			arr2[k++] = arr[i];
			for (int j = 0; j < arr.length; j++) {
				if (i == j) {
					continue;
				}
				temp += arr[j];
				arr2[k++] = arr[j];
			}
			if (!set.contains(temp)) {
				integers.add(arr2);
				set.add(temp);
			}
		}
		integers.remove(0);
		permutation(integers, set);
	}

	public static void permutation(int[] arr, int index, Set<String> set) {
		if (index > arr.length) {
			return;
		}
		for (int i = index; i < arr.length; i++) {
			String temp = arr[i] + "";
			for (int j = 0; j < arr.length; j++) {
				if (j == i) {
					continue;
				}
				temp += arr[j];
			}
			set.add(temp);
			temp = arr[i] + "";
			for (int j = arr.length - 1; j >= 0; j--) {
				if (j == i) {
					continue;
				}
				temp += arr[j];
			}
			set.add(temp);
		}
		permutation(arr, index + 1, set);
	}

	// backtracking
	public static void permutation2(int[] input, ArrayList<Integer> partial, boolean[] used) {
		if (partial.size() == input.length) {
			System.out.println(Arrays.toString(partial.toArray()));
			return;
		}
		for (int i = 0; i < input.length; i++) {
			if (!used[i]) {
				used[i] = true;
				partial.add(input[i]);
				permutation2(input, partial, used);
				used[i] = false;
				partial.remove(partial.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		List<int[]> integers = new ArrayList<>();
		integers.add(arr);
		Set<String> set = new HashSet<>();
		permutation(integers, set);
		System.out.println(set);
		set = new HashSet<>();
		permutation(arr, 0, set);
		System.out.println(set);
		ArrayList<Integer> integers2 = new ArrayList<>();
		boolean[] b = new boolean[arr.length];
		permutation2(arr, integers2, b);
	}
}
