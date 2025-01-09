package team11.team11project.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE menu SET is_deleted = true WHERE menu_id = ?")
public class Menu extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Setter
    private String name;
    @Setter
    private Integer price;
    @Setter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


    private boolean is_deleted = Boolean.FALSE;


    public Menu(String name, Integer price, String description, Store store) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.store = store;
    }

}
