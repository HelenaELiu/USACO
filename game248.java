import java.io.*;

public class game248 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		int n = Integer.parseInt(f.readLine());
		int[] nums = new int[n];
		
		for (int i = 0; i < n; i++)
			nums[i] = Integer.parseInt(f.readLine());
		
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++)
			dp[i][i] = nums[i]; //size = 1
		 
		int ans = -1;
		
		for (int size = 2; size <= n; size++) {
			for (int i = 0; i <= n - size; i++) {
				for (int partition = i; partition < i + size - 1; partition++) {
					if (dp[i][partition] == dp[partition + 1][i + size - 1]) { //equal
						dp[i][i + size - 1] = Math.max(dp[i][i + size - 1], dp[i][partition] + 1);
						ans = Math.max(ans, dp[i][i + size - 1]);
					}
				}
			}
		}
		
		/*for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		} */
		
		System.out.println(ans);
		out.println(ans);
		out.close();
	}
}