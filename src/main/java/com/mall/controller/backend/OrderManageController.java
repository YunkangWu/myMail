package com.mall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.service.IOrderService;
import com.mall.service.IUserService;
import com.mall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        //全部通过拦截器验证是否登录以及权限
        return iOrderService.manageList(pageNum, pageSize);

    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(Long orderNo,HttpServletRequest request) {
        return iOrderService.manageDetail(orderNo);
    }


    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,HttpServletRequest request) {
        return iOrderService.manageSearch(orderNo, pageNum, pageSize);
    }


    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(Long orderNo, HttpServletRequest request) {
        return iOrderService.manageSendGoods(orderNo);
    }


}
