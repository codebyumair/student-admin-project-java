
/*
 * @author Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.sql.*;
import java.util.*;

public class Examination {

    Scanner s;
    Connection c;
    ResultSet res;
    Statement st;
    PreparedStatement p;

    String name,subject,password,result,ch2;
    long millis;
    java.sql.Date date;

    public Examination(Scanner s, Connection c)
    {
        this.s = s;
        this.c = c;
        millis=System.currentTimeMillis();
        date=new java.sql.Date(millis);
        this.examMenu();
    }

    public void examMenu()
    {
        System.out.println("\t\t || SALVATORE UNIVERSITY ||");
        System.out.printf("%-6s%-10s\n","No","Menu");
        System.out.printf("%-6s%-10s\n","1","Register student");
        System.out.printf("%-6s%-10s\n","2","Exam result");
        System.out.printf("%-6s%-20s\n","3","Go to Menu Page");
        System.out.printf("%-6s%-20s\n","4","Exit");

        System.out.println("Enter your choice : ");
        int ch = s.nextInt();

        switch(ch)
        {
            case 1 :
                this.registerStudent();
                break;
            case 2 :
                this.examResult();
                break;
            case 3 :
                new AdminMenu(s,c);
                break;
            case 4 :
                System.exit(0);
                break;
            default:
                System.out.println("Please enter correct choice");
                new Examination(s,c);
        }
    }

    public  void registerStudent()
    {
        do
        {
            try
            {
                System.out.println("Enter student name : ");
                name = s.next();

                p = c.prepareStatement("select studentname from registerdstudent where studentname = ?");
                p.setString(1,name);
                res = p.executeQuery();

                if(res.next())
                {
                    System.out.println("Student has been already registered");
                    this.examMenu();
                }else
                {
                    try
                    {
                        System.out.println("Enter password: ");
                        password = s.next();

                        System.out.println("Enter subject : ");
                        subject = s.next();

                        p = c.prepareStatement("insert into registerdStudent values(?,?,?,?)");
                        p.setString(1,name);
                        p.setString(2,password);
                        p.setString(3,subject);
                        p.setDate(4, date);

                        int i =   p.executeUpdate();

                        if (i >= 0)
                        {
                            System.out.println("Student registered successfully");
                        }else
                        {
                            System.out.println("Student registration failed");
                        }
                    }catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                }
            }catch(SQLException se)
            {
                se.printStackTrace();
            }

            System.out.println("Do you want register more student ? (Y/N )");
            ch2 = s.next();
            ch2 = ch2.toLowerCase();
            if(ch2.equals("n"))
            {
                examMenu();
            }
        }while (ch2.equals("y"));

    }

    public void examResult()
    {
        try
        {
            st = c.createStatement();
            String sql = "select * from result";
            res = st.executeQuery(sql);
            System.out.printf("%-20s%-20s%-10s%-10s\n","StudentName","Date","Result","Subject");
            while(res.next())
            {
                name = res.getString(1);
                date = res.getDate(2);
                result = res.getString(3);
                subject = res.getString(4);
                System.out.printf("%-20s%-20s%-10s%-10s\n",name,date,result,subject);
            }
        }catch (SQLException se)
        {
            se.printStackTrace();
        }
    }
}
