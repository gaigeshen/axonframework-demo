package work.gaigeshen.axf.demo;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import work.gaigeshen.axf.demo.command.CreateOrderCommand;
import work.gaigeshen.axf.demo.command.ShipOrderCommand;
import work.gaigeshen.axf.demo.event.OrderCreatedEvent;
import work.gaigeshen.axf.demo.exception.UnconfirmedOrderException;

import java.util.UUID;

/**
 * @author gaigeshen
 */
public class TestCases {

  private FixtureConfiguration<OrderAggregate> fixture;

  @Before
  public void setUp() {
    fixture = new AggregateTestFixture<>(OrderAggregate.class);
  }

  @Test
  public void testCreateOrder() {
    String orderId = UUID.randomUUID().toString();
    String productId = "demo product";
    fixture.givenNoPriorActivity()
            .when(new CreateOrderCommand(orderId, productId))
            .expectEvents(new OrderCreatedEvent(orderId, productId));
  }

  @Test
  public void testCreateOrderWhenShip() {
    String orderId = UUID.randomUUID().toString();
    String productId = "demo product";
    fixture.given(new OrderCreatedEvent(orderId, productId))
            .when(new ShipOrderCommand(orderId))
            .expectException(UnconfirmedOrderException.class);
  }

}
