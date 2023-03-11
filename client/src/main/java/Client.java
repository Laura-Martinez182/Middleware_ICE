import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;


public class Client
{
    public static void main(String[] args)
    {
        try(Communicator communicator = Util.initialize(args, "client.cfg"))
        {
            ObjectPrx base = communicator.propertyToProxy("Service.Proxy");
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }    
            
            try {
                if (args.length == 0) {
                    System.out.println("Debe ingresar al menos un entero positivo y opcionalmente un nombre de archivo de texto local \n" + "Autoras: \n" + "Keren Lopez - A00368902 \n" +
                    "Laura Martinez - A00365187");
                } else{
                    int number = verifyNumber(args[0]);
                    if (number == -1) {
                        System.out.println("Debe ingresar un numero entero positivo mayor que 1"); 
                    } else if(args.length == 1) {
                        String guid = generateRandomGUID();
                        int msg = printer.message(guid, number);
                        System.out.println(msg);
                    }else{
                        boolean exist = fileExists(args[1]);
                        if(exist == true) {
                            System.out.println("Archivo encontrado");
                            String guid = readGUID(args[1]);
                            int msg = printer.message(guid, number);
                            System.out.println(msg);
                        } else {
                            System.out.println("El archivo no existe o el programa no puede acceder a el");
                        }
                    }
                }
            } catch (ConnectionRefusedException e) {
                System.out.println("test");
            }
                
                
        } 
    }

    public static boolean fileExists(String file) {
        String project = System.getProperty("user.dir");
        File directory = new File(project);
        File[] files = directory.listFiles();
        boolean exist = false;
        
        for (File search : files) {
            if (search.getName().equals(file)) {
                exist = true;
            } 
        }
        return exist; 
    }    

    public static int verifyNumber(String input){
        int inputNumber=0, response=0;
        if(input == null){
            response = -1;
        }else{
            try{
                inputNumber = Integer.parseInt(input);
                if(inputNumber <= 1){
                    response = -1;
                }else{
                    response=inputNumber;
                }
            } catch (NumberFormatException nfe) {
                response = -1;
            }
        }
        return response;
    }

    public static String generateRandomGUID(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString;
    }

    public static String readGUID (String file) {
		BufferedReader reader;
        String line = "";

		try {
			reader = new BufferedReader(new FileReader(file));
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return line;
	}

}
