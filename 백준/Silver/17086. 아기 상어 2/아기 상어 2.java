import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] str = in.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			str = in.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		int answer = 0;
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 0) {
					int temp = bfs(i, j);
					if(temp > answer) answer = temp; 
					visited[i][j] = true;
				}
			}
		}
		System.out.println(answer + 1);
	}
	

	private static int bfs(int x, int y) {
		int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
		int[] dy = {0, 0, 1, -1, -1, 1, 1, -1};
		boolean[][] visited = new boolean[N][M];
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y, 0});
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for(int i = 0; i < 8; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
				else if(map[nx][ny] == 1) { // 아기상어를 만남
					return temp[2];
				}
				else { // 걍 빈칸
					queue.offer(new int[]{nx, ny, temp[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
		
		
		return 0;
	}
}