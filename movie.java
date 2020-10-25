import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class movie {
	static int n, l;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("movie.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("movie.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		Showing[] movies = new Showing[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			movies[i] = new Showing(d, c);
			
			for (int j = 0; j < c; j++) {
				int showtime = Integer.parseInt(st.nextToken());
				movies[i].showtimes[j] = showtime;
			}
		}
		
		int size = (int) Math.pow(2, n);
		int[] dp = new int[size]; //dp[movies] = latest ending time
		
		for (int i = 0; i < size; i++) { //build up
			int num = i;
			
			for (int j = 0; j < n; j++) {
				if ((num & 1) == 0) {
					int digit = (int) Math.pow(2, j);
					int idx = Arrays.binarySearch(movies[j].showtimes, dp[i]);
					
					if (idx < 0) {
						idx += 2;
						idx *= -1;
					}
					
					if (idx < movies[j].showtimes.length && idx >= 0) {
						dp[i + digit] = Math.max(dp[i + digit], movies[j].showtimes[idx] + movies[j].dur);
					}
				}
					
				num = num >> 1;
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < size; i++) {
			if (dp[i] >= l) {
				ans = Math.min(ans, numOnes(i));
			}
		}
		
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		out.println(ans);
		out.close();
	}
	
	private static int numOnes(int num) {
		if (num == 0)
			return 0;
		return (num & 1) + numOnes(num >> 1);
	}
}

class Showing {
	int dur, numShows;
	int[] showtimes;
	
	public Showing (int dur, int numShows) {
		this.dur = dur;
		this.numShows = numShows;
		showtimes = new int[numShows];
	}
}