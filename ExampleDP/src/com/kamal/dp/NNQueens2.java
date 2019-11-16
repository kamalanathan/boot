package com.kamal.dp;

public class NNQueens2 {

	public static boolean queensPlacement(int[] arr, int index) {
		if (index == arr.length) {
			for (int row : arr) {
				for (int c = 0; c < arr.length; c++) {
					if (c == row) {
						System.out.print(" Q ");
					} else {
						System.out.print(" - ");
					}
				}
				System.out.println("");
			}
			return true;
		}

		for (int i = 0; i < arr.length; i++) {
			boolean bFlag = false;
			for (int j = 0; j < index; j++) {
				if (arr[j] == i) {
					bFlag = true;
					break;
				}
				if (Math.abs(arr[j] - i) == (index - j)) {
					bFlag = true;
					break;
				}
			}
			if (bFlag) {
				continue;
			}
			arr[index] = i;
			if (queensPlacement(arr, index + 1)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = new int[4];
		queensPlacement(arr, 0);
	}
}
