import Mysqpackage.Square;
import Myrectpackage.Rectangle;

public class MainTest {
    public static void main(String[] args) {
        
        // 1. Using Square2 from mysqpackage
        Square sq = new Square();
        System.out.println("Square Area (4 x 4): " + sq.displayIntArea(4));

        // 2. Using Rectangle from myRectPackage
        Rectangle rect = new Rectangle();
        System.out.println("Rectangle Area (5 x 10): " + rect.displayIntArea(5, 10));
    }
}