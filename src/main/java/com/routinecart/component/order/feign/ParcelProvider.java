package com.routinecart.component.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("component-parcel")
public interface ParcelProvider {

    @GetMapping(value = "/rest/v1/parcels/modes")
    String getMode();
}
