package com.kamal.dp;

public class KnapSack {

	public static int knapsack(int[] ws, int[] vs, int w, int i, int[][] dp) {
		if (i == -1 || w == 0) {
			return 0;
		}
		if (dp[w][i] != -1) {
			return dp[w][i];
		}
		if (ws[i] <= w) {
			int include = vs[i] + knapsack(ws, vs, w - ws[i], i - 1, dp);
			int exclude = knapsack(ws, vs, w, i - 1, dp);
			dp[w][i] = Math.max(include, exclude);
			return dp[w][i];
		} else {
			return dp[w][i] = knapsack(ws, vs, w, i - 1, dp);
		}
	}

	public static int knapsack(int[] ws, int[] vs, int W) {
		int N = ws.length;
		int[][] dp = new int[W + 1][N + 1];
		// dp[0][0] = 0;
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= W; w++) {
				if (ws[i - 1] <= w) {
					dp[w][i] = Math.max(vs[i - 1] + dp[w - ws[i - 1]][i - 1], dp[w][i - 1]);
				} else {
					dp[w][i] = dp[w][i - 1];
				}
			}
		}
		return dp[W][N];
	}

	public static void main(String[] args) {
		int[] ws = { 3, 7, 10, 6 };
		int[] vs = { 4, 14, 10, 5 };
		int w = 20;
		int i = 3;
		int[][] dp = new int[w + 1][i + 1];
		for (int j = 0; j < dp.length; j++) {
			for (int j2 = 0; j2 < dp[j].length; j2++) {
				dp[j][j2] = -1;
			}
		}
		System.out.println(knapsack(ws, vs, 20, 3, dp));
		System.out.println(knapsack(ws, vs, 20));
	}
}
