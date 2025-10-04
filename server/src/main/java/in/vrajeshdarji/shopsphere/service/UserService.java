package in.vrajeshdarji.shopsphere.service;

import in.vrajeshdarji.shopsphere.io.UserRequest;
import in.vrajeshdarji.shopsphere.io.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUser();

    void deleteUser(String id);
}
