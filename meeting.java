import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.StringTokenizer;

public class meeting {
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("meeting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] bGraph = new int[n][n];
		int[][] eGraph = new int[n][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			bGraph[a][b] = c;
			eGraph[a][b] = d;
		}
		
		/*
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(eGraph[i][j] + " ");
			System.out.println();
		}
		*/

		boolean[] bLengths = dp(bGraph); //contains all valid path lengths from 1 to n for bessie
		boolean[] eLengths = dp(eGraph); //contains all valid path lengths from 1 to n for elsie
		
		int ans = -1;
		for (int i = 0; i < 100 * n; i++) {
			if (bLengths[i] && eLengths[i]) {
				ans = i;
				break;
			}
		}

		if (ans == -1)
			out.println("IMPOSSIBLE");
		else
			out.println(ans);
		out.close();
	}

	private static boolean[] dp(int[][] graph) {
		boolean[][] dp = new boolean[n][100 * n];
		dp[0][0] = true;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < 100 * n; j++) {
				if (dp[i][j]) {
					for (int k = i + 1; k < n; k++) {
						if (graph[i][k] != 0)
							dp[k][j + graph[i][k]] = true;
					}
				}
			}
		}
		
		/*
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 100 * n; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}
		System.out.println();
		*/

		return dp[n - 1];
	}
}