package utils;

public class StringUtils {
	
	public static int[] placeInAlphabet(String str) {
		char[] ch  = str.toLowerCase().toCharArray();
		int[] result = new int[ch.length];
		for(int i=0; i < ch.length; i++) {
			int temp = (int) ch[i];
			// 96 for lower case
			if(temp<=122 & temp>=97) result[i] = temp-96;
		}
		return result;
	}

}
