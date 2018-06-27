package cn.pzh.person.application.controller;

import cn.pzh.person.application.model.Person;
import cn.pzh.person.application.service.PersonService;
import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping (value = "searchPersonList")
    @ResponseBody
    public Map<String,Object> searchPersonList(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();

        // page 为easyui分页插件默认传到后台的参数，代表当前的页码，起始页为1
        String pageNo = request.getParameter("page");

        // rows为为easyui分页插件默认传到后台的参数，代表当前设置的每页显示的记录条数
        String pageSize = request.getParameter("rows");

        // 调用service方法，获取人员记录
        List<Person> list = personService.findByPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(list);

        // 获取总记录数
        long total = pageInfo.getTotal();

        // 定义map
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        // total 存放总记录数
        jsonMap.put("total", total);

        // rows存放每页记录 ，这里的两个参数名是固定的，必须为 total和 rows
        jsonMap.put("rows", list);
        return jsonMap;
    }
}

