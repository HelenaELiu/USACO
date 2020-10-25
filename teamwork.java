
import java.io.*;
import java.util.StringTokenizer;

public class teamwork {
	static int n, k;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("teamwork.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int[] skill = new int[n];
		int[] dp = new int[n];
		
		for (int i = 0; i < n; i++) {
			skill[i] = Integer.parseInt(f.readLine());
		}
		
		dp[0] = skill[0];
		
		for (int i = 1; i < n; i++) {
			int max = skill[i];
			int j = i;
			while (j >= 0 && i - j + 1 <= k) {
				max = Math.max(max, skill[j]);
				if (j == 0) 
					dp[i] = Math.max(dp[i], max * (i - j + 1));
				else 
					dp[i] = Math.max(dp[i], dp[j - 1] + max * (i - j + 1));
				j--;
			}
		}
		
		out.println(dp[n-1]);
		out.close();
	}
}