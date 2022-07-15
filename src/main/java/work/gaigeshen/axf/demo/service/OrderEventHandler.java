package work.gaigeshen.axf.demo.service;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import work.gaigeshen.axf.demo.Order;
import work.gaigeshen.axf.demo.event.OrderCreatedEvent;
import work.gaigeshen.axf.demo.query.FindAllOrderedProductsQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaigeshen
 */
@Service
public class OrderEventHandler {

  private final Map<String, Order> orders = new HashMap<>();

  @EventHandler
  public void on(OrderCreatedEvent event) {
    String orderId = event.getOrderId();
    orders.put(orderId, new Order(orderId, event.getProductId()));
  }

  @QueryHandler
  public List<Order> handle(FindAllOrderedProductsQuery query) {
    return new ArrayList<>(orders.values());
  }
}
