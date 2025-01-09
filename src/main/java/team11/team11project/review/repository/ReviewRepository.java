package team11.team11project.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team11.team11project.common.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review R WHERE r.orders.store.id = :storeId")
    List<Review> findAllByStoreId(@Param("storeId") Long storeId);


}
