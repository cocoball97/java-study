package prob04;

public class Depart extends Employee {	
	private String department;
	
	Depart(String name, int salary, String department){
		this.setName(name);
		this.setSalary(salary);
		this.setDepartment(department);
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
}
