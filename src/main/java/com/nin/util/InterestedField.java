package com.nin.util;

public enum InterestedField
{
    _0("Special Offers"),
    _1("Food");
 
    private String stringValue;
 
    InterestedField(String envstringValue) {
        this.stringValue = envstringValue;
    }
 
    public String getstringValue() {
        return stringValue;
    }    
    
}
