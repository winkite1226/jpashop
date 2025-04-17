package jpaproject.jpashop.domain;

import jakarta.persistence.*;
import jpaproject.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_name")
    private Item item; //주문상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order; //주문

    private int orderPrice; //주문가격
    private int count; //주문수량
}
