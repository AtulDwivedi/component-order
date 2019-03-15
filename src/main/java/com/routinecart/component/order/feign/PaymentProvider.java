package com.routinecart.component.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("component-payment")
public interface PaymentProvider {

    @GetMapping(value = "/rest/v1/payments/modes")
    String getAvailablePaymentOptions();
}
