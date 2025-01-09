package team11.team11project.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team11.team11project.common.enums.OrderStatus;

@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Member customer; // 주문한 고객

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu; // 주문한 메뉴

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문 상태

    private int quantity; // 주문 수량

    //TODO : 총 주문 금액을 어떻게 할 것인가. Order or Menu

    public Orders(Member customer, Menu menu,int quantity) {
        this.customer = customer;
        this.menu = menu;
        this.orderStatus = OrderStatus.REQUESTED;
        this.quantity = quantity;
    }

    public void UpdateOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
