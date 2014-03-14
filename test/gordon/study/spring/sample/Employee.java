package gordon.study.spring.sample;

import java.util.Date;

public class Employee {

	private String name;

	private String address;

	private int age;

	private Date birthday;

	private Float salary;

	private boolean male;

	public void setMale(String YorN) {
		male = "Y".equals(YorN);
		throw new RuntimeException("should not invoke this method.");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ").append(getName()).append("\n");
		sb.append("Age: ").append(getAge()).append("\n");
		sb.append("Birthday: ").append(getBirthday()).append("\n");
		sb.append("Salary: ").append(getSalary()).append("\n");
		sb.append("Male: ").append(isMale()).append("\n");

		return sb.toString();
	}

}
