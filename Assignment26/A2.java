class ThreadImpl implements Runnable{
    public void run(){
        System.out.println("Hi");
    }
}

public class A2{
    public static void main(String[] args) {
        ThreadImpl t1 = new ThreadImpl();
        ThreadImpl t2 = new ThreadImpl();
        Thread tt = new Thread(t2);
        Thread t = new Thread(t1);
        t.start();
        tt.start();
    }
}