package tests.unit;

import business.DiscountUtility;
import business.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

class OrderServiceTest {

    @Mock
    DiscountUtility discountUtility;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCalculateDiscount() {
        // Arrange
        String username = "John Smith";
        String dateOfBirth = "1990/10/10";
        when(discountUtility.calculateDiscount(username, dateOfBirth)).thenReturn(3);

        // Act
        int discount = orderService.calculateDiscount(username, dateOfBirth);

        // Assert
        assertEquals(3, discount, "Discount for John Smith should be 3");
        verify(discountUtility, times(1)).calculateDiscount(username, dateOfBirth);
        verifyNoMoreInteractions(discountUtility);
    }
}
