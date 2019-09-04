package com.kamal.dp;

import java.util.ArrayList;

public class DictionaryLookUp {

	public static void dicLookUp(char[] arr, ArrayList<String> arrayList, int start, String[] arrDic) {
		String temp = "";
		for (int i = 0; i < arrayList.size(); i++) {
			temp += arrayList.get(i);
		}
		for (String string : arrDic) {
			if (temp.equals(string)) {
				System.out.println(temp);
			}
		}
		if (start >= arr.length) {
			return;
		}
		for (int i = start; i < arr.length; i++) {
			// if (i > start) {
			// continue;
			// }
			arrayList.add(arr[i] + "");
			dicLookUp(arr, arrayList, i + 1, arrDic);
			arrayList.remove(arrayList.size() - 1);
		}
	}

	public static void main(String[] args) {
		String input = "catsanddog";
		String[] dic = { "cat", "cats", "and", "sand", "dog" };
		dicLookUp(input.toCharArray(), new ArrayList<>(), 0, dic);
	}
}
