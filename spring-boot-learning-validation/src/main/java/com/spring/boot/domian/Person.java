package com.spring.boot.domian;

import com.spring.boot.bean.validation.constraints.PersonNamePrefix;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lianp
 * @date 2019/1/3 16:13
 * @since
 **/
public class Person {

    @NotNull
    private String id;

    @PersonNamePrefix(prefix = "king-")
    private String name;

    @NotEmpty
    private List<String> list = new ArrayList<>(  );

    @Max( value = 200 ,message = "{Person.age.max.massage}")
    @Positive
    private int age;


    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public List<String> getList() {
        return list;
    }

    public void setList( List<String> list ) {
        this.list = list;
    }
}
