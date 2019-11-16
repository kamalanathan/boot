package com.kamal.dp;

public class SolveHanoi {

	public static void solve(int n, char rodFrom, char rodMiddel, char rodTo) {
		if (n == 1) {
			System.out.println("Plate 1 from " + rodFrom + " To " + rodTo);
			return;
		}
		solve(n - 1, rodFrom, rodTo, rodMiddel);
		System.out.println("Plate " + n + " from " + rodFrom + " To " + rodTo);
		solve(n - 1, rodMiddel, rodFrom, rodTo);
	}

	public static void main(String[] args) {
		solve(3, 'A', 'B', 'C');
	}
}
