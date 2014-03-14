package gordon.study.spring.sample;

import gordon.mySpring.ApplicationContext;
import gordon.mySpring.ClassPathXmlApplicationContext;
import junit.framework.TestCase;

public class Test001 extends TestCase {

	public void testSample1() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("sample1.xml");
			Employee gordon = (Employee) context.getBean("gordon");
			Employee elf = (Employee) context.getBean("elf");
			Employee ghost = (Employee) context.getBean("ghost");
			Department lab = (Department) context.getBean("lab");
			Department warehouse = (Department) context.getBean("warehouse");
			Employee robot1 = (Employee) context.getBean("robot");
			robot1.setName("Robot1");
			Employee robot2 = (Employee) context.getBean("robot");
			robot2.setName("Robot2");

			assertEquals("Gordon", gordon.getName());
			assertEquals(32, gordon.getAge());
			assertEquals(817.52f, gordon.getSalary());
			assertEquals(true, gordon.isMale());
			
			assertTrue(gordon != elf);
			assertTrue(gordon.getDept() == elf.getDept());
			assertTrue(gordon.getDept() == lab);
			assertTrue(gordon.getDept() != ghost.getDept());
			assertTrue(robot1 != robot2);
			assertTrue(robot1.getDept() == robot2.getDept());
			assertTrue(robot1.getDept() == warehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
