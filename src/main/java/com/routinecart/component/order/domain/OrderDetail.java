package com.routinecart.component.order.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "rc_order")
@Data
@NoArgsConstructor(force = true)
public class OrderDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "total_number_of_items")
    private Integer totalNumberOfItems;

    @Column(name = "total")
    private BigDecimal total;
}
