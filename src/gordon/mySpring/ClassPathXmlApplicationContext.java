package gordon.mySpring;

import gordon.mySpring.config.BeanDefinition;
import gordon.mySpring.config.BeanProperty;
import gordon.mySpring.config.reader.XmlConfigReader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements ApplicationContext {

	Map<String, BeanDefinition> beanDefinitions;

	Map<String, Object> beans;

	public ClassPathXmlApplicationContext(String fileName) throws Exception {
		beanDefinitions = XmlConfigReader.readXmlConfig(fileName);
		beans = new HashMap<String, Object>();
	}

	@Override
	public Object getBean(String beanName) throws Exception {
		if (beans.containsKey("beanName")) {
			return beans.get(beanName);
		}

		Object bean = createBean(beanName);

		beans.put(beanName, bean);

		return bean;
	}

	private Object createBean(String beanName) throws Exception {
		if (!beanDefinitions.containsKey(beanName)) {
			throw new Exception("Bean " + beanName + " cannot be found.");
		}

		BeanDefinition beanDef = beanDefinitions.get(beanName);
		Object obj = Class.forName(beanDef.getClazz()).newInstance();
		for (BeanProperty prop : beanDef.getProperties()) {
			getSetterMethod(obj, prop.getPropertyName()).invoke(obj, prop.getPropertyValue());
		}
		
		return obj;
	}

	private Method getSetterMethod(Object obj, String property) throws Exception {
		String setter = getSetterString(property);
		Method method = obj.getClass().getMethod(setter, String.class);
		return method;
	}

	private String getSetterString(String property) {
		StringBuilder sb = new StringBuilder("set");
		sb.append(Character.toUpperCase(property.charAt(0))).append(
				property.substring(1));
		return sb.toString();
	}
}
