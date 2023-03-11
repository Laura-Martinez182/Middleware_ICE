import java.util.regex.*;
import com.zeroc.Ice.Current;
public class PrinterI implements Demo.Printer
{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public int message(String guid, int number, Current current){
        String regex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(guid).matches()){
            System.out.println(ANSI_GREEN+guid+" VALID_REQUEST"+ANSI_RESET);
            return searchNextPrime(number);
            
        }else{
            System.out.println(ANSI_RED+guid+" INVALID_REQUEST"+ANSI_RESET);
            return 1;
        }
    }

    private int searchNextPrime(int number){
        int cont = 1, prime;
        while(isPrime(number + cont) == false){
            cont += 1;
        }
        prime = number + cont;
        return prime;
    }

    private boolean isPrime(int number){
        boolean answer=true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                answer=false;
            }
        }
        return answer;
    }

}