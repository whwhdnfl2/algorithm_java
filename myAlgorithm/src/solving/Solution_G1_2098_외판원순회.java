package solving;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_G1_2098_외판원순회 {
	
	static int N;
	static int[][] dist;
	static int[][] dp;
	static int min;
	static int INF = Integer.MAX_VALUE/100;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 도시의 수
		dist = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dist[i][j] = sc.nextInt();
			}
		}
		dp = new int[1 << N][N];
		for(int i = 0; i < 1 << N; i++) {
			Arrays.fill(dp[i], -1);
		}
		min = tsp(1, 0);
		System.out.println(min);
	}
	private static int tsp(int visited, int city) {
		
		for(int i = 0; i < (1 << N); i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println();
		
		if(visited == ((1 << N) - 1)) { // 다 방문 했다는 뜻.
			if(dist[city][0] == 0) return INF;
			return dist[city][0];
		}
		
		if(dp[visited][city] != -1) {
			return dp[visited][city]; // 이미 계산된거면 return
		}
		
		//방문 표시
		dp[visited][city] = INF;
		for(int i = 0; i < N; i++) {
			// 중복방문 x
			if((visited & (1 << i)) != 0)continue;
			// 도시가 연결 안됨
			if(dist[city][i] == 0)continue;
			int res = tsp(visited | (1<<i), i) + dist[city][i];
			dp[visited][city] = Math.min(res, dp[visited][city]);
		}
		System.out.println("city: " + city);
		System.out.println(dp[visited][city]);
		return dp[visited][city];
	}
}
