package controllers;

import org.junit.Test;

/**
 * Created by andgra on 2014-10-13.
 */
public class ProductsControllerTest {
    String jsonCreateProduct = "{\"id\":\"5e7b2b0e-3430-48ac-bf6f-9d142438e3fc\",\"name\":\"product1\",\"priceIncVat\":1.000,\"vatPercentage\":0.250,\"vatAmount\":0.250000} ";

    @Test
    public void createProductsTest() {
        String answer;
        ProductsController productsController = new ProductsController();
      //  answer = productsController.createProducts(jsonCreateProduct);

    }
}
