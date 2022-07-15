package work.gaigeshen.axf.demo.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author gaigeshen
 */
@Data
public class CreateOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;

  private final String productId;
}
