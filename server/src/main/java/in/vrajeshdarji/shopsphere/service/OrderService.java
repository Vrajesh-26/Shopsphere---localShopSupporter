package in.vrajeshdarji.shopsphere.service;

import in.vrajeshdarji.shopsphere.io.OrderRequest;
import in.vrajeshdarji.shopsphere.io.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> getLatestOrders();
}
