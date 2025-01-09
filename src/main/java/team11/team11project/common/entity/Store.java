package team11.team11project.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import team11.team11project.store.model.request.CreateStoreRequest;

import java.time.LocalTime;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE store SET is_deleted = true WHERE store_id = ?")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    private int minOrderPrice;
    private LocalTime openTime;
    private LocalTime closeTime;

    private boolean is_deleted = Boolean.FALSE;

    public static Store createStore(CreateStoreRequest request, Member owner) {
        return new Store(
                null,
                request.getStoreName(),
                owner,
                request.getMinOrderPrice(),
                request.getOpenTime(),
                request.getCloseTime(),
                false
        );
    }
}
