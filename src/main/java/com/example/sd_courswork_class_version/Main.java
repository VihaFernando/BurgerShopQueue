package com.example.sd_courswork_class_version;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;

public class Main {
    private static final int[] MAX_CUSTOMERS_PER_QUEUE = new int[]{2, 3, 5};//initialise the queues
    public static FoodQueue[] queues = new FoodQueue[3];
    public static WaitingCircularQueue waitingQueue = new WaitingCircularQueue(10);//waiting queue
    public static int remainingBurgers = 50;//burger stock
    private static int[] queueSizes = new int[3];

    public Main() { //main program
    }

    public static void main(String[] args) { //indexing food queues
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;
        queues[0] = new FoodQueue(2);
        queues[1] = new FoodQueue(3);
        queues[2] = new FoodQueue(5);

        do { //creating display menu using switch case
            displayMenu();
            String choice = scanner.next().toUpperCase();
            scanner.nextLine();
            switch (choice) {
                case "100":
                case "VFQ":
                    viewAllQueues();
                    Continue(scanner);
                    break;
                case "101":
                case "VEQ":
                    viewAllEmptyQueues();
                    Continue(scanner);
                    break;
                case "102":
                case "ACQ":
                    addCustomer(scanner);
                    Continue(scanner);
                    break;
                case "103":
                case "RCQ":
                    removeCustomer(scanner);
                    Continue(scanner);
                    break;
                case "104":
                case "PCQ":
                    removeServedCustomer(scanner);
                    Continue(scanner);
                    break;
                case "105":
                case "VCS":
                    viewCustomersSorted();
                    Continue(scanner);
                    break;
                case "106":
                case "SPD":
                    storeProgramData();
                    Continue(scanner);
                    break;
                case "107":
                case "LPD":
                    loadProgramData();
                    Continue(scanner);
                    break;
                case "108":
                case "STK":
                    viewRemainingBurgerStock();
                    Continue(scanner);
                    break;
                case "109":
                case "AFS":
                    addBurgersToStock(scanner);
                    Continue(scanner);
                    break;
                case "110":
                case "IFQ":
                    incomeOfQueue();
                    Continue(scanner);
                    break;
                case "112":
                case "GUI":
                    Application.launch(HelloApplication.class, args);
                    Continue(scanner);
                    break;
                case "999":
                case "EXT":
                    System.out.println("Exiting the program...");
                    exitMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while(!exitMenu);

        scanner.close();
    }

    private static void displayMenu() {    //print the display menu
        if (remainingBurgers <= 10) {
            System.out.println("Remaining Burgers count is low!");
        }

        System.out.println("*********************************");
        System.out.println("*          Menu Options         *");
        System.out.println("*********************************");
        System.out.println("100 or VFQ: View all Queues");
        System.out.println("101 or VEQ: View all Empty Queues");
        System.out.println("102 or ACQ: Add customer to a Queue");
        System.out.println("103 or RCQ: Remove a customer from a Queue (From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("106 or SPD: Store Program Data into file");
        System.out.println("107 or LPD: Load Program Data from file");
        System.out.println("108 or STK: View Remaining burgers Stock");
        System.out.println("109 or AFS: Add burgers to Stock");
        System.out.println("110 or IFQ: Print Income of each queue");
        System.out.println("112 or GUI: GUI Application");
        System.out.println("999 or EXT: Exit the Program");
        System.out.println("");
        System.out.print("Enter your choice: ");
    }

    private static void viewAllQueues() {
        System.out.println("*****************");
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");

        for(int i = 0; i < 5; ++i) {      //checking all rows and columns
            String[] row = new String[3];

            for(int j = 0; j < 3; ++j) {
                try {
                    if (queues[j].getCustomers().get(i) != null) {
                        row[j] = "O";
                    } else if (i < queues[j].getMaxSize()) {
                        row[j] = "X";
                    } else {
                        row[j] = " ";
                    }
                } catch (Exception error) {
                    if (i < queues[j].getMaxSize()) {
                        row[j] = "X";
                    } else {
                        row[j] = " ";
                    }
                }
            }

            for (String k: row) {
                System.out.print("  " + k + " | ");
            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println("O - Occupied  X - Not  Occupied\n");
    }

    private static void Continue(Scanner scanner) {      //asking from user to continue the program
        System.out.print("Do you want to continue(y/n): ");
        switch (scanner.next()) {
            case "y":
            case "Y":
                System.out.println();
                break;
            case "n":
            case "N":
                System.out.println("Thank you for using our software");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Input....");
        }

    }

    private static void viewAllEmptyQueues() {
        System.out.println("* Empty Queues *");
        System.out.println("*****************");

        for(int i = 0; i < 3; ++i) {       //checking all the queue indexes to find empty queues
            if (queues[i].isEmpty()) {
                System.out.println("Cashier " + (i + 1) + " - Empty");
            }
        }

    }

    private static void addCustomer(Scanner scanner) {   //adding customers to queues
        int minQueueIndex = getMinQueueIndex();

        if (minQueueIndex == -1) {           //checking minimum queue
            System.out.println("All queues are full. Cannot add more customers. New Customer will be added to waiting Queue");
            System.out.print("Enter the customer's first name: ");
            String firstName = scanner.nextLine();            //getting customer names

            System.out.print("Enter the customer's last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter the number of burgers required: ");
            int numBurgers = scanner.nextInt();

            Customer customer = new Customer(firstName, lastName, numBurgers);   // taking customers form waiting queue
            waitingQueue.enqueue(customer);
            System.out.println("Added customer to waiting Queue");
            return;
        }

        System.out.print("Enter the customer's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the customer's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter the number of burgers required: ");
        int numBurgers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        Customer customer = new Customer(firstName, lastName, numBurgers);
        queues[minQueueIndex].enqueue(customer);


        System.out.println("Customer added successfully.");
    }

    private static int getMinQueueIndex() {          //getting minimum queue index
        int minIndex = -1;
        if (queues[0].getCustomers().size() < queues[0].getMaxSize()) {
            minIndex = 0;
        }

        if (queues[1].getSize() < queues[2].getSize() && queues[1].getSize() < queues[1].getMaxSize() && queues[1].getSize() < queues[2].getSize()) {
            minIndex = 1;
        } else if (queues[2].getSize() < queues[1].getSize() && queues[2].getSize() < queues[0].getSize() && queues[2].getSize() < queues[2].getMaxSize()) {
            minIndex = 2;
        } else if (queues[1].getSize() == queues[2].getSize() && queues[1].getSize() < queues[1].getMaxSize()) {
            minIndex = 1;
        } else if (queues[0].getSize() == 2 && queues[1].getSize() == 3 && queues[2].getSize() < queues[2].getMaxSize()) {
            minIndex = 2;
        }

        return minIndex;
    }

    private static void removeCustomer(Scanner scanner) {    //remove customer function
        System.out.print("Enter the cashier number (1 to 3): ");   //asking for customer location.
        int cashierNumber = scanner.nextInt();
        scanner.nextLine();

        int queueIndex = cashierNumber - 1;

        if (queueIndex < 0 || queueIndex >= 3) {          //check whether cashier number is correct
            System.out.println("Invalid cashier number. Please try again.");
            return;
        }

        if (queues[queueIndex].isEmpty()) {        //check if the queue is empty
            System.out.println("Queue is empty. Cannot remove customer.");
            return;
        }

        System.out.print("Enter the customer location (1 to " + queues[queueIndex].getSize() + "): ");
        int customerLocation = scanner.nextInt();
        scanner.nextLine();

        if (customerLocation < 1 || customerLocation > queues[queueIndex].getSize()) {
            System.out.println("Invalid customer location. Please try again.");
            return;
        }

        Customer removedCustomer = queues[queueIndex].dequeue(customerLocation - 1);
        if (!waitingQueue.getWaitingQ().isEmpty()) {       //condition for check waiting queue and get customers form there
            int index = waitingQueue.dequeue();
            if (index != -1){
                Customer customer = waitingQueue.getWaitingQ().get(index);
                queues[queueIndex].enqueue(customer);
                System.out.println(customer.getFullName() + " moved from waiting to Cashier");
                System.out.println(" ");
            }
        }
        System.out.println("Customer " + removedCustomer.getFullName() + " removed successfully.");
    }

    private static void removeServedCustomer(Scanner scanner) {
        int removedCount = 0;

        System.out.print("Enter the queue number (1 to " + 3 + "): ");     //ask for the queue number
        int queueNumber = scanner.nextInt();

        if (queueNumber < 1 || queueNumber > 3) {        //check the queue
            System.out.println("Invalid queue number. Please try again.");
            return;
        }

        int queueIndex = queueNumber - 1;

        if (!queues[queueIndex].isEmpty()) {
            Customer removedCustomer = queues[queueIndex].getCustomers().get(0);//removing customer and update burger count
            if (remainingBurgers < removedCustomer.getBurgersRequired()){
                System.out.println("Remaining burgers are not enough for serve. restore the burger stock");  //check the remaining burger count is enough for serve
            }
            else {
                remainingBurgers -= removedCustomer.getBurgersRequired();
                queues[queueIndex].dequeue(0);
                removedCount++;

                double customerIncome = removedCustomer.getBurgersRequired() * 650; //calculate customer income

                System.out.println("Customer " + removedCustomer.getFullName() + " from Cashier " + queueNumber + " removed.");
                System.out.println("Total Price: LKR." + customerIncome);

                if (!waitingQueue.getWaitingQ().isEmpty()) {        //taking customers from waiting queue
                    int index = waitingQueue.dequeue();
                    if (index != -1){
                        Customer customer = waitingQueue.getWaitingQ().get(index);
                        queues[queueIndex].enqueue(customer);
                        System.out.println(customer.getFullName() + " moved from waiting to Cashier");
                        System.out.println(" ");
                    }
                }
            }

            if (removedCount == 0) {
                System.out.println("No served customers found in Cashier " + queueNumber + ".");
            }

        }

    }


    private static void viewCustomersSorted() {  //storing customers name in alphabetical order
        Customer[] allCustomers = new Customer[MAX_CUSTOMERS_PER_QUEUE[2] * 3];
        int index = 0;

        int i;
        int j;
        for(i = 0; i < 3; ++i) {
            for(j = 0; j < queues[i].getSize(); ++j) {
                allCustomers[index] = (Customer)queues[i].getCustomers().get(j);
                ++index;
            }
        }

        for(i = 0; i < allCustomers.length - 1; ++i) {
            for(j = 0; j < allCustomers.length - i - 1; ++j) {
                if (allCustomers[j] != null && allCustomers[j + 1] != null && compareCustomers(allCustomers[j], allCustomers[j + 1]) > 0) {    // compare the names
                    Customer temp = allCustomers[j];
                    allCustomers[j] = allCustomers[j + 1];
                    allCustomers[j + 1] = temp;
                }
            }
        }

        System.out.println("Customers Sorted in alphabetical order:");
        for (Customer customer : allCustomers) {
            if (customer != null) {
                System.out.println(customer.getFullName());
            }
        }
    }

    private static int compareCustomers(Customer c1, Customer c2) {
        String fullName1 = c1.getFirstName() + " " + c1.getLastName();
        String fullName2 = c2.getFirstName() + " " + c2.getLastName();

        return fullName1.compareToIgnoreCase(fullName2);
    }

    private static void storeProgramData() {            //storing data to a text file , reading line by line
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("program_data.txt"));//file path
            writer.write("Remaining Burgers: " + remainingBurgers);//write the remaining burger count
            writer.newLine();
            writer.write("Queue Sizes:");

            int i;
            for(i = 0; i < 3; ++i) {            //write queue
                FoodQueue que = queues[i];
                writer.write(" " + que.getMaxSize());
            }

            writer.newLine();

            for(i = 0; i < 3; ++i) {
                writer.write("Queue " + (i + 1) + " Customers:");
                FoodQueue queue = queues[i];
                int size = queue.getSize();

                for(int j = 0; j < size; ++j) {//write customer name
                    Customer customer = (Customer)queue.getCustomers().get(j);
                    String getname = customer.getFirstName();
                    writer.write(" " + getname + " " + customer.getLastName() + " " + customer.getBurgersRequired());
                }

                writer.newLine();
            }

            writer.close();
            System.out.println("Program data stored successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while storing program data: " + e.getMessage());
        }

    }

    private static void loadProgramData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("program_data.txt"));    //loading the stored data to the program

            while(true) {
                String line;
                while((line = reader.readLine()) != null) {
                    String[] parts;
                    if (line.startsWith("Remaining Burgers:")) {   //loading the remaining burger count
                        parts = line.split(":");
                        remainingBurgers = Integer.parseInt(parts[1].trim());
                    } else {
                        String[] sizes;                 //loading queues
                        int count;
                        if (line.startsWith("Queue Sizes:")) {
                            parts = line.split(":");
                            sizes = parts[1].trim().split(" ");

                            for(count = 0; count < sizes.length; ++count) {
                                queueSizes[count] = Integer.parseInt(sizes[count]);
                            }
                        } else if (line.startsWith("Queue ")) {     //check the stored line using line.startsWith and loading the customers first name last name and burger count
                            int queueIndex = Character.getNumericValue(line.charAt(6)) - 1;
                            sizes = line.split(":");
                            count = 0;
                            String firstname = "";
                            String lastName = "";
                            String burgerCount = "";
                            String[] names = sizes[1].trim().split(" ");

                            for(int i = 0; i < names.length; ++i) {
                                if (count == 0) {
                                    firstname = names[i];
                                    ++count;
                                } else if (count == 1) {
                                    lastName = names[i];
                                    ++count;
                                } else if (count == 2) {
                                    burgerCount = names[i];
                                    Customer customer = new Customer(firstname, lastName, Integer.parseInt(burgerCount));
                                    queues[queueIndex].enqueue(customer);
                                    count = 0;
                                }
                            }
                        }
                    }
                }

                reader.close();
                System.out.println("Program data loaded successfully.");
                break;
            }
        } catch (IOException err) {       // print a error message if there is no stored data
            System.out.println("Cannot find a stored data: " + err.getMessage());
        }

    }

    private static void viewRemainingBurgerStock() {     // print remaining burger count
        System.out.println("Remaining burger stock: " + remainingBurgers);
        System.out.println("");
    }

    private static void addBurgersToStock(Scanner scanner) {        //adding burgers to the stock
        if (remainingBurgers < 50) {
            System.out.print("Enter the number of burgers to add: ");        //taking no.of burgers
            int burgersToAdd = scanner.nextInt();
            if (remainingBurgers + burgersToAdd > 50) {     //checking the capacity
                System.out.println("Cannot add more burgers. Maximum stock reached.");
            } else {
                remainingBurgers += burgersToAdd;
                System.out.println("Burgers added to stock. Remaining stock: " + remainingBurgers);
            }
        } else {
            System.out.println("Cannot add more burgers. Burger Stock is full");
        }

    }

    private static void incomeOfQueue() {  // calculating the income of each queues
        int[] queueIncome = new int[]{0, 0, 0};

        for(int i = 0; i < 3; ++i) {     //taking all three queues
            FoodQueue q = queues[i];

            for(int j = 0; j < q.getCustomers().size(); ++j) {
                Customer customer = (Customer)q.getCustomers().get(j);
                queueIncome[i] += customer.getBurgersRequired() * 650;
            }
        }

        System.out.println("Income of each queue:");
        System.out.println("Queue 1: LKR" + queueIncome[0]);
        System.out.println("Queue 2: LKR" + queueIncome[1]);
        System.out.println("Queue 3: LKR" + queueIncome[2]);
    }
}