package com.kamal.dp;

public class RodCutting {

	public static int cutIntoPcs(int length, int[] prices) {
		if (length <= 0) {
			return 0;
		}
		int total = Integer.MIN_VALUE;
		for (int i = 0; i < length; i++) {
			total = Math.max(total, prices[i] + cutIntoPcs(length - i - 1, prices));
		}
		return total;
	}

	public static int cutIntoPcs2(int length, int[] prices) {
		int[] dp = new int[length + 1];
//		dp[0] = 0;

		// int total = Integer.MIN_VALUE;
		for (int l = 1; l <= length; l++) {
			dp[l] = Integer.MIN_VALUE;
			for (int i = 0; i < l; i++) {
				dp[l] = Math.max(dp[l], prices[i] + dp[length - i - 1]);
			}
		}
		return dp[length];
	}

	public static void main(String[] args) {
		int l = 8;
		int[] p = { 1, 5, 8, 9, 10, 14, 17, 20, 24, 30 };
		System.out.println(cutIntoPcs(l, p));
		System.out.println(cutIntoPcs2(l, p));
	}
}
