package work.gaigeshen.axf.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author gaigeshen
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Order {

  private final String orderId;

  private final String productId;

  private OrderStatus orderStatus;

  public void setOrderConfirmed() {
    orderStatus = OrderStatus.CONFIRMED;
  }

  public void setOrderShipped() {
    orderStatus = OrderStatus.SHIPPED;
  }

}
