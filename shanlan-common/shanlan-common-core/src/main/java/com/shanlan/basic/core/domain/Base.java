package com.shanlan.trade.core.domain;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
public class Base {   
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private int id;   
       
    @Basic  
    private String baseName;   
}