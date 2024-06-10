import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PlaneManagement {

    public static Scanner scanner= new Scanner(System.in);
    public static int userSerlection;
    public static int[] rowA  = new int [14];
    public static int[] rowB  = new int [12];
    public static int[] rowC  = new int[12];
    public static int[] rowD  = new int [14];
    public static String[] tickets = {};
    public static int ticketprice;
    public static int totalTicketValue;
    public static Person customer;
    public static Ticket soldticket;
    public static Ticket[] soldtickets= {};



    public static void main(String[]args) {

        System.out.println("Welcome to the Plane Management application");


        System.out.println("**************************************************************");
        System.out.println("*                        Menu Option                         *");
        System.out.println("**************************************************************");
        System.out.println("1) Buy a seat");
        System.out.println("2) Cancel a seat");
        System.out.println("3) Find first available seat");
        System.out.println("4) Show seating plan");
        System.out.println("5) Print tickets information and total sales");
        System.out.println("6) Search ticket");
        System.out.println("0) Quit");
        System.out.println("**************************************************************");
        System.out.println("Please select an option: ");
        while (true){
            try {
                while (true) {
                    Scanner scan = new Scanner(System.in);
                    userSerlection = scan.nextInt();
                    if(userSerlection>-1 && userSerlection<7){
                        switch (userSerlection){
                            case 0: System. exit(0);
                            case 1: buy_seat();
                            case 2: cancel_seat();
                            case 3: find_first_available();
                            case 4: show_seating_plan();
                            case 5: print_tickets_info();
                            case 6: search_ticket();
                        }
                        break;
                    }else {
                        System.out.println("Please enter a valid number from the menu.");
                    }break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Please enter a valid input. Try again!\r");
                System.out.println("Please select an option: ");
            }
        }


    }
    public static void buy_seat(){
        while (true) {
            System.out.println("Please enter a row letter (or press Q to quit to the main menu)\n");

            String rowLetter = scanner.next();
            rowLetter = rowLetter.toUpperCase();
            if(rowLetter.equals("Q")){
                main(null);
            }

            if (rowLetter.equals("A") || rowLetter.equals("B") || rowLetter.equals("C") || rowLetter.equals("D")) {
                System.out.println("Please enter a seat number (or press 0 to quit to the main menu)\n");
                int seatNumber = scanner.nextInt();
                if(seatNumber==0){
                    main(null);
                }
                if (((rowLetter.equals("A") || rowLetter.equals("D")) && seatNumber <= 14)||((rowLetter.equals("C") || rowLetter.equals("B")) && seatNumber <= 12)) {
                    switch (rowLetter) {
                        case "A":
                            if (rowA[seatNumber - 1] == 0) {
                                rowA[seatNumber - 1] = 1;
                                break;

                            } else {
                                System.out.println("Seat is already reserved");
                                continue;
                            }

                        case "B":
                            if (rowB[seatNumber - 1] == 0) {
                                rowB[seatNumber - 1] = 1;
                                break;

                            } else {
                                System.out.println("Seat is already reserved");
                                continue;
                            }

                        case "C":
                            if (rowC[seatNumber - 1] == 0) {
                                rowC[seatNumber - 1] = 1;
                                break;

                            } else {
                                System.out.println("Seat is already reserved");
                                continue;
                            }

                        case "D":
                            if (rowD[seatNumber - 1] == 0) {
                                rowD[seatNumber - 1] = 1;
                                break;
                            } else {
                                System.out.println("Seat is already reserved");
                                continue;
                            }
                    }

                    String selectedseat = rowLetter+ seatNumber;

                    String[] newtickets = Arrays.copyOf(tickets, tickets.length + 1);
                    newtickets[tickets.length] = selectedseat;
                    tickets = Arrays.copyOf(newtickets, newtickets.length);

                    String name;
                    String surname;
                    String email;
                    System.out.println(rowLetter + seatNumber + " Seat is selected. We need your information to save the" +
                            " ticket info");

                    System.out.println("Please enter the name : ");
                    name = scanner.next();
                    System.out.println("Please enter the surname : ");
                    surname = scanner.next();
                    System.out.println("Please enter the email : ");
                    email = scanner.next();

                    customer = new Person(name, surname, email);

                    if (seatNumber < 6) {
                        ticketprice = 200;
                        totalTicketValue+=200;
                    } else if (seatNumber < 10) {
                        ticketprice = 150;
                        totalTicketValue+=150;
                    } else {
                        ticketprice=180;
                        totalTicketValue+=180;
                    }

                    soldticket = new Ticket(rowLetter,seatNumber,ticketprice,customer);
                    Ticket[] newsoldtickets = Arrays.copyOf(soldtickets, soldtickets.length + 1);
                    newsoldtickets[soldtickets.length] = soldticket;
                    soldtickets = Arrays.copyOf(newsoldtickets, newsoldtickets.length);

                    save(rowLetter,seatNumber,ticketprice,customer);

                    System.out.println("\r");
                    System.out.println("Enter Q to quit to the main menu\r");
                    System.out.println("Enter any letter to book another seat");
                    String userinput = scanner.next();
                    userinput = userinput.toUpperCase();
                    if (userinput.equals("Q")) {
                        main(null);

                    } else {
                        continue;
                    }
                } else {
                    System.out.println("Please enter a valid seat number\n");
                }

                }else{
                    System.out.println("Invalid row letter\n");
                }
        }

    }

    public static void cancel_seat() {
        while (true) {
            System.out.println("Please enter a row letter (or press Q to quit to the main menu)\n");

            String rowLetter = scanner.next();
            rowLetter = rowLetter.toUpperCase();
            if(rowLetter.equals("Q")){
                main(null);
            }

            if (rowLetter.equals("A") || rowLetter.equals("B") || rowLetter.equals("C") || rowLetter.equals("D")) {
                System.out.println("Please enter a seat number (or press 0 to quit to the main menu)\n");
                int seatNumber = scanner.nextInt();
                if(seatNumber==0){
                    main(null);
                }
                if (((rowLetter.equals("A") || rowLetter.equals("D")) && seatNumber <= 14)||((rowLetter.equals("C") || rowLetter.equals("B")) && seatNumber <= 12)) {
                    switch (rowLetter) {
                        case "A":
                            if (rowA[seatNumber - 1] == 1) {
                                rowA[seatNumber - 1] = 0;
                                System.out.println(rowLetter+seatNumber+" seat cancelled.");
                                break;

                            } else {
                                System.out.println("Seat is already available");
                                continue;
                            }

                        case "B":
                            if (rowB[seatNumber - 1] == 1) {
                                rowB[seatNumber - 1] = 0;
                                System.out.println(rowLetter+seatNumber+" seat cancelled.");
                                break;

                            } else {
                                System.out.println("Seat is already available");
                                continue;
                            }

                        case "C":
                            if (rowC[seatNumber - 1] == 1) {
                                rowC[seatNumber - 1] = 0;
                                System.out.println(rowLetter+seatNumber+" seat cancelled.");
                                break;

                            } else {
                                System.out.println("Seat is already available");
                                continue;
                            }

                        case "D":
                            if (rowD[seatNumber - 1] == 1) {
                                rowD[seatNumber - 1] = 0;
                                System.out.println(rowLetter+seatNumber+" seat cancelled.");
                                break;
                            } else {
                                System.out.println("Seat is already available");
                                continue;
                            }
                    }
                    String selectedseat = rowLetter+Integer.toString(seatNumber);

                    // Find the index of the element to remove
                    int indexToRemove = -1;
                    for (int i = 0; i < tickets.length; i++) {
                        if (Objects.equals(tickets[i], selectedseat)) {
                            indexToRemove = i;
                            break;
                        }
                    }

                    // If the element was found in the array
                    if (indexToRemove != -1) {
                        // Create a new array with size one less than the original array
                        String[] newArray = new String[tickets.length - 1];

                        // Copy elements from the original array to the new array, excluding the element to remove
                        for (int i = 0, j = 0; i < tickets.length; i++) {
                            if (i != indexToRemove) {
                                newArray[j++] = tickets[i];
                            }
                        }

                        // Update the original array reference
                        tickets = Arrays.copyOf(newArray, newArray.length);
                    }


                    if (seatNumber < 6) {
                        totalTicketValue-=200;
                    } else if (seatNumber < 10) {
                        totalTicketValue-=150;
                    } else {
                        totalTicketValue-=180;
                    }



                    System.out.println("Enter Q to quit to the main menu\r");
                    System.out.println("Enter any letter to cancel another seat");
                    String userinput = scanner.next();
                    userinput = userinput.toUpperCase();
                    if (userinput.equals("Q")) {

                        main(null);

                    } else {
                        continue;
                    }
                } else {
                    System.out.println("Please enter a valid seat number\n");
                }

            } else {
                System.out.println("Invalid row letter\n");
            }
        }
    }

    public static void find_first_available(){
        boolean findseat = false;
        while (true){
            for(int i=0;i<rowA.length;i++){
                if (rowA[i]==0) {
                    System.out.println("The first available seat is A" + (i + 1));
                    findseat=true;
                    break;
                }
            }
            if(findseat==false){
                for(int i=0;i<rowB.length;i++){
                    if (rowB[i]==0) {
                        System.out.println("The first available seat is B" + (i + 1));
                        findseat=true;
                        break;
                    }
                }
            }
            if(findseat==false){
                for(int i=0;i<rowC.length;i++){
                    if (rowC[i]==0) {
                        System.out.println("The first available seat is C" + (i + 1));
                        findseat=true;
                        break;
                    }
                }
            }
            if(findseat==false){
                for(int i=0;i<rowD.length;i++){
                    if (rowD[i]==0) {
                        System.out.println("The first available seat is D" + (i + 1));
                        findseat=true;
                        break;
                    }
                }
            }else{}



            System.out.println("Enter Q to quit to the main menu\r");
            System.out.println("Enter any letter to find first available seat again");
            String userinput = scanner.next();
            userinput = userinput.toUpperCase();
            if (userinput.equals("Q")) {
                main(null);

            } else {
                continue;
            }
        }

    }

    public static void show_seating_plan(){
        while (true){
            for(int i=0;i<rowA.length;i++){
                if (rowA[i] == 0){
                    System.out.printf("O ");
                }else{
                    System.out.printf("X ");
                }
            }
            System.out.println("\r");
            for(int i=0;i<rowB.length;i++){
                if (rowB[i] == 0){
                    System.out.printf("O ");
                }else{
                    System.out.printf("X ");
                }
            }

            System.out.println("\n");
            for(int i=0;i<rowC.length;i++){
                if (rowC[i] == 0){
                    System.out.printf("O ");
                }else{
                    System.out.printf("X ");
                }
            }
            System.out.println("\r");
            for(int i=0;i<rowD.length;i++){
                if (rowD[i] == 0){
                    System.out.printf("O ");
                }else{
                    System.out.printf("X ");
                }
            }
            System.out.println(" ");
            System.out.println("Enter Q to quit to the main menu\r");
            System.out.println("Enter any letter to show seating plan again");
            String userinput = scanner.next();
            userinput = userinput.toUpperCase();
            if (userinput.equals("Q")) {
                main(null);

            } else {
                continue;
            }
        }

    }

    public static void print_tickets_info(){
        while (true){
            System.out.println("Sold Tickets are: ");
            for(int i=0;i<tickets.length;i++){
                System.out.printf(tickets[i]+" ");
            }
            System.out.println("\nTotal sold ticket vale is: "+totalTicketValue);
            System.out.println("Enter Q to quit to the main menu\r");
            System.out.println("Enter any letter to Print tickets information and total sales again");
            String userinput = scanner.next();
            userinput = userinput.toUpperCase();
            if (userinput.equals("Q")) {
                main(null);

            } else {
                continue;
            }
        }

    }

    public static void search_ticket(){
        while (true){
            System.out.println("Please enter a row letter (or press Q to quit to the main menu)\n");

            String rowLetter = scanner.next();
            rowLetter = rowLetter.toUpperCase();
            if(rowLetter.equals("Q")){
                main(null);
            }

            if (rowLetter.equals("A") || rowLetter.equals("B") || rowLetter.equals("C") || rowLetter.equals("D")) {
                System.out.println("Please enter a seat number (or press 0 to quit to the main menu)\n");
                int seatNumber = scanner.nextInt();
                if(seatNumber == 0) {
                    main(null);
                }

                boolean isValidSeat = false;
                switch (rowLetter) {
                    case "A":
                    case "D":
                        isValidSeat = seatNumber >= 1 && seatNumber <= 14;
                        break;
                    case "B":
                    case "C":
                        isValidSeat = seatNumber >= 1 && seatNumber <= 12;
                        break;
                }

                if (isValidSeat) {
                    int[] selectedRow;
                    switch (rowLetter) {
                        case "A":
                            selectedRow = rowA;
                            break;
                        case "B":
                            selectedRow = rowB;
                            break;
                        case "C":
                            selectedRow = rowC;
                            break;
                        case "D":
                            selectedRow = rowD;
                            break;
                        default:
                            selectedRow = new int[0]; // Default to an empty array if rowLetter is invalid
                            break;
                    }

                    if (selectedRow.length >= seatNumber && selectedRow[seatNumber - 1] == 1) {
                        for(int i=0;i<soldtickets.length;i++){
                            if(rowLetter.equals(soldtickets[i].getRow()) && soldtickets[i].getSeat()==seatNumber){

                                System.out.println("Ticket is "+soldtickets[i].getRow()+soldtickets[i].getSeat());
                                System.out.println("Price is "+ soldtickets[i].getPrice()+"\n");
                                System.out.println("Customer Information:");
                                soldtickets[i].getPerson().print_person_info();
                                break;
                            }else{continue;}
                        }
                    } else {
                        System.out.println("This seat is available");
                    }
                    System.out.println("\r");
                    System.out.println("Enter Q to quit to the main menu\r");
                    System.out.println("Enter any letter to search another seat");
                    String userinput = scanner.next();
                    userinput = userinput.toUpperCase();
                    if (userinput.equals("Q")) {
                        main(null);

                    } else {
                        continue;
                    }
                } else {
                    System.out.println("Please enter a valid seat number\n");
                }
            } else {
                System.out.println("Invalid row letter\n");
            }

        }


    }

    public static void save(String row, int seat_num, int price, Person person){
        try {
            FileWriter myWriter = new FileWriter(row+seat_num+".txt");
            myWriter.write(
                "Ticket is "+row+seat_num+"\r"+
                    "Price is "+ price+"\r\r"+"Customer name is "+person.getName()+" "+ person.getSurname()+"\r"+
                    "Customer email is " + person.getEmail()
            );
            myWriter.close();
            System.out.println("Successfully created the text file.\r");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}









