package gordon.mySpring.config;

public class BeanProperty {

	private String propertyName;

	private String propertyValue;

	private String propertyRef;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getPropertyRef() {
		return propertyRef;
	}

	public void setPropertyRef(String propertyRef) {
		this.propertyRef = propertyRef;
	}

	@Override
	public String toString() {
		return "BeanProperty [propertyName=" + propertyName + ", propertyValue=" + propertyValue + ", propertyRef="
				+ propertyRef + "]";
	}

}
