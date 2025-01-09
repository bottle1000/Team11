package team11.team11project.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team11.team11project.common.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("select count(s) from Store s where s.owner.memberName = :memberName")
    int findStoresByMemberName(String memberName);
}
