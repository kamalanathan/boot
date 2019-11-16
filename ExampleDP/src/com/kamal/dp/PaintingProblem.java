package com.kamal.dp;

import java.util.List;
import java.util.ArrayList;

public class PaintingProblem {
	public static void paint(int[] rgb, int[][] cost, int numOfHouses, int prevColour, List<Integer> list) {

		int totalcost = 0;
		for (int i = numOfHouses; i < cost.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == prevColour) {
					continue;
				} else {
					rgb[i] = j;
				}
				// totalcost += cost[i][j] + paint(rgb, cost, i + 1, j, list);
				list.add(totalcost);
				rgb[i] = -1;
			}
		}
	}

	public static void main(String[] args) {
		int N = 2;
		int[] rgb = new int[3];
		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = -1;
		}
		int cost[][] = { { 17, 12, 17 }, { 16, 16, 5 }, { 14, 3, 9 } };
		paint(rgb, cost, 0, -1, new ArrayList<Integer>());
	}
}