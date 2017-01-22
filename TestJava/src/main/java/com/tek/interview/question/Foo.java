package com.tek.interview.question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* ****************************************************************************************
 
Please remove all bugs from the code below to get the following output:

<pre>

*******Order 1*******
1 book: 13.74
1 music CD: 16.49
1 chocolate bar: 0.94
Sales Tax: 2.84
Total: 28.33
*******Order 2*******
1 imported box of chocolate: 11.5
1 imported bottle of perfume: 54.62
Sales Tax: 8.62
Total: 57.5
*******Order 3*******
1 Imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 10.73
1 box of imported chocolates: 12.94
Sales Tax: 8.77
Total: 67.98
Sum of orders: 153.81
 
</pre>
 
******************************************************************************************** */

class Order {

	private List<OrderLine> orderLines = new ArrayList<OrderLine>();

	public void add(OrderLine o) throws Exception {
		if (o == null) {
			System.err.println("ERROR - Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}
		orderLines.add(o);
	}

	public int size() {
		return orderLines.size();
	}

	public OrderLine get(int i) {
		return orderLines.get(i);
	}

	public void clear() {
		this.orderLines.clear();
	}
}

class calculator {

	public static double rounding(double value) {
		return ((double) (value * 100.0)) / 100.0;
	}

	double grandtotal = 0;

	/**
	 * receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's price * quantity
	 * * taxes.
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order
	 */
	public void calculate(Map<String, Order> o) {

		// Iterate through the orders
		for (Map.Entry<String, Order> entry : o.entrySet()) {
			System.out.println("*******" + entry.getKey() + "*******");

			Order r = entry.getValue();

			double totalTax = 0;
			double total = 0;
			// Iterate through the items in the order
			for (int i = 0; i < r.size(); i++) {

				// Calculate the taxes
				double tax = 0;

				if (r.get(i).getItem().getDescription().contains("imported")
						|| r.get(i).getItem().getDescription().contains("Imported")) {
					tax = rounding(r.get(i).getItem().getPrice() * 0.15); // Extra
																			// 5%
																			// tax
																			// on
					// imported items
				} else {
					tax = rounding(r.get(i).getItem().getPrice() * 0.10);
				}

				// Calculate the total price
				double totalprice = Math.round((r.get(i).getItem().getPrice() + tax) * 100.0) / 100.0;

				// Print out the item's total price
				System.out.println(
						r.get(i).getQuantity() + " " + r.get(i).getItem().getDescription() + ": " + totalprice);

				// Keep a running total
				totalTax += Math.round(tax * 100.0) / 100.0;
				total += totalprice;

			}

			// Print out the total taxes
			System.out.println("Sales Tax: " + Math.round(totalTax * 100.0) / 100.0);

			total -= totalTax;
			// Print out the total amount
			System.out.println("Total: " + Math.floor(total * 100) / 100);
			grandtotal += total;
		}

		System.out.println("Sum of orders: " + Math.floor(grandtotal * 100) / 100);
	}
}

public class Foo {

	public static void main(String[] args) throws Exception {

		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order c = new Order();

		c.add(new OrderLine(new Item("book", (float) 12.49), 1));
		c.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		c.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		o.put("Order 1", c);

		// Reuse cart for an other order
		// c.clear();
		c = new Order();
		c.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.499), 1));

		o.put("Order 2", c);

		// Reuse cart for an other order
		// c.clear();
		c = new Order();

		c.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		c.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		c.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		c.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));

		o.put("Order 3", c);

		new calculator().calculate(o);

	}
}
