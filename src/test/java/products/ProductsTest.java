/*
package products;

import org.junit.BeforeClass;
import org.junit.Test;

*/
/**
 * Created by andgra on 2014-10-10.
 *//*


public class ProductsTest{
    Products PRODUCTS = Products.getInstance();


    @BeforeClass
    public static void createOneProduct(){
        String id = "100";
        String name = "milk";
        double priceIncVat = 2;
        double vatPercentage = 10;
        double vatAmount = 3;
        Products.getInstance().createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
    }


    @Test
    public void createProduct(){
        String id = "1";
        String name = "milk";
        double priceIncVat = 2;
        double vatPercentage = 10;
        double vatAmount = 3;

        String statusCode = PRODUCTS.createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
        CartRowItem product = PRODUCTS.getProduct(id);
        assert (product.getName().equals(name));
        assert (statusCode.equals("201 Created"));
    }
    @Test
    public void deleteProduct(){
        String id = "100";
        String id2 = "2";
        String answer;
        String answer2;

        assert (PRODUCTS.deleteProduct(id).equals("200 OK"));
        assert (PRODUCTS.deleteProduct(id2).equals("400 Bad Request"));
    }

}
*/
