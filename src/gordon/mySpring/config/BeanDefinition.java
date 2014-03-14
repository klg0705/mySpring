package gordon.mySpring.config;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
	private String name;
	private String clazz;
	private List<BeanProperty> properties = new ArrayList<BeanProperty>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<BeanProperty> getProperties() {
		return properties;
	}

	public void addPropertie(BeanProperty property) {
		properties.add(property);
	}

}
