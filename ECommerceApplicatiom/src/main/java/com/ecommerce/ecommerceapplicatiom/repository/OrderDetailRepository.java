package com.ecommerce.ecommerceapplicatiom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerceapplicatiom.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

	List<OrderDetail> findOrderDetailByOrderId(String id);

}
