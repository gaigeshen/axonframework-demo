package work.gaigeshen.axf.demo.event;

import lombok.Data;

/**
 * @author gaigeshen
 */
@Data
public class OrderShippedEvent {

  private final String orderId;
}
