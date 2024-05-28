package StringTemplates;

import java.util.FormatProcessor;

public class FMTStringTemplateProcessor {

    record CartItem(String name, float unitPrice, int qty) {
        float itemCost() {
            return unitPrice * qty;
        }
    }
    public static void main(String[] args) {
        CartItem[] items = new CartItem[]{
                new CartItem("notebooks", 75.50f, 3),
                new CartItem("geomaps", 1.75f, 10),
                };
        String invoiceTable = FormatProcessor.FMT."""
            ItemName     Unit Cost    Quantity     Total Cost
            %-12s\{items[0].name}  %7.2f\{items[0].unitPrice}  %7d\{items[0].qty}     %14.2f\{items[0].itemCost()}
            %-12s\{items[1].name}  %7.2f\{items[1].unitPrice}  %7d\{items[1].qty}     %14.2f\{items[1].itemCost()}
            \{" ".repeat(35)} Total %7.2f\{items[0].itemCost() + items[1].itemCost()}
            """;

        System.out.println(invoiceTable);
    }
}
