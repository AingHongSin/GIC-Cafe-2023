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
public class TableMangemet_Controller {
    
    @GetMapping(path = "/table")
    public ModelAndView displayPage(Model model) {


        model.addAttribute("tables", getAllTable());
        model.addAttribute("tableType", getTableType());
        return new ModelAndView("TableManagement");
    }

    @GetMapping("/table/deleteTable")
    public ModelAndView deleteTableHandlerAndView(Model model,
        @RequestParam("deleteTableID") int deleteTableID) {
        
        TableManagement tableManagement = new TableManagement();
        tableManagement.deleteTable(deleteTableID);

        model.addAttribute("tables", getAllTable());
        model.addAttribute("tableType", getTableType());
        return new ModelAndView("TableManagement");
    }

    @GetMapping("/table/emptyTable")
    public ModelAndView addEmptyTableHandlerAndView(Model model) {
        
        List<Table> tableList = getAllTable();
        Table table = new Table(0, 0, "Normal", "Available"); 
        tableList.add(table);

        model.addAttribute("tables", tableList);
        model.addAttribute("tableType", getTableType());
        return new ModelAndView("TableManagement");
    }    

    // @GetMapping("/table/emptyTable/addtable")
    // public ModelAndView addTableHandler(Model model,
    //     @RequestParam("tableID") int tableID,
    //     @RequestParam("tableNumber") int tableNumber,
    //     @RequestParam("tableType") String tableType,
    //     @RequestParam("tableStatus") String tableStatus) {  
            
    //     List<Table> tableList = getAllTable();

    //     if (tableID == 0 || tableNumber == 0 || tableStatus == "null" || tableType == null) {

    //     } else {
    //         Table table = new Table(tableID, tableNumber, tableType, tableStatus);
            
    //         TableManagement tableManagement = new TableManagement();
    //         tableManagement.addTable(table);

    //     }

    //     model.addAttribute("tables", tableList);
    //     model.addAttribute("tableType", getTableType());
    //     return new ModelAndView("TableManagement");
    // }

    @PostMapping("/table/updatetable")
    public ModelAndView updateTableHandlerAndView(Model model,
        @RequestParam("tableID") int tableID,
        @RequestParam("tableNumber") int tableNumber,
        @RequestParam("tableType") String tableType) {
        
        Table table = new Table();
        if (tableID == 0) {
            table.setId(0);
            table.setTableNumber(tableNumber);
            table.setTableStatus("Available");
            
            TableManagement tableManagement = new TableManagement();
            tableManagement.addTable(table);
        } else {
            
            TableManagement tableManagement = new TableManagement();
            table = tableManagement.getTableById(tableID);
            table.setTableNumber(tableNumber);
            table.setTableType(tableType);
            tableManagement.updateTable(table);
        }


        model.addAttribute("tables", getAllTable());
        model.addAttribute("tableType", getTableType());
        return new ModelAndView("TableManagement");
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