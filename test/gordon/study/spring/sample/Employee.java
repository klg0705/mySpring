package gordon.study.spring.sample;

public class Employee {

	private String name;

	private String address;

	private Long badgeNumber;

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

	public Long getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(Long badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public void welcome() {
		System.out.printf("Welcome to our company, %s!", getName());
	}

}
