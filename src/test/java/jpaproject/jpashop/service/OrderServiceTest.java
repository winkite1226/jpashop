package jpaproject.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaproject.jpashop.domain.Address;
import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.domain.Order;
import jpaproject.jpashop.domain.OrderStatus;
import jpaproject.jpashop.domain.item.Book;
import jpaproject.jpashop.domain.item.Item;
import jpaproject.jpashop.exception.NotEnoughStockException;
import jpaproject.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void order_test() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("JPA application", 10000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다." );
        assertEquals(10000*2, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이어야 한다..");
        assertEquals(8, item.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
    }

    //상품 주문시 재고 수량 초과
    @Test
    public void order_NotEnoughStock_test() {
        //given
        Member member = createMember();
        Item item = createBook("JPA application", 10000, 10);

        int orderCount = 11; //주문 수량

        //when, then
        assertThrows(
                NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), orderCount));
    }

    @Test
    public void cancelOrder_test() {
        //given
        Member member = createMember();
        Item item = createBook("learning JPA", 20000, 20);
        int orderCount = 8;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(20, item.getStockQuantity());
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("kim");
        member.setAddress(new Address("seoul", "gangnam", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }
}