package LibraryPackage;

public class Library{
	int bid;
	String title;
	String author;
	int price;

	public Library(int bid,String title,String author,int price){
		this.bid=bid;
		this.title=title;
		this.author=author;
		this.price=price;
	}

	public void displayDetails(){
		System.out.println("Book ID : "+bid);
		System.out.println("Book Title : "+title);
		System.out.println("Book Author : "+author);
		System.out.println("Book Price : "+price);
	}
	
	public static void main(String[] args){
		System.out.println("Library package called");
	}

}