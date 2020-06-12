package com.ucu.edu.bigdata.cassandra.repository;

import com.ucu.edu.bigdata.cassandra.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
