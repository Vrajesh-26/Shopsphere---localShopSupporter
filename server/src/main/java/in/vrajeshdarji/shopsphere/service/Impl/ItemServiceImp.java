package in.vrajeshdarji.shopsphere.service.Impl;

import in.vrajeshdarji.shopsphere.entity.CategoryEntity;
import in.vrajeshdarji.shopsphere.entity.ItemEntity;
import in.vrajeshdarji.shopsphere.io.ItemRequest;
import in.vrajeshdarji.shopsphere.io.ItemResponse;
import in.vrajeshdarji.shopsphere.repository.CategoryRepository;
import in.vrajeshdarji.shopsphere.repository.ItemRepository;
import in.vrajeshdarji.shopsphere.service.FileUploadService;
import in.vrajeshdarji.shopsphere.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {

    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;


    //Add Items into specific category

    @Override
    public ItemResponse add(ItemRequest request, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
       ItemEntity newItem = convertToEntity(request);
        CategoryEntity existingCategory = categoryRepository.findByCategoryId(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + request.getCategoryId()));
        newItem.setCategory(existingCategory);
        newItem.setImageUrl(imgUrl);
        newItem = itemRepository.save(newItem);
        return convertToResponse(newItem);
    }

    private ItemResponse convertToResponse(ItemEntity newItem) {
        return ItemResponse.builder()
                .itemId(newItem.getItemId())
                .name(newItem.getName())
                .description(newItem.getDescription())
                .price(newItem.getPrice())
                .imgUrl(newItem.getImageUrl())
                .categoryName(newItem.getCategory().getName())
                .categoryId(newItem.getCategory().getCategoryId())
                .createdAt(newItem.getCreatedAt())
                .updatedAt(newItem.getUpdatedAt())
                .build();
    }

    private ItemEntity convertToEntity(ItemRequest request) {
        return ItemEntity.builder()
                .itemId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    @Override
    public List<ItemResponse> fetchItems() {
        return itemRepository.findAll()
                .stream()
                .map(itemEntity -> convertToResponse(itemEntity))
                .collect(Collectors.toList());
    }

    //We also delete on cloud storage

    @Override
    public void deleteItem(String itemId) {
        ItemEntity existingItem = itemRepository.findByItemId(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found: "+itemId));
        boolean isFileDeleted = fileUploadService.deleteFile(existingItem.getImageUrl());

        if (isFileDeleted) {
            itemRepository.delete(existingItem);
        }else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete the image");
        }
    }
}
