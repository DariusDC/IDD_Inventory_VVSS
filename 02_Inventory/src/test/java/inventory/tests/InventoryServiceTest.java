package inventory.tests;

import inventory.model.InhousePart;
import inventory.model.InventoryRepositoryMemory;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepositoryFile;
import inventory.service.InventoryService;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import inventory.validator.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static inventory.validator.PartValidator.*;
import static inventory.validator.PartValidator.emptyNameErrMsg;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.*;


public class InventoryServiceTest {
    InventoryService service;
    InventoryRepositoryFile repo;

    @BeforeEach
    void setUp() {
//        PartValidator partValidator = new PartValidator();
//        ProductValidator productValidator = new ProductValidator();
//        InventoryRepositoryFile repo = new InventoryRepositoryFile(partValidator, productValidator);
        repo = mock(InventoryRepositoryFile.class);
        service = new InventoryService(repo);
    }

    @Test
    void testGetAllProducts(){
        Product prod=mock(Product.class);
        Mockito.when(repo.getAllProducts()).thenReturn(FXCollections.observableArrayList(prod));
        assertEquals(service.getAllProducts().size(),1);
    }

    @Test
    void testGetAllParts(){
        Part part=mock(Part.class);
        Mockito.when(repo.getAllParts()).thenReturn(FXCollections.observableArrayList(part));
        assertEquals(service.getAllParts().size(),1);
    }

//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void addInhousePart_validData_OK_ECP_1() {
//        try {
//            //setup
//            Part part = new InhousePart(1, "Piulita", 0.5, 200, 5, 1000, 1);
//
//            //act
//            Part part_service = service.addInhousePart("Piulita", 0.5, 200, 5, 1000, 1);
//            part.setPartId(part_service.getPartId());
//
//            //assert
//            assert (part_service.equals(part));
//        } catch (ValidationException err) {
//            assert (false);
//        }
//    }
//
//    @Test
//    void addInhousePart_validData_OK_ECP_2() {
//        try {
//            //setup
//            Part part = new InhousePart(1, "Amortizare", 200, 20, 4, 1000, 5);
//
//            //act
//            Part part_service = service.addInhousePart("Amortizare", 200, 20, 4, 1000, 5);
//            part.setPartId(part_service.getPartId());
//
//            //assert
//            assert (part_service.equals(part));
//        } catch (ValidationException err) {
//            assert (false);
//        }
//    }
//
//    @Test
//    void addInhousePart_InvalidMin_Exception_ECP_1() {
//        Exception exception = assertThrows(ValidationException.class, () -> {
//            service.addInhousePart("Surub", 1, 200, -5, 1000, 34);
//        });
//
//        String expectedMessage = minErrMsg;
//        String actualMessage = exception.getMessage();
//
//        assertEquals(expectedMessage, actualMessage);
//    }
//
//    @Test
//    void addInhousePart_InvalidMin_Exception_ECP_2() {
//        Exception exception = assertThrows(ValidationException.class, () -> {
//            service.addInhousePart("Roata", 500, 200, Integer.MAX_VALUE, 1000, 34);
//        });
//
//        String expectedMessage = minGreaterErrMsg + minGreaterMaxErrMsg + stockLowerErrMsg;
//        String actualMessage = exception.getMessage();
//
//        assertEquals(expectedMessage, actualMessage);
//    }
//
//    @Test
//    void addInhousePart_validData_OK_BVA_1() {
//        try {
//            //setup
//            Part part = new InhousePart(1, "M", 67, 20, 10, 30, 34);
//
//            //act
//            Part part_service = service.addInhousePart("M", 67, 20, 10, 30, 34);
//            part.setPartId(part_service.getPartId());
//
//            //assert
//            assert (part_service.equals(part));
//        } catch (ValidationException err) {
//            assert (false);
//        }
//    }
//
//    @Test
//    void addInhousePart_validData_OK_BVA_2() {
//        try {
//            //setup
//            Part part = new InhousePart(1, "M", 67, 20, 1, 30, 34);
//
//            //act
//            Part part_service = service.addInhousePart("M", 67, 20, 1, 30, 34);
//            part.setPartId(part_service.getPartId());
//
//            //assert
//            assert (part_service.equals(part));
//        } catch (ValidationException err) {
//            assert (false);
//        }
//    }
//
//    @Test
//    void addInhousePart_InvalidMin_Exception_BVA() {
//        Exception exception = assertThrows(ValidationException.class, () -> {
//            service.addInhousePart("M", 67, 20, 0, 30, 34);
//        });
//
//        String expectedMessage = minErrMsg;
//        String actualMessage = exception.getMessage();
//
//        assertEquals(expectedMessage, actualMessage);
//    }
//
//    @Test
//    void addInhousePart_InvalidName_Exception_BVA() {
//        Exception exception = assertThrows(ValidationException.class, () -> {
//            service.addInhousePart("", 67, 20, 1, 30, 34);
//        });
//
//        String expectedMessage = emptyNameErrMsg;
//        String actualMessage = exception.getMessage();
//
//        assertEquals(expectedMessage, actualMessage);
//    }

//    @Test
//    @Test(expected = ValidationException.class)
//    public void test06_add_invalid_Product() throws ValidationException {
//        Product prod=new Product(1,"name",100,10,2,3,null);
//        Mockito.doThrow(ValidationException.class).when(repo).addProduct(prod);
//        service.addProduct("name",100,10,2,3,null);
//    }
}
