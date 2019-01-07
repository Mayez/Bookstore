/*
 * 
 */
package javaapplication4;

/**
 *
 * @author Mayez
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.io.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
    // TODO code application logic here

public class Bookstore {

    HashMap<Integer,Book> newIndex = new HashMap<Integer,Book>();
    Scanner keyboard = new Scanner(System.in);
    String optionNum = "";
    int convertedOptionNum = 0;
    String bookName = "";
    String author = "";
    String year = "";
    String ISBN = "";
    String price = "";
    String numProblems = "";
    String subject = "";
    String workBookISBN = "";
    int convertedNumProblems = -1;
    int convertedYear = -2700;
    double convertedPrice = -1.00;
    int choice = 0;
    public static void main(String[] args) 
    {
        Bookstore bookstore = new Bookstore();
        bookstore.run();
    }

    public void run()
    {
        Bookstore bookstore = new Bookstore();

        Firstwindow test = new Firstwindow();
        test.setVisible(true);

        test.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent we){
               System.exit(0);
           }
        });

        test.saving.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            try{            
                FileWriter writing = new FileWriter("TestFile.txt");
                Writer output = new BufferedWriter(writing);
                for (Integer i : newIndex.keySet()) {
                    output.write(newIndex.get(i).toString()+ "\n");
                }
                output.close();
            }catch(IOException b){
                test.ta.append("The file was not written to\n");
            }
            test.ta.append("The file was sent to TestFile.txt\n");
        }
        });

        test.loading.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            String bookName = "";
            String author = "";
            String year = "";
            String ISBN = "";
            String price = "";
            String numProblems = "";
            String subject = "";
            String workBookISBN = "";
            int convertedNumProblems = 0;
            int convertedYear = -2700;
            double convertedPrice = -1.00;
            String line;
            try{
                BufferedReader input = new BufferedReader(new FileReader("TestFile.txt"));
                if(!input.ready()){
                    throw new IOException();
                }
                newIndex.clear();
                while((line = input.readLine()) != null)
                { 
                    String[] fields = line.split(",");
                    for(int i = 0; i < fields.length; i++)
                    {
                        test.ta.append(fields[i]+"\n");
                    }
                    bookName = fields[0];
                    author = fields[1];
                    year = fields[2];
                    ISBN = fields[3];
                    price = fields[4];
                    try
                    {
                        convertedYear = Integer.parseInt(year);
                    }
                    catch(NumberFormatException E)
                    {
                        test.ta.append("The year was not read\n");
                    }

                    try
                    {
                        convertedPrice = Double.parseDouble(price);
                    }
                    catch(NumberFormatException E)
                    {
                        test.ta.append("The price was not read\n");
                    }

                    if(fields.length == 5)
                    {

                        newIndex.put(newIndex.size()-1,new Book(bookName,author,convertedYear,ISBN,convertedPrice));
                    }

                    else if(fields.length == 6)
                    {
                        numProblems = fields[5];
                        try
                        {
                            convertedNumProblems = Integer.parseInt(numProblems);
                        }
                        catch(NumberFormatException E)
                        {
                            test.ta.append("The number of problems was not read\n");
                        }

                        newIndex.put(newIndex.size()-1,new Workbook(bookName,author,convertedYear,ISBN,convertedPrice,convertedNumProblems));
                    }

                    else if(fields.length == 7)
                    {
                        subject = fields[5];
                        workBookISBN = fields[6];
                        newIndex.put(newIndex.size()-1,new Textbook(bookName,author,convertedYear,ISBN,convertedPrice,subject,workBookISBN));
                    }
                }
            }catch(IOException f){
                test.ta.append("The file was not read properly.\n");
            }
        }
        });

        test.quitting.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
        });

        test.listBooks.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("List of books:\n");
            newIndex = bookstore.option2(newIndex,test);
        }
        });

        test.uniqueA.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Unique Authors:\n");
            newIndex = bookstore.option3(newIndex,test);
        }
        });

        test.averagePrice.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Average Price: \n");
            newIndex = bookstore.option4(newIndex,test);
        }
        });

        test.twPairs.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Textbook Work Pairs: \n");
            newIndex = bookstore.option5(newIndex,test);
        }
        });

        test.bookAdd.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Here are the list of errors for the input of ");
            test.ta.append("the book, if there are any.\n\n");
            int correctCheck = 0;
            bookName = test.titleText.getText();
            author = test.authorText.getText();
            year = test.yearText.getText();
            test.ta.append("Year input evaluation: \n");
            try
            {
                convertedYear = Integer.parseInt(year);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("This string for the year input is not a number because\n");
                test.ta.append(" it has non-numeric characters. Try again.\n");
                correctCheck = 1;
            }

            if(convertedYear < -2600 || convertedYear > 2017)
            {
                test.ta.append("That value for the year input is out of range. Try again\n");
                correctCheck = 1;
            }

            test.ta.append("\nISBN input evaluation: \n");
            int numberCheck = 0;
            ISBN = test.textISBN.getText();
            for(int i = 0; i < ISBN.length(); i++)
            {
                if(ISBN.charAt(i) == '0' || ISBN.charAt(i) == '1' ||
                   ISBN.charAt(i) == '2' || ISBN.charAt(i) == '3' ||
                   ISBN.charAt(i) == '4' || ISBN.charAt(i) == '5' ||
                   ISBN.charAt(i) == '6' || ISBN.charAt(i) == '7' ||
                   ISBN.charAt(i) == '8' || ISBN.charAt(i) == '9')
                {
                    numberCheck++;

                }
            }
            if(numberCheck != ISBN.length() && ISBN.length() == 10 || ISBN.length() == 13)
            {
                test.ta.append("You did not enter the correct input for the ISBN. Try again\n");
                correctCheck = 1;
            }

            else if(numberCheck == ISBN.length() && ISBN.length() != 10 && ISBN.length() != 13)
            {
                test.ta.append("The correct input was not entered for the ISBN. Try again\n");
                correctCheck = 1;
            }

            test.ta.append("\nPrice input evaluation: \n");
            price = test.priceText.getText();

            try
            {
                convertedPrice = Double.parseDouble(price);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("This string for the price input is not a number because\n");
                test.ta.append(" it has non-numeric characters. Try again.\n");
                correctCheck = 1;
            }

            if(convertedPrice < 0)
            {
                test.ta.append("That value for the price input is out of range. Try again\n");
                correctCheck = 1;
            }                    

            if(e.getSource() == test.bookAdd && correctCheck == 0)
            {
                test.ta.append("\nCheck to see if the book already exists: \n");
                int bookCheck = 0;
                for(Integer stuff : newIndex.keySet())
                {
                    if (bookName.equals(newIndex.get(stuff).getBookName()) && author.equals(newIndex.get(stuff).getAuthor()) &&
                        convertedYear == newIndex.get(stuff).getYear() && ISBN.equals(newIndex.get(stuff).getISBN()) &&
                        convertedPrice == newIndex.get(stuff).getPrice() && (newIndex.get(stuff).getClass()).equals("Book")) {
                        bookCheck = 1;
                        correctCheck = 1;
                        test.ta.append("Sorry, you cannot enter that book as it ");
                        test.ta.append("already exists in the bookstore\n");
                    } 

                }

                if(bookCheck == 0 && correctCheck == 0)
                {
                    test.ta.append("\n"); 
                    newIndex.put(newIndex.size()-1,new Book(bookName,author,convertedYear,ISBN,convertedPrice));
                    test.ta.append("Book included...\n");        
                }
            }
        }
        });

        test.textAdd.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Here are the list of errors for the input of ");
            test.ta.append("the textbook, if there are any.\n\n");
            int correctCheck = 0;
            bookName = test.titleText.getText();
            author = test.authorText.getText();
            test.ta.append("Year input evaluation: \n");
            year = test.yearText.getText();

            try
            {
                convertedYear = Integer.parseInt(year);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("This string for the year input is not a number because");
                test.ta.append(" it has non-numeric characters. Try again.\n");
                correctCheck = 1;
            }

            if(convertedYear < -2600 || convertedYear > 2017)
            {
                test.ta.append("That value for the year input is out of range. Try again\n");
                correctCheck = 1;
            }

            test.ta.append("\nISBN input evaluation: \n");
            int numberCheck = 0;
            ISBN = test.textISBN.getText();
            for(int i = 0; i < ISBN.length(); i++)
            {
                if(ISBN.charAt(i) == '0' || ISBN.charAt(i) == '1' ||
                   ISBN.charAt(i) == '2' || ISBN.charAt(i) == '3' ||
                   ISBN.charAt(i) == '4' || ISBN.charAt(i) == '5' ||
                   ISBN.charAt(i) == '6' || ISBN.charAt(i) == '7' ||
                   ISBN.charAt(i) == '8' || ISBN.charAt(i) == '9')
                {
                    numberCheck++;

                }
            }
            if(numberCheck != ISBN.length() && ISBN.length() == 10 || ISBN.length() == 13)
            {
                test.ta.append("You did not enter the correct input for the ISBN. Try again\n");
                correctCheck = 1;
            }

            else if(numberCheck == ISBN.length() && ISBN.length() != 10 && ISBN.length() != 13)
            {
                test.ta.append("The correct input was not entered for the ISBN. Try again\n");
                correctCheck = 1;
            }

            test.ta.append("\nPrice input evaluation: \n");
            price = test.priceText.getText();

            try
            {
                convertedPrice = Double.parseDouble(price);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("\nThis string for the price input is not a number because");
                test.ta.append(" it has non-numeric characters. Try again.\n");
                correctCheck = 1;
            }

            if(convertedPrice < 0)
            {
                test.ta.append("That value for the price input is out of range. Try again\n");
                correctCheck = 1;
            }                    


            if(e.getSource() == test.textAdd && correctCheck == 0)
            {
                test.ta.append("\nCheck to see if the book already exists: \n");
                subject = test.subjectText.getText();

                workBookISBN = test.workBookText.getText();

                int textBookCheck = 0;            
                for(Integer stuff: newIndex.keySet())
                {                
                    Book text1 = new Textbook();
                    if(text1 instanceof Textbook)
                    {
                        ((Textbook)text1).setSubject(subject);
                        ((Textbook)text1).setWorkbook(workBookISBN);
                    }

                    if (bookName.equals(newIndex.get(stuff).getBookName()) && author.equals(newIndex.get(stuff).getAuthor()) &&
                        convertedYear == newIndex.get(stuff).getYear() && ISBN.equals(newIndex.get(stuff).getISBN()) &&
                        convertedPrice == newIndex.get(stuff).getPrice() && subject.equals(((Textbook)text1).getSubject()) &&
                        workBookISBN.equals(((Textbook)text1).getWorkbookISBN()) && (newIndex.get(stuff).getClass()).equals("Textbook")) {
                        textBookCheck = 1;
                        correctCheck = 1;
                        test.ta.append("Sorry, you cannot enter that textbook as it ");
                        test.ta.append("already exists in the bookstore\n");
                    } 

                }

                if(textBookCheck == 0 && correctCheck == 0)
                {
                    test.ta.append("\n"); 
                    newIndex.put(newIndex.size()-1,new Textbook(bookName,author,convertedYear,ISBN,convertedPrice,subject,workBookISBN));
                    test.ta.append("Textbook included...\n");        

                }            
            }
        }
        });

        test.workAdd.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent e){
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Here are the list of errors for the input of ");
            test.ta.append("the workbook, if there are any.\n\n");
            int correctCheck = 0;
            bookName = test.titleText.getText();
            author = test.authorText.getText();
            test.ta.append("Year input evaluation: \n");
            year = test.yearText.getText();

            try
            {
                convertedYear = Integer.parseInt(year);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("This string for the year input is not a number because");
                test.ta.append(" it has non-numeric characters. Try again.\n");
                correctCheck = 1;
            }

            if(convertedYear < -2600 || convertedYear > 2017)
            {
                test.ta.append("That value for the year input is out of range. Try again\n");
                correctCheck = 1;
            }

            test.ta.append("\nISBN input evaluation: \n");
            int numberCheck = 0;
            ISBN = test.textISBN.getText();
            for(int i = 0; i < ISBN.length(); i++)
            {
                if(ISBN.charAt(i) == '0' || ISBN.charAt(i) == '1' ||
                   ISBN.charAt(i) == '2' || ISBN.charAt(i) == '3' ||
                   ISBN.charAt(i) == '4' || ISBN.charAt(i) == '5' ||
                   ISBN.charAt(i) == '6' || ISBN.charAt(i) == '7' ||
                   ISBN.charAt(i) == '8' || ISBN.charAt(i) == '9')
                {
                    numberCheck++;

                }
            }
            if(numberCheck != ISBN.length() && ISBN.length() == 10 || ISBN.length() == 13)
            {
                test.ta.append("You did not enter the correct input for the ISBN. Try again\n");
                correctCheck = 1;
            }

            else if(numberCheck == ISBN.length() && ISBN.length() != 10 && ISBN.length() != 13)
            {
                test.ta.append("The correct input was not entered for the ISBN. Try again\n");
                correctCheck = 1;
            }

            test.ta.append("\nPrice input evaluation: \n");
            price = test.priceText.getText();

            try
            {
                convertedPrice = Double.parseDouble(price);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("This string for the price input is not a number because\n");
                test.ta.append(" it has non-numeric characters. Try again.\n");
                correctCheck = 1;
            }

            if(convertedPrice < 0)
            {
                test.ta.append("That value for the price input is out of range. Try again\n");
                correctCheck = 1;
            }                    

            if(e.getSource() == test.workAdd && correctCheck == 0)
            {
                test.ta.append("\nCheck to see if the workbook already exists: \n");

                numProblems = test.problemsText.getText();
                try{
                    convertedNumProblems = Integer.parseInt(numProblems);
                }
                catch (NumberFormatException nfEx)
                {
                    test.ta.append("This string for the number of problems is not a number because");
                    test.ta.append(" it has non-numeric characters. Try again.\n");
                    correctCheck = 1;
                }

                if(convertedNumProblems < 0)
                {
                    test.ta.append("That value for the number of problems is out of range. Try again\n");
                    correctCheck = 1;
                }            

                int workCheck = 0;            
                for(Integer stuff: newIndex.keySet())
                {                
                    Book work1 = new Workbook();
                    if(work1 instanceof Workbook)
                    {
                        ((Workbook)work1).setNumProblems(convertedNumProblems);
                    }
           
                    if (bookName.equals(newIndex.get(stuff).getBookName()) && author.equals(newIndex.get(stuff).getAuthor()) &&
                        convertedYear == newIndex.get(stuff).getYear() && ISBN.equals(newIndex.get(stuff).getISBN()) &&
                        convertedPrice == newIndex.get(stuff).getPrice() && convertedNumProblems == ((Workbook)work1).getNumProblems()
                        && (newIndex.get(stuff).getClass()).equals("Workbook")) {
                        workCheck = 1;
                        correctCheck = 1;
                        test.ta.append("Sorry, you cannot enter that workbook as it ");
                        test.ta.append("already exists in the bookstore\n");
                    } 

                }

                if(workCheck == 0 && correctCheck == 0)
                {
                    test.ta.append("\n"); 
                    newIndex.put(newIndex.size()-1,new Workbook(bookName,author,convertedYear,ISBN,convertedPrice,convertedNumProblems));
                    test.ta.append("Workbook included...\n");        

                }

            }
        }
        });
    }
    public HashMap option1(HashMap<Integer,Book> index, Firstwindow test, int choice)
    {
        bookName = test.titleText.getText();
        author = test.authorText.getText();

        while(convertedYear < -2600 || convertedYear > 2017)
        {
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Year input Here: \n");
            year = test.yearText.getText();

            try
            {
                convertedYear = Integer.parseInt(year);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("\nThis string is not a number because\n");
                test.ta.append(" it has non-numeric characters. Try again.\n");
            }

            if(convertedYear < -2600 || convertedYear > 2017)
            {
                test.ta.append("That value is out of range. Try again\n");
            }
        }

        while(ISBN.length() != 10 || ISBN.length() != 13)
        {
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("ISBN input Here: \n");
            int numberCheck = 0;
            ISBN = test.textISBN.getText();
            for(int i = 0; i < ISBN.length(); i++)
            {
                if(ISBN.charAt(i) == '0' || ISBN.charAt(i) == '1' ||
                   ISBN.charAt(i) == '2' || ISBN.charAt(i) == '3' ||
                   ISBN.charAt(i) == '4' || ISBN.charAt(i) == '5' ||
                   ISBN.charAt(i) == '6' || ISBN.charAt(i) == '7' ||
                   ISBN.charAt(i) == '8' || ISBN.charAt(i) == '9')
                {
                    numberCheck++;
                    
                }
            }
            if(numberCheck != ISBN.length() && ISBN.length() == 10 || ISBN.length() == 13)
            {
                test.ta.append("You did not enter the correct input. Try again\n");
                continue;
            }

            else if(numberCheck == ISBN.length() && ISBN.length() != 10 && ISBN.length() != 13)
            {
                test.ta.append("The correct input was not entered. Try again\n");
                continue;
            }
            
            else if(numberCheck == ISBN.length() && ISBN.length() == 10 || ISBN.length() == 13)
            {
                break;
            }

        }

        while(convertedPrice < 0)
        {
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("Price input Here: \n");
            price = test.priceText.getText();

            try
            {
                convertedPrice = Double.parseDouble(price);
            }
            catch(NumberFormatException E)
            {
                test.ta.append("\nThis string is not a number because\n");
                test.ta.append(" it has non-numeric characters. Try again.\n");
            }

            if(convertedPrice < 0)
            {
                test.ta.append("That value is out of range. Try again\n");
            }                    
        }
        
/*        while(convertedOptionNum < 1 || convertedOptionNum > 3)
        {
            System.out.println("Here are a list of options for the object add:");
            System.out.println("Option 1: Add the parameters of a workbook and include the workbook");
            System.out.println("Option 2: Add the parameters of a textbook and include the textbook");
            System.out.println("Option 3: Add the parameters as is and add it as a book");
            optionNum = keyboard.nextLine();

            try{
                convertedOptionNum = Integer.parseInt(optionNum);
            }
            catch (NumberFormatException nfEx)
            {
                System.out.print("\nThis string is not a number because");
                System.out.println(" it has non-numeric characters. Try again.");
            }

            if(convertedOptionNum < 1 || convertedOptionNum > 3)
            {
                System.out.println("That value is out of range. Try again\n");
            }
            
        }
  */      
        if(choice == 1)
        {
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("workbook set input Here: \n");

            while(convertedNumProblems < 0)
            {
                numProblems = test.problemsText.getText();
                try{
                    convertedNumProblems = Integer.parseInt(numProblems);
                }
                catch (NumberFormatException nfEx)
                {
                    test.ta.append("\nThis string is not a number because\n");
                    test.ta.append(" it has non-numeric characters. Try again.\n");
                }

                if(convertedNumProblems < 0)
                {
                    test.ta.append("That value is out of range. Try again\n");
                }            
            }            
            
            int workCheck = 0;            
            for(Integer stuff: index.keySet())
            {                
                Book work1 = new Workbook();
                if(work1 instanceof Workbook)
                {
                    test.ta.append("HERE IT IS\n");
                    ((Workbook)work1).setNumProblems(convertedNumProblems);
                }
                
                if (bookName.equals(index.get(stuff).getBookName()) && author.equals(index.get(stuff).getAuthor()) &&
                    convertedYear == index.get(stuff).getYear() && ISBN.equals(index.get(stuff).getISBN()) &&
                    convertedPrice == index.get(stuff).getPrice() && convertedNumProblems == ((Workbook)work1).getNumProblems()) {
                    workCheck = 1;
                    test.ta.append("Sorry, you cannot enter that workbook as it\n");
                    test.ta.append("already exists in the bookstore\n");
                } 

            }

            if(workCheck == 0)
            {
                test.ta.append("\n"); 
                index.put(index.size()-1,new Workbook(bookName,author,convertedYear,ISBN,convertedPrice,convertedNumProblems));
                test.ta.append("Workbook included...\n");        

            }

        }
        
        else if(choice == 2)
        {
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("textbook set input Here: \n");
            subject = test.subjectText.getText();
            
            workBookISBN = test.workBookText.getText();

            int textBookCheck = 0;            
            for(Integer stuff: index.keySet())
            {                
                Book text1 = new Textbook();
                if(text1 instanceof Textbook)
                {
                    test.ta.append("HERE IT IS 1\n");
                    ((Textbook)text1).setSubject(subject);
                    ((Textbook)text1).setWorkbook(workBookISBN);
                }

                if (bookName.equals(index.get(stuff).getBookName()) && author.equals(index.get(stuff).getAuthor()) &&
                    convertedYear == index.get(stuff).getYear() && ISBN.equals(index.get(stuff).getISBN()) &&
                    convertedPrice == index.get(stuff).getPrice() && subject.equals(((Textbook)text1).getSubject()) &&
                    workBookISBN.equals(((Textbook)text1).getWorkbookISBN())) {
                    textBookCheck = 1;
                    test.ta.append("Sorry, you cannot enter that textbook as it\n");
                    test.ta.append("already exists in the bookstore\n");
                } 

            }

            if(textBookCheck == 0)
            {
                test.ta.append("\n"); 
                index.put(index.size()-1,new Textbook(bookName,author,convertedYear,ISBN,convertedPrice,subject,workBookISBN));
                test.ta.append("Textbook included...\n");        

            }            
        }
        
        else if(choice == 3)
        {
            test.ta.selectAll();
            test.ta.replaceSelection("");
            test.ta.append("book set input Here: \n");
            test.ta.append("HELLO1\n");
            int bookCheck = 0;
            for(Integer stuff : index.keySet())
            {
                if (bookName.equals(index.get(stuff).getBookName()) && author.equals(index.get(stuff).getAuthor()) &&
                    convertedYear == index.get(stuff).getYear() && ISBN.equals(index.get(stuff).getISBN()) &&
                    convertedPrice == index.get(stuff).getPrice()) {
                    bookCheck = 1;
                    test.ta.append("Sorry, you cannot enter that book as it\n");
                    test.ta.append("already exists in the bookstore\n");
                } 
   
            }

            if(bookCheck == 0)
            {
                test.ta.append("\n"); 
                index.put(index.size()-1,new Book(bookName,author,convertedYear,ISBN,convertedPrice));
                test.ta.append("Book included...\n");        

            }
        }
        return index;
    }

    
    public HashMap option2(HashMap<Integer,Book> index,Firstwindow test)
    {
        if(index.size() > 0)
        {
            for(Integer stuff: index.keySet())
            {
                test.ta.append(index.get(stuff).toString()+"\n");
            }
        }
        
        else
        {
            test.ta.append("\n, , 0, 0, 0.00\n");
        }
        return index;

    }
    
    public HashMap option3(HashMap<Integer,Book> index, Firstwindow test)
    {
        if(index.size() > 0)
        {
            int outerCount = 0;
            for(HashMap.Entry<Integer, Book> i : index.entrySet())
            {
                int authorCheck = 0;
                int innerCount = 0;
                for(HashMap.Entry<Integer,Book> j: index.entrySet())
                {
                    if(i.getValue().getAuthor().equals(j.getValue().getAuthor()) && !(i.getKey().equals(j.getKey())))
                    {
                        test.ta.append("The " + (outerCount+1) + "th book equals the "+(innerCount+1)+ "th book!\n");
                    }
                    innerCount++;
                }
                outerCount++;
            }
            test.ta.append("\n"); 
        }
        
        else
        {
            test.ta.append("\nNo authors will be listed because no ");
            test.ta.append("authors are present in the library\n");
        }
        return index;
    }
    
    public HashMap option4(HashMap<Integer,Book> index, Firstwindow test)
    {
        if(index.size() > 0)
        {
            double total = 0;
            double average = 0;
            for(Integer stuff: index.keySet())
            {
                total += index.get(stuff).getPrice();
            }
            average = total/(index.size());
            test.ta.append("\nAverage: "+ average + "\n");
            test.ta.append("Total Books: " + index.size()+ "\n");
        }

        else
        {
            test.ta.append("\nAverage: 0.0\n");
            test.ta.append("Number of Books: 0\n");
        }
        
        return index;
    }
    
    public HashMap option5(HashMap<Integer,Book> index, Firstwindow test)
    {   
        String name = "";
        if(index.size() > 0)
        {
            for(HashMap.Entry<Integer, Book> i : index.entrySet())
            {
                int ISBNCheck = 0;
                Book book1 = i.getValue();
                if(book1 instanceof Textbook)
                {
                    test.ta.append("HERE IT IS 1\n");
                    Textbook text1 = (Textbook)book1;
                    for(HashMap.Entry<Integer, Book> j : index.entrySet())
                    {
                        Book book2 = j.getValue();
                        if(book2 instanceof Workbook && text1.getWorkbookISBN().equals(book2.getISBN()))
                        {
                            test.ta.append("HERE IT IS 2\n");
                            test.ta.append("Textbook: "+ text1.getBookName()+ " <=> Workbook: " + book2.getBookName()+"\n");
                        }
                    }
                }
            }
            test.ta.append("\n"); 
        }
        
        else
        {
            test.ta.append("\nNo authors will be listed because no ");
            test.ta.append("authors are present in the library\n");
        }
        
        return index;
    }
}
