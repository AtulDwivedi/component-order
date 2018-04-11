package com.atuldwivedi.quickcart.component.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Order {

	private String orderId;

	private String customerId;

	private Integer totalNumberOfItems;

	private Double totalBill;
}
