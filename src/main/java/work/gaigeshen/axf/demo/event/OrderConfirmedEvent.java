package work.gaigeshen.axf.demo.event;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public class OrderConfirmedEvent {

  private final String orderId;
}
