package com.softwareegineering.GICCafe2023.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softwareegineering.GICCafe2023.DatabaseManagement.TableManagement;
import com.softwareegineering.GICCafe2023.Model.*;


@Controller
public class TableSelection_Controller {
    
    @GetMapping(path = "/tableselection")
    public ModelAndView displayPage(Model model) {


        model.addAttribute("tables", getAllTable());
        model.addAttribute("tableType", getTableType());
        return new ModelAndView("tableselection");
    }

    
    // MARK: - Private Function
    private List<Table> getAllTable() {
        TableManagement tableManagement = new TableManagement();
        return tableManagement.getAllTables();
    }
    private ArrayList<String> getTableType() {
        ArrayList<String> tableType = new ArrayList<String>();
        tableType.add("Normal");
        tableType.add("Delivery");
        return tableType;
    }
}
