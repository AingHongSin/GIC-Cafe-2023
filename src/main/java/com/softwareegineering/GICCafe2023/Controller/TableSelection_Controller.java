package com.softwareegineering.GICCafe2023.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.TableManagement;
import com.softwareegineering.GICCafe2023.Model.*;


@Controller
public class TableSelection_Controller {
    
    @GetMapping(path = "/tableselection")
    public ModelAndView displayPage(Model model) {

        ArrayList<String> tableType = new ArrayList<String>();
        tableType.add("Normal");
        tableType.add("Delivery");

        model.addAttribute("tables", getAllTable());
        model.addAttribute("tableType", tableType);
        return new ModelAndView("/tableselection");
    }

    private List<Table> getAllTable() {
        TableManagement tableManagement = new TableManagement();
        return tableManagement.getAllTables();
    }

    

}