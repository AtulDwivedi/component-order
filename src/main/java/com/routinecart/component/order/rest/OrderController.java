package com.routinecart.component.order.rest;

import com.routinecart.component.order.domain.Order;
import com.routinecart.component.order.feign.ParcelProvider;
import com.routinecart.component.order.feign.PaymentProvider;
import com.routinecart.component.order.mock.OrderData;
import com.routinecart.component.order.service.OrderOptionService;
import com.routinecart.component.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentProvider paymentProvider;

    @Autowired
    private ParcelProvider parcelProvider;

    @Autowired
    private OrderData orderData;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderOptionService orderOptionService;

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


    @GetMapping("/options")
    public @ResponseBody
    String getOrderOptions() {
        return orderOptionService.getPrcelOption() + " " + orderOptionService.getPaymentOption();
    }

    @GetMapping("/options/thru/feign")
    public @ResponseBody
    String getOrderOptionsThruFeign() {
        return
                parcelProvider.getMode() + " "
                        + paymentProvider.getAvailablePaymentOptions() + " ";
    }

    @GetMapping("/options/thru/restTemplate")
    public @ResponseBody
    String getOrderOptionsThruRestTemplate() {
        return
                getWordThruRestTemplate("component-parcel", "/rest/v1/parcels/modes") + " "
                        + getWordThruRestTemplate("component-payment", "/rest/v1/payments/modes") + " ";
    }

    public String getWordThruRestTemplate(String service, String path) {
        return restTemplate.getForObject("http://" + service + path, String.class);
    }

    @GetMapping("/options/thru/discoveryClient")
    public @ResponseBody
    String getOrderOptionsThruDiscoveryClient() {
        return
                getWordThruDiscoveryClient("component-parcel", "/rest/v1/parcels/modes") + " "
                        + getWordThruDiscoveryClient("component-payment", "/rest/v1/payments/modes") + " ";
    }

    public String getWordThruDiscoveryClient(String service, String path) {
        List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0) {
            URI uri = list.get(0).getUri();
            if (uri != null) {
                String completePath = "http://" + uri.getHost() + ":" + uri.getPort() + path;
                return (new RestTemplate()).getForObject(completePath, String.class);
            }
        }
        return null;
    }

}
