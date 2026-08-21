import FacultyPackage.Faculty;
import StudentPackage.Student;

class CollegeManagement{
	public static void main(String[] args){
		Faculty f = new Faculty("Paresh Paliwal","Applied Science",40000,"M.Sc.",101);
		System.out.println("Details of Faculty are : \n");
		f.displayDetails();

		System.out.println("Details of Student are : \n");
		Student s = new Student("Dhruv Sadhav",19,"CSE",'A',1);
		s.DisplayDetails();
	}
}