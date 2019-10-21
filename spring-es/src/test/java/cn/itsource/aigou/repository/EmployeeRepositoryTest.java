package cn.itsource.aigou.repository;

import cn.itsource.aigou.EsApplication;
import cn.itsource.aigou.domain.Employee;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void testAdd()throws Exception{
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("boob");
        employee.setDescription("追梦之旅!");
        employeeRepository.save(employee);

    }

    /**
     * 修改
     * @throws Exception
     */
    @Test
    public void testUpdate()throws Exception{
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setName("小依");
        employee.setDescription("梦之旅!");
        employee.setAge(18);
        employeeRepository.save(employee);
    }

    /**
     * 批量删除
     */
    @Test
    public void testAddBatch(){
        List<Employee> employees = Arrays.asList(
                new Employee(2L,"灵陌",18,"拉亚!"),
                new Employee(3L,"明珠",25,"拉亚!")
        );
        employeeRepository.saveAll(employees);
    }
    @Test
    public void testDelete()throws Exception{
        employeeRepository.deleteById(1L);
    }

    @Test
    public void testDeleteBatch()throws Exception{
        employeeRepository.deleteAll();
    }
    /**
     * 根据id查询
     * @throws Exception
     */
    @Test
    public void testFindById()throws Exception{
        Optional<Employee> optional = employeeRepository.findById(1L);
        Employee employee = optional.get();
        System.out.println(employee);
    }
    /**
     * 查询所有
     * @throws Exception
     */
    @Test
    public void testFindAll()throws Exception{

        Iterable<Employee> all = employeeRepository.findAll();

        for (Employee employee : all) {
            System.out.println(employee);
        }

    }
    @Test
    public void test()throws Exception{


        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        //query
        //bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //条件
        //match
        MatchQueryBuilder match = new MatchQueryBuilder("大幅度发","是多少");
        boolQueryBuilder.must(match);
        //filter
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        //range
        RangeQueryBuilder range = new RangeQueryBuilder("age").gte(18).lte(25);
        filter.add(range);
        //term
        TermQueryBuilder term = new TermQueryBuilder("name","梦");
        filter.add(term);
        builder.withQuery(boolQueryBuilder);

        //page
        builder.withPageable(PageRequest.of(0,2));

        //sort
        builder.withSort(new FieldSortBuilder("age").order(SortOrder.DESC));

        Page<Employee> page = employeeRepository.search(builder.build());

        //解析值
        //total
        long total = page.getTotalElements();
        //rows
        List<Employee> rows = page.getContent();
        System.out.println("total="+total);
        for (Employee employee : rows) {
            System.out.println(employee);
        }

    }

}