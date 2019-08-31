package com.kamal.dp;

public class PalindromeRecurssion {
	public static boolean palinsdrome(char[] ch, int start, int end) {
		System.out.println(start + "==" + end);
		if (start >= end) {
			return true;
		}
		return ch[start] == ch[end] && palinsdrome(ch, start + 1, end - 1);
	}

	public static void main(String[] args) {
		String str = "kamimaki";
		System.out.println(palinsdrome(str.toCharArray(), 0, str.toCharArray().length - 1));
	}
}
