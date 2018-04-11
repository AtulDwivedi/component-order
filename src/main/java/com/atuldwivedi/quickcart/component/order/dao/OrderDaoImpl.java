package com.atuldwivedi.quickcart.component.order.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atuldwivedi.quickcart.component.order.domain.Order;
import com.atuldwivedi.quickcart.component.order.mock.OrderData;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private OrderData orderData;

	@Override
	public List<Order> getOrders() {
		return orderData.getOrders();
	}

	@Override
	public Order getOrder(String orderId) {
		return orderData.getOrder(orderId);
	}

	@Override
	public Order placeOrder(Order order) {
		return orderData.placeOrder(order);
	}

	@Override
	public List<Order> deleteOrder(String orderId) {
		return orderData.deleteOrder(orderId);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderData.updateOrder(order);
	}

}
