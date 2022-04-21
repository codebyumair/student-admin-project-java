
/*
 * @author Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.sql.*;
import java.util.*;

public class LoginStudent {

    PreparedStatement p;
    Connection con;
    ResultSet res;
    Scanner s;

    String studentname, studentpassword;

    public LoginStudent(Scanner s)
    {
        try
        {
            this.s = s;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentAdminDB", "root", "root");
        }catch (Exception e)
        {
            System.out.println("Student Connection error : " + e);
        }
    }

    public void login()
    {
        System.out.println("\t\t\t || Student Login ||");
        System.out.println("Enter username : ");
        studentname = s.next();
        System.out.println("Enter password : ");
        studentpassword = s.next();
    }

    public void validateLogin()
    {
        try
        {
            p = con.prepareStatement("select Studentname,Studentpassword from RegisterdStudent where Studentname = ? and StudentPassword = ?");
            p.setString(1, studentname);
            p.setString(2, studentpassword);

            res = p.executeQuery();
            if (res.next()) {
                new StudentMenu(s,con,studentname);

            }else
            {
                System.out.println("Student not found");
            }
        }catch (Exception e)
        {
            System.out.println("Student login error : " + e);
        }
    }
}
