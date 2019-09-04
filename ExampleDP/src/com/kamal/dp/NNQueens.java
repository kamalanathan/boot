package com.kamal.dp;

public class NNQueens {

	public static boolean helper(int[] board, int i) {
		if (i == board.length) {
			for (int row : board) {
				for (int c = 0; c < board.length; c++) {
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

		for (int c = 0; c < board.length; c++) {
			boolean flag = false;
			for (int r = 0; r < i; r++) {
				if (board[r] == c) {
					flag = true;
					break;
				}
				if (Math.abs(board[r] - c) == (i - r)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				continue;
			}
			board[i] = c;
			if (helper(board, i + 1)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = new int[4];
		helper(arr, 0);
	}
}
