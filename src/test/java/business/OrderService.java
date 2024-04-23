package business;

public class OrderService {
    private DiscountUtility discountUtility;

    public OrderService(DiscountUtility discountUtility) {
        this.discountUtility = discountUtility;
    }

    public int calculateDiscount(String username, String dateOfBirth) {
        // Вызываем метод calculateDiscount из объекта discountUtility
        return discountUtility.calculateDiscount(username, dateOfBirth);
    }
}
