/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_jpa_relationship.service;

import com.mycompany.spring_mvc_jpa_relationship.entities.BookDetailEntity;
import com.mycompany.spring_mvc_jpa_relationship.repository.BookDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SaT_LoP
 */
@Service
public class BookDetailService {
    
    @Autowired
    private BookDetailRepository bookDetailRepository;
    
    public BookDetailEntity save(BookDetailEntity book){
        return bookDetailRepository.save(book);
    }
}
