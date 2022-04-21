
/*
 * @author Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.util.*;
import java.sql.*;

public class AdminMenu {

    PreparedStatement p;
    ResultSet res;
    Statement st;
    Connection c;
    Scanner s;
    String address, email,name,father,counsellor;
    int roll;
    long phone;
    int ch;
    String ch2;
    char ch3;

    public AdminMenu (Scanner s,Connection c)
    {
        this.s = s;
        this.c = c;
        this.menu();
    }

    public void menu()
    {
        System.out.println("\t\t || SALVATORE UNIVERSITY ||");
        System.out.printf("%-6s%-10s\n","No","Menu");
        System.out.printf("%-6s%-10s\n","1","Add student record");
        System.out.printf("%-6s%-10s\n","2","Update student record");
        System.out.printf("%-6s%-10s\n","3","Remove student record");
        System.out.printf("%-6s%-10s\n","4","Display student record");
        System.out.printf("%-6s%-10s\n","5","Course details");
        System.out.printf("%-6s%-10s\n","6","Examination Module");
        System.out.printf("%-6s%-10s\n","7","Logout");
        System.out.printf("%-6s%-10s\n","8","Exit");

        System.out.println("Enter your choice : ");
        ch = s.nextInt();

        switch(ch)
        {
            case 1 :
                this.addStudent();
                break;
            case 2 :
                this.updateStudent();
                break;
            case 3 :
                this.removeStudent();
                break;
            case 4 :
                this.displayStudent();
                break;
            case 5 :
                new CourseDetails(s,c);
                break;
            case 6 :
                new Examination(s,c);
                break;
            case 7 :
               new LoginAdmin(s);
            case 8 :
                System.exit(0);
            default:
                System.out.println("Please enter correct choice");
                new AdminMenu(s,c);
        }
    }

    public void addStudent()
    {
        do {
            try
            {
                System.out.println("Enter roll no. : ");
                roll = s.nextInt();

                p = c.prepareStatement("select roll from studentdetails where roll = ?");
                p.setInt(1,roll);

                res = p.executeQuery();

                if(res.next())
                {
                    System.out.println("This roll no. is already added");
                    this.menu();
                }else
                {
                    s.nextLine();

                    System.out.println("Enter student name : ");
                    name = s.nextLine();


                    System.out.println("Enter father name : ");
                    father = s.nextLine();

                    System.out.println("Enter student email id : ");
                    email = s.next();

                    s.nextLine();

                    System.out.println("Enter student address : ");
                    address = s.nextLine();

                    System.out.println("Enter student phone number  :");
                    phone = s.nextLong();

                    s.nextLine();

                    System.out.println("Enter counsellor name : ");
                    counsellor = s.nextLine();

                    try {
                        p = c.prepareStatement("insert into StudentDetails values (?,?,?,?,?,?,?)");
                        p.setInt(1, roll);
                        p.setString(2, name);
                        p.setString(3, father);
                        p.setString(4, email);
                        p.setString(5, address);
                        p.setLong(6,phone);
                        p.setString(7,counsellor);

                        int i = p.executeUpdate();

                        if (i >= 0) {
                            System.out.println("Student added successfully");
                        } else {
                            System.out.println("Student not added");
                        }
                    } catch (Exception e) {
                        System.out.println("Error : " + e);
                    }
                }
            }catch (SQLException se)
            {
                se.printStackTrace();
            }

            System.out.println("Do you want to add more student press(Y/N)");
            ch2 = s.next();
            ch2 =ch2.trim();
            ch2 = ch2.toLowerCase();
            ch3 = ch2.charAt(0);
        } while (ch3 == 'y');
        this.menu();
    }

    public void updateStudent()
    {
        do
        {
            try
            {
                System.out.println("Enter roll no. whose record you want to update :");
                roll = s.nextInt();

                try
                {
                    p = c.prepareStatement("select * from StudentDetails where roll = ?");
                    p.setInt(1, roll);

                    res = p.executeQuery();

                    if (!res.next())
                    {
                        System.out.println("Student not found");
                    }else
                    {
                        String uc;
                        roll = res.getInt(1);
                        name = res.getString(2);
                        father = res.getString(3);
                        email = res.getString(4);
                        address = res.getString(5);
                        phone = res.getLong(6);
                        counsellor = res.getString(7);

                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-6s%-20s%-20s%-30s%-30s%-20s%-10s\n","Roll","Student Name","Father Name","Email ID","Address","Phone","Counsellor");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-6s%-20s%-20s%-30s%-30s%-20s%-10s\n",roll,name,father,email,address,phone,counsellor);
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

                        System.out.println("What do you want to update");
                        uc = s.next();

                        switch(uc)
                        {
                            case "name" :
                                System.out.println("Enter new name : ");
                                s.nextLine();
                                name = s.nextLine();
                                break;
                            case "email" :
                                System.out.println("Enter new email id : ");
                                email = s.next();
                                break;
                            case "address" :
                                System.out.println("Enter new address : ");
                                s.nextLine();
                                address = s.nextLine();
                                break;
                            case "phone" :
                                System.out.println("Enter new phone number :");
                                phone = s.nextLong();
                                break;
                            case "father" :
                                System.out.println("Enter father name :");
                                s.nextLine();
                                father = s.nextLine();
                                break;
                            case "counsellor" :
                                System.out.println("Enter counsellor name :");
                                s.nextLine();
                                counsellor = s.nextLine();
                                break;
                            default:
                                System.out.println("Input not match");
                        }
                        p = c.prepareStatement("update StudentDetails set name = ?,fathername = ?,email = ?,address = ?,phone = ?,counsellor = ? where roll = ?");
                        p.setString(1, name);
                        p.setString(2,father);
                        p.setString(3, email);
                        p.setString(4,address);
                        p.setLong(5,phone);
                        p.setString(6,counsellor);
                        p.setInt(7, roll);

                    }
                }catch (SQLException se)
                {
                    se.printStackTrace();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("Do you want to update more student press(Y/N)");
            ch2 = s.next();
            ch2 =ch2.trim();
            ch2 = ch2.toLowerCase();
            ch3 = ch2.charAt(0);
        }while (ch3 == 'y');
        this.menu();
    }

    public void removeStudent()
    {
        do
        {
            try
            {
                System.out.println("Enter roll no. of student whose records you want to delete : ");
                roll = s.nextInt();

                try
                {
                    p = c.prepareStatement("select * from StudentDetails where roll = ?");
                    p.setInt(1, roll);

                    res = p.executeQuery();
                    if (!res.next()) {
                        System.out.println("Student not found");
                    }else
                    {
                        roll = res.getInt(1);
                        name = res.getString(2);
                        father = res.getString(3);
                        email = res.getString(4);
                        address = res.getString(5);
                        phone = res.getLong(6);
                        counsellor = res.getString(7);

                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-6s%-20s%-20s%-30s%-30s%-20s%-10s\n","Roll","Student Name","Father Name","Email ID","Address","Phone","Cousellor");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
                        System.out.printf("%-6s%-20s%-20s%-30s%-30s%-20s%-10s\n",roll,name,father,email,address,phone,counsellor);
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

                        p = c.prepareStatement("delete from StudentDetails where roll = ?");
                        p.setInt(1, roll);
                        int i = p.executeUpdate();

                        if (i >= 0)
                        {
                            System.out.println("Record deleted successfully");
                        }
                    }
                }catch (SQLException se)
                {
                    se.printStackTrace();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("Do you want to remove more student press(Y/N)");
            ch2 = s.next();
            ch2 =ch2.trim();
            ch2 = ch2.toLowerCase();
            ch3 = ch2.charAt(0);
        }while (ch3 == 'y');
        this.menu();
    }

    public void displayStudent()
    {
        do
        {
            try
            {
                st = c.createStatement();
                String sql = "select * from StudentDetails";

                res = st.executeQuery(sql);

                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-6s%-20s%-20s%-30s%-30s%-15s%-10s\n","Roll","Student Name","Father Name","Email ID","Address","Phone","Counsellor");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

                while (res.next())
                {
                    roll = res.getInt(1);
                    name = res.getString(2);
                    father = res.getString(3);
                    email = res.getString(4);
                    address = res.getString(5);
                    phone = res.getLong(6);
                    counsellor = res.getString(7);

                    System.out.printf("%-6s%-20s%-20s%-30s%-30s%-15s%-10s\n",roll,name,father,email,address,phone,counsellor);
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("Do you want to see more student press(Y/N)");
            ch2 = s.next();
            ch2 =ch2.trim();
            ch2 = ch2.toLowerCase();
            ch3 = ch2.charAt(0);
        }while (ch3 == 'y');
        this.menu();
    }
}
