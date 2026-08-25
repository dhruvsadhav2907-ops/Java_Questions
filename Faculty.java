package FacultyPackage;

public class Faculty{
	String name;
	String department;
	int salary;
	String degree;
	int fid;
	
	public Faculty(String name, String department, int salary, String degree, int fid){
		this.name=name;
		this.department=department;
		this.salary=salary;
		this.degree=degree;
		this.fid=fid;
	}

	public void displayDetails(){
		System.out.println("Faulty Name : "+name);
		System.out.println("Faulty's Department : "+department);
		System.out.println("Faulty Salary : "+salary);
		System.out.println("Faulty's Degree  : "+degree);
		System.out.println("Faulty ID : "+fid);
	}

	public static void main(String[] main){
		System.out.println("Faculty package called");
	}
}