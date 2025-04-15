package com.sincon.ecommerce.orderline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLIneRepository extends JpaRepository<OrderLine, Integer>{

    List<OrderLine> findAllByOrderId(Integer orderId);

}
