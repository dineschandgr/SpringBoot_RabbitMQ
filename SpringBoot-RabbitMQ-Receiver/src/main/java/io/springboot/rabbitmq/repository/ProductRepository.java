package io.springboot.rabbitmq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.springboot.rabbitmq.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
