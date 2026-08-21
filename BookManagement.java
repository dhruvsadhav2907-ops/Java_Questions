class Library{
    String Title;
    final int ISBN;
    double price;
    String author;

    Library(String Title, int ISBN, double price,String Author){
        this.Title = Title;
        this.ISBN = ISBN;
        this.price = price;
        this.author = Author;
    }

    void DisplayBookDetails(){
        System.out.println("Book Title : "+Title);
        System.out.println("ISBN : "+ISBN);
        System.out.println("Price in Rupees : "+price);
        System.out.println("Author of the book : "+author);
    }
}

public class BookManagement{
    public static void main(String[] args){
        Library l = new Library("Harry Potter",19148934,750.99,"J.K. Rowling");
        System.out.println("The Details of Book are added and ISBN Cannot change now!");
        l.DisplayBookDetails(); 
    }
}