package tests;

import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MockingTest {
    @Mock
    DiscountUtility utility;

    @InjectMocks
    OrderService orderService;

    @Test
    public void discountTest() {
        UserAccount userAccount = new UserAccount("John", "Smith", "1990/10/10", new ShoppingCart(new ArrayList<>()));

        Mockito.when(orderService.getDiscountUtility().calculateDiscount(userAccount)).thenReturn(3.0); //set value to mocked method call
        double discount = orderService.getDiscountUtility().calculateDiscount(userAccount);
        assertThat(discount).isEqualTo(3.0);

        Mockito.verify(utility, Mockito.times(1)).calculateDiscount(userAccount); //check number of calls to calculateDiscount method
        Mockito.verifyNoMoreInteractions(utility);
    }
}
