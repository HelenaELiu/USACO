import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class feast {
	static int max, t, a, b;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		t = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		max = 0;
		
		dfs();
		
		out.println(max);
		out.close();
	}
	
	private static void dfs() {
		Stack<Fullness> stack = new Stack<Fullness>();
		stack.add(new Fullness(0, false));
		boolean[] visited = new boolean[t + 1];
		
		while (!stack.isEmpty()) {
			Fullness fl = stack.pop();
			if (fl.full <= t && !visited[fl.full]) {
				visited[fl.full] = true;
				max = Math.max(fl.full, max);
				
				if (!fl.water) {
					Fullness fl1 = new Fullness(fl.full / 2, true);
					stack.push(fl1);
				}
				
				Fullness fl2 = new Fullness(fl.full + a, fl.water);
				stack.push(fl2);
				
				Fullness fl3 = new Fullness(fl.full + b, fl.water);
				stack.push(fl3);
			}
		}
	}
}

class Fullness {
	int full;
	boolean water;
	
	public Fullness(int x, boolean w) {
		full = x;
		water = w;
	}
}