
/*
ID: helena.6
LANG: JAVA
TASK: snowboots
*/

import java.io.*;
import java.util.StringTokenizer;

public class snowboots {
	static int n, b;
	static int min = 251;
	static int[] tiles;
	static Boot[] boots;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());	
		b = Integer.parseInt(st.nextToken());
		tiles = new int[n];
		boots = new Boot[b];
		
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++)
			tiles[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < b; i++) {
			st = new StringTokenizer(f.readLine());
			boots[i] = new Boot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		visited = new boolean[b][n]; //boot index, tile index
		dfs(0, 0); //number of boot using rn, tile number

		out.println(min);
		out.close();
	}
	
	private static void dfs(int boot, int tile) { //index of boot, index of tile		
		if (visited[boot][tile])
			return;
		
		visited[boot][tile] = true;
		
		if (boot >= min || (boot == b - 1 && tile + boots[boot].step < n - 1))
			return; //used too many boots, can't make it to end
		
		if (tile + boots[boot].step >= n - 1) //done
			min = Math.min(min, boot);
		else {
			for (int i = boots[boot].step; i > 0; i--) {//step
				if (tiles[tile + i] <= boots[boot].depth)
					dfs(boot, tile + i);
			}
			
			for (int i = 1; boot + i <= b - 1; i++) {
				if (tiles[tile] <= boots[boot + i].depth)
					dfs(boot + i, tile);
			}
		}
	}
	
}

class Boot {
	public int depth, step;

	public Boot(int depth, int step) {
		this.depth = depth;
	    this.step = step;
	}
}