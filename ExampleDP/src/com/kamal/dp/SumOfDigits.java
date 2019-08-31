package com.kamal.dp;

public class SumOfDigits {

	public static int countValue(int value) {
		if (value <= 0) {
			return value;
		}
		int val = value % 10;
		value = value / 10;
		return val + countValue(value);
	}

	public static void main(String[] args) {
		System.out.println(countValue(7777777));
	}
}
