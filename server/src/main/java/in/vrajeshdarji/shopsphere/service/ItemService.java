package in.vrajeshdarji.shopsphere.service;

import in.vrajeshdarji.shopsphere.io.ItemRequest;
import in.vrajeshdarji.shopsphere.io.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {

    ItemResponse add(ItemRequest request, MultipartFile file);

    List<ItemResponse> fetchItems();

    void deleteItem(String itemId);
}
