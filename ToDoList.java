import java.util.ArrayList;
public class ToDoList{

    public static void main(String[] args) {
        
        ArrayList<String> todoList = new ArrayList<>();

    
        todoList.add("Complete Java Assignment 5");
        todoList.add("Review Linear Algebra concepts");
        todoList.add("Prepare slides for AI presentation");
        todoList.add("Buy groceries");

        System.out.println("The To do List is preapared and it looks like :");
        
      
        displayTodoList("Initial Tasks", todoList);


        todoList.remove("Buy groceries");                  
        todoList.set(0, "Submit Java Assignment 5");       
        todoList.add(1, "Read Operating Systems chapter"); 

        
        displayTodoList("Updated Tasks", todoList);
    }

    
    public static void displayTodoList(String title, ArrayList<String> tasks) {
        
        StringBuffer displayBuffer = new StringBuffer();
        displayBuffer.append(" ").append(title.toUpperCase()).append(" (Total: ").append(tasks.size()).append(")\n");
        if (tasks.isEmpty()) {
            displayBuffer.append(" No pending tasks!\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                displayBuffer.append(tasks.get(i)).append("\n");
            }
        }
        System.out.println(displayBuffer.toString());
    }
}