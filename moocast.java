
/*
ID: helena.6
LANG: JAVA
TASK: moocast
*/

import java.io.*;
import java.util.StringTokenizer;

public class moocast {
	static int n;
	static int[] x, y, p;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		
		n = Integer.parseInt(f.readLine());
		x = new int[n];
		y = new int[n];
		p = new int[n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] directReachable = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j)
					directReachable[i][j] = true;
				else {
					double dist = Math.hypot(x[j] - x[i], y[j] - y[i]);
					if (dist <= p[i])
						directReachable[i][j] = true;
					if (dist <= p[j])
						directReachable[j][i] = true;
				}
			}
		}
		
		int count = 1; //a cow can transmit to itself
		for (int i = 0; i < n; i++) {
			boolean[] farReachable = new boolean[n];
			count = Math.max(count, dfs(i, farReachable, directReachable));
		}
		
		out.println(count);
		out.close();
	}
	
	public static int dfs(int curr, boolean[] farReachable, boolean[][] directReachable) {
		if (farReachable[curr])
			return 0;

		farReachable[curr] = true;
		int ret = 1;
		
		for(int i = 0; i < n; i++) {
			if(directReachable[curr][i])
				ret += dfs(i, farReachable, directReachable);
		}
		
		return ret;
	}
}
