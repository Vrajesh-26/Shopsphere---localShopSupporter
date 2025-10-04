package in.vrajeshdarji.shopsphere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String itemId;

    private String name;

    private BigDecimal price;

    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private String imageUrl;

    @ManyToOne                                          // set association property of database
    @JoinColumn(name = "category_id", nullable = false) // set foreign key for item table tell add
                                                        // column name with category_id & it should be not null
    @OnDelete(action = OnDeleteAction.RESTRICT)         // rule for database can't delete category while it has items
    private CategoryEntity category;
}
