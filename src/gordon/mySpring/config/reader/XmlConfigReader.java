package gordon.mySpring.config.reader;

import gordon.mySpring.config.BeanDefinition;
import gordon.mySpring.config.BeanProperty;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;

public class XmlConfigReader {

	public static Map<String, BeanDefinition> readXmlConfig(String fileName)
			throws Exception {
		Map<String, BeanDefinition> result = new HashMap<String, BeanDefinition>();

		URL url = ClassLoader.getSystemResource(fileName);

		File f = new File(url.getFile());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		DOMReader reader = new DOMReader();
		Document doc = reader.read(builder.parse(f));

		Element rootElement = doc.getRootElement();
		List<Element> $beans = rootElement.elements();
		for (Element $bean : $beans) {
			BeanDefinition beanDef = new BeanDefinition();
			beanDef.setName($bean.attributeValue("id"));
			beanDef.setClazz($bean.attributeValue("class"));
			if($bean.attribute("scope") != null) {
				if("prototype".equals($bean.attributeValue("scope"))) {
					beanDef.setScope(1);
				}
			}

			result.put(beanDef.getName(), beanDef);

			List<Element> $properties = $bean.elements();
			for (Element $property : $properties) {
				BeanProperty beanProp = new BeanProperty();
				beanProp.setPropertyName($property.attributeValue("name"));
				beanProp.setPropertyValue($property.attributeValue("value"));
				beanProp.setPropertyRef($property.attributeValue("ref"));

				beanDef.addPropertie(beanProp);
			}
		}

		return result;
	}
}
