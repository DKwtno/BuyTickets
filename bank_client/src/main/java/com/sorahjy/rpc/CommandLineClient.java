package com.sorahjy.rpc;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CommandLineClient {
    private BankServiceInterface service=null;
    private String username=null;
    private static Scanner in = new Scanner(System.in);
    private UserData user=null;

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
    public CommandLineClient(BankServiceInterface service) throws RemoteException {
        this.service = service;
        username="游客";
        while (true) {
            showGUI();



        }

    }
    private void login() throws RemoteException {
        System.out.println("请输入您的账户：");
        String name=in.next();
        System.out.println("请输入您的密码：");
        String password=in.next();
        user = new UserData(name, password);
        if (service.login(user)) {
            username=name+"";
            System.out.println("登录成功！");
        } else {
            System.out.println("您的账户或密码有误，请重新输入！");
        }

    }

    private void register() throws RemoteException {
        System.out.println("请输入您要注册的账户：");
        String name = in.next();
        System.out.println("请输入您要注册的密码：");
        String password = in.next();
        user = new UserData(name, password);
        if (service.register(user)) {
            username = name + "";
            System.out.println("注册成功！");
        } else {
            System.out.println("您的账户名已被注册，请重新输入！");
        }

    }
    private void withDrawMoney() throws RemoteException {
        System.out.print("请输入您的取钱金额：");

        String money = in.next();
        if (isInteger(money)) {
            if (service.withdrawMoney(user, Double.parseDouble(money) )< 0) {
                System.out.println("您的余额不足！操作失败");
            } else {
                System.out.println("取钱成功，这是 " + money + " 元钱。");
            }
        } else {
            System.out.println("您输入的金额非法！请重新输入");
        }




    }
    private void transferMoney() throws RemoteException {
        System.out.print("请输入对方账户名：");
        String user2=in.next();
        System.out.print("请输入您的转账金额：");
        String money = in.next();
        if(isInteger(money)) {
            System.out.println(service.transfer(user, Double.parseDouble(money), user2));
        }else {
            System.out.println("您输入的金额非法！请重新输入");
        }

    }
    private void addMoney() throws RemoteException {
        System.out.print("请输入您存钱的金额：");

        String money = in.next();
        if (isInteger(money)) {
            service.addMoney(user, Double.parseDouble(money));
            System.out.println("操作成功");
        } else {
            System.out.println("您输入的金额非法！请重新输入");
        }




    }
    private void showGUI() throws RemoteException {
        int count=1;
        System.out.println("您好， " + username+"\n");
        if(username.equals("游客")){
            System.out.println((count++)+"..注册");
            System.out.println((count++) + "..登录");
            System.out.print("请输入功能编号：");
            String num=in.next();
            System.out.println("*************************************");
            switch (num) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                default:
                    System.out.println("输入错误，请重新输入!");
                    break;
            }
        }else {

            System.out.println((count++) + "..登出");
            System.out.println((count++) + "..存钱");
            System.out.println((count++)+"..取钱");
            System.out.println((count++)+ "..转账");
//            System.out.println((count++)+"..查看余额");
            System.out.print("您的余额为： ");
            System.out.printf("%.2f\n", service.getBalance(user));
            System.out.print("请输入功能编号：");
            String num = in.next();
            System.out.println("*************************************");
            switch (num) {
                case "1":
                    username="游客";
                    break;
                case "2":
                    addMoney();
                    break;
                case "3":
                    withDrawMoney();
                    break;
                case "4":
                    transferMoney();
                    break;
//                case "5":
//                    break;
                default:
                    System.out.println("输入错误，请重新输入!");
                    break;
            }
        }
        System.out.println("*************************************");
    }






    public BankServiceInterface getService() {
        return service;
    }

    public void setService(BankServiceInterface service) {
        this.service = service;
    }
}
