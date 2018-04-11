package com.atuldwivedi.quickcart.component.order.dao;

import java.util.List;

import com.atuldwivedi.quickcart.component.order.domain.Order;

public interface OrderDao {

	public List<Order> getOrders();
	
	public Order getOrder(String orderId);
	
	public Order placeOrder(Order order);
	
	public List<Order> deleteOrder(String orderId);

	public Order updateOrder(Order order);
}
