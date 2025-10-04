//package in.vrajeshdarji.shopsphere.service.Impl;
//
//import in.vrajeshdarji.shopsphere.entity.CategoryEntity;
//import in.vrajeshdarji.shopsphere.entity.ItemEntity;
//import in.vrajeshdarji.shopsphere.io.ItemRequest;
//import in.vrajeshdarji.shopsphere.io.ItemResponse;
//import in.vrajeshdarji.shopsphere.repository.CategoryRepository;
//import in.vrajeshdarji.shopsphere.repository.ItemRepository;
//import in.vrajeshdarji.shopsphere.service.FileUploadService;
//import in.vrajeshdarji.shopsphere.service.ItemService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ItemServiceImp implements ItemService {
//
//    private final FileUploadService fileUploadService;
//    private final CategoryRepository categoryRepository;
//    private final ItemRepository itemRepository;
//
////
////    @Override
////    public ItemResponse add(ItemRequest request, MultipartFile file) {
////        String imgUrl = fileUploadService.uploadFile(file);
////       // ItemEntity newItem = convertToEntity(request);
////        CategoryEntity existingCategory = categoryRepository.findByCategoryId(request.getCategoryId())
////                .orElseThrow(() -> new RuntimeException("Category not found: " + request.getCategoryId()));
////        newItem.setCategory(existingCategory);
////        newItem.setImageUrl(imgUrl);
////        newItem = itemRepository.save(newItem);
////        //return convertToResponse(newItem);
////    }
//
////    private ItemResponse convertToResponse(ItemEntity newItem) {
////
////    }
////
////    private ItemEntity convertToEntity(ItemRequest request) {
////    }
//
//    @Override
//    public List<ItemResponse> fetchItems() {
//        return List.of();
//    }
//
//    @Override
//    public void deleteItem(String itemId) {
//
//    }
//}
