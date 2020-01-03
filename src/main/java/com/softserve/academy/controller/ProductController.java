package com.softserve.academy.controller;

import com.softserve.academy.model.Product;
import com.softserve.academy.model.Store;
import com.softserve.academy.model.User;
import com.softserve.academy.repository.ProductRepository;
import com.softserve.academy.repository.StoreRepository;
import com.softserve.academy.security.WebAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private StoreRepository storeRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String create(ModelMap model) {
        Product product = new Product();
        List<Store> stores = storeRepository.readAll();
        model.addAttribute("product", product);
        model.addAttribute("stores", stores);
        return "add-product";
    }



    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') and #product.store.userId == authentication.details.id")
    public String create(@ModelAttribute("product") Product product) {
        productRepository.create(product);
        return "redirect:list";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    @PostAuthorize("hasRole('ADMIN') or hasRole('USER') and #product.store.userId == authentication.details.id")
    public String update(@RequestParam("id") int id, ModelMap model, Product product) {
        product = productRepository.readById(id);
        System.out.println(product);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute("product") Product product) {
        productRepository.update(product);
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

    public User getUser() {
        WebAuthentication webAuthentication = (WebAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return (User) (webAuthentication.getUserDetails());
    }
}
