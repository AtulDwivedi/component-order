package com.atuldwivedi.quickcart.component.order.service;

import java.util.List;

import com.atuldwivedi.quickcart.component.order.domain.Order;

public interface OrderService {
	
	public List<Order> getOrders();

	public Order getOrder(String orderId);

	public Order placeOrder(Order order);

	public List<Order> deleteOrder(String orderId);

	public Order updateOrder(Order order);
}
