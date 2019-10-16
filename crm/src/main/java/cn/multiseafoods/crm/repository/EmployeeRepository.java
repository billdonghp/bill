package cn.multiseafoods.crm.repository;

import cn.multiseafoods.crm.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
