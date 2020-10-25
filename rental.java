
/*
ID: helena.6
LANG: JAVA
TASK: rental
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class rental {
	static int n, m, r;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("rental.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		int[] milk = new int[n];
		for (int i = 0; i < n; i++)
			milk[i] = Integer.parseInt(f.readLine());
		Arrays.sort(milk);

		int[] milkPrefix = new int[n];
		milkPrefix[0] = milk[n - 1];
		for (int i = 1; i < n; i++) // get the prefix sums
			milkPrefix[i] = milkPrefix[i - 1] + milk[n - i - 1];

		Sale[] sales = new Sale[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(f.readLine());
			sales[i] = new Sale(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(sales, new Comparator<Sale>() {
			@Override
			public int compare(Sale x, Sale y) { // highest price goes first
				return y.getPrice() - x.getPrice();
			}
		});

		int[] rent = new int[r];
		for (int i = 0; i < r; i++)
			rent[i] = Integer.parseInt(f.readLine());
		Arrays.sort(rent);

		int[] rentPrefix = new int[r];
		rentPrefix[0] = rent[r - 1];
		for (int i = 1; i < r; i++) // get the prefix sums
			rentPrefix[i] = rentPrefix[i - 1] + rent[r - i - 1];

		long maxProfit = 0;

		for (int i = r; i >= 0; i--) { // i is how many are rented
			int salesIndex = 0;
			long profit = 0;
			if (i > 0)
				profit = rentPrefix[i - 1];

			if (n - i - 2 >= 0) {
				int gallons = milkPrefix[n - i - 2]; // sell the rest
				while (gallons != 0) {
					if (salesIndex >= m) {
						break;
					}
					if (gallons <= sales[salesIndex].getGallons()) {
						profit += (gallons * sales[salesIndex].getPrice());
						
						gallons = 0;
					} else {
						profit += (sales[salesIndex].getGallons() * sales[salesIndex].getPrice());
						
						gallons -= sales[salesIndex].getGallons();
						salesIndex++;
					}
				}
			}
			
			maxProfit = Math.max(maxProfit, profit);
		}

		out.println(maxProfit);
		out.close();
	}
}

class Sale {
	private int gallons;
	private int price;

	public Sale(int gallons, int price) {
		this.gallons = gallons;
		this.price = price;
	}

	public int getGallons() {
		return gallons;
	}

	public int getPrice() {
		return price;
	}
}