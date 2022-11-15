package tests;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ShoppingCartTest {

    @Test
    public void testAddProductsToShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        Product product = new Product();
        shoppingCart.addProductToCart(product);
        assertThat(shoppingCart.getProducts()).contains(product);
    }

    @Test
    public void testAddProductsToShoppingCartViaConstructor() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        products.add(product);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        assertThat(shoppingCart.getProducts()).contains(product);
    }

    @Test
    public void testRemoveProductFromShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        Product product = new Product();
        shoppingCart.addProductToCart(product);
        shoppingCart.removeProductFromCart(product);
        assertThat(shoppingCart.getProducts()).doesNotContain(product);
        assertThat(shoppingCart.getProducts()).isEmpty();
    }

    @Test
    public void testRemoveNonAddedProductFromShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        Product product = new Product();
        assertThatThrownBy(() -> shoppingCart.removeProductFromCart(product)).isInstanceOf(ProductNotFoundException.class);
    }

    @Test
    public void testTotalPriceShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(new ArrayList<>());
        Product firstProduct = new Product(1, "milk", 2.03, 1);
        Product secondProduct = new Product(2, "sugar", 3.15, 1);
        shoppingCart.addProductToCart(firstProduct);
        shoppingCart.addProductToCart(secondProduct);
        assertThat(shoppingCart.getCartTotalPrice()).isEqualTo(firstProduct.getPrice()* firstProduct.getQuantity() + secondProduct.getPrice()*secondProduct.getQuantity());
    }
}
