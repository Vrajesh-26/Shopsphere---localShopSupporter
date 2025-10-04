package in.vrajeshdarji.shopsphere.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String role;
    private String email;
    private String token;
}
