package com.atuldwivedi.quickcart.component.order.rest;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atuldwivedi.quickcart.component.order.domain.Order;
import com.atuldwivedi.quickcart.component.order.mock.OrderData;
import com.atuldwivedi.quickcart.component.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderData orderData;

	@Autowired
	private OrderService orderService;

	@PostConstruct
	public void init() {
		orderData.createOrders();
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> orders = orderService.getOrders();
		MultiValueMap<String, String> headers = new HttpHeaders(); 
		headers.put("message", Arrays.asList("Success"));
		return new ResponseEntity(orders, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable("orderId") String orderId) {
		return orderService.getOrder(orderId);
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Order placeOrder(@RequestBody Order order) {
		return orderService.placeOrder(order);
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
	public List<Order> deleteOrder(@PathVariable String orderId) {
		return orderService.deleteOrder(orderId);
	}

	@RequestMapping(value = "/order", method = RequestMethod.PUT)
	public Order updateOrder(@RequestBody Order order) {
		return orderService.updateOrder(order);
	}

}
