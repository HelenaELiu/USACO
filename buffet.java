import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class buffet {
	static int n, e;
	static int[][] grid;
	static ArrayList<Integer>[] adj;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("buffet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		grid = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(grid[i], Integer.MAX_VALUE);
		Grass[] grass = new Grass[n];
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj[i] = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			int q = Integer.parseInt(st.nextToken());
			grass[i] = new Grass(i, q);
			int d = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < d; j++) {
				int neighbor = Integer.parseInt(st.nextToken()) - 1;
				grid[i][neighbor] = 1;
				adj[i].add(neighbor);
			}
		}
		 
		Arrays.sort(grass);
		
		for (int i = 0; i < n; i++) //fills grid
			bfs(i);
		
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		} */
		
		int[] dp = new int[n];
		int ans = -1;
		dp[0] = grass[0].val;
		
		for (int i = 1; i < n; i++) {
			Grass g = grass[i];
			int max = 0; //start at i
			
			for (int j = 0; j < i; j++) {
				Grass g2 = grass[j];
				if (grid[g.id][g2.id] != Integer.MAX_VALUE)
					max = Math.max(max, dp[j] - grid[g.id][g2.id] * e);
			}
			
			dp[i] = g.val + max;
			ans = Math.max(ans, dp[i]);
		}
		
		out.println(ans);
		out.close();
	}
	
	private static void bfs(int src) {
		Queue<Integer> qNode = new LinkedList<Integer>();
		qNode.add(src);
		Queue<Integer> qDist = new LinkedList<Integer>();
		qDist.add(0);
		boolean[] visited = new boolean[n];
		
		while (!qNode.isEmpty()) {
			int node = qNode.poll();
			int dist = qDist.poll();
			visited[node] = true;
			grid[src][node] = Math.min(dist, grid[src][node]);
			
			for (Integer i: adj[node]) {
				if (!visited[i]) {
					qNode.add(i);
					qDist.add(dist + 1);
				}
			}
		}
	}
}

class Grass implements Comparable<Grass> {
	int id, val;
	
	public Grass(int id, int val) {
		this.id = id;
		this.val = val;
	}

	@Override
	public int compareTo(Grass other) {
		return val - other.val;
	}
}