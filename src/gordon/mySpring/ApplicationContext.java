package gordon.mySpring;

public interface ApplicationContext {

	Object getBean(String beanName) throws Exception;
}
