package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_S3_15652_N과M4 {
	
	static int N;
	static int M;
	
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String str[] = in.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		numbers = new int[M];
		
		permutation(0, 1);
	}

	private static void permutation(int cnt, int start) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				if(numbers[i] != 0) System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i = start; i < N + 1; i++) {
			numbers[cnt] = i;
			permutation(cnt + 1, i);
			numbers[cnt] = 0;
		}
	}
}
