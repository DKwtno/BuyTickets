package com.sorahjy.buytickets.controller;

import com.sorahjy.buytickets.converter.OrderMaster2OrderDTOconverter;
import com.sorahjy.buytickets.dataobject.BuyerInfo;
import com.sorahjy.buytickets.dataobject.OrderDetail;
import com.sorahjy.buytickets.dataobject.OrderMaster;
import com.sorahjy.buytickets.dataobject.TicketInfo;
import com.sorahjy.buytickets.dto.OrderDTO;
import com.sorahjy.buytickets.exception.SellException;
import com.sorahjy.buytickets.form.LoginForm;
import com.sorahjy.buytickets.service.BuyerLoginService;
import com.sorahjy.buytickets.service.OrderService;
import com.sorahjy.buytickets.service.TicketInfoService;
import com.sorahjy.buytickets.utils.KeyUtil;
import com.sorahjy.rpc.BankServiceInterface;
import com.sorahjy.rpc.UserData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class LoginController {

    @Autowired
    TicketInfoService ticketInfoService;

    @Autowired
    BuyerLoginService buyerLoginService;

    @Autowired
    OrderService orderService;

    @GetMapping("/login")
    public ModelAndView index(Map<String,Object> map) {

        return new ModelAndView("buyer/login", map);
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid LoginForm loginForm, BindingResult bindingResult, Map<String, Object> map){

        String buyerOpenid=loginForm.getBuyerName()+loginForm.getPassword();
        // BuyerInfo buyerInfo=new BuyerInfo();
        // buyerInfo.setBuyerName(loginForm.getBuyerName());
        // buyerInfo.setBuyerOpenid(buyerOpenid);
        // buyerInfo.setPassword(loginForm.getPassword());
        //
        // buyerLoginService.save(buyerInfo);

        BuyerInfo result;
        try {
            result = buyerLoginService.findBuyerInfoByBuyerOpenid(buyerOpenid);
        }catch (SellException e){
            map.put("msg", "用户不存在或密码错误");
            map.put("url","/tickets/index/login");
            return new ModelAndView("common/error",map);
        }
        if(result==null){
            map.put("msg", "用户不存在或密码错误");
            map.put("url", "/tickets/index/login");
            return new ModelAndView("common/error",map);
        }else {
            map.put("buyerInfo", result);
            map.put("url","/tickets/index/login");

            int page=1;
            int size=20;
            PageRequest request = new PageRequest(page - 1, size);
            Page<TicketInfo> ticketInfoPage = ticketInfoService.findAll(request);
            map.put("ticketInfoPage", ticketInfoPage);
            map.put("currentPage", page);
            map.put("size", size);


            return new ModelAndView("buyer/list", map);
        }


    }

    @GetMapping("/list")
    public ModelAndView buy(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            Map<String, Object> map) {

        PageRequest request = new PageRequest(page - 1, size);
        Page<TicketInfo> ticketInfoPage = ticketInfoService.findAll(request);
        map.put("ticketInfoPage", ticketInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("buyer/list", map);

    }

    @GetMapping("/ticket")
    public ModelAndView ticket(@RequestParam(value = "ticketId", required = true) String ticketId, Map<String, Object> map){
        OrderMaster orderMaster=new OrderMaster();

        String orderId=KeyUtil.genUniqueKey();
        orderMaster.setOrderId(orderId);
        orderMaster.setBuyerName("sorahjy");
        orderMaster.setBuyerPhone("18888888888");
        orderMaster.setBuyerOpenid("sorahjynihaoya12345");
        orderMaster.setOrderAmount(ticketInfoService.findOne(ticketId).getTicketPrice());
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);

        OrderDTO orderDTO=OrderMaster2OrderDTOconverter.convert(orderMaster);

        ArrayList<OrderDetail> list = new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        TicketInfo ticket=ticketInfoService.findOne(ticketId);
        BeanUtils.copyProperties(ticket,orderDetail);
        orderDetail.setDetailId(KeyUtil.genUniqueKey());
        orderDetail.setOrderId(orderId);
        orderDetail.setTicketQuantity(1);
        list.add(orderDetail);
        orderDTO.setOrderDetailList(list);


        orderService.create(orderDTO);

        try {


            String url = "rmi://localhost:8000/";
            Context namingContext = new InitialContext();
            BankServiceInterface service = (BankServiceInterface) namingContext.lookup(url + "Service");
            map.put("balance", service.getBalance(new UserData("sorahjy", "nihaoya12345")));
            String money = ticket.getTicketPrice().toString();
            if (isInteger(money)) {
                if (service.withdrawMoney(new UserData("sorahjy","nihaoya12345"), Double.parseDouble(money)) < 0) {
                    System.out.println("您的余额不足！操作失败");
                } else {
                    System.out.println("取钱成功，这是 " + money + " 元钱。");
                }
            } else {
                System.out.println("您输入的金额非法！请重新输入");
            }




        }catch (SellException e){

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        map.put("ticketPrice",ticket.getTicketPrice());
        // map.put("url", "/tickets/index/list");
        return new ModelAndView("buyer/pay",map);



    }

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

}
