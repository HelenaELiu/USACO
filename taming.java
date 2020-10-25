
/*
ID: helena.6
LANG: JAVA
TASK: taming
*/

import java.io.*;
import java.util.StringTokenizer;

public class taming {
	static int n;
	static int max = 101;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("taming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));

		n = Integer.parseInt(f.readLine());
		int[] breakouts = new int[n];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++)
			breakouts[i] = Integer.parseInt(st.nextToken());

		int[][][] dp = new int[n][n][n + 1]; //// [current index][last breakout][number of breakouts]

		for (int i = 0; i < n; i++)
			for (int j = 0; j <= i; j++) // n?
				for (int k = 0; k <= n; k++)
					dp[i][j][k] = max;

		if (breakouts[0] == 1)
			dp[0][0][1] = 1;
		else
			dp[0][0][1] = 0;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= i; j++)
				for (int k = 1; k <= i + 1; k++) {
					if (j < i)
						dp[i][j][k] = dp[i - 1][j][k];
					else
						for (int x = 0; x < i; x++)
							dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][x][k - 1]);
					if (breakouts[i] != i - j)
						dp[i][j][k]++;
				}
		}

		for (int k = 1; k <= n; k++) {
			int low = max;
			for (int j = 0; j < n; j++)
				low = Math.min(low, dp[n - 1][j][k]);
			out.println(low);
		}

		out.close();
	}
}
