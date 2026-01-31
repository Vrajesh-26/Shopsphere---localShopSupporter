package in.vrajeshdarji.shopsphere.repository;

import in.vrajeshdarji.shopsphere.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemEntityRepository extends JpaRepository<OrderItemEntity, Long> {
}
