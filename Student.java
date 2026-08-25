package StudentPackage;

public class Student{
	String name;
	int RollNumber;
	String Department;
	char grade;
	int batch;
	
	public Student(String name, int RollNumber, String Department, char grade, int batch){
		this.name=name;
		this.RollNumber=RollNumber;
		this.Department=Department;
		this.grade=grade;
		this.batch=batch;
	}
		
	public void DisplayDetails(){
		System.out.println("Student's Name : "+name);
		System.out.println("Student's Roll No. : "+RollNumber);
		System.out.println("Student's Department : "+Department);
		System.out.println("Student's Grade : "+grade);
		System.out.println("Student's batch : "+batch);
	}

	public static void main(String[] args){
		System.out.println("Student Package Called");
	}
}