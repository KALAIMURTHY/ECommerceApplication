package com.ecommerce.ecommerceapplicatiom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapplicatiom.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
