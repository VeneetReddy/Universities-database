package db.entities;

public class University extends DatabaseEntity {
	@Override
	public String toString() {
		return "University [universityId=" + universityId + ", name=" + name + "]";
	}

	private int universityId;
	private String name;

	public University(int universityId) {
		this.universityId = universityId;
	}

	public University(int universityId, String name) {
		this.universityId = universityId;
		this.name = name;
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
