
/*
 * @author Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.sql.*;
import java.util.*;

public class LoginAdmin {

    PreparedStatement p;
    Connection con;
    ResultSet res;
    Scanner s;

    String username, password;
    int ch;

    public LoginAdmin(Scanner s)
    {
        try
        {
            this.s = s;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentAdminDB", "root", "root");
        }catch (Exception e)
        {
            System.out.println("Admin Connection error : " + e);
        }
        this.loginSignUp();
    }

    public void loginSignUp()
    {
        System.out.printf("%-6s%-10s\n","No","OPTION");
        System.out.printf("%-6s%-10s\n","1","Login");
        System.out.printf("%-6s%-10s\n","2","Sign up");
        System.out.printf("%-6s%-20s\n","3","Go to Home Page");
        System.out.printf("%-6s%-20s\n","4","Exit");
        System.out.println("Enter your choice :");
        ch = s.nextInt();
        switch (ch)
        {
            case 1 :
                login();
                validateLogin();
                break;
            case 2 :
                createAdmin();
                break;
            case 3 :
                new StudentAdmin(s);
                break;
            case 4 :
                System.exit(0);
                break;
            default:
                System.out.println("Please enter correct choice");
                new LoginAdmin(s);
        }
    }

    public void createAdmin()
    {
       try
       {
           System.out.println("|| Creating new admin account ||");
           System.out.println("Enter new username : ");
           username = s.next();
           p = con.prepareStatement("select username from adminlogin where username = ?");
           p.setString(1,username);

           res = p.executeQuery();
           if(res.next())
           {
               System.out.println("Username not available");
               System.out.println("Please enter unique name");
               this.createAdmin();

           }else
           {
               System.out.println("Enter new password");
               password = s.next();

               try
               {
                   p = con.prepareStatement("insert into adminlogin value (?,?)");
                   p.setString(1, username);
                   p.setString(2, password);

                   int i = p.executeUpdate();

                   if (i >= 0) {
                       System.out.println("Account has been created successfully");
                       this.login();
                       this.validateLogin();
                   } else {
                       System.out.println("Account creation failed");
                   }
               }catch(Exception e)
               {
                   e.printStackTrace();
               }
           }
       }catch(SQLException se)
       {
           se.printStackTrace();
       }
    }

    public void login()
    {
        System.out.println("\t\t\t || Admin Login ||");
        System.out.println("Enter username : ");
        username = s.next();
        System.out.println("Enter password : ");
        password = s.next();
    }

    public void validateLogin()
    {
        try
        {
            p = con.prepareStatement("select * from adminlogin where Username = ? and Password = ?");
            p.setString(1, username);
            p.setString(2, password);

            res = p.executeQuery();
            if (res.next()) {
                new AdminMenu(s,con);
            }else
            {
                System.out.println("Admin account not found");
                System.out.println("Please enter correct name & password OR create an account");
                this.loginSignUp();
            }
        }catch (Exception e)
        {
            System.out.println("Admin login error : " + e);
        }
    }
}
