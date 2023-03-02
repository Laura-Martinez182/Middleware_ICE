import java.io.File;

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
            
            // printer.printString("Arguments:");
            //     for(String i : args)
            //     {
            //        printer.printString(i);
            //     } 
            
             
            //boolean response = verifyNumber(args[0]);
            

        //    if (args.length == 0) {
        //     printer.printString("Debe ingresar al menos un entero positivo y opcionalmente un nombre de archivo de texto local \n" + "Autoras: \n" + "Keren Lopez - A00368902 \n" +
        //     "Laura Martinez - A00365187");
        //    } else if (args.length == 1) {
        //     boolean response = verifyNumber(args[0]);
        //     if (response == false) {
        //         printer.printString("Debe ingresar un numero entero positivo mayor que 1");    
        //     }
        //    }

            
            // if(response == false){
            //     printer.printString("The number is not valid");
            // }
            
            //fileExists();
            boolean exist = fileExists(args[1]);

            if(exist == true) {
                printer.printString("File exists!!");
            } else {
                printer.printString("File doesn't exist or program doesn't have access to the file");
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

    // public static void fileExists() {
    //     String project = System.getProperty("user.dir");
    //     File directory = new File(project);
    //     File[] files = directory.listFiles();

    //     for (File search : files) {
    //         String f = search.getName();
    //         System.out.println(f);
    //         System.out.println("Found the file: " + search.getAbsolutePath());
    //     }
    // }


    public static boolean verifyNumber(String input){
        boolean response = true;
        int inputNumber = 0;
        if(input == null){
            response = false;
        }else{
            try{
                inputNumber = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                response = false;
            }
        }
        if(inputNumber < 1){
            response = false;
        }
        return response;
    }

}
