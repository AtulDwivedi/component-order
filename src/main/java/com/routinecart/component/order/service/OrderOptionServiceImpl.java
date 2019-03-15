package com.routinecart.component.order.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.routinecart.component.order.feign.ParcelProvider;
import com.routinecart.component.order.feign.PaymentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderOptionServiceImpl implements OrderOptionService {

    @Autowired
    private ParcelProvider parcelProvider;

    @Autowired
    private PaymentProvider paymentProvider;


    public String getDefaultPaymentMode() {
        return "UPI";
    }

    public String getDefaultParcelMode() {
        return "AIR";
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultParcelMode")
    public String getPrcelOption() {
        return parcelProvider.getMode();
    }

    @Override
    @HystrixCommand(fallbackMethod = "getDefaultPaymentMode")
    public String getPaymentOption() {
        return paymentProvider.getAvailablePaymentOptions();
    }
}
