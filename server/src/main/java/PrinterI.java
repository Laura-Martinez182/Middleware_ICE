import java.util.regex.*;
import com.zeroc.Ice.Current;
public class PrinterI implements Demo.Printer
{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void printString(String s, com.zeroc.Ice.Current current)
    {
        System.out.println(s);
    }

    public void verifyGUID(String guid, int n, com.zeroc.Ice.Current current){
        String regex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(guid).matches()){
            System.out.println(ANSI_GREEN+guid+" VALID_REQUEST"+ANSI_RESET);
            System.out.println("The closest prime number to "+n+" is "+searchNextPrime(n, current));
        }else{
            System.out.println(ANSI_RED+guid+" INVALID_REQUEST"+ANSI_RESET);
            System.out.println("1");
        }
    }

    public int searchNextPrime(int n, Current current){
        int cont=1, prime;
        while(isPrime(n+cont, current)==false){
            cont+=1;
        }
        prime=n+cont;
        return prime;
    }

    public boolean isPrime(int n, Current current){
        boolean answer=true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                answer=false;
            }
        }
        return answer;
    }

}