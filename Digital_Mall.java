
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Digital_Mall {

    static Scanner sc = new Scanner(System.in);
    static Connection con;
    products pl = new products();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");

        String dbURL = "jdbc:mysql://localhost:3306/digitalmall";
        String dbUser = "root";
        String dbPass = "";

        con = DriverManager.getConnection(dbURL, dbUser, dbPass);

        System.out.println("================================================");
        System.out.println("|                                             |");
        System.out.println("|  __        __   _                           |");
        System.out.println("|  \\ \\      / /__| | ___ ___  _ __ ___   ___  |");
        System.out.println("|   \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\ |");
        System.out.println("|    \\ V  V /  __/ | (_| (_) | | | | | |  __/ |");
        System.out.println("|     \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___| |");
        System.out.println("|                                             |");
        System.out.println("|            to Digital Mall                  |");
        System.out.println("|                                             |");
        System.out.println("================================================");

        boolean m = true;
        while (m) {

            System.out.println("---------------------------------------------------------");
            System.out.println("|    1. for login or registrationas a customer :-       |");
            System.out.println("|    2. for login as a employee :-                      |");
            System.out.println("|    3. for close Application :-                        |");
            System.out.println("---------------------------------------------------------");
            int choice = sc.nextInt();
            switch (choice) {

                case 1:

                    int ch;
                    boolean b = true;
                    while (b) {

                        //customer login and registration
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("|   1. for login                        :-                           |");
                        System.out.println("|   2. for Registration(For new Account):-                           |");
                        System.out.println("|   3. for back                         :-                           |");
                        System.out.println("---------------------------------------------------------------------");

                        ch = sc.nextInt();
                        switch (ch) {
                            case 1:
                                boolean b1 = loginUser();
                                if (b1 == false) {
                                    m = false;
                                }
                                b = false;
                                break;

                            case 2:

                                registrationUser();

                                b = false;
                                break;

                            case 3:

                                b = false;
                                System.out.println("Thank you for visiting our \"Digital Mall\"!");
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                case 2:
                    Digital_Mall.employee_management.loginEmployee();
                    break;

                case 3:
                    m = false;
                    System.out.println("DIGITAL MALL SOFTWARE IS CLOSE");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        }
    }

    //user login 
    static boolean loginUser() throws Exception {
        try {
            System.out.println("Enter username:-");
            String user_name = sc.next().toLowerCase();

            System.out.println("Enter Password:-");
            String pass_word = sc.next();

            Statement st = con.createStatement();
            String checkDataQuery = "select * from customer_data where username='" + user_name + "' and password='" + pass_word + "'";
            ResultSet rs = st.executeQuery(checkDataQuery);

            while (rs.next()) {
                System.out.println("-------------------------------------Login successfull----------------------------------------------------------");
                System.out.println("----------------------------WELCOME\"" + rs.getString("FullName") + "\" -----------------------------------------------------");
                System.out.println();
                boolean a = Digital_Mall.products.purchase_in_boolean(rs.getString("FullName"), rs.getString(3));
                if (a == false) {
                    return false;
                } else {
                    return true;
                }
            }

            System.out.println("username or password not found!");
            return true;
        } catch (Exception e) {
            System.out.println("Invalid input...");
            return false;
        }
    }

    //user registration
    static void registrationUser() throws Exception {

        System.out.println("Enter First Name:-");
        String fName = sc.next().toUpperCase();
        System.out.println("Enter Middle Name:-");
        String mName = sc.next().toUpperCase();
        System.out.println("Enter last Name:-");
        String lName = sc.next().toUpperCase();
        sc.nextLine();
        String fullName = fName + " " + mName + " " + lName;
        System.out.println("Enter address:-");
        String address = sc.nextLine().toUpperCase();
        String mobileNumber = "";
        boolean k = true;

        while (k) {
            int flag = 0;
            System.out.println("Enter mobile number:-");
            String mobile_number = sc.nextLine();
            if (mobile_number.length() == 10) {
                for (int i = 0; i < 10; i++) {
                    if ((mobile_number.charAt(i) >= '0') && (mobile_number.charAt(i) <= '9')) {
                        flag++;
                    }
                }

                if (flag == 10) {
                    k = false;
                    mobileNumber = mobile_number;
                } else {
                    System.out.println("mobile number should be only digits!");
                }
            } else {
                System.out.println("---mobile number should be 10 digits!---");
            }
        }

        System.out.println("Enter username:-");
        String user_name1 = sc.next().toLowerCase();

        boolean c = true;
        while (c) {
            System.out.println("Enter Password:-");
            String pass_word1 = sc.next();
            System.out.println("Confirm your password");
            String confirm_password = sc.next();
            if (confirm_password.equals(pass_word1)) {

                c = false;
                Statement st1 = con.createStatement();
                int n = 0;
                try {
                    String insertDataQuery = "insert into customer_data (FullName, username, password,mobile_number,address) values ('" + fullName + "','" + user_name1 + "','" + pass_word1 + "','" + mobileNumber + "','" + address + "')";
                    n = st1.executeUpdate(insertDataQuery);
                } catch (Exception e) {
                    System.out.println("username allready exist. Please registor again....");
                }

                if (n > 0) {
                    System.out.println();
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("|       Registration successfull. PLease login now...             |");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println();
                    loginUser();
                } else {
                    System.out.println("Registration failed!");
                }

            } else {
                System.out.println("password and confirm password doesn't match. Please enter again...");
            }

        }
    }

    class products {

        static Statement st;
        static PreparedStatement pst;
        static linked_list_of_cart user_LL = new linked_list_of_cart();

        static linked_list_of_cart uc;

        static boolean purchase_in_boolean(String Name, String username) throws Exception {

            boolean d = true;

            while (d) {
                System.out.println();
                System.out.println("---------------------------------------------------------");
                System.out.println("|    1. for buying products                    :-       |");
                System.out.println("|    2. for search products                    :-       |");
                System.out.println("|    3. for confirm order to create bill       :-       |");
                System.out.println("|    4. for watch your order history           :-       |");
                System.out.println("|    5. for watch your cart                    :-       |");
                System.out.println("|    6. for remove product from your your cart :-       |");
                System.out.println("|    7. for update your username               :-       |");
                System.out.println("|    8. for update password                    :-       |");
                System.out.println("|    9. for update your address                :-       |");
                System.out.println("|   10. for update your mobile number          :-       |");
                System.out.println("|   11. for view you personal details          :-       |");
                System.out.println("|   12. for back to the login page             :-       |");
                System.out.println("|    0. for close application                  :-       |");
                System.out.println("---------------------------------------------------------");
                System.out.println("Enter Your Choice from above!");
                int ch = sc.nextInt();
                switch (ch) {

                    //categorywise start
                    case 1:
                        purchaseProductCategoryWise();
                        break;

                    //purchasing by categorywise end
                    //searching prpduct 
                    case 2:
                        System.out.println("----------------------------------------------------------");
                        purchaseProductBySearching();
                        System.out.println("----------------------------------------------------------");

                        break;

                    //create your products bill
                    case 3:

                        String ans = createbill_for_billing(Name, username);

                        if (ans.equalsIgnoreCase("no")) {
                            d = false;
                            System.out.println("THANK YOU " + Name + " FOR SHOPING FROM OUR DIGITAL MALL. HAVE A NICE DAY!.....");
                            System.out.println();
                        }

                        break;

                    //watch your order history
                    case 4:
                        view_order_history(username);
                        break;

                    case 5:

                        System.out.println("---------------------------------------------------");
                        user_LL.displayCart();
                        System.out.println("---------------------------------------------------");
                        break;

                    case 6:

                        boolean b = user_LL.displayCart();
                        if (b) {
                            while (true) {
                                try {
                                    System.out.println("Enter product id from above your cart list which you want to remove product from cart :- ");
                                    int removeID = sc.nextInt();
                                    user_LL.removeProductFromCart(removeID);
                                    System.out.println("Removed product from cart successfully.");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Please enter valid product id.");
                                }

                            }
                        } else {
                            System.out.println("First add products in cart.....");
                        }
                        break;

                    case 7: {
                        System.out.println("Enter new username :- ");
                        sc.nextLine();
                        String newusername = sc.nextLine().toLowerCase();
                        String sql = "update customer_data set username='" + newusername + "' where FullName='" + Name + "'";
                        pst = con.prepareStatement(sql);
                        int i = pst.executeUpdate();
                        if (i > 0) {
                            System.out.println("New USername updated successfully...");
                        }
                        break;
                    }
                    case 8: {
                        System.out.println("Enter new password :- ");
                        sc.nextLine();
                        String newpassword = sc.nextLine();
                        String sql = "update customer_data set password='" + newpassword + "' where FullName='" + Name + "'";
                        pst = con.prepareStatement(sql);
                        int i = pst.executeUpdate();
                        if (i > 0) {
                            System.out.println("New password updated successfully...");
                        }
                        break;
                    }
                    case 9: {
                        System.out.println("Enter new address :- ");
                        sc.nextLine();
                        String newaddress = sc.nextLine().toUpperCase();
                        String sql = "update customer_data set address='" + newaddress + "' where FullName='" + Name + "'";
                        pst = con.prepareStatement(sql);
                        int i = pst.executeUpdate();
                        if (i > 0) {
                            System.out.println("New address updated successfully...");
                        }
                        break;
                    }
                    case 10: {
                        String mobileNumber = "";

                        boolean k = true;

                        while (k) {
                            int flag = 0;
                            System.out.println("Enter mobile number:-");
                            String mobile_number = sc.next();
                            if (mobile_number.length() == 10) {
                                for (int i = 0; i < 10; i++) {
                                    if ((mobile_number.charAt(i) >= '0') && (mobile_number.charAt(i) <= '9')) {
                                        flag++;
                                    }
                                }

                                if (flag == 10) {
                                    k = false;
                                    mobileNumber = mobile_number;
                                    String sql = "update customer_data set mobile_number='" + mobileNumber + "' where FullName='" + Name + "'";
                                    pst = con.prepareStatement(sql);
                                    int i = pst.executeUpdate();
                                    if (i > 0) {
                                        System.out.println("New Mobile number updated successfully...");
                                    }
                                } else {
                                    System.out.println("mobile number should be only digits!");
                                }
                            } else {
                                System.out.println("---mobile number should be 10 digits!---");
                            }
                        }
                        break;
                    }
                    case 11: {
                        System.out.println("details");
                        String sql = "select * from customer_data where FullName =?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, Name);
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            System.out.println("-------------Customer Personal Details-------------");
                            System.out.println("Full Name       :- " + rs.getString(2));
                            System.out.println("Username        :- " + rs.getString(3));
                            System.out.println("Password        :- " + rs.getString(4));
                            System.out.println("Address         :- " + rs.getString(5));
                            System.out.println("Mobile Number   :- " + rs.getString(6));
                        }
                        break;
                    }
                    case 12: {
                        d = false;
                        System.out.println("Thank you " + Name + " for visit our digital mall!");
                        break;
                    }

                    case 0: {
                        System.out.println("Thank you " + Name + " for visit our digital mall!");
                        return false;
                    }
                    default: {
                        System.out.println("Invalid choice !");
                        break;
                    }

                }
            }
            return true;

        }

//purschase by category
        static void purchaseProductCategoryWise() throws Exception {

            boolean a = true;
            while (a) {
                System.out.println("---------------------------------------------------------");
                System.out.println("| 1. for Grocery :-                                     |");
                System.out.println("| 2. for fashion :-                                     |");
                System.out.println("| 3. for utensils :-                                    |");
                System.out.println("| 4. for back to the main menu :-                       |");
                System.out.println("---------------------------------------------------------");
                int ch1 = sc.nextInt();

                switch (ch1) {

                    case 1: //display all grocery item
                    {
                        System.out.println("------------------------ GROCERY ITEMS ------------------------");
                        st = con.createStatement();
                        String utensilsItemQuery = "select * from grocery";
                        ResultSet rs = st.executeQuery(utensilsItemQuery);

                        while (rs.next()) {
                            System.out.println("Product ID : " + rs.getInt(1) + " | " + rs.getString(2) + " | " + "Quantity : "
                                    + rs.getInt(3) + " | " + "Price per piece : " + rs.getDouble(4) + " | ");

                        }

                        try {
                            System.out.println("Enter pid you want to add cart :");
                            int pID = sc.nextInt();
                            System.out.println("Enter quantity : ");
                            int quantity = sc.nextInt();

                            String sql1 = "select * from grocery where product_id='" + pID + "'";
                            pst = con.prepareStatement(sql1);
                            ResultSet rs1 = pst.executeQuery();
                            rs1.next();

                            if (rs1.getInt(3) >= quantity) {
                                try {

                                    System.out.println("Product ID :- " + rs1.getInt(1) + " " + rs1.getString(2) + " | " + "Quantity : "
                                            + quantity + " | " + " Total price : " + (rs1.getDouble(4) * quantity) + " |  Successfully added in cart.");
                                    int id = rs1.getInt(1);
                                    String productName = rs1.getString(2);
                                    int q = rs1.getInt(3);
                                    double price = rs1.getDouble(4);
                                    UserCart uc = new UserCart(id, productName, quantity, price * quantity);
                                    user_LL.addProductToCart(uc);

                                } catch (Exception e) {
                                    System.out.println("NO PRODUCTS OF PID : " + pID);
                                }
                            } else if (rs1.getInt(3) == 0) {
                                System.out.println("PRODUCT OUT OF STOCK");
                            } else {
                                System.out.println("THE QUANTITY YOU ENTERED OF PRODUCT IS NOT AVAILABLE..");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Please enter valid product id.");

                        }
                    }
                    break;
                    //grocery end

                    case 2: {
                        System.out.println("------------------------ FASHION ITEMS ------------------------");
                        st = con.createStatement();
                        String fashionItemQuery = "select * from fashion";
                        ResultSet rs = st.executeQuery(fashionItemQuery);

                        while (rs.next()) {

                            System.out.println("Product ID : " + rs.getInt(1) + " | " + rs.getString(2) + " | " + "Quantity : "
                                    + rs.getInt(3) + " | " + "Price per piece : " + rs.getDouble(4) + " | ");

                        }
                        System.out.println("Enter pid you want to add cart :");
                        int pID = sc.nextInt();
                        System.out.println("Enter quantity : ");
                        int quantity = sc.nextInt();

                        try {

                            String sql1 = "select * from fashion where product_id='" + pID + "'";
                            pst = con.prepareStatement(sql1);
                            ResultSet rs1 = pst.executeQuery();

                            rs1.next();

                            if (rs1.getInt(3) >= quantity) {

                                try {

                                    System.out.println("Product ID :- " + rs1.getInt(1) + " " + rs1.getString(2) + " | " + "Quantity : "
                                            + quantity + " | " + " Total price : " + (rs1.getDouble(4) * quantity) + " |  Successfully added in cart.");
                                    int id = rs1.getInt(1);
                                    String productName = rs1.getString(2);
                                    int q = rs1.getInt(3);
                                    double price = rs1.getDouble(4);
                                    UserCart uc = new UserCart(id, productName, quantity, price * quantity);
                                    user_LL.addProductToCart(uc);

                                } catch (Exception e) {
                                    System.out.println("NO PRODUCTS OF PID : " + pID);
                                }
                            } else if (rs1.getInt(3) == 0) {
                                System.out.println("PRODUCT OUT OF STOCK");
                            } else {
                                System.out.println("THE QUANTITY YOU ENTERED OF PRODUCT IS NOT AVAILABLE..");
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter valid product id.");

                        }
                    }
                    break;

                    case 3: {
                        System.out.println("------------------------ UTENSILS ITEMS ------------------------");
                        st = con.createStatement();
                        String groceryItemQuery = "select * from utensils";
                        ResultSet rs = st.executeQuery(groceryItemQuery);

                        while (rs.next()) {

                            System.out.println("Product ID : " + rs.getInt(1) + " | " + rs.getString(2) + " | " + "Quantity : "
                                    + rs.getInt(3) + " | " + "Price per piece : " + rs.getDouble(4) + " | ");

                        }
                        System.out.println("Enter pid you want to add cart :");
                        int pID = sc.nextInt();
                        System.out.println("Enter quantity : ");
                        int quantity = sc.nextInt();

                        try {
                            String sql1 = "select * from utensils where product_id='" + pID + "'";
                            pst = con.prepareStatement(sql1);
                            ResultSet rs1 = pst.executeQuery();

                            rs1.next();

                            if (rs1.getInt(3) >= quantity) {
                                try {

                                    System.out.println("Product ID :- " + rs1.getInt(1) + " " + rs1.getString(2) + " | " + "Quantity : "
                                            + quantity + " | " + " Total price : " + (rs1.getDouble(4) * quantity) + " |  Successfully added in cart.");
                                    int id = rs1.getInt(1);
                                    String productName = rs1.getString(2);
                                    int q = rs1.getInt(3);
                                    double price = rs1.getDouble(4);
                                    UserCart uc = new UserCart(id, productName, quantity, price * quantity);
                                    user_LL.addProductToCart(uc);

                                } catch (Exception e) {
                                    System.out.println("NO PRODUCTS OF PID : " + pID);
                                }
                            } else if (rs1.getInt(3) == 0) {
                                System.out.println("PRODUCT OUT OF STOCK");
                            } else {
                                System.out.println("THE QUANTITY YOU ENTERED OF PRODUCT IS NOT AVAILABLE..");
                            }
                        } catch (Exception e) {
                            System.out.println("Please enter valid product id.");

                        }
                        break;
                    }
                    case 4: {

                        a = false;
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice ! ");
                    }

                }
            }

        }

//purchse  product by searching
        static void purchaseProductBySearching() throws Exception {

            sc.nextLine();
            System.out.println("Enter product name you want to search product :- ");
            String pName = sc.nextLine().toUpperCase();
            String sql = "select * from searchProduct where product_name like '%" + pName + "%'";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("Product ID :- " + rs.getInt(1) + " | " + rs.getString(2) + " | " + "Quantity : "
                        + rs.getInt(3) + " | " + "Price : " + rs.getDouble(4) + " | ");
                try {
                    while (rs.next()) {

                        System.out.println("Product ID :- " + rs.getInt(1) + " | " + rs.getString(2) + " | " + "Quantity : "
                                + rs.getInt(3) + " | " + "Price : " + rs.getDouble(4) + " | ");

                    }
                } catch (Exception e) {
                    System.out.println("Not Valid name...");
                }

                System.out.println("Enter pid you want to add cart :");
                int pID = sc.nextInt();
                System.out.println("Enter quantity : ");
                int quantity = sc.nextInt();

                try {
                    String sql1 = "select * from searchProduct where product_id='" + pID + "'";
                    pst = con.prepareStatement(sql1);
                    ResultSet rs1 = pst.executeQuery();

                    rs1.next();

                    if (rs1.getInt(3) >= quantity) {

                        System.out.println("Product ID :- " + rs1.getInt(1) + " " + rs1.getString(2) + " | " + "Quantity : "
                                + quantity + " | " + " Total price : " + (rs1.getDouble(4) * quantity) + " |  Successfully added in cart.");
                        int id = rs1.getInt(1);
                        String productName = rs1.getString(2);
                        int q = rs1.getInt(3);
                        double price = rs1.getDouble(4);
                        UserCart uc = new UserCart(id, productName, quantity, price * quantity);
                        user_LL.addProductToCart(uc);

                    } else if (rs1.getInt(3) == 0) {
                        System.out.println("PRODUCT OUT OF STOCK");
                    } else {
                        System.out.println("THE QUANTITY YOU ENTERED OF PRODUCT IS NOT AVAILABLE..");
                    }
                } catch (Exception e) {
                    System.out.println("Please enter valid product id");

                }
            } else {
                System.out.println("NO PRODUCT FOUND!");
            }

        }

//create bill ,bill banse
        static String createbill_for_billing(String Name, String username) throws Exception {

            Date d = new Date();

            Date currentDate = d;

            double totalAmount = 0;
            if (user_LL.head == null) {

                System.out.println("NO ITEMS IN CART!");
                return "";

            } else {
                FileWriter fw = new FileWriter(username + "ShopingBill.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);

                linked_list_of_cart.Node temp = user_LL.head;

                Date date = new Date();

                bw.write("-------------------ONLINE DIGITAL MALL BILL---------------------------");
                bw.newLine();
                bw.newLine();
                bw.write("----------------------------" + Name + "----------------------------------");
                bw.newLine();
                bw.newLine();
                bw.write("                                                                  " + date);
                bw.newLine();
                bw.newLine();

                while (temp != null) {
                    int pid = temp.userCart.getpID();
                    String pname = temp.userCart.getpName();
                    int quantity = temp.userCart.getpQuantity();
                    double price = temp.userCart.getpPrice();

                    System.out.println("Product Id:- " + pid);
                    System.out.println("Product Name:-" + pname);
                    System.out.println("Product Quantity:-" + quantity);
                    System.out.println("Product Price:-" + price);

                    bw.write("Product Id:- " + pid + "  ");
                    bw.write("Product Name:-" + pname + "  ");
                    bw.write("Product Quantity:-" + quantity + "  ");
                    bw.write("Product Price:-" + price);
                    bw.newLine();

                    totalAmount = totalAmount + price;
                    temp = temp.next;

                }

                System.out.println("-------------------------------------------------------------------");
                System.out.println("|                                                                  |");
                System.out.println("|TOTAL AMOUNT OF YOUR BILL IS :- " + totalAmount + "               |");
                System.out.println("|                                                                  |");
                System.out.println("--------------------------------------------------------------------");

                String paymentMethod = "";
                System.out.println("--------------------------------------------------------");
                System.out.println("|choose payment method:                                |");
                System.out.println("| 1. for Cash On Delivery:                             |");
                System.out.println("| 2. for UPI:                                          |");
                System.out.println("| 3. for Debit Card:                                   |");
                System.out.println("--------------------------------------------------------");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:

                        paymentMethod = "PAYMENT BY CASH ON DELIVERY.";
                        System.out.println("Order successfully done.. \n Payment by Cash On Delivery...");
                        linked_list_of_cart.Node temp1 = user_LL.head;
                        while (temp1 != null) {
                            String pname = temp1.userCart.getpName();
                            int quantity = temp1.userCart.getpQuantity();
                            double price = temp1.userCart.getpPrice();

                            String sql = "insert into orderhistory (username,product_name,total_quantity,total_price,order_date) values('" + username + "','" + pname + "','" + quantity + "','" + price + "','" + currentDate + "')";
                            pst = con.prepareStatement(sql);
                            pst.execute();

                            String sql2 = "{call updateQuantity(?,?)}";
                            pst = con.prepareCall(sql2);
                            pst.setInt(1, (quantity));
                            pst.setString(2, pname);
                            pst.executeUpdate();
                            temp1 = temp1.next;
                        }
                        break;

                    case 2:

                        paymentMethod = "PAYMENT BY UPI.";
                        System.out.println("ENTER UPI ID:");
                        String upi = sc.next();

                        boolean p = true;
                        while (p) {
                            System.out.println("ENTER AMOUNT:");
                            double amount = sc.nextDouble();

                            if (amount == totalAmount) {
                                try {
                                    System.out.println("Payment is procesing.");
                                    Thread.sleep(5000);
                                    System.out.println("Payment Successfully completed.... Thank you!");
                                } catch (Exception e) {
                                    System.out.println("Payment is failed!");
                                }

                                linked_list_of_cart.Node temp2 = user_LL.head;
                                while (temp2 != null) {
                                    String pname = temp2.userCart.getpName();
                                    int quantity = temp2.userCart.getpQuantity();
                                    double price = temp2.userCart.getpPrice();

                                    String sql = "insert into orderhistory (username,product_name,total_quantity,total_price,order_date) values('" + username + "','" + pname + "','" + quantity + "','" + price + "','" + currentDate + "')";
                                    pst = con.prepareStatement(sql);
                                    pst.execute();

                                    String sql2 = "{call updateQuantity(?,?)}";
                                    pst = con.prepareCall(sql2);
                                    pst.setInt(1, (quantity));
                                    pst.setString(2, pname);
                                    pst.executeUpdate();
                                    temp2 = temp2.next;
                                }
                                p = false;
                            } else {
                                System.out.println("THE PAYMENT OF AN INVOICE THAT IS LESS THAN FULL AOUNT DUE...");
                            }
                        }
                        break;

                    case 3:

                        paymentMethod = "PAYMENT BY DEBIT CARD.";
                        boolean flag = true;
                        while (flag) {
                            int flag1 = 0;
                            System.out.println("ENTER DEBITCARD NUMBER:");
                            String accountNumber = sc.nextLine();
                            if (String.valueOf(accountNumber).length() == 16) {
                                for (int i = 0; i < 16; i++) {
                                    if ((String.valueOf(accountNumber).charAt(i) >= '0') && (String.valueOf(accountNumber).charAt(i) <= '9')) {
                                        flag1++;
                                    }
                                }

                                if (flag1 == 16) {
                                    flag = false;

                                    boolean p1 = true;
                                    while (p1) {
                                        System.out.println("ENTER AMOUNT:");
                                        double amount = sc.nextDouble();

                                        if (amount == totalAmount) {
                                            try {
                                                System.out.println("Payment is procesing.");
                                                Thread.sleep(2000);
                                                System.out.println("Payment Successfully completed.... Thank you!");
                                            } catch (Exception e) {
                                                System.out.println("Payment is failed!");
                                            }

                                            linked_list_of_cart.Node temp3 = user_LL.head;
                                            while (temp3 != null) {
                                                String pname = temp3.userCart.getpName();
                                                int quantity = temp3.userCart.getpQuantity();
                                                double price = temp3.userCart.getpPrice();

                                                String sql = "insert into orderhistory (username,product_name,total_quantity,total_price,order_date) values('" + username + "','" + pname + "','" + quantity + "','" + price + "','" + currentDate + "')";
                                                pst = con.prepareStatement(sql);
                                                pst.execute();

                                                String sql2 = "{call updateQuantity(?,?)}";
                                                pst = con.prepareCall(sql2);
                                                pst.setInt(1, (quantity));
                                                pst.setString(2, pname);
                                                pst.executeUpdate();
                                                temp3 = temp3.next;
                                            }
                                            p1 = false;
                                        } else {
                                            System.out.println("THE PAYMENT OF AN INVOICE THAT IS LESS THAN FULL AOUNT DUE...");
                                        }
                                    }
                                } else {
                                    System.out.println("debitcard number should be only digits!");
                                }
                            } else {
                                System.out.println("###---debitcard number should be 16 digits!---###");
                            }
                        }

                        break;

                    default:
                        System.out.println("Select invalid payment method");
                }

                bw.write("--------------------------------------------------------");
                bw.newLine();
                bw.write("|                                                      |");
                bw.newLine();
                bw.write("|TOTAL AMOUNT OF YOUR BILL IS :- " + totalAmount + "                |");
                bw.newLine();
                bw.write("|                                                      |");
                bw.newLine();
                bw.write("| Payment method : " + paymentMethod + "                                    |");
                bw.newLine();
                bw.write("--------------------------------------------------------");
                bw.newLine();

                bw.close();
                fw.close();

                System.out.println("You want to continue your shoping (yes/no) :");
                String ans = sc.next();
                user_LL.head = null;
                if (ans.equalsIgnoreCase("no")) {
                    return "no";
                } else {
                    return "yes";
                }
            }

        }

        static void view_order_history(String username) throws Exception {
            String sql = "select * from orderhistory where username='" + username + "'order by No desc ";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("| Product name : " + rs.getString(3) + "          ");
                System.out.print("| Total quantity : " + rs.getInt(4) + "     ");
                System.out.print("| Total price : " + rs.getDouble(5) + "     ");
                System.out.println("| Order date : " + rs.getString(6) + "             ");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
                while (rs.next()) {

                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.print("| Product name : " + rs.getString(3) + "          ");
                    System.out.print("| Total quantity : " + rs.getInt(4) + "     ");
                    System.out.print("| Total price : " + rs.getDouble(5) + "     ");
                    System.out.println("| Order date : " + rs.getString(6) + "             ");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

                }
            } else {
                System.out.println("NO RECORDS FOUND!");
            }

        }

    }

    class employee_management {

        static Scanner sc = new Scanner(System.in);

        //employee login
        static void loginEmployee() throws Exception {

            System.out.println("Enter employee id:-");
            String employeeID = sc.next().toLowerCase();
            System.out.println("Enter Password:-");
            String pass_word = sc.next();
            Statement st = con.createStatement();
            String checkDataQuery = "select * from employee_data where Employee_ID='" + employeeID + "' and Password='" + pass_word + "'";
            ResultSet rs = st.executeQuery(checkDataQuery);
            while (rs.next()) {
                System.out.println("-------------------------------------Login successfull----------------------------------------------------------");
                System.out.println("----------------------------WELCOME \"" + rs.getString(4) + "\"-----------------------------------------------------");
                System.out.println();
                productManagemenet();
                break;
            }
        }

        //product add,remove,update
        static void productManagemenet() throws Exception {
            PreparedStatement pst;
            CallableStatement cst;

            System.out.println(" WELCOME TO MALL STORE SYSTEM");

            boolean n = true;
            while (n) {
                System.out.println("------------------------------------------------------");
                System.out.println("| 1. for add new products :-                         |");
                System.out.println("| 2. for remove products :-                          |");
                System.out.println("| 3. for change quantity by product_id :-            |");
                System.out.println("| 4. for exit :-                                     |");
                System.out.println("------------------------------------------------------");

                int ch = sc.nextInt();
                switch (ch) {
                    //add product
                    case 1:
                        sc.nextLine();
                        System.out.println("--------------------------------------------------------------------");
                        System.out.println("|Enter category type in which you want to update product :-         |");
                        System.out.println("|1. For Grocery Related Products :-                                 |");
                        System.out.println("|2. For Fashion Related Products :-                                 |");
                        System.out.println("|3. For Utenils Related Products :-                                 |");
                        System.out.println("--------------------------------------------------------------------");

                        int table_name_for_add = sc.nextInt();

                        System.out.println("Enter new product name with details :-");
                        sc.nextLine();
                        String productName = sc.nextLine().toUpperCase();

                        System.out.println("Enter quantity :- ");
                        int p_quantity = sc.nextInt();

                        System.out.println("Enter price :- ");
                        double price = sc.nextDouble();

                        switch (table_name_for_add) {
                            case 1:

                                try {
                                    String sql = "{call insertGrocery(?,?,?)}";
                                    cst = con.prepareCall(sql);
                                    cst.setString(1, productName);
                                    cst.setInt(2, p_quantity);
                                    cst.setDouble(3, price);
                                    cst.executeUpdate();
                                    System.out.println(" Product Name :- " + productName + " \n" + " Quantity :- " + p_quantity + " \n" + " product added Successfully.");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;

                            case 2:

                                try {
                                    String sql = "{call insertFashion(?,?,?)}";
                                    cst = con.prepareCall(sql);
                                    cst.setString(1, productName);
                                    cst.setInt(2, p_quantity);
                                    cst.setDouble(3, price);
                                    cst.executeUpdate();
                                    System.out.println(" Product Name :- " + productName + " \n" + " Quantity : " + p_quantity + " \n" + " product added Successfully.");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;

                            case 3:
                                try {
                                    String sql = "{call insertUtensils(?,?,?)}";
                                    cst = con.prepareCall(sql);
                                    cst.setString(1, productName);
                                    cst.setInt(2, p_quantity);
                                    cst.setDouble(3, price);
                                    cst.executeUpdate();
                                    System.out.println(" Product Name :- " + productName + " \n" + " Quantity :- " + p_quantity + " \n" + " product added Successfully.");
                                } catch (Exception e) {
                                    System.out.println(e);
                                }

                                break;

                            default:
                                System.out.println("NO CATEGORY FOUND!");
                                break;
                        }

                        break;

                    //remove product
                    case 2:
                        System.out.println("Enter category type in which you want to update product :- ");
                        System.out.println("1. For Grocery Related Products :- ");
                        System.out.println("2. For Fashion Related Products :- ");
                        System.out.println("3. For Utenils Related Products :- ");
                        int table_name_for_remove = sc.nextInt();
                        System.out.println("Enter product id in which product you want to remove");
                        int pid = sc.nextInt();

                        switch (table_name_for_remove) {
                            case 1:
                                String sql = "{call removeGrocery(?)}";
                                pst = con.prepareCall(sql);
                                pst.setInt(1, pid);
                                int i = pst.executeUpdate();
                                if (i > 0) {
                                    System.out.println("Removed Successfull");
                                } else {
                                    System.out.println("Removed failed!");
                                }

                                break;

                            case 2:

                                String sql1 = "{call removeFashion(?)}";
                                pst = con.prepareCall(sql1);
                                pst.setInt(1, pid);
                                int i1 = pst.executeUpdate();
                                if (i1 > 0) {
                                    System.out.println("Removed Successfull");
                                } else {
                                    System.out.println("Removed failed!");
                                }

                                break;

                            case 3:

                                String sql2 = "{call removeUtensils(?)}";
                                pst = con.prepareCall(sql2);
                                pst.setInt(1, pid);
                                int i2 = pst.executeUpdate();
                                if (i2 > 0) {
                                    System.out.println("Removed Successfull");
                                } else {
                                    System.out.println("Removed failed!");
                                }
                                break;

                            default:
                                System.out.println("NOT FOUND CATEGORY!");

                        }
                        break;

                    //update in product quantity
                    case 3:
                        System.out.println("Enter category type in which you want to update product :- ");
                        System.out.println("1. For Grocery Related Products :- ");
                        System.out.println("2. For Fashion Related Products :- ");
                        System.out.println("3. For Utenils Related Products :- ");
                        int table_name_for_update = sc.nextInt();
                        System.out.println("Enter product_id you want to update its quantity :-");
                        int pid1 = sc.nextInt();
                        System.out.println("Enter new quantity::-");
                        int q = sc.nextInt();

                        switch (table_name_for_update) {
                            case 1:
                                String sql = "{call updateQuantityInGrocery(?,?)}";
                                pst = con.prepareCall(sql);
                                pst.setInt(1, pid1);
                                pst.setInt(2, q);
                                int i = pst.executeUpdate();
                                if (i > 0) {
                                    System.out.println("Updated Successfull");
                                } else {
                                    System.out.println("Updated failed!");
                                }

                                break;

                            case 2:

                                String sql1 = "{call updateQuantityInFashion(?,?)}";
                                pst = con.prepareCall(sql1);
                                pst.setInt(1, pid1);
                                pst.setInt(2, q);
                                int i1 = pst.executeUpdate();
                                if (i1 > 0) {
                                    System.out.println("Updated Successfull");
                                } else {
                                    System.out.println("Updated failed!");
                                }

                                break;

                            case 3:

                                String sql2 = "{call updateQuantityInUtensils(?,?)}";
                                pst = con.prepareCall(sql2);
                                pst.setInt(1, pid1);
                                pst.setInt(2, q);
                                int i2 = pst.executeUpdate();
                                if (i2 > 0) {
                                    System.out.println("Updated Successfull");
                                } else {
                                    System.out.println("Updated failed!");
                                }
                                break;

                            default:
                                System.out.println("NOT FOUND CATEGORY!");
                        }

                        break;
                    case 4: {
                        n = false;
                        System.out.println("THANK YOU EMPLOYEE MEMBER FOR DOING WELLED JOB IN OUR MALL!");
                        break;
                    }

                    default: {
                        System.out.println("Invalid choice!");
                        break;
                    }
                }
            }
        }
    }
}
