package work.gaigeshen.axf.demo;

import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import work.gaigeshen.axf.demo.command.ConfirmOrderCommand;
import work.gaigeshen.axf.demo.command.CreateOrderCommand;
import work.gaigeshen.axf.demo.command.ShipOrderCommand;
import work.gaigeshen.axf.demo.event.OrderConfirmedEvent;
import work.gaigeshen.axf.demo.event.OrderCreatedEvent;
import work.gaigeshen.axf.demo.event.OrderShippedEvent;
import work.gaigeshen.axf.demo.exception.UnconfirmedOrderException;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author gaigeshen
 */
@Getter
@Aggregate
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;

  private boolean orderConfirmed;

  @CommandHandler
  public OrderAggregate(CreateOrderCommand command) {
    apply(new OrderCreatedEvent(command.getOrderId(), command.getProductId()));
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent event) {
    orderId = event.getOrderId();
    orderConfirmed = false;
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
    orderConfirmed = true;
  }

  @CommandHandler
  public void handle(ConfirmOrderCommand command) {
    if (!orderConfirmed) {
      apply(new OrderConfirmedEvent(orderId));
    }
  }

  @CommandHandler
  public void handle(ShipOrderCommand command) {
    if (!orderConfirmed) {
      throw new UnconfirmedOrderException(orderId);
    }
    apply(new OrderShippedEvent(orderId));
  }

  protected OrderAggregate() { }

}
