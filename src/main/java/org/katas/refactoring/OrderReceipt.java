package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {

    private static final String HEADER = "======Printing Orders======\n";
    private static final Double RATE_OF_TEN = .10;
    private static final String FORMAT = "%s\t%s\t%s\t%s\n";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        String itemContent = "";
        double totalSalesTax = 0d;
        double totalCount = 0d;
        StringBuilder output = new StringBuilder();

        // print headers, customer name and customer address
        output.append(HEADER + order.getCustomerName() + order.getCustomerAddress());

        for (LineItem lineItem : order.getLineItems()) {
            itemContent = String.format(FORMAT, lineItem.getDescription(), lineItem.getPrice(), lineItem.getQuantity(), lineItem.totalAmount());
            output.append(itemContent);

            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * RATE_OF_TEN;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalCount += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        output.append("Sales Tax").append('\t').append(totalSalesTax);

        // print total amount
        output.append("Total Amount").append('\t').append(totalCount);
        return output.toString();
    }
}