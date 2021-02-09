package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders") // 이름을 지정해주는 이유는 order가 되면 order by ~~ 이런 함수가 되므로
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id") // 테이블명_id로 맞춰준다.
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="member_id") // FK 가 member_id. 연관관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)  // orderItems 를 다같이 저장하고 다 같이 지움. 각자 해주는게 아님.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    //order_date 로 컬럼명 변경됨
    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
