/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cocktail.cocktails;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Brecht
 */
public interface CocktailRepository extends JpaRepository<Cocktail, Long>{
    
    Cocktail findById(long id);
    
    long deleteById(long id);
    
    List<Cocktail> findByIdIn(List<Long> ids);
}
