package com.sorahjy.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankService extends UnicastRemoteObject implements BankServiceInterface{
    private static MySqlConnector connector=new MySqlConnector();
    private static final long serialVersionUID=1L;
    public BankService() throws RemoteException {
        super();
    }
    @Override
    public boolean login(UserData userData) throws RemoteException{
        return connector.login(userData.getUsername(),userData.getPasswd());
    }

    @Override
    public boolean register(UserData userData) throws RemoteException{
        if (!connector.checkUser(userData.getUsername())) {
            connector.register(userData.getUsername(),userData.getPasswd());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public double getBalance(UserData userData) throws RemoteException{
        return connector.getBalance(userData.getUsername());
    }

    @Override
    public double addMoney(UserData userData, double money) throws RemoteException{
        double balance = getBalance(userData)+money;
        connector.setBalance(userData.getUsername(),balance);
        return balance;
    }

    @Override
    public double withdrawMoney(UserData userData, double money) throws RemoteException{
        double balance=getBalance(userData);
        balance -= money;
        if(balance>0){
            connector.setBalance(userData.getUsername(),balance);
        }
        return balance;
    }

    @Override
    public String transfer(UserData userData, double money, String username2) throws RemoteException {
        if(connector.checkUser(username2)){
            if(withdrawMoney(userData,money)<0){
                return "错误！您的账户余额不足！";
            }else {
                double b2=connector.getBalance(username2)+money;
                connector.setBalance(username2,b2);
                return "提示，转账成功！";
            }
        }else {
            return "错误！用户不存在！！";
        }

    }
}
