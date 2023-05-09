package tests;
import model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ProductTest {
    Product prod;

    @BeforeEach
    void setUp() {
        prod=new Product(1,"name",100,10,2,3,null);
    }

    @Test
    void getNameTest() {
        assert(prod.getName().equals("name"));
    }

    @Test
    void setNameTest() {
        prod.setName("test");
        assert(prod.getName().equals("test"));
    }


}
