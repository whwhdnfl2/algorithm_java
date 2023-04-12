package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution_G1_23290_마법사상어와복제 {
	
	static int M, S;
	
	static List<int[]> fishList;
	static int[][] map; // 냄새 숫자 맵
	static int[][] fishMap; // 물고기 숫자 맵
	
	static int[] shark;
	//←, ↖, ↑, ↗, →, ↘, ↓, ↙ 
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int[] dx2 = {-1, 0, 1, 0};
	static int[] dy2 = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str[] = in.readLine().split(" ");
		M = Integer.parseInt(str[0]); // 물고기 수
		S = Integer.parseInt(str[1]); // 연습한 횟수
		
		fishList = new ArrayList<>();
		map = new int[4][4];
		
		fishMap = new int[4][4];
		for(int i = 0; i < M; i++) {
			str = in.readLine().split(" ");
			fishList.add(new int[] {Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]) - 1}); // x, y, 방향
			fishMap[Integer.parseInt(str[0]) - 1][Integer.parseInt(str[1]) - 1]++;
		}
		str = in.readLine().split(" ");
		shark = new int[] {Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1};
		
		for(int s = 0; s < S; s++) //명령 횟수 만큼 실행
		{
			
			List<int[]> beforeFishList = clone(fishList);
			
			for(int i = 0; i < fishList.size(); i++) {
				int[] temp = fishList.get(i);
				for(int j = 0; j <= 7; j++) { //회전 시작
					
					int direc = temp[2];
					if(temp[2] - j < 0) {
						direc = (temp[2] - j + 8);
					}else {
						direc = (temp[2] - j);
					}				
					int nx = temp[0] + dx[direc];
					int ny = temp[1] + dy[direc];
					if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
					if((nx == shark[0] && ny == shark[1]) || map[nx][ny] >= s - 2) continue; // 상어 위치에 있거나 물고기 냄새가 있으면
					fishMap[temp[0]][temp[1]] --;
					temp[0] = nx;
					temp[1] = ny;
					temp[2] = direc;
					fishMap[nx][ny] ++;
					break;
				} 
			} // 물고기 이동 끝
			
//			for(int i = 0; i < fishList.size(); i++) {
//				System.out.println(fishList.get(i)[0] + " " + fishList.get(i)[1] + " " +  fishList.get(i)[2]);
//			}
			
//			System.out.println("이동 후 출력");
//			for(int i = 0; i < 4; i++) {
//				System.out.println(Arrays.toString(fishMap[i]));
//			}
			
			//상어 이동 경로
//			System.out.println("이동하기 전 상어 위치: " + shark[0] + " " + shark[1]);
			int[] load = bfs();
//			System.out.println("길: " + Arrays.toString(load));
			
			
			//이동하면서 물고기 잡아먹음.
			for(int i = 0; i < 3; i++) {
				shark[0] += dx2[load[i] - 1];
				shark[1] += dy2[load[i] - 1];
				if(fishMap[shark[0]][shark[1]] > 0) {//지나가는 길에 물고기가 있으면 잡아먹고 냄새를 만든다.
					map[shark[0]][shark[1]] = s;
					fishMap[shark[0]][shark[1]] = 0;
				}
				for(int j = 0; j < fishList.size(); j++) {
					int[] temp = fishList.get(j);
					if(temp[0] == shark[0] && temp[1] == shark[1]) {
						fishList.remove(j);
						j--;
					}
				}
			}
			
			//물고기 복제
			for(int[] arr : beforeFishList) {
				fishMap[arr[0]][arr[1]] ++;
			}
			
			for(int i = 0; i < beforeFishList.size(); i++) {
				
				int[] temp = beforeFishList.get(i);
				
				int[] nice = new int[] {temp[0], temp[1], temp[2]};
				
				fishList.add(nice);
			}
			
//			System.out.println("복제 후 출력");
//			for(int i = 0; i < 4; i++) {
//				System.out.println(Arrays.toString(fishMap[i]));
//			}
//			System.out.println();
//			System.out.println(fishList.size());
		}

		System.out.println(fishList.size());
	}
	
	//상좌하우 1234
  	private static int[] bfs() {

		
		int move = 999; // 이동 경로
		int count = 0; // 물고기 먹은 수
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {shark[0], shark[1], 0, 0, 0, -1, -1}); // 2 얼마나 움직였나, 3 먹은 물고기 수, 4 이동 경로, 5: 이전 x, 6: 이전 y
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			
			if(temp[2] == 3) {//3번 이동했다면
				if(temp[3] > count) {
					count = temp[3];
					move = temp[4];
				}
				else if(temp[3] == count) {
					if(move > temp[4]) move = temp[4];
				}
				
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = temp[0] + dx2[i];
				int ny = temp[1] + dy2[i];
				if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
				
				if(nx == temp[5] && ny == temp[6]) {
					queue.offer(new int[] {nx, ny, temp[2] + 1, temp[3], i + 1 + temp[4] * 10, temp[0], temp[1]});
				}else {
					queue.offer(new int[] {nx, ny, temp[2] + 1, temp[3] + fishMap[nx][ny], i + 1 + temp[4] * 10, temp[0], temp[1]});

				}
			}
		}
//		System.out.println("루트: " + move);
		
		String strNum = Integer.toString(move);
		int[] arrNum = new int[strNum.length()];
		for (int i = 0; i < strNum.length(); i++) {
			arrNum[i] = strNum.charAt(i) - '0';
		}
//		System.out.println("먹을거:" + count);
		return arrNum;
		
	}

	private static List<int[]> clone(List<int[]> fishList2) {
		List<int[]> temp = new ArrayList<>();
		for(int[] arr : fishList2) temp.add(arr.clone());
		return temp;
	}
}