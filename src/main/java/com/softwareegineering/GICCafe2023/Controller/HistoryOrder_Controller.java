package com.softwareegineering.GICCafe2023.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.OrderItemManagement;
import com.softwareegineering.GICCafe2023.Model.OrderItem;


@Controller
public class HistoryOrder_Controller {
    
    @GetMapping(path = "/historyorder")
    public ModelAndView displayPage(Model model) {

        List<OrderItem>  ot = getOrdersItems();
        model.addAttribute("orderItems", getOrdersItems());
        return new ModelAndView("HistoryOrder");
    }

    private List<OrderItem> getOrdersItems() {
        OrderItemManagement orderItemManagement = new OrderItemManagement();
        return orderItemManagement.getAllOrderItems();
    }

}