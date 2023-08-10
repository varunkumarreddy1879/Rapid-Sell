package com.rapidesell.service;

import com.rapidesell.dao.CartDao;
import com.rapidesell.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartDao cartDao;
    public void save(Cart cart) {
        cartDao.save(cart);
        return;
    }

    public void deleteProductFromCart(int cartId) {
        Optional<Cart> optionalCart=cartDao.findById(cartId);

        if(optionalCart.isPresent()){
            cartDao.delete(optionalCart.get());
        }

    }
}
