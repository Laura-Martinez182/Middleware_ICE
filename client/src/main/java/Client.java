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
                boolean response = verifyNumber(args[0]);
                if (response == false) {
                    printer.printString("Debe ingresar un numero entero positivo mayor que 1");    
                } else if(args.length == 1) {
                    printer.printString(generateRandomGUII());
                }else{
                    boolean exist = fileExists(args[1]);
                    if(exist == true) {
                        printer.printString("File exists!!");
                        String uuid = readUUID(args[1]);
                        printer.printString(uuid);
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
        boolean exist = true;
         
        for (File search : files) {
            if (search.getName().equals(file)) {
                exist = true;
            } else {
                exist = false;
            }     
        }
        return exist; 
    }

    public static boolean verifyNumber(String input){
        boolean response = true;
        int inputNumber = 0;
        if(input == null){
            response = false;
        }else{
            try{
                inputNumber = Integer.parseInt(input);
                if(inputNumber < 1){
                    response = false;
                }
            } catch (NumberFormatException nfe) {
                response = false;
            }
        }
        return response;
    }

    public static String generateRandomGUII(){
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        return uuidString;
    }

    public static String readUUID (String file) {
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
