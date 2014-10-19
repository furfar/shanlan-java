package com.shanlan.trade.core.domain;

@Entity
@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")   
public class Derived2 extends Base {   
    @Basic  
    private String derived2Name;   
}  