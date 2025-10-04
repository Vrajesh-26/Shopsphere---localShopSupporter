package in.vrajeshdarji.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vrajeshdarji.shopsphere.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByCategoryId(String categoryId);
}
