package com.shanlan.trade.core.domain;

@Entity
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")   
public class Derived1 extends Base {   
    @Basic  
    private String derived1Name;   
}  