package com.atuldwivedi.quickcart.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atuldwivedi.quickcart.order.dao.OrderDao;
import com.atuldwivedi.quickcart.order.domain.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}

	@Override
	public Order getOrder(String orderId) {
		return orderDao.getOrder(orderId);
	}

	@Override
	public Order placeOrder(Order order) {
		return orderDao.placeOrder(order);
	}

	@Override
	public List<Order> deleteOrder(String orderId) {
		return orderDao.deleteOrder(orderId);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderDao.updateOrder(order);
	}

}
