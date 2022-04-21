
/*
 * @author Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.sql.*;
import java.util.*;

public class StudentMenu {

    Scanner s;
    Connection c;
    PreparedStatement p;
    ResultSet res;
    Iterator itr;
    ArrayList al;
    long millis;
    java.sql.Date date;
    String studentname,result,subject;
    int i,ch,correct,queNo,wrong;

    String ques,op1,op2,op3,op4,ans,answer;

    public StudentMenu(Scanner s, Connection c,String studentname) {
        this.s = s;
        this.c = c;
        this.studentname = studentname;
        al  = new ArrayList();
        millis=System.currentTimeMillis();
        date=new java.sql.Date(millis);
        this.studentMenu();
    }

    public StudentMenu(int queNo,String ques,String op1,String op2,String op3,String op4,String answer)
    {
        this.queNo = queNo;
        this.ques = ques;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.answer = answer;
    }

    public void studentMenu() {
        System.out.println("\t\t || SALVATORE UNIVERSITY ||");
        System.out.printf("%-6s%-10s\n","No","OPTION");
        System.out.printf ("%-6s%-10s\n","1","Give Exam");
        System.out.printf ("%-6s%-10s\n","2","Check result");
        System.out.printf("%-6s%-10s\n","3","Go Back");
        System.out.printf ("%-6s%-10s\n","4","Exit");

        System.out.println("Enter your choice : ");
        ch = s.nextInt();

        switch (ch) {

            case 1:
                this.validateStudent();
                break;
            case 2:
               this.checkResult();
                break;
            case 3:
                new StudentAdmin(s);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Please enter correct choice");
                this.studentMenu();
        }
    }

    public  void validateStudent()
    {
        try
        {
            p = c.prepareStatement("select studentname from result where studentname = ?");
            p.setString(1,studentname);

            res = p.executeQuery();
            if(res.next())
            {
                System.out.println("You have already attempted for exam");
                this.studentMenu();
            }else
            {
                this.getQuestion();
            }
        }catch (SQLException se)
        {
            se.printStackTrace();
        }
    }

    public void getQuestion()
    {
        System.out.println("\t\t || SALVATORE UNIVERSITY ||");
        System.out.printf("%-6s%-10s\n","No","OPTION");
        System.out.printf ("%-6s%-10s\n","1","Java");
        System.out.printf ("%-6s%-10s\n","2","HTML");
        System.out.printf("%-6s%-10s\n","3","Go Back");
        System.out.printf("%-6s%-10s\n","4","Exit");
        System.out.println("Enter your choice : ");
        int ch2 = s.nextInt();

        switch (ch2)
        {
            case 1 :
                this.java();
                break;
            case 2:
                this.html();
                break;
            case 3:
                this.studentMenu();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Please enter correct choice");
                this.getQuestion();
        }
    }

    public void java()
    {
        try
        {
            p = c.prepareStatement("select * from java");

            res = p.executeQuery();

            while (res.next())
            {
                queNo = res.getInt(1);
                ques = res.getString(2);
                op1 = res.getString(3);
                op2 = res.getString(4);
                op3 = res.getString(5);
                op4 = res.getString(6);
                answer = res.getString(7);

                al.add(new StudentMenu(queNo,ques,op1,op2,op3,op4,answer));
            }

        }catch(SQLException se)
        {
            se.printStackTrace();
        }

        itr = al.iterator();

        System.out.println("Student Name : " + studentname);
        System.out.println("Subject : Java ");
        while(itr.hasNext())
        {
            StudentMenu sm = (StudentMenu)itr.next();
            System.out.printf("%-6s%-10s\n","No","Question");
            System.out.printf("%-6s%-10s\n",sm.queNo,sm.ques);
            System.out.printf("%-6s%-10s\n","(a) ",sm.op1);
            System.out.printf("%-6s%-10s\n","(b) ",sm.op2);
            System.out.printf("%-6s%-10s\n","(c) ",sm.op3);
            System.out.printf("%-6s%-10s\n","(d) ",sm.op4);
            System.out.println("Submit your answer : ");
            ans = s.next();
            ans = ans.toLowerCase();

            if(ans.equals(sm.answer))
            {
                correct++;
            }else {
                wrong++;
            }
        }
        System.out.println("Your answers are submitted!!!");
        System.out.println("Check your result now");
        if(correct > 3)
        {
            result = "PASS";
        }else
        {
            result = "FAIL";
        }
        try
        {
            p = c.prepareStatement("insert into Result values(?,?,?,?)");
            p.setString(1,studentname);
            p.setDate(2, date);
            p.setString(3,result);
            p.setString(4,"Java");

            i = p.executeUpdate();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        this.studentMenu();
    }

    public void html()
    {
        try
        {
            p = c.prepareStatement("select * from html");

            res = p.executeQuery();

            while (res.next())
            {
                queNo = res.getInt(1);
                ques = res.getString(2);
                op1 = res.getString(3);
                op2 = res.getString(4);
                op3 = res.getString(5);
                op4 = res.getString(6);
                answer = res.getString(7);

                al.add(new StudentMenu(queNo,ques,op1,op2,op3,op4,answer));
            }

        }catch(SQLException se)
        {
            se.printStackTrace();
        }

        itr = al.iterator();

        while(itr.hasNext())
        {
            StudentMenu sm = (StudentMenu)itr.next();
            System.out.println("Student Name : " + studentname);
            System.out.println("Subject : HTML ");
            System.out.printf("%-6s%-10s\n","No","Question");
            System.out.printf("%-6s%-10s\n",sm.queNo,sm.ques);
            System.out.printf("%-6s%-10s\n","(a) ",sm.op1);
            System.out.printf("%-6s%-10s\n","(b) ",sm.op2);
            System.out.printf("%-6s%-10s\n","(c) ",sm.op3);
            System.out.printf("%-6s%-10s\n","(d) ",sm.op4);
            System.out.println("Submit your answer : ");
            ans = s.next();
            ans = ans.toLowerCase();

            if(ans.equals(sm.answer))
            {
                correct++;
            }else {
                wrong++;
            }
        }
        System.out.println("Your answers are submitted!!!");
        System.out.println("Check your result now");
        if(correct > 3)
        {
            result = "PASS";
        }else
        {
            result = "FAIL";
        }
        try
        {
            p = c.prepareStatement("insert into Result values(?,?,?,?)");
            p.setString(1,studentname);
            p.setDate(2, date);
            p.setString(3,result);
            p.setString(4,"HTML");

            i = p.executeUpdate();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        this.studentMenu();
    }

    public void checkResult()
    {
        try
        {
            p = c.prepareStatement("select * from result where studentname = ?");
            p.setString(1,studentname);
            res = p.executeQuery();

            if (!res.next()) {
                System.out.println("Record not found");
            }else
            {
                studentname = res.getString(1);
                date = res.getDate(2);
                result = res.getString(3);
                subject = res.getString(4);
                System.out.printf("%-20s%-20s%-10s%-10s\n","StudentName","Date","Result","Subject");
                System.out.printf("%-20s%-20s%-10s%-10s\n",studentname,date,result,subject);
            }

        }catch(SQLException se)
        {
            se.printStackTrace();
        }
    }

}


