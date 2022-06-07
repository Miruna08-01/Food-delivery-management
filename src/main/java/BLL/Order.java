package BLL;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Serializable,Comparable<Order> {
    private int clientId;
    private int orderId;
    private LocalDateTime orderDate;
    private double totalPrice;


    public Order(int clientId, int orderId, LocalDateTime orderDate) {
        this.clientId = clientId;
        this.orderId = orderId;
        this.orderDate = LocalDateTime.from(orderDate);

    }

    public String getByIndex(int index){
        if(index==0){
            return String.valueOf("clientId "+ clientId);
        }
        if(index==1)
            return String.valueOf("orderId "+ orderId);
        if(index==2)
            return String.valueOf("orderDate "+ orderDate);
        if(index==3)
            return String.valueOf("totalPrice "+ totalPrice);
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, orderId, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", orderId=" + orderId +
                ", orderDate=" + orderDate +
                "}\n";
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = LocalDateTime.from(orderDate);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Order o) {
        if(this.getOrderDate().getHour()>o.getOrderDate().getHour())
            return 1;
       if(this.getOrderDate().getHour()<o.getOrderDate().getHour())
           return -1;
       return 0;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Order)) return false;
//        Order order = (Order) o;
//        return getClientId() == order.getClientId() && getOrderId() == order.getOrderId() && Double.compare(order.getTotalPrice(), getTotalPrice()) == 0 && Objects.equals(getOrderDate(), order.getOrderDate());
//    }
}
