package team11.team11project.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team11.team11project.common.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
