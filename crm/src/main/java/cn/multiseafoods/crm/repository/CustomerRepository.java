package cn.multiseafoods.crm.repository;

import cn.multiseafoods.crm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
