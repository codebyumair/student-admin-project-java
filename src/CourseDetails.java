
/*
 * @author Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.sql.Connection;
import java.util.Scanner;

public class CourseDetails {
    Scanner s;
    Connection c;
    int ch;

    public CourseDetails(Scanner s,Connection c)
    {
        this.s = s;
        this.c = c;
        this.displayCourse();
    }

    public void displayCourse()
    {
        System.out.println("\t\t || SALAVATORE UNIVERSITY ||");
        System.out.printf("%-6s%-20s\n", "No","Course");
        System.out.printf("%-6s%-20s\n", "1","JAVA");
        System.out.printf("%-6s%-20s\n", "2","HTML");
        System.out.printf("%-6s%-20s\n", "3","Go to Menu Page");
        System.out.printf("%-6s%-20s\n", "4","Exit");

        System.out.println("Enter your choice :");
        ch = s.nextInt();
        switch(ch)
        {
            case 1:
                this.javaDetails();
                break;
            case 2:
                this.htmlDetails();
                break;
            case 3:
                new AdminMenu(s,c);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Please enter correct choice");
                new CourseDetails(s,c);
        }
        System.out.println("\nDo you want to take admission ? (Yes/No)");
        String confirm = s.next();
        confirm = confirm.trim();
        confirm = confirm.toLowerCase();
        char cnf = confirm.charAt(0);

        if(cnf == 'y')
        {
            System.out.println("Enter details of student : ");
            AdminMenu am = new AdminMenu(s,c);
            am.addStudent();
        }else
        {
            new CourseDetails(s,c);
        }
    }

    public void javaDetails()
    {
        System.out.printf("%-6s%-10s%-20s%-20s\n", "No","Course","Developer","Year");
        System.out.printf("%-6s%-10s%-20s%-20s\n", "1","JAVA","James Gosling","1995");
    }

    public void htmlDetails()
    {
        System.out.printf("%-6s%-10s%-20s%-20s\n", "No","Course","Developer","Year");
        System.out.printf("%-6s%-10s%-20s%-20s\n", "2","HTML","Tim Bernes Lee","1993");
    }

}
