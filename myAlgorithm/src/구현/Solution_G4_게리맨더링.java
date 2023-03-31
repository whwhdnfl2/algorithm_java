package 구현;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_G4_게리맨더링 {
	
	static int N;
	static int[] population;
	static int[][] graph;
	static int ans = 999999999;
	
	static int check[];

	
	static int[] split;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		population = new int[N];
		for(int i = 0; i < N; i++) {
			population[i] = sc.nextInt();
		}
		graph = new int[N][];
		for(int i = 0; i < N; i++) {
			int M = sc.nextInt();
			int temp[] = new int[M];
			for(int j = 0; j < M; j++) {
				temp[j] = sc.nextInt();
			}
			graph[i] = temp;
		}
		split = new int[N];
		solve(0);
		
		if(ans == 999999999) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}
	
	public static void check1(int num, int one2) {
		if(one2 == 1) {
			check[num] = 1;
			return ;
		}else {
			check[num] = 1;
			for(int i = 0; i < graph[num].length; i++) {
				if(split[graph[num][i] - 1] == 1 && check[graph[num][i] - 1] == 0) {
					check1(graph[num][i] - 1, one2);
				}
			}
		}
		return ;
	}
	public static void check2(int num, int zero2) {
		if(zero2 == 1) {
			check[num] = 1;
			return ;
		}else {
			check[num] = 1;
			for(int i = 0; i < graph[num].length; i++) {
				if(split[graph[num][i] - 1] == 0 && check[graph[num][i] - 1] == 0) {
					check2(graph[num][i] - 1, zero2);
				}
			}
		}
		return ;
	}
	
	public static void solve(int count) {
		if(count == N) {
			//System.out.println(Arrays.toString(split));
			int one2 = 0;
			int zero2 = 0;
			for(int i = 0; i < N; i++) { // 전부 0이거나 1인거 삭제 
				if(split[i] == 1) {
					one2 += 1;
				}else {
					zero2 += 1;
				}
				if(one2 == N  || zero2 == N) {
					return;
				}
			}
			
			
			int done1 = 1;
			int done2 = 1;
			
			check = new int[N];
			for(int i = 0; i < N; i++) {
				if(split[i] == 1 && done1 == 1) {
					check1(i, one2);
				//	System.out.println();
					done1 = 0;
				}else if(split[i] == 0 && done2 == 1) {
					check2(i, zero2);
					done2 = 0;
				}
			}
			
			for(int i = 0; i < N; i++) {
				if(check[i] == 0) {
					return;
				}
			}
			
			int zero = 0;
			int one = 0;
			for(int i = 0; i < N; i++) {
				if(split[i] == 1) {
					one += population[i];
				}
				else {
					zero += population[i];
				}
			}
			int diff = Math.abs(one - zero);
		//	System.out.println(diff);
			ans = Math.min(ans, diff);
			return;
		}else {
			split[count] = 1;
			solve(count + 1);
			split[count] = 0;
			solve(count + 1);
		}
	}
}
