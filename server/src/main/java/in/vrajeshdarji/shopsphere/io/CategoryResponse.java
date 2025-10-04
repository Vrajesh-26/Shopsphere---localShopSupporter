package in.vrajeshdarji.shopsphere.io;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryResponse {
    private String categoryId;
    private String name;
    private String description;
    private String bgColor;
    private String imgUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
