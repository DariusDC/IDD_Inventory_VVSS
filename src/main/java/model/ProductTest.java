package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
    Product prod;

    @BeforeEach
    void setUp() {
        prod=new Product(1,"name",100,10,2,3,null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProductId() {
    }

    @Test
    void getName() {
        assert(prod.getName().equals("name"));
    }

    @Test
    void setProductId() {
    }

    @Test
    void setName() {
    }
}