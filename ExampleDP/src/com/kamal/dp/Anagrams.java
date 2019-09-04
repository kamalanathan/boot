package com.kamal.dp;

import java.util.ArrayList;

public class Anagrams {

	public static void anagram(char[] arr, ArrayList<String> arrayList, boolean[] bArr) {
		if (arrayList.size() == arr.length) {
			System.out.println(arrayList);
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (bArr[i] == true) {
				continue;
			}
			bArr[i] = true;
			arrayList.add(new String(arr[i] + ""));
			anagram(arr, arrayList, bArr);
			arrayList.remove(new String(arr[i] + ""));
			bArr[i] = false;
		}
	}

	public static void main(String[] args) {
		String string = "god";
		anagram(string.toCharArray(), new ArrayList<>(), new boolean[string.toCharArray().length]);
	}
}
