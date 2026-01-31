package in.vrajeshdarji.shopsphere.controller;

import com.razorpay.RazorpayException;
import in.vrajeshdarji.shopsphere.io.OrderResponse;
import in.vrajeshdarji.shopsphere.io.PaymentRequest;
import in.vrajeshdarji.shopsphere.io.PaymentVerificationRequest;
import in.vrajeshdarji.shopsphere.io.RazorpayOrderResponse;
import in.vrajeshdarji.shopsphere.service.OrderService;
import in.vrajeshdarji.shopsphere.service.RazorpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final RazorpayService razorpayService;
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public RazorpayOrderResponse createRazorpayOrder(@RequestBody PaymentRequest request) throws RazorpayException{
       return razorpayService.createOrder(request.getAmount(), request.getCurrency());

    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment( @RequestBody PaymentVerificationRequest request){
         return orderService.VerifyPayment(request);

    }
}
