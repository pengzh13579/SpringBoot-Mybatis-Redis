package cn.pzh.person.application.service;

import cn.pzh.person.application.model.Person;
import com.github.pagehelper.Page;
import java.util.List;

public interface PersonService {

    List<Person> findAll();

    /**
     * 分页查询
     * @param pageNo 页号
     * @param pageSize 每页显示记录数
     * @return
     */
    Page<Person> findByPage(String pageNo, String pageSize);

    void insert(Person person);
}
