
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Dept {

    private final String deptName;
    private final int noOfEmployees;
    private Employee e;

    public Dept(String dept, int noOfEmployees) {
        this.deptName = dept;
        this.noOfEmployees = noOfEmployees;
    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to Email Administration System!");
            System.out.println("Create a department and add employees." +
                    "Following are the available departments:\n" +
                    "Sales\nAccounting\nDevelopment\n If you add a dept other than the " +
                    "ones mentioned, you will get a blank result." +
                    " What is the name of the dept you want to work on?");
            String userDept = scan.nextLine();
            System.out.println("How many employees does the department have?");
            int userEmployees = scan.nextInt();
            scan.nextLine();  // Consume newline left-over
            Dept dept = new Dept(userDept, userEmployees);
            ArrayList<Employee> deptList = new ArrayList<>();
            for (int i = 0; i < userEmployees; i++) {
                System.out.println("Please enter Employee " + (i + 1) + "'s information");
                if (i > 0) {
                    scan.nextLine();  // Consume newline left-over
                }
                System.out.println("Enter the first name");
                String fName = scan.nextLine();
                System.out.println("Enter the last name");
                String lName = scan.nextLine();
                System.out.println("Enter the company name");
                String cName = scan.nextLine();
                System.out.println("Enter the dept that employee works in:");
                String dName = scan.nextLine();
                System.out.println("Enter the mailbox capacity");
                int cap = scan.nextInt();
                Employee e = new Employee(fName, lName, cName, dName, cap);
                System.out.println("Employee created: " + e);
                dept.addEmployee(e);
                deptList.add(e);
            }
            System.out.println("You currently have " + deptList.size() + " employee(s):");
            int i = 1;
            for (Employee e : deptList) {
                System.out.println(i + ". " + e);
                i++;
            }
            System.out.println("Which employee's details do you want to work on? (Enter numbers)");
            int empNo = scan.nextInt();
            Employee userEmp = deptList.get(empNo - 1);
            System.out.println("What would you like to do with this employee's details?");
            int userInput = -1;
            do {
                System.out.println("1.Generate password\n2.Generate Email\n3.Set Alternate Email\n" +
                        "4.Change password\n5.Set Mailbox Capacity\n" +
                        "6.See employee info\n7.Write employee info to file\n"+
                        "8.Read text file\n9.Exit\n(Choose a number from above list)");
                userInput = scan.nextInt();
                switch (userInput) {
                    case 1:
                        userEmp.generatePassword();
                        break;
                    case 2:
                        userEmp.generateEmail();
                        break;
                    case 3:
                        System.out.println("Enter alternate email id:");
                        String altEmail = scan.nextLine();
                        userEmp.setAlternateEmail(altEmail);
                        break;
                    case 4:
                        userEmp.changePassword();
                        break;
                    case 5:
                        System.out.println("Enter mailbox capacity:");
                        int mailboxCap = scan.nextInt();
                        scan.nextLine();  // Consume newline left-over
                        userEmp.setMailboxCapacity(mailboxCap);
                        break;
                    case 6:
                        System.out.println(userEmp);
                        break;
                    case 7:
                        userEmp.writeFile();
                        break;
                    case 8:
                        scan.nextLine();  // Consume newline left-over
                        System.out.println("Enter absolute path of file that you want to read:");
                        String path = scan.nextLine();
                        userEmp.readFile(path);
                        break;
                    case 9: break;
                    default:
                        System.out.println("Wrong choice");
                }
            } while (userInput != 9);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addEmployee(Employee emp) throws Exception {
        if (emp.getDepartmentName().equalsIgnoreCase(deptName)) {
            this.e = emp;
        } else {
            throw new Exception("Department mismatch: Employee you are trying to include belongs to " +
                    emp.getDepartmentName() +
                    " but this is " + this.deptName);
        }
    }


    @Override
    public String toString() {
        return "Dept{" +
                "deptName='" + deptName + '\'' +
                ", noOfEmployees=" + noOfEmployees +
                ", e=" + e +
                '}';
    }
}
