/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_jpa_relationship.repository;

import com.mycompany.spring_mvc_jpa_relationship.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SaT_LoP
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer>{
    
}
