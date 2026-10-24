class Mytask implements Runnable{
    public void run(){
        for(int i=0;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+" : "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("e");
            }
        }
    }
}


public class RunnableMultiThread{
    public static void main(String[] args) {
        //create Runnable objects
        Mytask task1 = new Mytask();
        Mytask task2 = new Mytask();
        Mytask task3 = new Mytask();

        //create Thread objects
        Thread t1 = new Thread(task1,"Thread -1");
        Thread t2 = new Thread(task2,"Thread -2");
        Thread t3 = new Thread(task3,"Thread -3");
        t1.start();
        t2.start();
        t3.start();
    }
}