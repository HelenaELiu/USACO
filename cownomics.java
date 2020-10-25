import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class cownomics {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String[] spotty = new String[n];
		String[] plain = new String[n];
		
		for (int i = 0; i < n; i++)
			spotty[i] = f.readLine();
		for (int i = 0; i < n; i++)
			plain[i] = f.readLine();
		
		
		for (int size = 1; size < m; size++) {
			for (int i = 0; size + i <= m; i++) {
				int start = i;
				int end = i + size;
				HashSet<String> set = new HashSet<String>();
				boolean works = true;

				for (int j = 0; j < n; j++) {
					String s = spotty[j].substring(start, end);
					set.add(s);
				}

				for (int j = 0; j < n; j++) {
					String s = plain[j].substring(start, end);
					if (set.contains(s)) {
						works = false;
						break;
					}
				}

				if (works) {
					out.println(size);
					//System.out.println(start + " " + end);
					out.close();
					System.exit(0);
				}
			}
		}
		
		out.close();
	}
}