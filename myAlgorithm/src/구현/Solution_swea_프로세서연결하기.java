package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_swea_프로세서연결하기 {
	
	static int dx[] = {1, -1, 0, 0};
	static int dy[] = {0, 0, 1, -1};
	
	static List<int[]> processorList;
	
	static int N;
	
	static int ans;
	static int maxCore;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for(int test_case = 1; test_case <= t; test_case++) 
		{
			ans = Integer.MAX_VALUE;
			maxCore = 0;
			N = Integer.parseInt(in.readLine());
			int[][] map = new int[N][N];
			processorList = new ArrayList<>();
			String str[];
			for(int i = 0; i < N; i++) {
				str = in.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
					if(map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						processorList.add(new int[] {i, j});
					}else if(map[i][j] == 1 && (i == 0 || i == N - 1 || j == 0 || j == N - 1)){//벽에 박혀있는 거
						maxCore ++;
					}
				}
			}
			
			start(0, 0, map, maxCore); // count, 전선 길이, 맵, 연결된 코어 수
			
//			System.out.println(maxCore);
			System.out.println("#" + test_case + " " + ans);
		}
	}

	private static void start(int cnt, int length, int[][] map, int core) {
		if(cnt == processorList.size()) { // 프로세서 다 봄.
			if(maxCore < core) {
				ans = length;
				maxCore = core;
			}
			if(maxCore == core) {
				if(ans > length) {
					ans = length;
				}
			}
			return;
		}
		int x = processorList.get(cnt)[0];
		int y = processorList.get(cnt)[1];
		for(int i = 0; i < 4; i++) {
			int[][] newMap = clone(map);
			int tempLength = 0;
			int nx = x;
			int ny = y;
			while(true) {
				nx = nx + dx[i];
				ny = ny + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {// 벽에 닿은거임.
					start(cnt + 1, length + tempLength, newMap, core + 1); // map 복사
					break;
				}
				tempLength ++;
				if(newMap[nx][ny] == 1 || newMap[nx][ny] == 2) break; //1은 프로세서, 2는 전선
				newMap[nx][ny] = 2;
			}
		}
        int[][] newMap = clone(map);
        start(cnt + 1, length, newMap, core);
	}

	private static int[][] clone(int[][] map) {
		int[][] newMap = new int[N][N]; 
		for(int i = 0; i < N; i++) {
			newMap[i] = map[i].clone();
		}
		return newMap;
	}
}