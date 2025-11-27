import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number: ");
            long num = scanner.nextLong();
            if (num % 2 == 0) {
                throw new Exception("Given number is even");
            }
            if(num>Math.pow(2,62)-1){
                throw new InputMismatchException();
            }
            System.out.print("Enter amount of repeats: ");
            long repeats = scanner.nextLong();

            Backend b = new Backend(num,repeats);
            boolean res = b.run();
            if(res){
                System.out.println("==============================");
                System.out.println("||   This number is prime   ||");
                System.out.println("==============================");
            }else{
                System.out.println("==================================");
                System.out.println("||   This number is not prime   ||");
                System.out.println("==================================");
            }

        }catch (InputMismatchException e){
            System.out.println("Given number is greater than 2^62-1 or not a number.");
        }catch (Exception e) {
            System.out.println("Error! " + e);
        }
    }
}
