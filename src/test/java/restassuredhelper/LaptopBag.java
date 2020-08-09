package restassuredhelper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="Laptop")
public class LaptopBag {
	private String brandName;
	private String Id;
	private String LaptopName;
	private Features Feature;
	
	@XmlElement(name="BrandName")
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@XmlElement(name="Id")
	public String getId() {
		return Id;
	}
	
	public void setId(String id) {
		Id = id;
	}
	
	@XmlElement(name="LaptopName")
	public String getLaptopName() {
		return LaptopName;
	}
	
	public void setLaptopName(String laptopName) {
		LaptopName = laptopName;
	}
	
	@XmlElement(name="Features")
	public Features getFeature() {
		return Feature;
	}
	
	public void setFeature(Features feature) {
		Feature = feature;
	}
}
