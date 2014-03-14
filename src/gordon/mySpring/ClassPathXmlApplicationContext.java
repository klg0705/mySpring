package gordon.mySpring;

import gordon.mySpring.config.BeanDefinition;
import gordon.mySpring.config.BeanProperty;
import gordon.mySpring.config.reader.XmlConfigReader;
import gordon.mySpring.util.SimpleTypeConverter;

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
		if (beans.containsKey(beanName)) {
			return beans.get(beanName);
		}

		Object bean = createBean(beanName);

		if (beanDefinitions.get(beanName).getScope() == 0) {
			beans.put(beanName, bean);
		}

		assembleBean(beanName, bean);

		return bean;
	}

	private void assembleBean(String beanName, Object targetBean) throws Exception {
		BeanDefinition beanDef = beanDefinitions.get(beanName);
		Method[] methods = targetBean.getClass().getMethods();
		for (BeanProperty prop : beanDef.getProperties()) {
			String beanRef = prop.getPropertyRef();
			if (beanRef != null && beanRef.length() > 0) {

				Object refBean = getBean(beanRef);

				Method setterMethod = findSetterMethod(methods, targetBean.getClass(), prop.getPropertyName());
				setterMethod.invoke(targetBean, refBean);
			}
		}
	}

	private Object createBean(String beanName) throws Exception {
		if (!beanDefinitions.containsKey(beanName)) {
			throw new Exception("Bean " + beanName + " cannot be found.");
		}

		BeanDefinition beanDef = beanDefinitions.get(beanName);
		Object bean = Class.forName(beanDef.getClazz()).newInstance();
		Method[] methods = bean.getClass().getMethods();
		for (BeanProperty prop : beanDef.getProperties()) {
			if (prop.getPropertyValue() != null && !prop.getPropertyValue().trim().isEmpty()) {
				Method setterMethod = findSetterMethod(methods, bean.getClass(), prop.getPropertyName());
				setterMethod.invoke(bean,
						SimpleTypeConverter.convertTo(setterMethod.getParameterTypes()[0], prop.getPropertyValue()));
			}
		}
		return bean;
	}

	// For multiple setter methods with same name:
	// 1. If there is a getter/is(only for boolean/Boolean) method. The setter
	// method which
	// needs the same type param as getter's return type should be used.
	// 2. Use the setter method which takes String type as param.
	// 3. Return a random setter method. Do not do any smart try for type
	// convert.
	private Method findSetterMethod(Method[] methods, Class<?> beanClass, String property) {
		Method getterMethod = getGetterMethodReturnType(methods, beanClass, property);
		Class<?> returnType = null;
		if (getterMethod != null) {
			returnType = getterMethod.getReturnType();
		}

		String setterStr = getSetterMethodString(property);

		Method setterMethodStringParam = null;
		Method someSetterMethod = null;
		for (Method method : methods) {
			if (method.getName().equals(setterStr) && method.getParameterTypes().length == 1) {
				if (returnType != null && method.getParameterTypes()[0].equals(returnType)) {
					return method;
				} else if (method.getParameterTypes()[0].equals(String.class)) {
					setterMethodStringParam = method;
				} else {
					someSetterMethod = method;
				}
			}
		}

		if (setterMethodStringParam != null) {
			return setterMethodStringParam;
		} else if (someSetterMethod != null) {
			return someSetterMethod;
		} else {
			throw new RuntimeException("Bean " + beanClass.getName() + " does not have the property " + property);
		}
	}

	private Method getGetterMethodReturnType(Method[] methods, Class<?> beanClass, String property) {
		String getterStr = getGetterMethodString(property);
		String isStr = getIsMethodString(property);

		for (Method method : methods) {
			if (method.getName().equals(getterStr) && method.getParameterTypes().length == 0) {
				return method;
			} else if (method.getName().equals(isStr) && method.getParameterTypes().length == 0) {
				if (method.getReturnType() == boolean.class || method.getReturnType() == Boolean.class) {
					return method;
				}
			}
		}
		return null;
	}

	private String getSetterMethodString(String property) {
		StringBuilder sb = new StringBuilder("set");
		sb.append(Character.toUpperCase(property.charAt(0))).append(property.substring(1));
		return sb.toString();
	}

	private String getGetterMethodString(String property) {
		StringBuilder sb = new StringBuilder("get");
		sb.append(Character.toUpperCase(property.charAt(0))).append(property.substring(1));
		return sb.toString();
	}

	private String getIsMethodString(String property) {
		StringBuilder sb = new StringBuilder("is");
		sb.append(Character.toUpperCase(property.charAt(0))).append(property.substring(1));
		return sb.toString();
	}

}
