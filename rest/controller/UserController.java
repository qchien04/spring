package com.example.pro.rest.controller;


import com.example.pro.rest.exception.CustomerNotFoundException;
import com.example.pro.models.User;
import com.example.pro.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pro.response.PageResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String naocac(){
        return "chao mung em";
    }

    @GetMapping("/users")
    public Page<User> getusers(@RequestParam(value="page",defaultValue = "0") int page,
                               @RequestParam(value="size",defaultValue = "2") int size){
        return userService.findAll(page,size);
    }

    @GetMapping("/users/{id}")
    public User getCustomers(@PathVariable int id){
        User customer=userService.findById(id);

        if(customer==null){
            throw new CustomerNotFoundException("Customer id not found "+ id);
        }
        return customer;
    }

    @PostMapping("/users")
    public User addCustomer(@RequestBody User customer){
        customer.setUserId(0);
        User dbCustomer=userService.save(customer);
        return dbCustomer;
    }

    @PutMapping("/users")
    public User updateCustomer(@RequestBody User customer){
        User dbCustomer=userService.save(customer);
        return dbCustomer;
    }

    @DeleteMapping("/users/{id}")
    public String deleteCustomer(@PathVariable int id){
        User tmpCustomer=userService.findById(id);
        if(tmpCustomer==null){
            throw new CustomerNotFoundException("Customer id not found "+ id);
        }
        userService.deleteById(id);
        return "Deleted customer with id "+id;
    }

    @GetMapping("/pageusers")
    public ResponseEntity<PageResponse<User>> getPageCustomers(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "2") int size){
        PageResponse<User> a=userService.getPageUsers(page, size);
        return ResponseEntity.ok(a);
    }



}
