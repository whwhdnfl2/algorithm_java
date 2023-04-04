package 최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_1263_사람네트워크2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(in.readLine());
		for(int test_case = 1; test_case <= t; test_case++) 
		{
			int ans = Integer.MAX_VALUE;
			String[] str = in.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int[][] graph = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(str[N * i + j + 1]);
					if(i == j) continue;
					if(graph[i][j] == 0) graph[i][j] = N * 2;
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i == k) continue;
					for(int j = 0; j < N; j++) {
						if(i == j || j == k) continue;
						graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}

			
			for(int i = 0; i < N; i++) {
				int a = 0;
				for(int j = 0; j < N; j++) {
					a += graph[i][j];
				}
				if(ans > a) {
					ans = a;
				}
			}
			sb.append("#" + test_case +" " + ans + "\n");
		}
		System.out.println(sb.toString());
	}
}
