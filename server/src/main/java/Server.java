import com.zeroc.Ice.DNSException;
import com.zeroc.Ice.SocketException;

public class Server
{
    public static void main(String[] args)
    {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args, "server.cfg"))
        {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Service");
            com.zeroc.Ice.Object object = new PrinterI();
            adapter.add(object, com.zeroc.Ice.Util.stringToIdentity("SimplePrinter"));
            adapter.activate();
            communicator.waitForShutdown();
        }catch (SocketException u) {
            System.out.println("No se pudo asignar la direccion IP o el puerto solicitado al socket, verifique que la direccion IP del servidor este correcta y que el puerto este disponible.");
        } catch (DNSException k) {
            System.out.println("El nombre del host es desconocido");
        }
    }
}