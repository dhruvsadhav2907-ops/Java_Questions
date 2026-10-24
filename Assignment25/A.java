class ThreadImpl extends Thread{
    static int count =0;
    public void run(){
        System.out.println("Hi");
        System.out.println("A new thread is being implemented");
        count++;
        System.out.println("Thread number :"+count);
    }
}

public class A{
    public static void main(String[] args) {
        ThreadImpl t1 = new ThreadImpl();
        t1.start(); 
        ThreadImpl t2 = new ThreadImpl();
        t2.start();
        ThreadImpl t3 = new ThreadImpl();
        t3.start();
    }
}