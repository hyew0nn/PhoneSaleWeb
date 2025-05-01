package com.mycom.myapp.exception;

public class ProductException extends RuntimeException {
    public static class ProductNotFoundException extends BusinessException {
        public ProductNotFoundException(int productId) {
            super("PRODUCT_NOT_FOUND", String.format("ID가 %d인 상품을 찾을 수 없습니다.", productId));
        }
    }

    public static class ProductInsufficientException extends BusinessException {
        public ProductInsufficientException(String productName) {
            super("INSUFFICIENT_STOCK", String.format("상품 %s 의 재고가 부족합니다.", productName));
        }
    }
}
