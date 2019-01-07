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
import java.util.*;
public class Book {
    private String bookName;
    private String author;
    private int year;
    private String ISBN;
    private double price;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.bookName);
        hash = 17 * hash + Objects.hashCode(this.author);
        hash = 17 * hash + this.year;
        hash = 17 * hash + Objects.hashCode(this.ISBN);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }
    
    public Book(String bookName,String author,int year,String ISBN,double price)
    {
        if(bookName == null)
        {
            this.bookName = "";
        } else{
            this.bookName = bookName;
        }
        
        if(author == null)
        {
            this.author = "";
        } else{
            this.author = author;
        }
        
        if(this.year < 0)
        {
            this.year = 0;
        } else{
            this.year = year;
        }
        
        if(ISBN == null)
        {
            this.ISBN = "";
        } else{
            this.ISBN = ISBN;
        }
        
        if(price < 0)
        {
            this.price = 0;
        } else{
            this.price = price;
        }
    }
    
    public Book(){
        this.bookName = "";
        this.author = "";
        this.year = 0;
        this.ISBN = "";
        this.price = 0;
    }
    
    public boolean equals(Object b){
        if(!(b instanceof Book)) 
            return false;
        else{
            Book a = (Book) b;
            if (!(this.getBookName().equals(a.getBookName()))) 
                return false;
            if (!(this.getAuthor().equals(a.getAuthor()))) 
                return false;
            if (!(this.getYear() == a.getYear())) 
                return false;
            if (!(this.getISBN().equals(a.getISBN()))) 
                return false;
            if (!(this.getPrice() == a.getPrice())) 
                return false;
            return true;
        }
    }
    
    @Override
    public String toString(){
        String result = String.format("%.2f",this.price);
        return this.bookName+","+this.author+","+this.year+","+this.ISBN+","+result;
    }
    
    public String getBookName(){
        return bookName;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public int getYear(){
        return year;
    }
    
    public String getISBN(){
        return ISBN;
    }
    
    public double getPrice(){
        return price;
    }
}    
  