package in.vrajeshdarji.shopsphere.io;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.PublicKey;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class PaymentDetails {

    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private PaymentStatus status;

    public enum  PaymentStatus{
        PENDING, COMPLETED, FAILED

    }

}
