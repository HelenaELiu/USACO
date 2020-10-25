import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class art2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
		int n = Integer.parseInt(f.readLine());
		
		int[] min = new int[n + 2];
		int[] max = new int[n + 2];
		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(max, -Integer.MAX_VALUE);
		int[] colors = new int[n + 2];

		for (int i = 0; i <= n + 1; i++) {
			if (i >= 1 && i <= n) {
				colors[i] = Integer.parseInt(f.readLine());
			}
			
			min[colors[i]] = Math.min(min[colors[i]], i);
			max[colors[i]] = Math.max(max[colors[i]], i);
		}

		Stack<Integer> stack = new Stack<Integer>();
		int ans = 0;

		for (int i = 0; i <= n + 1; i++) {
			int color = colors[i];
 
			if (i == min[color]) {
				stack.push(color);
				ans = Math.max(ans, stack.size());
			}

			if (stack.peek() != color) {
				ans = 0;
				break;
			}

			if (i == max[color])
				stack.pop();
		}
		
		System.out.println(ans - 1); //0 - 1 gives -1
		out.println(ans - 1);
		
		out.close();
	}
}