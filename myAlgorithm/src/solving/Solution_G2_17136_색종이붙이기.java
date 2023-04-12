package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_G2_17136_색종이붙이기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[10][10];
		for(int i = 0; i < 10; i++) {
			String str = in.readLine();
			map[i] = str.toCharArray();
		}
		
	}
}
