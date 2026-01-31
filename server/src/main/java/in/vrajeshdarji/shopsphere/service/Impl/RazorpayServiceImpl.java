package in.vrajeshdarji.shopsphere.service.Impl;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import in.vrajeshdarji.shopsphere.io.RazorpayOrderResponse;
import in.vrajeshdarji.shopsphere.service.RazorpayService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RazorpayServiceImpl implements RazorpayService {

    @Value("${razorpay.key.id")
    private String razorpayId;
    @Value("${razorpay.key.secret")
    private String razorpayKeySecret;

    @Override
    public RazorpayOrderResponse createOrder(Double amount, String currency) throws RazorpayException {
        RazorpayClient razorpayClient= new RazorpayClient(razorpayId, razorpayKeySecret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount*100);
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", "order_rcptid_"+System.currentTimeMillis());
        orderRequest.put("payment_capture", 1);

        Order order = razorpayClient.orders.create(orderRequest);
        return convertToResponse(order);
    }

    private RazorpayOrderResponse convertToResponse(Order order) {
        return RazorpayOrderResponse.builder()
                .id(order.get("id"))
                .entity(order.get("entity"))
                .amount(order.get("amount"))
                .currency(order.get("currency"))
                .status(order.get("status"))
                .createdAT(order.get("createdAt"))
                .receipt(order.get("receipt"))
                .build();
    }
}
