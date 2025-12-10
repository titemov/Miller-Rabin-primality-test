import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(System.in);
            boolean isTwo=false;
            boolean result=false;

            //2305843009213693951 - prime
            System.out.print("Enter a number: ");
            long inputNumber = scanner.nextLong();

            if(inputNumber==2){
                isTwo=true;
            }

            if(inputNumber>Math.pow(2,62)-1 || inputNumber<2){
                throw new InputMismatchException();
            }
            if (inputNumber % 2 == 0) {
                if(!isTwo) throw new Exception("Given number is even");
            }

            System.out.print("Enter repeats: ");
            long repeats = scanner.nextLong();
            if(repeats<1 || repeats>Math.pow(2,62)-1) throw new Exception("Incorrect repeat count");

            System.out.println("Input number: "+inputNumber+"\n"+"Repeats: "+repeats);

            if(!isTwo){
                Backend b = new Backend(inputNumber,repeats);
                result = b.run();
            }

            if(result || isTwo){
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
