import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "product: " + product + ", cost: " + cost;
    }

}
public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        Map<String, List<Order>> groupingOrdersByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));
        System.out.println(groupingOrdersByProduct);

        Map<String, Double> sumCostByProduct = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,
                Collectors.summingDouble(Order::getCost)));
        System.out.println(sumCostByProduct);

        List<Map.Entry<String, Double>> sortedProductsToSumCost = sumCostByProduct.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue())).toList();
        System.out.println(sortedProductsToSumCost);

        List<Order> top3Expensive = orders.stream()
                .sorted(((o1, o2) -> Double.compare(o2.getCost(), o1.getCost())))
                .limit(3).toList();
        System.out.println(top3Expensive);

        Double sumOfTop3Expensive = top3Expensive.stream()
                .collect(Collectors.summingDouble(Order::getCost));
        System.out.println(sumOfTop3Expensive);
    }
}