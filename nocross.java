import java.io.*;

public class nocross {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		int n = Integer.parseInt(f.readLine());
		int[] left = new int[n];
		int[] right = new int[n];
		
		for (int i = 0; i < n; i++)
			left[i] = Integer.parseInt(f.readLine());
		for (int i = 0; i < n; i++)
			right[i] = Integer.parseInt(f.readLine());
		
		int[][] dp = new int[n][n];
		int max = 0;
		
		for (int i = 0; i < n; i++) { //left side
			for (int j = 0; j < n; j++) { //right side
				if (i == 0 || j == 0) {
					if (Math.abs(left[i] - right[j]) <= 4)
						dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i - 1][j - 1];
					if (Math.abs(left[i] - right[j]) <= 4)
						dp[i][j]++;
					dp[i][j] = Math.max(Math.max(dp[i][j], dp[i - 1][j]), dp[i][j - 1]);
				}
				
				max = Math.max(max, dp[i][j]);
			}
		}
		
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}*/
		
		System.out.println(max);
		out.println(max);
		out.close();
	}
}