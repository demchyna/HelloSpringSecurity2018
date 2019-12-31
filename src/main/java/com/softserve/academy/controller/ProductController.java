package com.softserve.academy.controller;

import com.softserve.academy.model.Product;
import com.softserve.academy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String create(ModelMap model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String create(@ModelAttribute("product") Product product) {
        productRepository.create(product);
        return "redirect:list";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String update(@RequestParam("id") int id, ModelMap model) {
        Product product = productRepository.readById(id);
        System.out.println(product);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute("product") Product product) {
        productRepository.update(product);
        System.out.println(product);
        return "redirect:list";
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id) {
        productRepository.delete(id);
        return "redirect:list";
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<Product> products = productRepository.readAll();
        model.addAttribute("products", products);
        return "products-list";
    }
}
