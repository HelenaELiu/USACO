import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class radio {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("radio.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int fx = Integer.parseInt(st.nextToken());
		int fy = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(f.readLine());
		int bx = Integer.parseInt(st.nextToken());
		int by = Integer.parseInt(st.nextToken());
		
		String fstr = f.readLine();
		String bstr = f.readLine();
		
		Pair[] fj = new Pair[n + 1]; //here Pair will be used as coordinates
		fj[0] = new Pair(fx, fy);
		for (int i = 0; i < n; i++) {
			if (fstr.charAt(i) == 'N')
				fj[i + 1] = new Pair(fj[i].x, fj[i].y + 1);
			else if (fstr.charAt(i) == 'S')
				fj[i + 1] = new Pair(fj[i].x, fj[i].y - 1);
			else if (fstr.charAt(i) == 'E')
				fj[i + 1] = new Pair(fj[i].x + 1, fj[i].y);
			else //W
				fj[i + 1] = new Pair(fj[i].x - 1, fj[i].y);
		}
		
		Pair[] bessie = new Pair[m + 1];
		bessie[0] = new Pair(bx, by);
		for (int i = 0; i < m; i++) {
			if (bstr.charAt(i) == 'N')
				bessie[i + 1] = new Pair(bessie[i].x, bessie[i].y + 1);
			else if (bstr.charAt(i) == 'S')
				bessie[i + 1] = new Pair(bessie[i].x, bessie[i].y - 1);
			else if (bstr.charAt(i) == 'E')
				bessie[i + 1] = new Pair(bessie[i].x + 1, bessie[i].y);
			else //W
				bessie[i + 1] = new Pair(bessie[i].x - 1, bessie[i].y);
		}
		
		/*for (int i = 0; i <= n; i++) 
			System.out.println(fj[i].x + " " + fj[i].y);
		System.out.println();
		for (int i = 0; i <= m; i++) 
			System.out.println(bessie[i].x + " " + bessie[i].y);*/
		
		
		int[][] dp = new int[n + 1][m + 1]; //[farmer john idx][bessie idx] = min energy
		for (int i = 0; i <= n; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[0][0] = 0;
		
		Queue<Pair> q = new LinkedList<Pair>(); //here pair will be used as dp indexes
		q.add(new Pair(0, 0));
		boolean[][] visited = new boolean[n + 1][m + 1];
		
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int i = p.x; //farmer john idx
			int j = p.y; //bessie idx
			
			if (i < n && j < m && !visited[i][j]) {
				//System.out.println(i + " " + j);
				visited[i][j] = true;
				dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + getDist(fj[i + 1], bessie[j]));
				dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + getDist(fj[i], bessie[j + 1]));
				dp[i + 1][j + 1] = Math.min(dp[i + 1][j + 1], dp[i][j] + getDist(fj[i + 1], bessie[j + 1]));
				
				q.add(new Pair(i + 1, j));
				q.add(new Pair(i, j + 1));
				q.add(new Pair(i + 1, j + 1));
			}
		}
		
		System.out.println(dp[n][m]);
		out.println(dp[n][m]);
		out.close();
	}

	private static int getDist(Pair fj, Pair b) {
		return (fj.x - b.x) * (fj.x - b.x) + (fj.y - b.y) * (fj.y - b.y);
	}
}

class Pair {
	int x, y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}