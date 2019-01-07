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
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
public class Firstwindow extends JFrame{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 350;
    JPanel mainPanel = new JPanel(new GridLayout(2,2,8,10));

    JPanel topLeft = new JPanel(new GridLayout(2,2,8,8));
    
    JButton listBooks = new JButton("List books");
    JButton uniqueA = new JButton("Unique authors");
    JButton averagePrice = new JButton("Average price");
    JButton twPairs = new JButton("Textbook-Workbook pairs");

    JPanel topRight = new JPanel(new GridLayout(3,1));
    JButton bookAdd = new JButton("Add Book");
    JButton textAdd = new JButton("Add Textbook");
    JButton workAdd = new JButton("Add Workbook");

    JPanel bottomLeft = new JPanel();
    JTextArea ta = new JTextArea(19,37);
    JScrollPane sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JPanel bottomRight = new JPanel(new GridLayout(8,2));
    JLabel title = new JLabel("Title");
    JTextField titleText = new JTextField();

    JLabel author = new JLabel("Author");
    JTextField authorText = new JTextField();    

    JLabel year = new JLabel("Year published");
    JTextField yearText = new JTextField();

    JLabel ISBN = new JLabel("ISBN");
    JTextField textISBN = new JTextField();

    JLabel price = new JLabel("Price");
    JTextField priceText = new JTextField();
    
    JLabel subject = new JLabel("Subject(Textbook only)");
    JTextField subjectText = new JTextField();

    JLabel workBookISBN = new JLabel("WorkbookISBN(Textbook only)");
    JTextField workBookText = new JTextField();

    JLabel numProblems = new JLabel("NumProblems(Workbook only)");
    JTextField problemsText = new JTextField();
    
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");

    JMenuItem saving = new JMenuItem("Save As...");
    JMenuItem loading = new JMenuItem("Load File...");
    JMenuItem quitting = new JMenuItem("Quit");

    public Firstwindow()
    {
        super("Main menu");
        setSize(900,700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        topLeft.add(listBooks);
        topLeft.add(uniqueA);
        topLeft.add(averagePrice);
        topLeft.add(twPairs);
        
        topRight.add(bookAdd);
        topRight.add(textAdd);
        topRight.add(workAdd);
        
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        bottomLeft.add(sp);
        
        bottomRight.add(title);
        bottomRight.add(titleText);
    
        bottomRight.add(author);
        bottomRight.add(authorText);
        
        bottomRight.add(year);
        bottomRight.add(yearText);
        
        bottomRight.add(ISBN);
        bottomRight.add(textISBN);
        
        bottomRight.add(price);
        bottomRight.add(priceText);
        
        bottomRight.add(subject);
        bottomRight.add(subjectText);
        
        bottomRight.add(workBookISBN);
        bottomRight.add(workBookText);
        
        bottomRight.add(numProblems);
        bottomRight.add(problemsText);

        mainPanel.add(topLeft);
        mainPanel.add(topRight);
        mainPanel.add(bottomLeft);
        mainPanel.add(bottomRight);

        file.add(saving);
        menuBar.add(file);
        file.add(loading);
        menuBar.add(file);
        file.add(quitting);
        menuBar.add(file);
        setJMenuBar(menuBar);

        add(mainPanel);
    }
}
