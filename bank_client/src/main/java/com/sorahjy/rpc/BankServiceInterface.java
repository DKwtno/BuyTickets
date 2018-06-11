// package com.sorahjy.rpc;
//
// public interface BankServiceInterface {
//     boolean login(UserData userData);
//     boolean register(UserData userData);
//     double getBalance(UserData userData);
//     double addMoney(UserData userData, double money);
//     double withdrawMoney(UserData userData, double money);
//     String transfer(UserData userData, double money, String username2);
// }


package com.sorahjy.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankServiceInterface extends Remote {

    boolean login(UserData userData) throws RemoteException;

    boolean register(UserData userData) throws RemoteException;

    double getBalance(UserData userData) throws RemoteException;

    double addMoney(UserData userData, double money) throws RemoteException;

    double withdrawMoney(UserData userData, double money) throws RemoteException;

    String transfer(UserData userData, double money, String username2) throws RemoteException;
}

