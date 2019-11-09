/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_jpa_relationship.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SaT_LoP
 */
@RestController
@RequestMapping("/api")
public class HelloRestFullAPI {
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object helloAPI(){
        return "Hello RESTFul API";
    }
    
    @RequestMapping(value = "/hello-data", method = RequestMethod.GET)
    public Object helloAPI(@RequestParam("name") String name){
        return "Hello " +name;
    }
    
    @RequestMapping(value = "/hello-data-1/{name}/{country}", method = RequestMethod.GET)
    public Object helloAPI(@PathVariable("name") String name, @PathVariable("country") String country){
        return "Hello " +name +" country: " + country;
    }
}
