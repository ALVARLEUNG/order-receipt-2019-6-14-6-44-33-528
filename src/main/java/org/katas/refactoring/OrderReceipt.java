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
    private static final String SALES_TAX_AND_TOTAL_AMOUNT = "Sales Tax\t%sTotal Amount\t%s";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        double totalSalesTax = 0d;
        double totalCount = 0d;
        StringBuilder output = new StringBuilder();
        // print headers, customer name and customer address
        output.append(HEADER + order.getCustomerName() + order.getCustomerAddress());

        for (LineItem lineItem : order.getLineItems()) {
            output.append(String.format(FORMAT, lineItem.getDescription(), lineItem.getPrice(), lineItem.getQuantity(), lineItem.totalAmount()));

            // calculate sales tax @ rate of 10%
            totalSalesTax = calculateTotalSalesTax(totalSalesTax, lineItem);

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalCount = calculateTotalCount(totalCount, lineItem);
        }

        // prints the state tax and total amount
        output.append(String.format(SALES_TAX_AND_TOTAL_AMOUNT, totalSalesTax, totalCount));

        return output.toString();
    }

    private double calculateTotalCount(double totalCount, LineItem lineItem) {
        totalCount += lineItem.totalAmount() + lineItem.totalAmount() * RATE_OF_TEN;
        return totalCount;
    }

    private double calculateTotalSalesTax(double totalSalesTax, LineItem lineItem) {
        totalSalesTax += lineItem.totalAmount() * RATE_OF_TEN;
        return totalSalesTax;
    }
}