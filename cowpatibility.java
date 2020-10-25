
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class compatibility {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowpatibility.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		StringTokenizer st;
		n = Integer.parseInt(f.readLine());
		Flavors[] flavor = new Flavors[n]; //the flavor...
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			flavor[i] = new Flavors(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Arrays.sort(flavor[i].arr);
			for (int j = 0; j < 32; j++) {
				//subsets[get_subset(flavors[i], j)]++;
			}
		}
		
		long count = n * (n-1)/2;
		
		out.println(count);
		out.close();
	}
}

class Flavors {
	public int[] arr;
	
	public Flavors() {
		arr = new int[5];
	}
	
	public Flavors(int a, int b, int c, int d, int e) {
		arr = new int[5];
		arr[0] = a;
		arr[1] = b;
		arr[2] = c;
		arr[3] = d;
		arr[4] = e;
	}
}