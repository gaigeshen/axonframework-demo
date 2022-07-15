package work.gaigeshen.axf.demo.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author gaigeshen
 */
@Data
public class ShipOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;
}
