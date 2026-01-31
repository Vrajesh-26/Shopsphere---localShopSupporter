package in.vrajeshdarji.shopsphere.service;

import com.razorpay.RazorpayException;
import in.vrajeshdarji.shopsphere.io.RazorpayOrderResponse;

public interface RazorpayService {
     RazorpayOrderResponse createOrder(Double amount, String currency) throws RazorpayException;
}
