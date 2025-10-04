package in.vrajeshdarji.shopsphere.service;

import in.vrajeshdarji.shopsphere.io.CategoryRequest;
import in.vrajeshdarji.shopsphere.io.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse add(CategoryRequest request, MultipartFile file);

    List<CategoryResponse> read();

    void delete(String categoryId);
}
