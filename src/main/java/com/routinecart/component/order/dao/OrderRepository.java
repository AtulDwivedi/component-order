package com.routinecart.component.order.dao;

import com.routinecart.component.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderDetail, UUID> {

    List<OrderDetail> findByCustomerId(String customerId);

}
