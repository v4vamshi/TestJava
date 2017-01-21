package com.tek.interview.question;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * 
 * @author vamshi
 *
 */
public class FooTest extends TestCase {
	calculator cal = new calculator();

	// test method calculate
	@Test
	public void testCalculate() throws Exception {

		Map<String, Order> o = new LinkedHashMap<String, Order>();

		Order c = new Order();

		c.add(new OrderLine(new Item("book", (float) 12.49), 1));
		c.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		c.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));
		assertEquals(3, c.size());

		o.put("Order 1", c);

		// Reuse cart for an other order
		c = new Order();
		assertEquals(1, o.size());

		c.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		c.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.499), 1));
		assertEquals(2, c.size());

		o.put("Order 2", c);

		// Reuse cart for an other order
		c = new Order();
		assertEquals(2, o.size());

		c.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		c.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		c.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		c.add(new OrderLine(new Item("box of imported chocolates", (float) 11.25), 1));

		o.put("Order 3", c);
		assertEquals(4, c.size());
		assertEquals(3, o.size());
		
		cal.calculate(o);
		assertEquals(153.81, cal.grandtotal);

	}
}