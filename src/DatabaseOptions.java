import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.*;

import static jdk.nashorn.internal.objects.NativeMath.round;


public class DatabaseOptions{
    private static final String USERNAME = "root";
    private static final String PASSWORD = "7298";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/pacificbank";
    private Connection conn = null;
    private Integer tempCustomerNumber = 0;
    private double checkingsBalance;
    private double savingsBalance;
    private Integer accountID;
    private String[][] transactionTableData;
    private int rows;

    //Account Details
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private Date dateOfBirth;
    private String emailAddress = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String securityQuestion = "";
    private String securityAnswer = "";
    private Integer pincode = 0;
    private String ssn = "";
    private String password = "";




    public boolean checkUniqueEmail(String email){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT * FROM customer_information";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String dataBaseEmail = rs.getString("Email_Address");
                if (dataBaseEmail.equals(email)){
                    return false;
                }
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return true;
    }

    public boolean checkUniqueSSN(String ssn){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT * FROM customer_information";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String dataBaseSSN = rs.getString("SSN");
                if (dataBaseSSN.equals(ssn)){
                    return false;
                }
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return true;
    }

    public void createAccount(String firstName, String middleName, String lastName, String dateOfBirthString, String email, String address, String city, String state, String securityQuestion, String securityAnswer, Integer pincode, String ssn, String password){
        Double currentBalance = 0.00;
        Integer customerNumber = getLastCustomerNumber() + 1;
        Integer checkingsID = getLastAccountID() + 50;
        Integer savingsID = checkingsID + 50;
        String checkingsType = "c";
        String savingsType = "s";
        //Integer accountID = 542;
        try{
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String insertCustomerInformation = "insert into customer_information (Customer_Number, First_Name,Middle_Name,Last_Name,Date_of_Birth,Email_Address,Address,City,State,Security_Question,Security_Answer,Pincode,SSN,Password) values('"+customerNumber+"','"+firstName+"','"+middleName+"','"+lastName+"','"+dateOfBirthString+"','"+email+"','"+address+"','"+city+"','"+state+"','"+securityQuestion+"','"+securityAnswer+"','"+pincode+"','"+ssn+"','"+password+"')";
            stmt.executeUpdate(insertCustomerInformation);
            String insertAccountInformation1 = "insert into account_information (Customer_Number, Account_ID, current_balance, Account_Type) values('"+customerNumber+"','"+checkingsID+"','"+currentBalance+"','"+checkingsType+"')";
            stmt.executeUpdate(insertAccountInformation1);
            String insertAccountInformation2 = "insert into account_information (Customer_Number, Account_ID, current_balance, Account_Type) values('"+customerNumber+"','"+savingsID+"','"+currentBalance+"','"+savingsType+"')";
            stmt.executeUpdate(insertAccountInformation2);
            stmt.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public Integer getLastCustomerNumber(){
        Integer lastCustomerNumber = 0;
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Customer_Number FROM customer_information WHERE Customer_Number=(SELECT MAX(Customer_Number) FROM customer_information);";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lastCustomerNumber = ((Number) rs.getObject(1)).intValue();
            }
            //lastAccountID = ((Number) rs.getObject(1)).intValue();
            stmt.close();
            return lastCustomerNumber;
        }catch(Exception e){
            System.err.println(e);
        }
        return lastCustomerNumber;
    }

    public Integer getLastAccountID(){
        Integer lastAccountID = 0;
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Account_ID FROM account_information WHERE Account_ID=(SELECT MAX(Account_ID) FROM account_information);";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lastAccountID = ((Number) rs.getObject(1)).intValue();
            }
            stmt.close();
            return lastAccountID;
        }catch(Exception e){
            System.err.println(e);
        }
        return lastAccountID;
    }

    public boolean checkLogin(String email_, String password_, Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT * FROM customer_information";
            //String query2 = "SELECT * FROM accountcredentials where Username = '" +user+"' && where Password = '" + pass +"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                tempCustomerNumber = rs.getInt("Customer_Number");
                String email = rs.getString("Email_Address");
                String password = rs.getString("Password");
                if (email.equals(email_.toLowerCase()) && password.equals(password_)){//password is case sensetive
                    return true;
                }
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }
    public Integer getCustomerNumber(){//For returning the value of customer number in here to PageOptions
        return tempCustomerNumber;
    }



    public Double getCheckingsBalance(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT current_balance FROM account_information WHERE Account_Type = '"+"c"+"' AND Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                checkingsBalance = rs.getDouble("current_balance");//returning a double truncated to 1 decimal place if its .00
                return checkingsBalance;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return checkingsBalance;
    }

    public String getCheckingsBalanceString(Integer customerNumber){
        String balString = "";
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT current_balance FROM account_information WHERE Account_Type = '"+"c"+"' AND Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                checkingsBalance = rs.getDouble("current_balance");//returning a double truncated to 1 decimal place if its .00
                balString = Double.toString(checkingsBalance);
                if(balString.matches("[0-9]+\\.[0-9]")){//If the balance matches any # followed by a decimal point followed by any # followed by a white space
                    balString = balString + "0";//adding extra 0 at the end for looks
                }
                return balString;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return balString;
    }

    public double getSavingsBalance(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT current_balance FROM account_information WHERE Account_Type = '"+"s"+"' AND Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                savingsBalance = rs.getDouble("current_balance");
                return savingsBalance;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return savingsBalance;
    }

    public String getSavingsBalanceString(Integer customerNumber){
        String balString = "";
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT current_balance FROM account_information WHERE Account_Type = '"+"s"+"' AND Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                savingsBalance = rs.getDouble("current_balance");
                balString = Double.toString(savingsBalance);
                if(balString.matches("[0-9]+\\.[0-9]")){//If the balance matches any # followed by a decimal point followed by any # followed by a white space
                    balString = balString + "0";//adding extra 0 at the end for looks
                }
                return balString;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return balString;
    }

    public Integer getAccountID(Integer customerNumber, String type){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Account_ID FROM account_information WHERE Account_Type = '"+type+"' AND Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                accountID = rs.getInt("Account_ID");
                return accountID;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return accountID;
    }

    public java.sql.Timestamp getCurrentDateTime(){//SQL Date type wont take a specific time(seconds, minutes, hours)
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date currentDate = new Date();
        df.format(currentDate);
        java.sql.Timestamp sqlCurrentDate = new java.sql.Timestamp(currentDate.getTime());
        return sqlCurrentDate;
    }

    public void deposit(double amount, double origBalance, String type, Integer customerNumber){
        double newBalance = amount + origBalance;
        String transactionType = "Deposit";
        String typeString = "";
        if(type.equals("c")){typeString = "checkings account.";}
        if(type.equals("s")){typeString = "savings account.";}
        String depositDescription = "Deposit to your " + typeString;

        Integer accountID = getAccountID(customerNumber, type);
//      getCurrentDateTime();

        try{
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String updateCurrentBalance = "UPDATE account_information SET current_balance = "+newBalance+" WHERE Account_Type = '"+type+"' AND Customer_Number = '"+customerNumber+"'";
            stmt.executeUpdate(updateCurrentBalance);
            String addTransaction = "insert into account_transactions (Account_ID, Date, Type, Description, Amount) values('"+accountID+"','"+getCurrentDateTime()+"','"+transactionType+"','"+depositDescription+"','"+amount+"')";
            stmt.executeUpdate(addTransaction);
            stmt.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public void withdraw(double amount, double origBalance, String type, Integer customerNumber){
        double newBalance = origBalance - amount;
//        double credits = amount;
//        double debits = 0.00;
        String transactionType = "Withdraw";
        String typeString = "";
        if(type.equals("c")){typeString = "checkings account.";}
        if(type.equals("s")){typeString = "savings account.";}
        String withdrawDescription = "Withdraw from your " + typeString;

        Integer accountID = getAccountID(customerNumber, type);
//      getCurrentDateTime();

        try{
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String updateCurrentBalance = "UPDATE account_information SET current_balance = "+newBalance+" WHERE Account_Type = '"+type+"' AND Customer_Number = '"+customerNumber+"'";
            stmt.executeUpdate(updateCurrentBalance);
            String addTransaction = "insert into account_transactions (Account_ID, Date, Type, Description, Amount) values('"+accountID+"','"+getCurrentDateTime()+"','"+transactionType+"','"+withdrawDescription+"','"+amount+"')";
            stmt.executeUpdate(addTransaction);
            stmt.close();
        }catch(SQLException e){
            System.err.println(e);
        }
    }


    public void findTransactions(Integer customerNumber) {
        Integer checkingsID = getAccountID(customerNumber, "c");
        Integer savingsID = getAccountID(customerNumber, "s");
        int count = 0;
        rows = 0;
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT * FROM account_transactions WHERE Account_ID IN ('"+checkingsID+"', '"+savingsID+"') ";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){//calculates how many rows there are that contain the ID's
                rows++;
            }
            transactionTableData = new String[rows][6];
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        try{
            String query = "SELECT * FROM account_transactions WHERE Account_ID IN ('"+checkingsID+"', '"+savingsID+"') ORDER BY Date DESC";//make date a timestamp
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String accountID = rs.getString("Account_ID");
                String date = rs.getString("Date");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String amount = rs.getString("Amount");
                transactionTableData[count][0] = accountID;
                transactionTableData[count][1] = date;
                transactionTableData[count][2] = type;
                transactionTableData[count][3] = description;
                transactionTableData[count][4] = amount;
                count++;
                //System.out.println(accountID+" "+date+" "+type+" "+description+" "+credits+" "+debits);
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    public boolean checkWithdrawAmount(double amount, Integer customerNumber, String type){
        Integer accountID = getAccountID(customerNumber, type);
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT current_balance FROM account_information WHERE Account_ID = '"+accountID+"'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){//calculates how many rows there are that contain the ID's
                if(rs.getDouble("current_balance") >= amount){
                    return true;
                }else{
                    return false;
                }
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return false;
    }

    public String[][] getTransactionTableData(){
        return transactionTableData;
    }
    public int getTransactionTableRows(){return rows;}


    public void transfer(Integer customerNumber, double amount, String from, String to){
        String type = "";
        String type2 = "";
        double checkingsBal  = getCheckingsBalance(customerNumber);
        double savingsBal = getSavingsBalance(customerNumber);
        if(from.equals("Checkings")){
            type = "c";
            type2 = "s";
        }else if(from.equals("Savings")){
            type = "s";
            type2 = "c";
        }
        withdraw(amount, checkingsBal, type,customerNumber);
        deposit(amount, savingsBal, type2, customerNumber);


        //add this transfer to the transactions page as well

    }


    //Account Details Page
    public String getFirstName(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT First_Name FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                firstName = rs.getString("First_Name");
                return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);//making the first letter uppercase
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return firstName;
    }

    public String getMiddleName(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Middle_Name FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                middleName = rs.getString("Middle_Name");
                return middleName.substring(0, 1).toUpperCase() + middleName.substring(1);//making the first letter uppercase
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return middleName;
    }

    public String getLastName(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Last_Name FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lastName = rs.getString("Last_Name");
                return lastName.substring(0, 1).toUpperCase() + lastName.substring(1);//making the first letter uppercase
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return lastName;
    }

    public Date getDateOfBirth(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Date_of_Birth FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dateOfBirth = rs.getDate("Date_of_Birth");
                return dateOfBirth;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return dateOfBirth;
    }

    public String getEmailAddress(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Email_Address FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                emailAddress = rs.getString("Email_Address");
                return emailAddress;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return emailAddress;
    }

    public String getAddress(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Address FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                address = rs.getString("Address");
                return address;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return address;
    }

    public String getCity(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT City FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                city = rs.getString("City");
                return city;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return city;
    }

    public String getState(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT State FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                state = rs.getString("State");
                return state;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return state;
    }

    public String getSecurityQuestion(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Security_Question FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                securityQuestion = rs.getString("Security_Question");
                return securityQuestion;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return securityQuestion;
    }

    public String getSecurityAnswer(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Security_Answer FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                securityAnswer = rs.getString("Security_Answer");
                return securityAnswer;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return securityAnswer;
    }

    public Integer getPincode(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Pincode FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                pincode = rs.getInt("Pincode");
                return pincode;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return pincode;
    }

    public String getSSN(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT SSN FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ssn = rs.getString("SSN");
                return ssn;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return ssn;
    }

    public String getPassword(Integer customerNumber){
        try {
            conn = DriverManager.getConnection(CONN_STRING+"?autoReconnect=true&useSSL=false", USERNAME, PASSWORD);
            String query = "SELECT Password FROM customer_information WHERE Customer_Number = '"+customerNumber+"'";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                password = rs.getString("Password");
                return password;
            }
            stmt.close();
        }catch(Exception e){
            System.err.println(e);
        }
        return password;
    }
}