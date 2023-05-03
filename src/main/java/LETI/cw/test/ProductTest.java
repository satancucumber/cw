package LETI.cw.test;

import LETI.cw.models.Product;
import org.junit.jupiter.api.*;

public class ProductTest {
    @BeforeAll
    static void setUp() {
        System.out.println("START TESTING");
    }
    @BeforeEach
    void setupThis(){
        System.out.println("START TEST");
    }

    @Tag("ADD_STRING")
    @Test
    void testProductAddString()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Product product = new Product();
        product.setName("String");
        Assertions.assertEquals( "String" , product.getName());
    }

    @Tag("ADD_NONE")
    @Test
    void testProductAddNone()
    {
        System.out.println("======TEST THREE EXECUTED=======");
        Product product = new Product();
        product.setName("");
        Assertions.assertEquals( "" , product.getName());
    }

    @Tag("ADD_NULL")
    @Test
    void testProductAddNull()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Product product = new Product();
        product.setName(null);
        Assertions.assertEquals( null , product.getName());
    }
    @AfterEach
    void tearThis(){
        System.out.println("END TEST");
    }
    @AfterAll
    static void tear(){
        System.out.println("END TESTING");
    }
}
