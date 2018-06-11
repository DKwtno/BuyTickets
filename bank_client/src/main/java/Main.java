import com.sorahjy.rpc.BankServiceInterface;
import com.sorahjy.rpc.CommandLineClient;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {
    public static void main(String[] args) throws Exception {
        String url="rmi://localhost:8000/";
        Context namingContext=new InitialContext();
        BankServiceInterface service= (BankServiceInterface) namingContext.lookup(url + "Service");
        System.out.println("Service start...");

        // ServiceFactory factory =new BankServiceFactory();
        // BankServiceInterface service=factory.call(BankServiceInterface.class,"localhost",8000);



        //启动命令行Client
        CommandLineClient client = new CommandLineClient(service);




//        UserData user=new UserData("sorahjy","123");
//        UserData user2= new UserData("hjy", "123");
//        System.out.println(service.register(user));
//        System.out.println(service.login(user2));
//        System.out.println(service.addMoney(user,100));
//        System.out.println(service.getBalance(user));
//        System.out.println(service.withdrawMoney(user,5000));
//        System.out.println(service.withdrawMoney(user,100));
//        System.out.println(service.getBalance(user)+" "+service.getBalance(user2));
//        System.out.println(service.transfer(user,5000,"hjy"));
//        System.out.println(service.getBalance(user) + " " + service.getBalance(user2));
//        System.out.println(service.transfer(user, 100, "hjy"));
//        System.out.println(service.getBalance(user) + " " + service.getBalance(user2));
//        System.out.println(service.transfer(user,300,"hah"));
//        System.out.println(service.addMoney(user, 100));


    }

}
