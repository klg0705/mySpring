package gordon.mySpring.config;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
	
	private String name;
	
	private String clazz;
	
	// 0 : singleton, default
	// 1 : prototype
	private int scope = 0;
	
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

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "BeanDefinition [name=" + name + ", clazz=" + clazz + ", scope=" + scope + ", properties=" + properties
				+ "]";
	}
	
}
