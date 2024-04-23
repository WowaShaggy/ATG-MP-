package tests.unit;

import business.Product;
import business.ShoppingCart;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductsAndShoppingCartTest {

    @Test
    public void testAddProductToCart() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("Product1", 10.0);

        // Act
        cart.addProduct(product);

        // Assert
        assertTrue(cart.getProducts().contains(product));
    }

    @Test
    public void testRemoveProductFromCart() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("Product1", 10.0);
        cart.addProduct(product);

        // Act
        cart.removeProduct(product);

        // Assert
        assertFalse(cart.getProducts().contains(product));
    }

    @Test
    public void testGetTotalPrice() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        Product product1 = new Product("Product1", 10.0);
        Product product2 = new Product("Product2", 20.0);
        cart.addProduct(product1);
        cart.addProduct(product2);

        // Act
        double totalPrice = cart.getTotalPrice();

        // Assert
        assertEquals(30.0, totalPrice);
    }


}
