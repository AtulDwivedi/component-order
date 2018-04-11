package com.atuldwivedi.quickcart.component.order.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.atuldwivedi.quickcart.component.order.domain.Order;

@Component
public class OrderData {

	private List<Order> orders;

	public void createOrders() {
		orders = new ArrayList<Order>();
		orders.add(new Order("ORD1001", "CUST1001", new Integer(4), new Double(500.7)));
		orders.add(new Order("ORD1002", "CUST1002", new Integer(8), new Double(1000.90)));
		orders.add(new Order("ORD1003", "CUST1003", new Integer(12), new Double(1500.8)));
		orders.add(new Order("ORD1004", "CUST1004", new Integer(16), new Double(2000.332)));
		orders.add(new Order("ORD1005", "CUST1005", new Integer(20), new Double(2500.17)));
	}

	public List<Order> getOrders() {
		return orders;
	}

	public Order getOrder(String orderId) {
		return orders.stream().filter(order -> order.getOrderId().equals(orderId)).findFirst().orElse(null);
	}

	public Order placeOrder(Order order) {
		if (null != order) {
			orders.add(order);
		}
		return order;
	}

	public List<Order> deleteOrder(String orderId) {
		if (null != orderId) {
			Order ord = orders.stream().filter(order -> order.getOrderId().equals(orderId)).findFirst().orElse(null);
			orders.remove(ord);
		}
			return orders;
	}

	public Order updateOrder(Order order) {
		if(null != order && null != order.getOrderId()) {
			Order ord = orders.stream().filter(o -> o.getOrderId().equals(order.getOrderId())).findFirst().orElse(null);
			ord.setCustomerId(order.getCustomerId());
			ord.setTotalBill(order.getTotalBill());
			ord.setTotalNumberOfItems(order.getTotalNumberOfItems());
		}
		return order;
	}
}