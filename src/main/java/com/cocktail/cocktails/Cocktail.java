/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cocktail.cocktails;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Brecht
 */
@Entity
@JsonSerialize
public class Cocktail implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @Column(length = 2048)
    private String recept;
    
    public Cocktail(){
        
    }
    
    public Cocktail(long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
