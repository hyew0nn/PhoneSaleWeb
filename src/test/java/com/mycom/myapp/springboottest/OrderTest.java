package com.mycom.myapp.springboottest;

import com.mycom.myapp.dto.InsertOrderItemRequest;
import com.mycom.myapp.dto.InsertOrderRequest;
import com.mycom.myapp.dto.InsertOrderResponse;
import com.mycom.myapp.exception.ProductException;
import com.mycom.myapp.exception.UserException;
import com.mycom.myapp.repository.OrderItemRepository;
import com.mycom.myapp.repository.OrderRepository;
import com.mycom.myapp.repository.ProductRepository;
import com.mycom.myapp.repository.UserRepository;
import com.mycom.myapp.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("testInsertOrder")
    @Transactional
    public void testInsertOrder() {

        // given
        int customerId = 1; // 실제 DB에 존재하는 사용자 ID
        int productId1 = 1; // 실제 DB에 존재하는 상품 ID
        int productId2 = 2; // 실제 DB에 존재하는 상품 ID

        InsertOrderRequest request = new InsertOrderRequest();
        request.setCustomerId(customerId);
        request.setTotalPrice(new BigDecimal("3000000.00"));
        request.setRecipientName("주문 테스트");
        request.setRecipientPhone("010-9999-8888");
        request.setShippingAddress("고구마시 감자구");

        InsertOrderItemRequest item1 = new InsertOrderItemRequest();
        item1.setProductId(productId1);
        item1.setQuantity(1);
        item1.setPrice(new BigDecimal("1000000.00"));

        InsertOrderItemRequest item2 = new InsertOrderItemRequest();
        item2.setProductId(productId2);
        item2.setQuantity(2);
        item2.setPrice(new BigDecimal("1500000.00"));

        request.setOrderItems(Arrays.asList(item1, item2));

        // when
        InsertOrderResponse response = orderService.insertOrder(request);

        // then
        assertNotNull(response);
        assertTrue(response.getOrderId() > 0);
        assertEquals(2, response.getOrderItemCount());
        assertEquals("결제완료", response.getStatus());
    }

    @Test
    @DisplayName("testInsertOrderUserNotFound")
    @Transactional
    public void testInsertOrderUserNotFound() {
        // given
        int customerId = 100; // 실제 DB에 존재하는 사용자 ID
        int productId1 = 1; // 실제 DB에 존재하는 상품 ID
        int productId2 = 2; // 실제 DB에 존재하는 상품 ID

        InsertOrderRequest request = new InsertOrderRequest();
        request.setCustomerId(customerId);
        request.setTotalPrice(new BigDecimal("3000000.00"));
        request.setRecipientName("주문 테스트");
        request.setRecipientPhone("010-9999-8888");
        request.setShippingAddress("고구마시 감자구");

        InsertOrderItemRequest item1 = new InsertOrderItemRequest();
        item1.setProductId(productId1);
        item1.setQuantity(1);
        item1.setPrice(new BigDecimal("1000000.00"));

        InsertOrderItemRequest item2 = new InsertOrderItemRequest();
        item2.setProductId(productId2);
        item2.setQuantity(2);
        item2.setPrice(new BigDecimal("1500000.00"));

        request.setOrderItems(Arrays.asList(item1, item2));

        assertThrows(UserException.UserNotFoundException.class, () -> {
            orderService.insertOrder(request);
        });
    }

    @Test
    @DisplayName("testInsertOrderProductNotFound")
    @Transactional
    public void testInsertOrderProductNotFound() {
        // given
        int customerId = 1; // 실제 DB에 존재하는 사용자 ID
        int productId1 = 100; // 실제 DB에 존재하는 상품 ID
        int productId2 = 20; // 실제 DB에 존재하는 상품 ID

        InsertOrderRequest request = new InsertOrderRequest();
        request.setCustomerId(customerId);
        request.setTotalPrice(new BigDecimal("3000000.00"));
        request.setRecipientName("주문 테스트");
        request.setRecipientPhone("010-9999-8888");
        request.setShippingAddress("고구마시 감자구");

        InsertOrderItemRequest item1 = new InsertOrderItemRequest();
        item1.setProductId(productId1);
        item1.setQuantity(1);
        item1.setPrice(new BigDecimal("1000000.00"));

        InsertOrderItemRequest item2 = new InsertOrderItemRequest();
        item2.setProductId(productId2);
        item2.setQuantity(2);
        item2.setPrice(new BigDecimal("1500000.00"));

        request.setOrderItems(Arrays.asList(item1, item2));

        assertThrows(ProductException.ProductNotFoundException.class, () -> {
            orderService.insertOrder(request);
        });
    }
}
