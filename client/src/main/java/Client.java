import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class Client
{
    public static void main(String[] args)
    {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            if(printer == null)
            {
                throw new Error("Invalid proxy");
            }    
             

           if (args.length == 0) {
            printer.printString("Debe ingresar al menos un entero positivo y opcionalmente un nombre de archivo de texto local \n" + "Autoras: \n" + "Keren Lopez - A00368902 \n" +
            "Laura Martinez - A00365187");
           } else{
                int number = verifyNumber(args[0]);
                if (number == -1) {
                    printer.printString("Debe ingresar un numero entero positivo mayor que 1");    
                } else if(args.length == 1) {
                    String guid = generateRandomGUID();
                    printer.verifyGUID(guid, number);
                }else{
                    boolean exist = fileExists(args[1]);
                    if(exist == true) {
                        printer.printString("File exists!!");
                        String guid = readGUID(args[1]);
                        printer.verifyGUID(guid, number);
                    } else {
                        printer.printString("File doesn't exist or program doesn't have access to the file");
                    }
                }
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

    /*public static void routes() {
        String project = System.getProperty("user.dir");
        File directory = new File(project);
        File[] files = directory.listFiles();

        for (File search : files) {
            String f = search.getName();
            System.out.println(f);
            System.out.println("Found the file: " + search.getAbsolutePath());
        }    
    }*/

    public static int verifyNumber(String input){
        int inputNumber=0, response=0;
        if(input == null){
            response = -1;
        }else{
            try{
                inputNumber = Integer.parseInt(input);
                if(inputNumber < 1){
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
