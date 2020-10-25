
/*
ID: helena.6
LANG: JAVA
TASK: spainting
*/

import java.io.*;
import java.util.StringTokenizer;

public class spainting {
	static int mod = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		long[] dp = new long[10000001];
				
		dp[0] = 0;
		for (int i = 1; i < k; i++)
			dp[i] = (m * dp[i-1] + m) % mod;
		
		for (int i = k; i <= n; i++)
			dp[i] = (m * dp[i-1] - (((m-1)* dp[i-k]) % mod) + mod) % mod;
	 
		long ans = 1;
		for (int i = 1; i <= n; i++)
			ans = (m * ans) % mod;

	    out.println((ans - dp[n] + dp[n-1] + mod) % mod);
		out.close();
	}
}
