package work.gaigeshen.axf.demo.event;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public class OrderCreatedEvent {

  private final String orderId;

  private final String productId;
}
