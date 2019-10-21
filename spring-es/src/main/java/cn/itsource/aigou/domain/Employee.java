package cn.itsource.aigou.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class Employee {
    @Id
    private Long id;
    @Field(store = true,type = FieldType.Text)
    private String name;
    @Field(store = true,type = FieldType.Integer)
    private Integer age;
    @Field(store = true,type = FieldType.Text)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }

    public Employee(Long id, String name, Integer age, String description) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public Employee() {
    }
}
