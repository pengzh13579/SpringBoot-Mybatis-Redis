package cn.pzh.person.application;

import cn.pzh.person.application.common.MyLocaleResolver;
import cn.pzh.person.application.common.Person;
import javax.servlet.http.HttpServletRequest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootApplication
@Controller
@MapperScan (basePackages="cn.pzh.person.application.mapper")
public class Application {

	@Autowired
	private Person person;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public LocaleResolver localeResolver(){
		return new MyLocaleResolver();
	}

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request){

		// 判定session是否存在
		if(null == request.getSession().getAttribute("user")){

			// session中不存在指定属性则添加
			request.getSession().setAttribute("user", "admin");
			request.getSession().setAttribute("url", request.getRequestURI());
			ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
			mav.addObject("第一次登陆");
			return mav;
		}
		System.out.print(request.getSession().getId());

		// session中存在则返回页面
		ModelAndView mav = new ModelAndView();
		mav.setViewName("personList");
		mav.addObject("option", "success");
		return mav;
	}

	@RequestMapping("/person")
	@ResponseBody
	public String person(HttpServletRequest request){
		return person.toString();
	}

	@RequestMapping("/image")
	public ModelAndView image(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("image");
		mav.addObject("img", "1.png");
		return mav;
	}

	@RequestMapping("/webjarsDemo")
	public String webjarsDemo(HttpServletRequest request){
		return "webjarsDemo";
	}
}
