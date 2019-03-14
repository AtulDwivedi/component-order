package com.routinecart.component.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

	private String orderId;

	private String customerId;

	private Integer totalNumberOfItems;

	private Double totalBill;
}
