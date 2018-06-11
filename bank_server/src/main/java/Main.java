import com.sorahjy.rpc.BankService;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) throws Exception {

        // System.setProperty("java.rmi.server.hostname", "123.206.214.219");
        LocateRegistry.createRegistry(8000);
        // ServiceFactory factory=new BankServiceFactory();
        // factory.startService(new BankService(),8000);
        BankService service=new BankService();
        Context namingContext=new InitialContext();
        namingContext.rebind("rmi://localhost:8000/Service",service);



        System.out.println("服务开始......");
    }
}
