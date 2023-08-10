package com.rapidesell.service;

import com.rapidesell.dao.CartDao;
import com.rapidesell.dao.OrderDao;
import com.rapidesell.model.Cart;
import com.rapidesell.model.Orders;
import com.rapidesell.model.User;
import com.rapidesell.utility.Constants;
import com.rapidesell.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;


    public String orderfoods(User user) {

        String orderId = Helper.getAlphaNumericOrderId(10);
        String orderedDate = LocalDate.now().toString();

        List<Cart> carts = cartDao.findByUserId(user.getId());

        for(Cart cart : carts) {
            Orders order = new Orders();
            order.setOrderDate(orderedDate);
            order.setOrderId(orderId);
            order.setUserId(user.getId());
            order.setQuantity(cart.getQuantity());
            order.setFoodId(cart.getFoodId());
            order.setDeliveryStatus(Constants.DeliveryStatus.PENDING.value());
            order.setDeliveryDate(Constants.DeliveryStatus.PENDING.value());
            orderDao.save(order);
            cartDao.delete(cart);
        }

        return orderId;

    }

    public List<Orders> findAll() {
        return orderDao.findAll();
    }

    public List<Orders> findByOrderId(String orderId) {
        return orderDao.findByOrderId(orderId);
    }

    public List<Orders> findByOrderDateAndUserId(String orderDate, int id) {
        return orderDao.findByOrderDateAndUserId(orderDate,id);
    }

    public void addDeliveryStatus(String orderId, String deliveryDate, String deliveryStatus) {
        List<Orders> orders = orderDao.findByOrderId(orderId);

        for(Orders order : orders) {
            order.setDeliveryDate(deliveryDate);
            order.setDeliveryStatus(deliveryStatus);
            this.orderDao.save(order);
        }
    }
}
