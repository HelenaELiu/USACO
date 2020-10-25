import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj {
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[][] grid = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] dp = new int[n][n][3];
		for (int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
		
		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(0, 0, 0, 0, true));
		
		while (!pq.isEmpty()) {
			State s = pq.poll();
			
			if (s.row >= 0 && s.row < n && s.col >= 0 && s.col < n) { //valid state
				//System.out.println(s.row + " " + s.col);
				int addOn = 0;
				if (s.stepsLeft == 0 && !s.isStart)
					addOn = grid[s.row][s.col];
				
				if (s.time + addOn < dp[s.row][s.col][s.stepsLeft]) {
					dp[s.row][s.col][s.stepsLeft] = s.time + addOn;
					
					if (s.stepsLeft == 0 && !s.isStart) { //must eat grass and not at start
						for (int i = 0; i < 4; i++) {
							pq.add(new State(s.row + dx[i], s.col + dy[i], 2, s.time + t + grid[s.row][s.col], false));
						}
					} else {
						for (int i = 0; i < 4; i++) {
							pq.add(new State(s.row + dx[i], s.col + dy[i], (s.stepsLeft + 2) % 3, s.time + t, false)); //s.stepsLeft - 1 will be negative at beginning
						}
					}
				}
			}
		}
		
		int min = Math.min(Math.min(dp[n - 1][n - 1][2], dp[n - 1][n - 1][1]), dp[n - 1][n - 1][0]);
		//System.out.println(min);
		out.println(min);
		out.close();
	}
}

class State implements Comparable<State> {
	int row, col, stepsLeft, time;
	boolean isStart;

	public State(int r, int c, int s, int t, boolean b) {
		this.row = r;
		this.col = c;
		this.stepsLeft = s;
		this.time = t;
		this.isStart = b;
	}

	@Override
	public int compareTo(State other) {
		return this.time - other.time;
	}
}