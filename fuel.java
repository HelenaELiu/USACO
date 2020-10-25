import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class fuel {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fuel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fuel.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		Station[] gas = new Station[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			int xi = Integer.parseInt(st.nextToken());
			int yi = Integer.parseInt(st.nextToken());
			gas[i] = new Station(xi, yi);
		}
		Arrays.sort(gas);
		
		for (int i = 0; i < n; i++)
			gas[i].idx = i;
		
		//for (Station s: gas)
		//	System.out.println(s.idx + " " + s.x + " " + s.cost);
		
		Stack<Station> stack = new Stack<Station>();
		int[] pointers = new int[n]; //contains pointers using indexes
		
		for (int i = n - 1; i >= 0; i--) {
			Station s = gas[i];
			if (stack.isEmpty()) {
				stack.push(s);
				pointers[i] = -1;
			} else {
				while (!stack.isEmpty() && stack.peek().cost >= s.cost)
					stack.pop();
				if (stack.isEmpty())
					pointers[i] = -1;
				else
					pointers[i] = stack.peek().idx;
				stack.push(s);
			}
		}
		
		//for (int i: pointers)
		//	System.out.println(i);
		
		long ans = 0;
		int fuel = b;
		int pos = 0;
		
		if (d - gas[n - 1].x > g) {
			ans = -1;
		} else {
			for (int i = 0; i < n; i++) { //visit every gas station
				Station s = gas[i];
				fuel -= (s.x - pos);
				pos = s.x;
				
				if (i < n - 1 && gas[i + 1].x - pos > g) {
					ans = -1;
					break;
				}
				
				int gasToBuy = Math.min(g, (pointers[i] == -1 ? d : gas[pointers[i]].x) - pos);
				if (gasToBuy > fuel) {
					ans += ((long) (gasToBuy - fuel)) * ((long) gas[i].cost);
					fuel = gasToBuy;
				}
	
			}
		}
		
		out.println(ans);
		out.close(); 
	}
}

class Station implements Comparable<Station> {
	int idx, x, cost;
	
	public Station(int x, int cost) {
		this.x = x;
		this.cost = cost;
	}

	@Override
	public int compareTo(Station other) {
		return this.x - other.x;
	}
}