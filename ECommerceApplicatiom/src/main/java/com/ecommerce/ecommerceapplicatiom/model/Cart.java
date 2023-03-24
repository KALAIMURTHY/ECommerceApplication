package com.ecommerce.ecommerceapplicatiom.model;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.ecommerceapplicatiom.entity.Product;

public class Cart {
    private final List<CartItem> items;
    private double total;

    public Cart() {
        items = new ArrayList<>();
        total = 0;
    }

    public CartItem getItem(Product product){
        for (CartItem item : items){
            if(item.getProduct().getProductId() == product.getProductId()){
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getItemCount(){
        return items.size();
    }

    public void addItem(CartItem item){
        addItem(item.getProduct());
    }

    public void addItem(Product product){
        CartItem item = getItem(product);

        if (item != null){
        	items.add(item);
        } else {
            item = new CartItem(product, product.getProductPrice());
            items.add(item);
        }
    }

    public void updateItem(Product product, int quantity){ // throws ProductNotFoundException
        CartItem item = getItem(product);

        if (item != null){
        	items.add(item);
        } else {
            // throw new ProductNotFoundException();
        }
    }

    public void removeItem(Product product){ // throws ProductNotFoundException
        CartItem item = getItem(product);

        if (item != null){
            items.remove(item);
        } else {
            // throw new ProductNotFoundException();
        }
    }

    public void clear(){
        items.clear();
        total = 0;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double getTotal(){
        total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}
