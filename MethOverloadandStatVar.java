class MethOverloadandStatVarA{
    static int count;
    int a;
    int b; 

    MethOverloadandStatVarA() {
        System.out.println("Inside Default Constructor");
        a=10;
        b=20;
        count++;
 
    }

    MethOverloadandStatVarA(int p, int q) {
        System.out.println("Inside Parameterized contructor");
        a=p;
        b=q;
        count++;
    }
    void show(){
        System.out.println("Value of a:"+a);
        System.out.println("Value of b:"+b);
    }
    void show(int p, int q){
        System.out.println("Value of a:"+p);
        System.out.println("Value of a:"+q);

    }
    void show(int p){
        System.out.println("Passed value : "+p);
    }
    static void countShow(){
        System.out.println("Total number of object :"+count);
    }
    
}
public class MethOverloadandStatVar{
    public static void main(String[] args) {
        MethOverloadandStatVarA m1 = new MethOverloadandStatVarA();
        MethOverloadandStatVarA.countShow();
        m1.show();

        MethOverloadandStatVarA m2 =new MethOverloadandStatVarA(56,89);

        MethOverloadandStatVarA.countShow();
        m2.show(m2.a,m2.b);
        MethOverloadandStatVarA m3= new MethOverloadandStatVarA(100,200);
        m3.show(m3.a);
        m3.show(m3.b);
    }
}