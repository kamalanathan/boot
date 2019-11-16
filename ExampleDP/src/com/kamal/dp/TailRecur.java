package com.kamal.dp;

public class TailRecur {

	public static int tailRec(int val) {
		if (val == 0) {
			return val;
		}
		int x = tailRec(val - 1);
		System.out.println(x);
		return x;
	}

	public static void main(String[] args) {
		tailRec(3);
	}
}
