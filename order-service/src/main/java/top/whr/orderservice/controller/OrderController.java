package top.whr.orderservice.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/order")
    public String createOrder(@RequestParam String username, String productId, String orderId) {
        String userServiceUrl = "http://localhost:8081/user?username=" + username;
        String userInfo = restTemplate.getForObject(userServiceUrl, String.class);
        String productServiceUrl = "http://localhost:8083/product?productId=" + productId;
        String productInfo = restTemplate.getForObject(productServiceUrl, String.class);
        return "订单ID" + orderId + ", " + "下单人:" + userInfo + ", " + "商品信息:" + productInfo;
    }
}
