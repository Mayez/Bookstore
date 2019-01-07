/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

/**
 *
 * @author Mayez
 */
public class Workbook extends Book {
    private int numProblems;
    
    public Workbook(){
        super("","",0,"",0);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.numProblems;
        return hash;
    }
    
    public Workbook(String bookName,String author,int year,String ISBN,double price,
            int numProblems)
    {
        super(bookName,author,year,ISBN,price);
        this.numProblems = numProblems;
    }
    
    public String toString(){
        return super.toString()+","+this.numProblems;
    }
    
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Workbook wb = (Workbook) o;
        return this.getNumProblems() == wb.getNumProblems() && super.equals(o);  
    }
    
    public int getNumProblems() {
        return numProblems;
    }

    public void setNumProblems(int numProblems) {
        this.numProblems = numProblems;
    }    
}
