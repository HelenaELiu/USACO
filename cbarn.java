import java.io.*;
import java.util.Stack;

public class cbarn {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int n = Integer.parseInt(f.readLine());
		int[] rooms = new int[n];
		for (int i = 0; i < n; i++) 
			rooms[i] = Integer.parseInt(f.readLine());
		
		int[] prefix = new int[n]; //counts cows from back
		prefix[n - 1] = rooms[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			prefix[i] = prefix[i + 1] + rooms[i];
		}
		
		int idx = 0; //find starting room
		int min = Integer.MAX_VALUE;
		for (int i = n - 1; i >= 0; i--) {
			if (n - i - prefix[i] < min) {
				min = n - i - prefix[i];
				idx = i;
			}
		}
		
		int[] rooms2 = new int[n];
		for (int i = idx; i < n; i++)
			rooms2[i - idx] = rooms[i];
		for (int i = 0; i < idx; i++)
			rooms2[n - idx + i] = rooms[i];
		
		//for (int i: rooms2)
		//	System.out.println(i);
		//System.out.println(); 
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < rooms2[i]; j++) {
				stack.push(i);
			}
		}
		
		long ans = 0;
		
		for (int i = n - 1; i >= 0; i--) {
			int num = stack.pop();
			ans += (long) (i - num) * (long) (i - num);
		}
		
		System.out.println(ans);
		out.println(ans);
		
		out.close();
	}
}