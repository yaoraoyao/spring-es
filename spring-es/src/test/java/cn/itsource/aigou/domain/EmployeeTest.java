package cn.itsource.aigou.domain;

import cn.itsource.aigou.EsApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class EmployeeTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    private void addFind() throws Exception{
        //删库
        elasticsearchTemplate.deleteIndex(Employee.class);
        //建库
        elasticsearchTemplate.createIndex(Employee.class);
        //创建类型映射
        elasticsearchTemplate.putMapping(Employee.class);

    }

}