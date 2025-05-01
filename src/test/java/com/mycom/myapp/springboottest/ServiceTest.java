package com.mycom.myapp.springboottest;

import com.mycom.myapp.dto.*;
import com.mycom.myapp.exception.ProductException;
import com.mycom.myapp.exception.UserException;
import com.mycom.myapp.service.LoginService;
import com.mycom.myapp.service.ProductService;
import com.mycom.myapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Transactional // 자동 rollback
    public void testRegister(){
        UserDto userDto = UserDto.builder()
                .email("test@test.com")
                .password("1234")
                .name("test")
                .phone("010-0000-0000")
                .address("자바시 스프링구")
                .adminRole(0)
                .build();


        InsertUserResponse insertUserResponse = userService.insertUser(userDto);
        assertEquals("success", insertUserResponse.getMessage());
        assertTrue(insertUserResponse.getUserId() > 0);
    }

//    @Test
//    @Transactional // 자동 rollback
//    public void testRegisterFail() {
//        UserDto userDto = UserDto.builder()
//                .email("test@test.com")
//                .password("1234")
//                .phone("010-0000-0000")
//                .address("자바시 스프링구")
//                .adminRole(0)
//                .build();
//
//        assertThrows(UserException.UserCreationFailedException.class, () -> {
//            userService.insertUser(userDto);
//        });
//    }

    @Test
    @Transactional // 자동 rollback
    public void testRegisterEmailExist() {
        UserDto userDto = UserDto.builder()
                .email("user4@example.com")
                .password("1234")
                .phone("010-0000-0000")
                .address("자바시 스프링구")
                .adminRole(0)
                .build();

        assertThrows(UserException.UserExistException.class, () -> {
            userService.insertUser(userDto);
        });
    }

    @Autowired
    private LoginService loginService;

    @Test
    @Transactional // 자동 rollback
    public void testLogin() {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail("user4@example.com");
        getUserRequest.setPassword("123");

        GetUserResponse getUserResponse = loginService.login(getUserRequest);
        assertEquals(24, getUserResponse.getId());
        assertEquals("hel", getUserResponse.getName());
    }

    @Test
    @Transactional // 자동 rollback
    public void testLoginFail() {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail("user4@example.com");
        getUserRequest.setPassword("111");

        assertThrows(UserException.InvalidPasswordException.class, () -> {
            loginService.login(getUserRequest);
        });
    }

    @Test
    @Transactional // 자동 rollback
    public void testLoginNotFound() {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail("user@example.com");
        getUserRequest.setPassword("111");

        assertThrows(UserException.UserNotFoundException.class, () -> {
            loginService.login(getUserRequest);
        });
    }

    @Autowired
    private ProductService productService;

    @Test
    @Transactional // 자동 rollback
    public void testGetProducts() {
        GetProductsResponse getProductsResponse = productService.findProductAll();
        assertEquals("success", getProductsResponse.getMessage());
        assertEquals(58, getProductsResponse.getCount());
        assertNotNull(getProductsResponse.getProducts());
    }

    @Test
    @Transactional
    public void testGetProduct() {
        int productId = 10;
        ProductDto productDto = productService.findProductById(productId);

        assertNotNull(productDto);
    }

    @Test
    @Transactional
    public void testProductNotFound() {
        int productId = 100;

        assertThrows(ProductException.ProductNotFoundException.class, () -> {
            productService.findProductById(productId);
        });
    }

    @Test
    @Transactional // 자동 rollback
    public void testDecreaseProducts() {

        int productId1 = 1;
        int productId2 = 2;

        InsertOrderItemRequest item1 = new InsertOrderItemRequest();
        item1.setProductId(productId1);
        item1.setQuantity(2);
        item1.setPrice(new BigDecimal("1000000.00"));

        InsertOrderItemRequest item2 = new InsertOrderItemRequest();
        item2.setProductId(productId2);
        item2.setQuantity(2);
        item2.setPrice(new BigDecimal("1500000.00"));

        Boolean bool = productService.decreaseProductStock(Arrays.asList(item1, item2));
        assertTrue(bool);
    }

    @Test
    @Transactional // 자동 rollback
    public void testDecreaseProductsNotFound() {

        int productId1 = 100;
        int productId2 = 2;

        InsertOrderItemRequest item1 = new InsertOrderItemRequest();
        item1.setProductId(productId1);
        item1.setQuantity(2);
        item1.setPrice(new BigDecimal("1000000.00"));

        InsertOrderItemRequest item2 = new InsertOrderItemRequest();
        item2.setProductId(productId2);
        item2.setQuantity(2);
        item2.setPrice(new BigDecimal("1500000.00"));

        assertThrows(ProductException.ProductNotFoundException.class, () -> {
            productService.decreaseProductStock(Arrays.asList(item1, item2));
        });
    }

    @Test
    @Transactional // 자동 rollback
    public void testDecreaseProductsInsufficient() {

        int productId1 = 1;
        int productId2 = 2;

        InsertOrderItemRequest item1 = new InsertOrderItemRequest();
        item1.setProductId(productId1);
        item1.setQuantity(5);
        item1.setPrice(new BigDecimal("1000000.00"));

        InsertOrderItemRequest item2 = new InsertOrderItemRequest();
        item2.setProductId(productId2);
        item2.setQuantity(2);
        item2.setPrice(new BigDecimal("1500000.00"));

        assertThrows(ProductException.ProductInsufficientException.class, () -> {
            productService.decreaseProductStock(Arrays.asList(item1, item2));
        });
    }

}
