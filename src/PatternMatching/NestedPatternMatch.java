package PatternMatching;

import java.util.List;
public class NestedPatternMatch {
    record Product (Integer id, String name, Double price) {}
    record OrderItem (Product p1, Integer qty) {}
    record Order(List<OrderItem> orderItemList) {}

    public static void printProductNames(Object object) {
        if (object instanceof Order(List<OrderItem> orderItemList)) {
            orderItemList.stream().forEach(orderItem -> {
                if (orderItem instanceof OrderItem(Product(var id, var name, var price), Integer qty)) {
                    System.out.println(String.format("name -> %-50s", name) + String.format(" price -> %-50s", price));
                }
            });
        }
        if (object instanceof Product(Integer id, String name, Double price)) {
            System.out.println(String.format("name -> %-50s", name) + String.format(" price -> %-50s", price));
        }
    }

    public static void main(String[] args) {
        Order order = prepareOrder();
        printProductNames(order);
        Product product = new Product(2, "Milk Packet", 550.00);
        printProductNames(product);
    }

    public static Order prepareOrder() {
        Product product = new Product(1, "cashew", 550.00);
        OrderItem orderItem = new OrderItem(product, 1);
        Order order = new Order(List.of(orderItem));
        return order;
    }
}
