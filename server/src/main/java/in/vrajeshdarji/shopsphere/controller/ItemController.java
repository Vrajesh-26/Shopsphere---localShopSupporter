package in.vrajeshdarji.shopsphere.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.vrajeshdarji.shopsphere.io.ItemRequest;
import in.vrajeshdarji.shopsphere.io.ItemResponse;
import in.vrajeshdarji.shopsphere.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    //API for add items
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/items")
    public ItemResponse addItem(@RequestPart("item")String itemString,
                                @RequestPart("file")MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper(); //Parsing into JSON to object
        ItemRequest itemRequest = null;

        try{
            itemRequest = objectMapper.readValue(itemString, ItemRequest.class);
            return itemService.add(itemRequest, file);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error occured while processing to json");
        }
    }


    //API for to fetch items
    //This api open for user also because item's in explore part can add or checkout
    @GetMapping("/items")
    public List<ItemResponse> readItems(){
        return itemService.fetchItems();
    }


    //API for remove items and only access by admin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/admin/items/{itemId}")
    public void removeItem(@PathVariable String itemId){
        try{
            itemService.deleteItem(itemId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
    }
}

















