
/*
 * @author : Umair Sheikh
 * version 1.0
 * 28-04-2021
 */

import java.util.*;

class StudentAdmin
{
    Scanner s;
    int ch;

    public StudentAdmin(Scanner s)
    {
        this.s = s;
        this.studentAdmin();
    }

    public void studentAdmin()
    {
        try
        {
            System.out.println("\t\t || SALVATORE UNIVERSITY ||");
            System.out.printf("%-6s%-10s\n","No","OPTION");
            System.out.printf ("%-6s%-10s\n","1","Admin");
            System.out.printf ("%-6s%-10s\n","2","Student");
            System.out.printf ("%-6s%-10s\n","3","Exit");
            System.out.println("Enter your choice : ");
            ch = s.nextInt();

            switch (ch)
            {
                case 1 :
                    new LoginAdmin(s);
                    break;
                case 2 :
                    LoginStudent st = new LoginStudent(s);
                    st.login();
                    st.validateLogin();
                    break;
                case 3 :
                    System.out.println("Closed...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter correct choice");
                    new StudentAdmin(s);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


public class StudentAdminPRJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new StudentAdmin(sc);
    }
}
