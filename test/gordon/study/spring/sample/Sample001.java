package gordon.study.spring.sample;

import gordon.mySpring.ApplicationContext;
import gordon.mySpring.ClassPathXmlApplicationContext;

public class Sample001 {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"sample1.xml");
		Employee gordon = (Employee) context.getBean("gordon");
		System.out.println(gordon.getInfo());
	}
}
