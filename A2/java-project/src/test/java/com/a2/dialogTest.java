package com.a2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class dialogTest 
{
    dialog dia= new dialog();

    @Test
    public void testPOS() {	
       System.out.println("Inside testPOS()");    
       assertEquals("Hi! How are you?", dia.answer("Hi!"));     
    }

    @Test
    public void testSetiment(){
        System.out.println("Inside testSetiment()");    
       assertEquals("What's Wrong with you?", dia.answer("I am sad.")); 
    }

    @Test
    public void testNER(){
        System.out.println("Inside testNER()");    
       assertEquals("Hi, I am robot. How are you?", dia.answer("I am Bob.")); 
    }
}
