public class Country {
	private String countryID;
	private String countryName;
	
	public Country(String countryID, String countryName) {
		super();
		this.countryID = countryID;
		this.countryName = countryName;
	}

	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public void printInfo(int index) {
		System.out.println(" " + index + ". " + countryName);
	}
}
