/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.util.Objects;

/**
 *
 * @author Mayez
 */
public class Textbook extends Book {
    private String subject;
    private String workBookISBN;
    
    public Textbook(){
        super();
        subject = "";
        workBookISBN = "";
    }
    
    public Textbook(String bookName,String author,int year,String ISBN,double price,
            String subject,String workBookISBN)
    {
        super(bookName,author,year,ISBN,price);
        this.subject = subject;
        this.workBookISBN = workBookISBN;
    }
    
    public String toString(){
        return super.toString()+","+this.subject+","+this.workBookISBN;
    }
    
    public boolean equals(Object o){
        if (o == this) return true;
        
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Textbook tb = (Textbook) o;
        return this.getSubject().equals(tb.getSubject()) && 
               this.getWorkbookISBN().equals(tb.getWorkbookISBN()) && super.equals(o);  
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.subject);
        hash = 97 * hash + Objects.hashCode(this.workBookISBN);
        return hash;
    }
    
    
    
    public String getSubject() {
        return subject;
    }

    public String getWorkbookISBN(){
        return workBookISBN;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void setWorkbook(String workBookISBN){
        this.workBookISBN = workBookISBN;
    }

    int getNumProblems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
