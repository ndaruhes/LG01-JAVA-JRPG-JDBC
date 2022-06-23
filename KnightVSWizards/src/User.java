public class User {
	private String userID;
	private String username;
	private String password;
	private String className;
	private String countryID;
	private Integer level;
	private Integer exp;
	private Integer score;
	
	public User(String userID, String username, String password, String className, String countryID, Integer level,
			Integer exp, Integer score) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.className = className;
		this.countryID = countryID;
		this.level = level;
		this.exp = exp;
		this.score = score;
	}

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
