import java.io.*;

public class circlecross {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		int n = Integer.parseInt(f.readLine());
		Line[] lines = new Line[n];
		for (int i = 0; i < n; i++)
			lines[i] = new Line();
		
		for (int i = 0; i < 2 * n; i++) {
			int x = Integer.parseInt(f.readLine()) - 1;
			if (lines[x].min == -1)
				lines[x].min = i;
			else
				lines[x].max = i;
		}
		
		//for (Line l: lines)
		//	System.out.println(l.min + " " + l.max);
		
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((lines[j].min < lines[i].min && lines[j].max < lines[i].max && lines[j].max > lines[i].min) ||
						(lines[j].min > lines[i].min && lines[j].min < lines[i].max && lines[j].max > lines[i].max)) {
					//System.out.println(i + " " + lines[i].min + " " + lines[i].max);
					//System.out.println(j + " " + lines[j].min + " " + lines[j].max);
					//System.out.println();
					ans++;
				}
			}
		}
		
		System.out.println(ans);
		out.println(ans);
		
		out.close();
	}
}

class Line {
	int min, max; //indexes 
	
	public Line() {
		this.min = -1;
		this.max = -1;
	}
	
	public Line(int min, int max) {
		this.min = min;
		this.max = max;
	}	
}