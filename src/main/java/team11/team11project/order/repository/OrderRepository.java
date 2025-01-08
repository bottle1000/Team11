package team11.team11project.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team11.team11project.common.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
