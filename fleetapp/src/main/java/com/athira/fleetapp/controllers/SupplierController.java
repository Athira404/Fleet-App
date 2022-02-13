package com.athira.fleetapp.controllers;

import com.athira.fleetapp.entities.Supplier;
import com.athira.fleetapp.entities.Supplier;
import com.athira.fleetapp.services.*;
import com.athira.fleetapp.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private StateService stateService;


    @GetMapping("/suppliers")
    public String getSuppliers(Model model) {
//        List<Supplier> supplierList = supplierService.getSuppliers();
//        model.addAttribute("suppliers", supplierList);
//
//        List<Country> countryList = countryService.getCountries();
//        model.addAttribute("countries", countryList);

//        List<State> stateList = stateService.getStates();
//        model.addAttribute("states", stateList);


        model.addAttribute("suppliers", supplierService.getSuppliers());
        model.addAttribute("countries", countryService.getCountries());
        model.addAttribute("states", stateService.getStates());

        return "Supplier";
    }

    @PostMapping("/suppliers/addNew")
    public String addNew(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @RequestMapping("suppliers/findById")
    @ResponseBody
    public Optional<Supplier> findById(Integer id) {
        return supplierService.findById(id);
    }

    @RequestMapping(value="/suppliers/update", method= {RequestMethod.PUT, RequestMethod.GET})
    public String update(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @RequestMapping(value="/suppliers/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }

}
