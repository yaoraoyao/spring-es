package cn.itsource.aigou.repository;

import cn.itsource.aigou.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee,Long> {
}
