public class User {
    public String name;
    public String dateOfBirth;
    
    public User(String name, String dob) {
		this.name = name;
		this.dateOfBirth = dob;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
