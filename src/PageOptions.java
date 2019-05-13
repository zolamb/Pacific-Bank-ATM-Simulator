import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageOptions{///////DONT FORGET ABOUT SIGN IN STATUS (TRUE OR FALSE) //////ALSO, no spaces in my textfields because it could mess up database
    private JFrame mainFrame, basicPopupFrame;
    private JLabel greetingLabel, exitLabel, registrationLabel;
    private JLabel finishRegistrationPopupLabel;
    private JLabel loginLabel, emailLoginLabel, passwordLoginLabel, incorrectEmailOrPasswordLabel, successfulLoginLabel;
    private JLabel firstNameLabel, middleNameLabel, lastNameLabel, dateOfBirthLabel, emailRegistrationLabel, emailConfirmationRegistrationLabel, incorrectRegistrationPageLabel, incorrectRegistrationPageAsterisk;
    private JLabel addressLabel, cityLabel, zipCodeLabel, stateLabel, securityQuestionLabel, securityAnswerLabel;
    private JLabel pincodeLabel, sSNLabel, ssnDash1, ssnDash2, passwordRegistrationLabel, passwordConfirmationRegistrationLabel, securityQuestionLabel2;
    private JLabel incorrectRegistrationPopupLabel, correctRegistrationPopupLabel;
    private JLabel logoutLabel, mainBankTitle, depositPageLabel, withdrawPageLabel, accountDetailsPageLabel, balancePageLabel, transactionsPageLabel, transferPageLabel;
    private JLabel currentBalanceDepositLabel, depositAmountLabel, depositConfirmationLabel, withdrawConfirmationLabel;
    private JLabel checkingsBalanceLabel, savingsBalanceLabel, checkingsBalanceActual, savingsBalanceActual;
    private JLabel transactionConfirmationLabel;
    private JLabel transferAmountLabel, transferFromLabel, transferToLabel;
    private JButton openingRegisterButton, openingLoginButton;
    private JButton exitButton, noButtonOnExitPopup, yesButtonOnExitPopup, previousButton, nextButton, loginButton;
    private JButton finishRegistrationButton;
    private JButton noButtonOnExitRegistrationPopup, yesButtonOnExitRegistrationPopup;
    private JButton quitRegistrationButton, createAccountButton;
    private JButton logoutButton, noOnLogoutPopupButton, yesOnLogoutPopupButton;
    private JButton depositButton, withdrawButton, accountDetailsButton, balanceButton, transactionsButton, transferButton;
    private JButton depositCheckingsButton, depositSavingsButton, finalDepositButton, yesButtonOnDepositPopup, finalWithdrawButton, yesButtonOnWithdrawPopup;
    private JButton finalTransferButton, yesButtonOnTransferPopup;
    private JTextField emailLoginTF, passwordLoginTF;
    private JTextField firstNameTF, middleNameTF, lastNameTF, emailRegistrationTF, emailConfirmationRegistrationTF;
    private JTextField addressTF, cityTF, securityAnswerTF;
    private JTextField passwordRegistrationTF, passwordConfirmationRegistrationTF;
    private JTextField registerCodeTF;
    private DoubleJTextField zipCodeTF;
    private DoubleJTextField pincodeTF, sSNTF1, sSNTF2, sSNTF3;
    private DoubleJTextField depositAmountTF;
    private DoubleJTextField transferAmountTF;
    private JCheckBox emailAgreementCheckBox;
    private JComboBox yearCB, monthCB, dayCB;
    private JComboBox stateCB, securityQuestionCB;
    private JComboBox transferFromCB, transferToCB;
    private JTable transactionsTable;
    private static final String greeting = "Welcome to Pacific Bank ATM Simulator! Feel free to register or sign into your account.";
    private static final String exitConfirmation = "Are you sure you want to exit the program?";
    private static final String exitRegistrationConfirmation = "Are you sure you want to quit the registration process?";
    private static final String registrationPageTitle = "-Registration Page ";
    private static final String incorrectEmailOrPasswordString = "You have entered an incorrect email/password combination";
    private static final String successfulLoginString = "Successful Login";
    private static final String incorrectFirstName = "Must enter your first name";
    private static final String incorrectMiddleName = "Must enter your middle name";
    private static final String incorrectLastName = "Must enter your last name";
    private static final String incorrectDateOfBirth = "Must be at least 18 to create an account";
    private static final String incorrectEmail = "Invalid email address";
    private static final String incorrectEmailConfirmation = "Email addresses do not match";
    private static final String blankAddress = "Must enter your address";
    private static final String blankCity = "Must enter your city";
    private static final String incorrectCity = "City does not correspond to the address provided";
    private static final String blankZipCode = "Must enter a ZIP Code";
    private static final String shortZipCode = "ZIP Code must be 5 digits in length";
    private static final String incorrectZipCode = "ZIP Code does not correspond to the address provided";
    private static final String incorrectSecurityAnswer = "Must have an answer for your security question";
    private static final String invalidAddressString = "Address does not exist";
    private static final String incorrectPincode = "Must enter a 4 digit PIN";
    private static final String incorrectSSN = "Must enter a 9 digit SSN";
    private static final String incorrectPassword = "Must enter a password at least 6 characters in length";
    private static final String incorrectPasswordConfirmation = "Passwords do not match";
    private  final String finishRegistrationLabel = "To create your account, enter the code that was sent to: ";
    private static final String registrationCodeEmailMessage1 = "Here is your activation code: ";
    //private static final String registrationCodeEmailMessage2 = "   Enter the code above to finish creating your account";
    private static final String logoutConfirmationString = "Are you sure you want to logout?";
    private String depositAccountSelection = "";

    //For saving registration page 1
    private String firstName, middleName, lastName, birthMonthString, birthMonth, birthDay, birthYear, dateOfBirthString, email, emailConfirmation;
    private String address, city, zipCode, state, securityQuestion, securityAnswer;
    private String pincode, ssnPart1, ssnPart2, ssnPart3, ssn, password, passwordConfirmation;
    private Integer pincodeInt;
    private String registerCode;
    private Integer birthDayInt, birthYearInt;
    private String webAddress;
    private SimpleDateFormat format;
    private Date dateOfBirth, currentDate;
    private Calendar calendarBirth, calendarCurrent;
    private int daysBetween;
    //private int registrationStatus = 0;
    public String currentPage = "Opening Page";
            //This is to keep up with 'previous' and 'next' buttons
    public String currentPageButtons = "Opening Page Buttons";
            //"Basic Buttons" is for pages with just the bottom row of buttons
            //"Specific Page..." is for a certain page with more than just the bottom row of buttons
    private Integer[] years = new Integer[100];
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private Integer[] days = new Integer[31];
    private String[] states = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
    private String[] securityQuestions = {"What was the color of your first car?","What was the name of your first pet?","What city were you born in?","What is your mothers maiden name?","What is your favorite type of food?"};
    private String[] partsOfAddress;
    private String[] transactionTableColumns = {"Account ID","Date","Type","Description","Amount"};
    private String[][] transactionTableData;
    private String[] transferSelections = {"Checkings", "Savings"};



    public final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    //public boolean invalidAddress = false;
    private boolean emailAgreementCheckBoxStatus = false;
    private MailOptions mailOptions = new MailOptions();
    private DatabaseOptions databaseOptions = new DatabaseOptions();
    private Popups popups = new Popups(this);
    private Double depositAmount = 0.00;
    private String depositAmountStringActual;
    private String depositType;
    String transferAmountString = "";
    private double checkingsBalance;
    private double savingsBalance;
    double transferAmount = 0;
    String transferAmountStringActual = "";
    public int exitTransferLoop = 0;
    public Integer customerNumber = 0;//Global variable to determine who is logged in
                                       //Zero means no one is logged in

    //BASIC METHODS
    public void setupBasicFrame(){
        mainFrame = new JFrame();
        mainFrame.setUndecorated(true);
        mainFrame.setLocation(1200,300);
        mainFrame.getContentPane().setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setSize(1500,1500);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

        try {
            mainFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/ZLamb/Desktop/Desktop/Coding/Java/Images/ATM_Bank_Simulator_Background_Image1.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupBasicPopup(String title){
        basicPopupFrame = new JFrame();
        basicPopupFrame.setTitle(title);
        basicPopupFrame.setLocation(1500,600);
        basicPopupFrame.getContentPane().setLayout(null);
        basicPopupFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        basicPopupFrame.setSize(900,500);
        basicPopupFrame.setVisible(true);
        basicPopupFrame.setResizable(false);
        try {
            basicPopupFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/ZLamb/Desktop/Desktop/Coding/Java/Images/Popup_Blue_Background2.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeContents(){
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

    public void deleteRegistrationInformation(){
        firstName = "";
        middleName = "";
        lastName = "";
        birthMonthString = "January";
        birthYearInt = currentYear;
        birthDayInt = 1;
        email = "";
        emailConfirmation = "";
        address = "";
        city = "";
        zipCode = "";
        state = "Alabama";
        securityQuestion = "What was the color of your first car?";
        securityAnswer = "";
        pincode = "";
        ssnPart1 = "";
        ssnPart2 = "";
        ssnPart3 = "";
        ssn = "";
        password = "";
        passwordConfirmation = "";
        emailAgreementCheckBoxStatus = false;
    }


    //COMPONENTS OPENING PAGE..........

    public  static void addGreeting(PageOptions pageOptions){
        pageOptions.greetingLabel = new JLabel();
        Font labelFont = pageOptions.greetingLabel.getFont().deriveFont(Font.PLAIN, 60f);
        pageOptions.greetingLabel.setFont(labelFont);
        pageOptions.greetingLabel.setLocation(100,200);
        pageOptions.greetingLabel.setSize(1300,200);
        pageOptions.greetingLabel.setText("<html><div style='text-align: center;'>"+greeting+"</div></html>");
        pageOptions.mainFrame.getContentPane().add(pageOptions.greetingLabel);
    }

    public void addOpeningRegisterButton(){
        openingRegisterButton = new JButton();
        OpeningRegisterButtonClass orb = new OpeningRegisterButtonClass();//OpeningRegisterButtonClass orb = new OpeningRegisterButtonClass();
        openingRegisterButton.addActionListener(orb);
        Font buttonFont = openingRegisterButton.getFont().deriveFont(Font.PLAIN, 50f);
        openingRegisterButton.setFont(buttonFont);
        openingRegisterButton.setLocation(375,450);
        openingRegisterButton.setSize(750,100);
        openingRegisterButton.setText("Register");
        mainFrame.getContentPane().add(openingRegisterButton);
    }

    public static void addOpeningLoginButton(PageOptions pageOptions){
        pageOptions.openingLoginButton = new JButton();
        OpeningLoginButtonClass olb = pageOptions.new OpeningLoginButtonClass();//OpeningLoginButtonClass olb = pageOptions.new OpeningLoginButtonClass();
        pageOptions.openingLoginButton.addActionListener(olb);
        Font buttonFont = pageOptions.openingLoginButton.getFont().deriveFont(Font.PLAIN, 50f);
        pageOptions.openingLoginButton.setFont(buttonFont);
        pageOptions.openingLoginButton.setLocation(375,600);
        pageOptions.openingLoginButton.setSize(750,100);
        pageOptions.openingLoginButton.setText("Login");
        pageOptions.mainFrame.getContentPane().add(pageOptions.openingLoginButton);
    }


    //BASIC BUTTONS

    public void addExitButton(){
        exitButton = new JButton();
        ExitButtonClass eb = new ExitButtonClass();
        exitButton.addActionListener(eb);
        Font buttonFont = exitButton.getFont().deriveFont(Font.PLAIN, 50f);
        exitButton.setFont(buttonFont);
        exitButton.setLocation(600,1375);
        exitButton.setSize(300,80);
        exitButton.setText("EXIT");
        mainFrame.getContentPane().add(exitButton);
    }

    public void addPreviousButton(){
        previousButton = new JButton();
        PreviousButtonClass pb = new PreviousButtonClass();
        previousButton.addActionListener(pb);
        Font buttonFont = previousButton.getFont().deriveFont(Font.PLAIN, 50f);
        previousButton.setFont(buttonFont);
        previousButton.setLocation(100,1375);
        previousButton.setSize(300,80);
        previousButton.setText("Previous");
        mainFrame.getContentPane().add(previousButton);
    }

    public void addNextButton(){
        nextButton = new JButton();
        NextButtonClass pb = new NextButtonClass();
        nextButton.addActionListener(pb);
        Font buttonFont = nextButton.getFont().deriveFont(Font.PLAIN, 50f);
        nextButton.setFont(buttonFont);
        nextButton.setLocation(1100,1375);
        nextButton.setSize(300,80);
        nextButton.setText("Next");
        mainFrame.getContentPane().add(nextButton);
    }

    //Login Page Components
    public void addLoginLabel(){
        loginLabel = new JLabel("-Login-");
        Font labelFont = loginLabel.getFont().deriveFont(Font.PLAIN, 80f);
        loginLabel.setFont(labelFont);
        loginLabel.setLocation(600,200);
        loginLabel.setSize(400,150);
        mainFrame.getContentPane().add(loginLabel);
    }

    public void addEmailLoginLabel(){
        emailLoginLabel = new JLabel("Email");
        Font labelFont = emailLoginLabel.getFont().deriveFont(Font.PLAIN, 55f);
        emailLoginLabel.setFont(labelFont);
        emailLoginLabel.setLocation(150,500);
        emailLoginLabel.setSize(200,100);
        mainFrame.getContentPane().add(emailLoginLabel);
    }

    public void addPasswordLoginLabel(){
        passwordLoginLabel = new JLabel("Password(Case Sens.)");
        Font labelFont = passwordLoginLabel.getFont().deriveFont(Font.PLAIN, 55f);
        passwordLoginLabel.setFont(labelFont);
        passwordLoginLabel.setLocation(150,600);
        passwordLoginLabel.setSize(600,100);
        mainFrame.getContentPane().add(passwordLoginLabel);
    }

    public void addLoginButton(){
        loginButton = new JButton();
        LoginButtonClass lb = new LoginButtonClass();
        loginButton.addActionListener(lb);
        Font buttonFont = loginButton.getFont().deriveFont(Font.PLAIN, 50f);
        loginButton.setFont(buttonFont);
        loginButton.setLocation(1100,1375);
        loginButton.setSize(300,80);
        loginButton.setText("Login");
        mainFrame.getContentPane().add(loginButton);
    }

    public void addEmailLoginTF(){
        emailLoginTF = new JTextField();
        Font textFieldFont = emailLoginTF.getFont().deriveFont(Font.PLAIN, 45f);
        emailLoginTF.setFont(textFieldFont);
        emailLoginTF.setLocation(800,520);
        emailLoginTF.setSize(500,60);
        mainFrame.getContentPane().add(emailLoginTF);
    }

    public void addPasswordLoginTF(){
        passwordLoginTF = new JTextField();
        Font textFieldFont = passwordLoginTF.getFont().deriveFont(Font.PLAIN, 45f);
        passwordLoginTF.setFont(textFieldFont);
        passwordLoginTF.setLocation(800,620);
        passwordLoginTF.setSize(500,60);
        mainFrame.getContentPane().add(passwordLoginTF);
    }

    public void addIncorrectEmailOrPasswordLabel(){
        incorrectEmailOrPasswordLabel = new JLabel("");
        Font labelFont = incorrectEmailOrPasswordLabel.getFont().deriveFont(Font.PLAIN, 35f);
        incorrectEmailOrPasswordLabel.setFont(labelFont);
        incorrectEmailOrPasswordLabel.setLocation(820,700);
        incorrectEmailOrPasswordLabel.setSize(600,100);
        mainFrame.getContentPane().add(incorrectEmailOrPasswordLabel);
    }

    public void addSuccessfulLoginLabel(){
        successfulLoginLabel = new JLabel("");
        Font labelFont = successfulLoginLabel.getFont().deriveFont(Font.PLAIN, 35f);
        successfulLoginLabel.setFont(labelFont);
        successfulLoginLabel.setLocation(890,685);
        successfulLoginLabel.setSize(600,100);
        mainFrame.getContentPane().add(successfulLoginLabel);
    }


    //Incorrect Components For Registration
    public void addIncorrectRegistrationPageLabel(){
        incorrectRegistrationPageLabel = new JLabel("");
        Font labelFont = incorrectRegistrationPageLabel.getFont().deriveFont(Font.PLAIN, 35f);
        incorrectRegistrationPageLabel.setFont(labelFont);
        incorrectRegistrationPageLabel.setLocation(100,100);//Location is set in checkRegistrationPage...()
        incorrectRegistrationPageLabel.setSize(600,100);
        mainFrame.getContentPane().add(incorrectRegistrationPageLabel);
    }

    public void addIncorrectRegistrationPopupLabel(){
        incorrectRegistrationPopupLabel = new JLabel();
        Font labelFont = incorrectRegistrationPopupLabel.getFont().deriveFont(Font.PLAIN, 35f);
        incorrectRegistrationPopupLabel.setFont(labelFont);
        incorrectRegistrationPopupLabel.setLocation(80,190);//Location is set in checkRegistrationPage...()
        incorrectRegistrationPopupLabel.setSize(500,100);
        basicPopupFrame.getContentPane().add(incorrectRegistrationPopupLabel);
    }

    public void addCorrectRegistrationPopupLabel(){
        correctRegistrationPopupLabel = new JLabel();
        Font labelFont = correctRegistrationPopupLabel.getFont().deriveFont(Font.PLAIN, 35f);
        correctRegistrationPopupLabel.setFont(labelFont);
        correctRegistrationPopupLabel.setLocation(80,190);//Location is set in checkRegistrationPage...()
        correctRegistrationPopupLabel.setSize(500,100);
        basicPopupFrame.getContentPane().add(correctRegistrationPopupLabel);
    }

    public void addIncorrectRegistrationPageAsterisk(){
        incorrectRegistrationPageAsterisk = new JLabel("");
        Font labelFont = incorrectRegistrationPageAsterisk.getFont().deriveFont(Font.PLAIN, 55f);
        incorrectRegistrationPageAsterisk.setFont(labelFont);
        incorrectRegistrationPageAsterisk.setSize(50,50);
        incorrectRegistrationPageAsterisk.setLocation(100, 100);//Location is set in checkRegistrationPage...()
        mainFrame.getContentPane().add(incorrectRegistrationPageAsterisk);
    }


    //Registration title
    public void addRegistrationPageTitle(){
        if (currentPage.equals("Registration Page 1")){
            registrationLabel = new JLabel();
            Font labelFont = registrationLabel.getFont().deriveFont(Font.PLAIN, 80f);
            registrationLabel.setFont(labelFont);
            registrationLabel.setLocation(375,200);
            registrationLabel.setSize(800,150);
            registrationLabel.setText("<html>"+registrationPageTitle+"1-"+"</html>");
            mainFrame.getContentPane().add(registrationLabel);
        }else if (currentPage.equals("Registration Page 2")){
            registrationLabel.setText("<html><div style='text-align: center;'>"+registrationPageTitle+"2-"+"</div></html>");
            mainFrame.getContentPane().add(registrationLabel);
        }else if (currentPage.equals("Registration Page 3")){
            registrationLabel.setText("<html><div style='text-align: center;'>"+registrationPageTitle+"3-"+"</div></html>");
            mainFrame.getContentPane().add(registrationLabel);
        }else if (currentPage.equals("Registration Page 4")){
            registrationLabel.setText("<html><div style='text-align: center;'>"+"-Confirm Information-"+"</div></html>");
             mainFrame.getContentPane().add(registrationLabel);
        }
    }


    //Registration Page 1 Components
    public void addFirstNameLabel(){
        firstNameLabel = new JLabel("First Name");
        Font labelFont = firstNameLabel.getFont().deriveFont(Font.PLAIN, 55f);
        firstNameLabel.setFont(labelFont);
        firstNameLabel.setLocation(200,400);
        firstNameLabel.setSize(400,100);
        mainFrame.getContentPane().add(firstNameLabel);
    }

    public void addFirstNameTF(){
        firstNameTF = new JTextField();
        Font textFieldFont = firstNameTF.getFont().deriveFont(Font.PLAIN, 45f);
        firstNameTF.setFont(textFieldFont);
        firstNameTF.setLocation(800,420);
        firstNameTF.setSize(500,60);
        firstNameTF.setDocument(new AlphaDocument());
        mainFrame.getContentPane().add(firstNameTF);
    }

    public void addMiddleNameLabel(){
        middleNameLabel = new JLabel("Middle Name");
        Font labelFont = middleNameLabel.getFont().deriveFont(Font.PLAIN, 55f);
        middleNameLabel.setFont(labelFont);
        middleNameLabel.setLocation(200,500);
        middleNameLabel.setSize(400,100);
        mainFrame.getContentPane().add(middleNameLabel);
    }

    public void addMiddleNameTF(){
        middleNameTF = new JTextField();
        Font textFieldFont = middleNameTF.getFont().deriveFont(Font.PLAIN, 45f);
        middleNameTF.setFont(textFieldFont);
        middleNameTF.setLocation(800,520);
        middleNameTF.setSize(500,60);
        middleNameTF.setDocument(new AlphaDocument());
        mainFrame.getContentPane().add(middleNameTF);
    }

    public void addLastNameLabel(){
        lastNameLabel = new JLabel("Last Name");
        Font labelFont = lastNameLabel.getFont().deriveFont(Font.PLAIN, 55f);
        lastNameLabel.setFont(labelFont);
        lastNameLabel.setLocation(200,600);
        lastNameLabel.setSize(400,100);
        mainFrame.getContentPane().add(lastNameLabel);
    }

    public void addLastNameTF(){
        lastNameTF = new JTextField();
        Font textFieldFont = lastNameTF.getFont().deriveFont(Font.PLAIN, 45f);
        lastNameTF.setFont(textFieldFont);
        lastNameTF.setLocation(800,620);
        lastNameTF.setSize(500,60);
        lastNameTF.setDocument(new AlphaDocument());
        mainFrame.getContentPane().add(lastNameTF);
    }

    public void addDateOfBirthLabel(){
        dateOfBirthLabel = new JLabel("Date of Birth");
        Font labelFont = dateOfBirthLabel.getFont().deriveFont(Font.PLAIN, 55f);
        dateOfBirthLabel.setFont(labelFont);
        dateOfBirthLabel.setLocation(200,700);
        dateOfBirthLabel.setSize(400,100);
        mainFrame.getContentPane().add(dateOfBirthLabel);
    }

    public void addDateOfBirthCBs(){//MUST BE 18 TO REGISTER
        monthCB = new JComboBox(months);
        Font comboBoxFont = monthCB.getFont().deriveFont(Font.PLAIN, 45f);
        monthCB.setFont(comboBoxFont);
        monthCB.setLocation(800,720);
        monthCB.setSize(250,60);
        monthCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(monthCB);

        int tempDay = 1;
        for(int i = 0; i < 31; i++){
            days[i] = tempDay;
            tempDay++;
        }
        dayCB = new JComboBox(days);
        dayCB.setFont(comboBoxFont);
        dayCB.setLocation(1050,720);
        dayCB.setSize(100,60);
        dayCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(dayCB);

        int tempYear = currentYear;
        for (int i = 0; i < 100; i++){
            years[i] = tempYear;
            tempYear--;
        }
        yearCB = new JComboBox(years);
        yearCB.setFont(comboBoxFont);
        yearCB.setLocation(1150,720);
        yearCB.setSize(150,60);
        yearCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(yearCB);

    }

    public void addEmailRegistrationLabel(){
        emailRegistrationLabel = new JLabel("Email");
        Font labelFont = emailRegistrationLabel.getFont().deriveFont(Font.PLAIN, 55f);
        emailRegistrationLabel.setFont(labelFont);
        emailRegistrationLabel.setLocation(200,800);
        emailRegistrationLabel.setSize(400,100);
        mainFrame.getContentPane().add(emailRegistrationLabel);
    }

    public void addEmailRegistrationTF(){
        emailRegistrationTF = new JTextField();
        Font textFieldFont = emailRegistrationTF.getFont().deriveFont(Font.PLAIN, 45f);
        emailRegistrationTF.setFont(textFieldFont);
        emailRegistrationTF.setLocation(800,820);
        emailRegistrationTF.setSize(500,60);
        emailRegistrationTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(emailRegistrationTF);
    }

    public void addEmailConfirmationRegistrationLabel(){
        emailConfirmationRegistrationLabel = new JLabel("Confirm Email");
        Font labelFont = emailConfirmationRegistrationLabel.getFont().deriveFont(Font.PLAIN, 55f);
        emailConfirmationRegistrationLabel.setFont(labelFont);
        emailConfirmationRegistrationLabel.setLocation(200,900);
        emailConfirmationRegistrationLabel.setSize(400,100);
        mainFrame.getContentPane().add(emailConfirmationRegistrationLabel);
    }

    public void addEmailConfirmationRegistrationTF(){
        emailConfirmationRegistrationTF = new JTextField();
        Font textFieldFont = emailConfirmationRegistrationTF.getFont().deriveFont(Font.PLAIN, 45f);
        emailConfirmationRegistrationTF.setFont(textFieldFont);
        emailConfirmationRegistrationTF.setLocation(800,920);
        emailConfirmationRegistrationTF.setSize(500,60);
        emailConfirmationRegistrationTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(emailConfirmationRegistrationTF);
    }


    //Registration Page 2 Components
    public void addAddressLabel(){
        addressLabel = new JLabel("Address");
        Font labelFont = addressLabel.getFont().deriveFont(Font.PLAIN, 55f);
        addressLabel.setFont(labelFont);
        addressLabel.setLocation(200,400);
        addressLabel.setSize(400,100);
        mainFrame.getContentPane().add(addressLabel);
    }

    public void addAddressTF(){
        addressTF = new JTextField();
        Font textFieldFont = addressTF.getFont().deriveFont(Font.PLAIN, 45f);
        addressTF.setFont(textFieldFont);
        addressTF.setLocation(800,420);
        addressTF.setSize(500,60);
        addressTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(addressTF);
    }

    public void addCityLabel(){
        cityLabel = new JLabel("City");
        Font labelFont = cityLabel.getFont().deriveFont(Font.PLAIN, 55f);
        cityLabel.setFont(labelFont);
        cityLabel.setLocation(200,500);
        cityLabel.setSize(400,100);
        mainFrame.getContentPane().add(cityLabel);
    }

    public void addCityTF(){
        cityTF = new JTextField();
        Font textFieldFont = cityTF.getFont().deriveFont(Font.PLAIN, 45f);
        cityTF.setFont(textFieldFont);
        cityTF.setLocation(800,520);
        cityTF.setSize(500,60);
        cityTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(cityTF);
    }

    public void addZipCodeLabel(){
        zipCodeLabel = new JLabel("ZIP Code");
        Font labelFont = zipCodeLabel.getFont().deriveFont(Font.PLAIN, 55f);
        zipCodeLabel.setFont(labelFont);
        zipCodeLabel.setLocation(200,600);
        zipCodeLabel.setSize(400,100);
        mainFrame.getContentPane().add(zipCodeLabel);
    }

    public void addZipCodeTF(){
        zipCodeTF = new DoubleJTextField();
        Font textFieldFont = zipCodeTF.getFont().deriveFont(Font.PLAIN, 45f);
        zipCodeTF.setFont(textFieldFont);
        zipCodeTF.setLocation(800,620);
        zipCodeTF.setSize(500,60);
        zipCodeTF.setDocument(new JTextFieldLimit(5));
        mainFrame.getContentPane().add(zipCodeTF);
    }

    public void addStateLabel(){
        stateLabel = new JLabel("State");
        Font labelFont = stateLabel.getFont().deriveFont(Font.PLAIN, 55f);
        stateLabel.setFont(labelFont);
        stateLabel.setLocation(200,700);
        stateLabel.setSize(400,100);
        mainFrame.getContentPane().add(stateLabel);
    }

    public void addStateCB(){
        stateCB = new JComboBox(states);
        Font comboBoxFont = stateCB.getFont().deriveFont(Font.PLAIN, 45f);
        stateCB.setFont(comboBoxFont);
        stateCB.setLocation(800,720);
        stateCB.setSize(500,60);
        stateCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(stateCB);
    }

    public void addSecurityQuestionLabel(){
        securityQuestionLabel = new JLabel("Security Question");
        Font labelFont = securityQuestionLabel.getFont().deriveFont(Font.PLAIN, 55f);
        securityQuestionLabel.setFont(labelFont);
        securityQuestionLabel.setLocation(200,800);
        securityQuestionLabel.setSize(450,100);
        mainFrame.getContentPane().add(securityQuestionLabel);
    }

    public void addSecurityQuestionCB(){
        securityQuestionCB = new WideComboBox(securityQuestions);
        Font comboBoxFont = securityQuestionCB.getFont().deriveFont(Font.PLAIN, 45f);
        securityQuestionCB.setFont(comboBoxFont);
        securityQuestionCB.setLocation(800,820);
        securityQuestionCB.setSize(500,60);
        securityQuestionCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(securityQuestionCB);
    }

    public void addSecurityAnswerLabel(){
        securityAnswerLabel = new JLabel("Security Answer");
        Font labelFont = securityAnswerLabel.getFont().deriveFont(Font.PLAIN, 55f);
        securityAnswerLabel.setFont(labelFont);
        securityAnswerLabel.setLocation(200,900);
        securityAnswerLabel.setSize(400,100);
        mainFrame.getContentPane().add(securityAnswerLabel);
    }

    public void addSecurityAnswerTF(){
        securityAnswerTF = new JTextField();
        Font textFieldFont = securityAnswerTF.getFont().deriveFont(Font.PLAIN, 45f);
        securityAnswerTF.setFont(textFieldFont);
        securityAnswerTF.setLocation(800,920);
        securityAnswerTF.setSize(500,60);
        securityAnswerTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(securityAnswerTF);
    }

    //Registration page 3 components
    public void addPincodeLabel(){
        pincodeLabel = new JLabel("Pin Code");
        Font labelFont = pincodeLabel.getFont().deriveFont(Font.PLAIN, 55f);
        pincodeLabel.setFont(labelFont);
        pincodeLabel.setLocation(200,400);
        pincodeLabel.setSize(400,100);
        mainFrame.getContentPane().add(pincodeLabel);
    }

    public void addPincodeTF(){
        pincodeTF = new DoubleJTextField();
        Font textFieldFont = pincodeTF.getFont().deriveFont(Font.PLAIN, 45f);
        pincodeTF.setFont(textFieldFont);
        pincodeTF.setLocation(800,420);
        pincodeTF.setSize(500,60);
        pincodeTF.setDocument(new JTextFieldLimit(4));
        mainFrame.getContentPane().add(pincodeTF);
    }

    public void addSSNLabel(){
        sSNLabel = new JLabel("SSN");
        Font labelFont = sSNLabel.getFont().deriveFont(Font.PLAIN, 55f);
        sSNLabel.setFont(labelFont);
        sSNLabel.setLocation(200,500);
        sSNLabel.setSize(400,100);
        mainFrame.getContentPane().add(sSNLabel);
    }

    public void addSSNTF1(){
        sSNTF1 = new DoubleJTextField();
        Font textFieldFont = sSNTF1.getFont().deriveFont(Font.PLAIN, 45f);
        sSNTF1.setFont(textFieldFont);
        sSNTF1.setLocation(800,520);
        sSNTF1.setSize(150,60);
        sSNTF1.setDocument(new JTextFieldLimit(3));
        sSNTF1.setHorizontalAlignment(JTextField.CENTER);
        mainFrame.getContentPane().add(sSNTF1);
    }

    public void addSSNTF2(){
        sSNTF2 = new DoubleJTextField();
        Font textFieldFont = sSNTF2.getFont().deriveFont(Font.PLAIN, 45f);
        sSNTF2.setFont(textFieldFont);
        sSNTF2.setLocation(1000,520);
        sSNTF2.setSize(100,60);
        sSNTF2.setDocument(new JTextFieldLimit(2));
        sSNTF2.setHorizontalAlignment(JTextField.CENTER);
        mainFrame.getContentPane().add(sSNTF2);
    }

    public void addSSNTF3(){
        sSNTF3 = new DoubleJTextField();
        Font textFieldFont = sSNTF3.getFont().deriveFont(Font.PLAIN, 45f);
        sSNTF3.setFont(textFieldFont);
        sSNTF3.setLocation(1150,520);
        sSNTF3.setSize(150,60);
        sSNTF3.setDocument(new JTextFieldLimit(4));
        sSNTF3.setHorizontalAlignment(JTextField.CENTER);
        mainFrame.getContentPane().add(sSNTF3);
    }

    public void addSSNDashes(){
        ssnDash1 = new JLabel("-");
        Font labelFont = ssnDash1.getFont().deriveFont(Font.PLAIN, 45f);
        ssnDash1.setFont(labelFont);
        ssnDash1.setLocation(965,535);
        ssnDash1.setSize(20,20);
        mainFrame.getContentPane().add(ssnDash1);

        ssnDash2 = new JLabel("-");
        ssnDash2.setFont(labelFont);
        ssnDash2.setLocation(1115,535);
        ssnDash2.setSize(20,20);
        mainFrame.getContentPane().add(ssnDash2);
    }

    public void addPasswordRegistrationLabel(){
        passwordRegistrationLabel = new JLabel("Password");
        Font labelFont = passwordRegistrationLabel.getFont().deriveFont(Font.PLAIN, 55f);
        passwordRegistrationLabel.setFont(labelFont);
        passwordRegistrationLabel.setLocation(200,600);
        passwordRegistrationLabel.setSize(400,100);
        mainFrame.getContentPane().add(passwordRegistrationLabel);
    }

    public void addPasswordRegistrationTF(){
        passwordRegistrationTF = new JTextField();
        Font textFieldFont = passwordRegistrationTF.getFont().deriveFont(Font.PLAIN, 45f);
        passwordRegistrationTF.setFont(textFieldFont);
        passwordRegistrationTF.setLocation(800,620);
        passwordRegistrationTF.setSize(500,60);
        passwordRegistrationTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(passwordRegistrationTF);
    }

    public void addPasswordConfirmationRegistrationLabel(){
        passwordConfirmationRegistrationLabel = new JLabel("Confirm Password");
        Font labelFont = passwordConfirmationRegistrationLabel.getFont().deriveFont(Font.PLAIN, 55f);
        passwordConfirmationRegistrationLabel.setFont(labelFont);
        passwordConfirmationRegistrationLabel.setLocation(200,700);
        passwordConfirmationRegistrationLabel.setSize(500,100);
        mainFrame.getContentPane().add(passwordConfirmationRegistrationLabel);
    }

    public void addPasswordConfirmationRegistrationTF(){
        passwordConfirmationRegistrationTF = new JTextField();
        Font textFieldFont = passwordConfirmationRegistrationTF.getFont().deriveFont(Font.PLAIN, 45f);
        passwordConfirmationRegistrationTF.setFont(textFieldFont);
        passwordConfirmationRegistrationTF.setLocation(800,720);
        passwordConfirmationRegistrationTF.setSize(500,60);
        passwordConfirmationRegistrationTF.setDocument(new JTextFieldLimit(45));
        mainFrame.getContentPane().add(passwordConfirmationRegistrationTF);
    }

    public void addEmailAgreementCheckBox(){
        emailAgreementCheckBox = new JCheckBox("  I agree to receive emails from Pacific Bank");
        Font checkBoxFont = emailAgreementCheckBox.getFont().deriveFont(Font.PLAIN, 40f);
        emailAgreementCheckBox.setFont(checkBoxFont);
        emailAgreementCheckBox.setLocation(355, 950);
        emailAgreementCheckBox.setSize(1000, 100);
        new ScaleCheckBoxIcon(emailAgreementCheckBox);
        emailAgreementCheckBox.setOpaque(false);
        mainFrame.getContentPane().add(emailAgreementCheckBox);
    }

    //Registration page 4 components
    public void addFinishRegistrationButton(){
        finishRegistrationButton = new JButton();
        FinishRegistrationButtonClass frb = new FinishRegistrationButtonClass();
        finishRegistrationButton.addActionListener(frb);
        Font buttonFont = finishRegistrationButton.getFont().deriveFont(Font.PLAIN, 50f);
        finishRegistrationButton.setFont(buttonFont);
        finishRegistrationButton.setLocation(1100,1375);
        finishRegistrationButton.setSize(300,80);
        finishRegistrationButton.setText("Finish");
        mainFrame.getContentPane().add(finishRegistrationButton);
    }

    public void addFirstNameConfirmationLabel(){//////////////////////////////////////////////////////////
        firstNameLabel = new JLabel("<html><b>First name: </b><html>" + firstName);
        Font labelFont = firstNameLabel.getFont().deriveFont(Font.PLAIN, 35f);
        firstNameLabel.setFont(labelFont);
        firstNameLabel.setLocation(225,400);
        firstNameLabel.setSize(600,50);
        mainFrame.getContentPane().add(firstNameLabel);
    }

    public void addMiddleNameConfirmationLabel(){
        middleNameLabel = new JLabel("<html><b>Middle name: </b><html>" + middleName);
        Font labelFont = middleNameLabel.getFont().deriveFont(Font.PLAIN, 35f);
        middleNameLabel.setFont(labelFont);
        middleNameLabel.setLocation(225,475);
        middleNameLabel.setSize(600,50);
        mainFrame.getContentPane().add(middleNameLabel);
    }

    public void addLastNameConfirmationLabel(){
        lastNameLabel = new JLabel("<html><b>Last name: </b><html>" + lastName);
        Font labelFont = lastNameLabel.getFont().deriveFont(Font.PLAIN, 35f);
        lastNameLabel.setFont(labelFont);
        lastNameLabel.setLocation(225,550);
        lastNameLabel.setSize(600,50);
        mainFrame.getContentPane().add(lastNameLabel);
    }

    public void addDateOfBirthConfirmationLabel(){
        dateOfBirthLabel = new JLabel("<html><b>Date of birth: </b><html>" + dateOfBirthString);
        Font labelFont = dateOfBirthLabel.getFont().deriveFont(Font.PLAIN, 35f);
        dateOfBirthLabel.setFont(labelFont);
        dateOfBirthLabel.setLocation(225,625);
        dateOfBirthLabel.setSize(600,50);
        mainFrame.getContentPane().add(dateOfBirthLabel);
    }

    public void addEmailConfirmationLabel(){
        emailRegistrationLabel = new JLabel("<html><b>Email: </b><html>" + email);
        Font labelFont = emailRegistrationLabel.getFont().deriveFont(Font.PLAIN, 35f);
        emailRegistrationLabel.setFont(labelFont);
        emailRegistrationLabel.setLocation(225,700);
        emailRegistrationLabel.setSize(600,50);
        mainFrame.getContentPane().add(emailRegistrationLabel);
    }

    public void addAddressConfirmationLabel(){
        addressLabel = new JLabel("<html><b>Address: </b><html>" + address);
        Font labelFont = addressLabel.getFont().deriveFont(Font.PLAIN, 35f);
        addressLabel.setFont(labelFont);
        addressLabel.setLocation(225,775);
        addressLabel.setSize(600,50);
        mainFrame.getContentPane().add(addressLabel);
    }

    public void addCityConfirmationLabel(){
        cityLabel = new JLabel("<html><b>City: </b><html>" + city);
        Font labelFont = cityLabel.getFont().deriveFont(Font.PLAIN, 35f);
        cityLabel.setFont(labelFont);
        cityLabel.setLocation(225,850);
        cityLabel.setSize(600,50);
        mainFrame.getContentPane().add(cityLabel);
    }

    public void addZipcodeConfirmationLabel(){
        zipCodeLabel = new JLabel("<html><b>Zipcode: </b><html>" + zipCode);
        Font labelFont = zipCodeLabel.getFont().deriveFont(Font.PLAIN, 35f);
        zipCodeLabel.setFont(labelFont);
        zipCodeLabel.setLocation(800,400);
        zipCodeLabel.setSize(600,50);
        mainFrame.getContentPane().add(zipCodeLabel);
    }

    public void addStateConfirmationLabel(){
        stateLabel = new JLabel("<html><b>State: </b><html>" + state);
        Font labelFont = stateLabel.getFont().deriveFont(Font.PLAIN, 35f);
        stateLabel.setFont(labelFont);
        stateLabel.setLocation(800,475);
        stateLabel.setSize(600,50);
        mainFrame.getContentPane().add(stateLabel);
    }

    public void addSecurityQuestionConfirmationLabel(){
        securityQuestionLabel = new JLabel("<html><b>Security question: </b><html>");
        Font labelFont = securityQuestionLabel.getFont().deriveFont(Font.PLAIN, 35f);
        securityQuestionLabel.setFont(labelFont);
        securityQuestionLabel.setLocation(800,550);
        securityQuestionLabel.setSize(900,50);
        mainFrame.getContentPane().add(securityQuestionLabel);
    }

    public void addSecurityQuestionConfirmationLabel2(){
        securityQuestionLabel2 = new JLabel(securityQuestion);
        Font labelFont = securityQuestionLabel2.getFont().deriveFont(Font.PLAIN, 35f);
        securityQuestionLabel2.setFont(labelFont);
        securityQuestionLabel2.setLocation(875,600);
        securityQuestionLabel2.setSize(900,50);
        mainFrame.getContentPane().add(securityQuestionLabel2);
    }

    public void addSecurityAnswerConfirmationLabel(){
        securityAnswerLabel = new JLabel("<html><b>Security answer: </b><html>" + securityAnswer);
        Font labelFont = securityAnswerLabel.getFont().deriveFont(Font.PLAIN, 35f);
        securityAnswerLabel.setFont(labelFont);
        securityAnswerLabel.setLocation(800,675);
        securityAnswerLabel.setSize(600,50);
        mainFrame.getContentPane().add(securityAnswerLabel);
    }

    public void addPincodeConfirmationLabel(){
        pincodeLabel = new JLabel("<html><b>Pincode: </b><html>" + pincode);
        Font labelFont = pincodeLabel.getFont().deriveFont(Font.PLAIN, 35f);
        pincodeLabel.setFont(labelFont);
        pincodeLabel.setLocation(800,750);
        pincodeLabel.setSize(600,50);
        mainFrame.getContentPane().add(pincodeLabel);
    }

    public void addSSNConfirmationLabel(){
        sSNLabel = new JLabel("<html><b>SSN: </b><html>" + "XXX-XX-" +ssnPart3);
        Font labelFont = sSNLabel.getFont().deriveFont(Font.PLAIN, 35f);
        sSNLabel.setFont(labelFont);
        sSNLabel.setLocation(800,825);
        sSNLabel.setSize(600,50);
        mainFrame.getContentPane().add(sSNLabel);
    }

    public void addPasswordConfirmationLabel(){
        passwordRegistrationLabel = new JLabel("<html><b>Password: </b><html>" + password);
        Font labelFont = passwordRegistrationLabel.getFont().deriveFont(Font.PLAIN, 35f);
        passwordRegistrationLabel.setFont(labelFont);
        passwordRegistrationLabel.setLocation(800,900);
        passwordRegistrationLabel.setSize(600,50);
        mainFrame.getContentPane().add(passwordRegistrationLabel);
    }////////////////////////////////////////////////////////////////////////////////////////////////////

    //Main bank page components
    public void addLogoutButton(){
        logoutButton = new JButton("Logout");
        LogoutButtonClass lb = new LogoutButtonClass();
        logoutButton.addActionListener(lb);
        Font buttonFont = logoutButton.getFont().deriveFont(Font.PLAIN, 50f);
        logoutButton.setFont(buttonFont);
        logoutButton.setLocation(100,1375);
        logoutButton.setSize(300,80);
        mainFrame.getContentPane().add(logoutButton);
    }

    public  void addMainBankTitle(){
        mainBankTitle = new JLabel();
        Font labelFont = mainBankTitle.getFont().deriveFont(Font.PLAIN, 75f);
        mainBankTitle.setFont(labelFont);
        mainBankTitle.setLocation(425,200);
        mainBankTitle.setSize(700,200);
        mainBankTitle.setText("Welcome, " + databaseOptions.getFirstName(customerNumber));
        mainFrame.getContentPane().add(mainBankTitle);
    }

    public void addDepositButton(){
        depositButton = new JButton();
        DepositButtonClass db = new DepositButtonClass();
        depositButton.addActionListener(db);
        Font buttonFont = depositButton.getFont().deriveFont(Font.PLAIN, 55f);
        depositButton.setFont(buttonFont);
        depositButton.setLocation(150,425);
        depositButton.setSize(500,125);
        depositButton.setText("Deposit");
        mainFrame.getContentPane().add(depositButton);
    }

    public void addWithdrawButton(){
        withdrawButton = new JButton();
        WithdrawButtonClass wb = new WithdrawButtonClass();
        withdrawButton.addActionListener(wb);
        Font buttonFont = withdrawButton.getFont().deriveFont(Font.PLAIN, 55f);
        withdrawButton.setFont(buttonFont);
        withdrawButton.setLocation(850,425);
        withdrawButton.setSize(500,125);
        withdrawButton.setText("Withdraw");
        mainFrame.getContentPane().add(withdrawButton);
    }

    public void addAccountDetailsButton(){
        accountDetailsButton = new JButton();
        AccountDetailsButtonClass adb = new AccountDetailsButtonClass();
        accountDetailsButton.addActionListener(adb);
        Font buttonFont = accountDetailsButton.getFont().deriveFont(Font.PLAIN, 55f);
        accountDetailsButton.setFont(buttonFont);
        accountDetailsButton.setLocation(150,625);
        accountDetailsButton.setSize(500,125);
        accountDetailsButton.setText("Account Details");
        mainFrame.getContentPane().add(accountDetailsButton);
    }

    public void addBalanceButton(){
        balanceButton = new JButton();
        BalanceButtonClass bb = new BalanceButtonClass();
        balanceButton.addActionListener(bb);
        Font buttonFont = balanceButton.getFont().deriveFont(Font.PLAIN, 55f);
        balanceButton.setFont(buttonFont);
        balanceButton.setLocation(850,625);
        balanceButton.setSize(500,125);
        balanceButton.setText("Balance");
        mainFrame.getContentPane().add(balanceButton);
    }

    public void addTransactionsButton(){
        transactionsButton = new JButton();
        TransactionsButtonClass tb = new TransactionsButtonClass();
        transactionsButton.addActionListener(tb);
        Font buttonFont = transactionsButton.getFont().deriveFont(Font.PLAIN, 55f);
        transactionsButton.setFont(buttonFont);
        transactionsButton.setLocation(150,825);
        transactionsButton.setSize(500,125);
        transactionsButton.setText("Transactions");
        mainFrame.getContentPane().add(transactionsButton);
    }

    public void addTransferButton(){
        transferButton = new JButton();
        TransferButtonClass tb = new TransferButtonClass();
        transferButton.addActionListener(tb);
        Font buttonFont = transferButton.getFont().deriveFont(Font.PLAIN, 55f);
        transferButton.setFont(buttonFont);
        transferButton.setLocation(850,825);
        transferButton.setSize(500,125);
        transferButton.setText("Transfer");
        mainFrame.getContentPane().add(transferButton);
    }





    //Deposit Page components
    public void addDepositPageTitle(){
        depositPageLabel = new JLabel();
        Font labelFont = depositPageLabel.getFont().deriveFont(Font.PLAIN, 75f);
        depositPageLabel.setFont(labelFont);
        depositPageLabel.setLocation(550,200);
        depositPageLabel.setSize(700,200);
        depositPageLabel.setText("-DEPOSIT-");
        mainFrame.getContentPane().add(depositPageLabel);
    }

    public void addDepositCheckingsButton(){
        depositCheckingsButton = new JButton();
        DepositCheckingsButtonClass dcb = new DepositCheckingsButtonClass();
        depositCheckingsButton.addActionListener(dcb);
        Font buttonFont = depositCheckingsButton.getFont().deriveFont(Font.PLAIN, 55f);
        depositCheckingsButton.setFont(buttonFont);
        depositCheckingsButton.setLocation(200,425);
        depositCheckingsButton.setSize(450,125);
        depositCheckingsButton.setText("Checkings");
        mainFrame.getContentPane().add(depositCheckingsButton);
    }

    public void addDepositSavingsButton(){
        depositSavingsButton = new JButton();
        DepositSavingsButtonClass dsb = new DepositSavingsButtonClass();
        depositSavingsButton.addActionListener(dsb);
        Font buttonFont = depositSavingsButton.getFont().deriveFont(Font.PLAIN, 55f);
        depositSavingsButton.setFont(buttonFont);
        depositSavingsButton.setLocation(850,425);
        depositSavingsButton.setSize(450,125);
        depositSavingsButton.setText("Savings");
        mainFrame.getContentPane().add(depositSavingsButton);
    }

    public void addCurrentBalanceDepositLabel(){//put the current balance next to this in green next to this in green
        currentBalanceDepositLabel = new JLabel();
        Font labelFont = currentBalanceDepositLabel.getFont().deriveFont(Font.PLAIN, 50f);
        currentBalanceDepositLabel.setFont(labelFont);
        currentBalanceDepositLabel.setLocation(350,550);
        currentBalanceDepositLabel.setSize(700,200);
        currentBalanceDepositLabel.setText("Current Balance: ");
        mainFrame.getContentPane().add(currentBalanceDepositLabel);
    }

    public void addDepositAmountLabel(){
        depositAmountLabel = new JLabel();
        Font labelFont = depositAmountLabel.getFont().deriveFont(Font.PLAIN, 50f);
        depositAmountLabel.setFont(labelFont);
        depositAmountLabel.setLocation(350,650);
        depositAmountLabel.setSize(200,200);
        depositAmountLabel.setText("Deposit: ");
        mainFrame.getContentPane().add(depositAmountLabel);
    }

    public void addDepositAmountTF(){
        depositAmountTF = new DoubleJTextField();
        Font textFieldFont = depositAmountTF.getFont().deriveFont(Font.PLAIN, 45f);
        depositAmountTF.setFont(textFieldFont);
        depositAmountTF.setLocation(600,725);
        depositAmountTF.setSize(500,60);
        depositAmountTF.setText("0.00");
        mainFrame.getContentPane().add(depositAmountTF);
    }

    public void addFinalDepositButton(){
        finalDepositButton = new JButton();
        FinalDepositButtonClass fdb = new FinalDepositButtonClass();
        finalDepositButton.addActionListener(fdb);
        Font buttonFont = finalDepositButton.getFont().deriveFont(Font.PLAIN, 50f);
        finalDepositButton.setFont(buttonFont);
        finalDepositButton.setLocation(1100,1375);
        finalDepositButton.setSize(300,80);
        finalDepositButton.setText("Deposit");
        mainFrame.getContentPane().add(finalDepositButton);
    }

    public void addDepositConfirmationLabel(){
        depositConfirmationLabel = new JLabel("<html><br>"+"Are you sure you want to deposit "+"</br><br>"+"$"+depositAmountStringActual+ " into your " + depositAccountSelection + " account?" + "</br></html>");//need a variable that tells me which account they are depositing into
        Font labelFont = depositConfirmationLabel.getFont().deriveFont(Font.PLAIN, 45f);
        depositConfirmationLabel.setFont(labelFont);
        depositConfirmationLabel.setForeground(Color.WHITE);
        depositConfirmationLabel.setLocation(90,0);
        depositConfirmationLabel.setSize(800,200);
        basicPopupFrame.getContentPane().add(depositConfirmationLabel);
    }

    public void addYesButtonOnDepositPopup(){
        yesButtonOnDepositPopup = new JButton("Yes");
        YesOnDepositPopupClass yodb = new YesOnDepositPopupClass();
        yesButtonOnDepositPopup.addActionListener(yodb);
        Font buttonFont = yesButtonOnDepositPopup.getFont().deriveFont(Font.PLAIN, 45f);
        yesButtonOnDepositPopup.setFont(buttonFont);
        yesButtonOnDepositPopup.setLocation(500,300);
        yesButtonOnDepositPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(yesButtonOnDepositPopup);
    }



    //Withdraw Page components
    public void addWithdrawPageTitle(){
        withdrawPageLabel = new JLabel();
        Font labelFont = withdrawPageLabel.getFont().deriveFont(Font.PLAIN, 75f);
        withdrawPageLabel.setFont(labelFont);
        withdrawPageLabel.setLocation(525,200);
        withdrawPageLabel.setSize(700,200);
        withdrawPageLabel.setText("-WITHDRAW-");
        mainFrame.getContentPane().add(withdrawPageLabel);
    }

    public void addWithdrawAmountLabel(){
        depositAmountLabel = new JLabel();
        Font labelFont = depositAmountLabel.getFont().deriveFont(Font.PLAIN, 50f);
        depositAmountLabel.setFont(labelFont);
        depositAmountLabel.setLocation(350,650);
        depositAmountLabel.setSize(250,200);
        depositAmountLabel.setText("Withdraw: ");
        mainFrame.getContentPane().add(depositAmountLabel);
    }

    public void addFinalWithdrawButton(){
        finalWithdrawButton = new JButton();
        FinalWithdrawButtonClass fwb = new FinalWithdrawButtonClass();
        finalWithdrawButton.addActionListener(fwb);
        Font buttonFont = finalWithdrawButton.getFont().deriveFont(Font.PLAIN, 50f);
        finalWithdrawButton.setFont(buttonFont);
        finalWithdrawButton.setLocation(1100,1375);
        finalWithdrawButton.setSize(300,80);
        finalWithdrawButton.setText("Withdraw");
        mainFrame.getContentPane().add(finalWithdrawButton);
    }

    public void addWithdrawConfirmationLabel(){
        withdrawConfirmationLabel = new JLabel("<html><br>"+"Are you sure you want to withdraw "+"</br><br>"+"$"+depositAmountStringActual+ " from your " + depositAccountSelection + " account?" + "</br></html>");//need a variable that tells me which account they are depositing into
        Font labelFont = withdrawConfirmationLabel.getFont().deriveFont(Font.PLAIN, 45f);
        withdrawConfirmationLabel.setFont(labelFont);
        withdrawConfirmationLabel.setForeground(Color.WHITE);
        withdrawConfirmationLabel.setLocation(90,0);
        withdrawConfirmationLabel.setSize(800,200);
        basicPopupFrame.getContentPane().add(withdrawConfirmationLabel);
    }

    public void addYesButtonOnWithdrawPopup(){
        yesButtonOnWithdrawPopup = new JButton("Yes");
        YesOnWithdrawPopupClass yowb = new YesOnWithdrawPopupClass();
        yesButtonOnWithdrawPopup.addActionListener(yowb);
        Font buttonFont = yesButtonOnWithdrawPopup.getFont().deriveFont(Font.PLAIN, 45f);
        yesButtonOnWithdrawPopup.setFont(buttonFont);
        yesButtonOnWithdrawPopup.setLocation(500,300);
        yesButtonOnWithdrawPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(yesButtonOnWithdrawPopup);
    }



    //Account Details components
    public void addAccountDetailsPageTitle(){
        accountDetailsPageLabel = new JLabel();
        Font labelFont = accountDetailsPageLabel.getFont().deriveFont(Font.PLAIN, 75f);
        accountDetailsPageLabel.setFont(labelFont);
        accountDetailsPageLabel.setLocation(365,200);
        accountDetailsPageLabel.setSize(1000,200);
        accountDetailsPageLabel.setText("-ACCOUNT DETAILS-");
        mainFrame.getContentPane().add(accountDetailsPageLabel);
    }

    public void addFirstNameAccountDetailLabel(){
        firstNameLabel = new JLabel("<html><b>First name: </b><html>" + databaseOptions.getFirstName(customerNumber));
        Font labelFont = firstNameLabel.getFont().deriveFont(Font.PLAIN, 35f);
        firstNameLabel.setFont(labelFont);
        firstNameLabel.setLocation(175,400);
        firstNameLabel.setSize(600,50);
        mainFrame.getContentPane().add(firstNameLabel);
    }

    public void addMiddleNameAccountDetailLabel(){
        middleNameLabel = new JLabel("<html><b>Middle name: </b><html>" + databaseOptions.getMiddleName(customerNumber));
        Font labelFont = middleNameLabel.getFont().deriveFont(Font.PLAIN, 35f);
        middleNameLabel.setFont(labelFont);
        middleNameLabel.setLocation(175,475);
        middleNameLabel.setSize(600,50);
        mainFrame.getContentPane().add(middleNameLabel);
    }

    public void addLastNameAccountDetailLabel(){
        lastNameLabel = new JLabel("<html><b>Last name: </b><html>" + databaseOptions.getLastName(customerNumber));
        Font labelFont = lastNameLabel.getFont().deriveFont(Font.PLAIN, 35f);
        lastNameLabel.setFont(labelFont);
        lastNameLabel.setLocation(175,550);
        lastNameLabel.setSize(600,50);
        mainFrame.getContentPane().add(lastNameLabel);
    }

    public void addDateOfBirthAccountDetailLabel(){
        dateOfBirthLabel = new JLabel("<html><b>Date of birth: </b><html>" + databaseOptions.getDateOfBirth(customerNumber));
        Font labelFont = dateOfBirthLabel.getFont().deriveFont(Font.PLAIN, 35f);
        dateOfBirthLabel.setFont(labelFont);
        dateOfBirthLabel.setLocation(175,625);
        dateOfBirthLabel.setSize(600,50);
        mainFrame.getContentPane().add(dateOfBirthLabel);
    }

    public void addEmailAccountDetailLabel(){
        emailRegistrationLabel = new JLabel("<html><b>Email: </b><html>" + databaseOptions.getEmailAddress(customerNumber));
        Font labelFont = emailRegistrationLabel.getFont().deriveFont(Font.PLAIN, 35f);
        emailRegistrationLabel.setFont(labelFont);
        emailRegistrationLabel.setLocation(175,700);
        emailRegistrationLabel.setSize(600,50);
        mainFrame.getContentPane().add(emailRegistrationLabel);
    }

    public void addAddressAccountDetailLabel(){
        addressLabel = new JLabel("<html><b>Address: </b><html>" + databaseOptions.getAddress(customerNumber));
        Font labelFont = addressLabel.getFont().deriveFont(Font.PLAIN, 35f);
        addressLabel.setFont(labelFont);
        addressLabel.setLocation(175,775);
        addressLabel.setSize(600,50);
        mainFrame.getContentPane().add(addressLabel);
    }

    public void addCityAccountDetailLabel(){
        cityLabel = new JLabel("<html><b>City: </b><html>" + databaseOptions.getCity(customerNumber));
        Font labelFont = cityLabel.getFont().deriveFont(Font.PLAIN, 35f);
        cityLabel.setFont(labelFont);
        cityLabel.setLocation(175,850);
        cityLabel.setSize(600,50);
        mainFrame.getContentPane().add(cityLabel);
    }

//    public void addZipcodeAccountDetailLabel(){
//        zipCodeLabel = new JLabel("<html><b>Zipcode: </b><html>" + databaseOptions.getZipcode(customerNumber));
//        Font labelFont = zipCodeLabel.getFont().deriveFont(Font.PLAIN, 35f);
//        zipCodeLabel.setFont(labelFont);
//        zipCodeLabel.setLocation(800,400);
//        zipCodeLabel.setSize(600,50);
//        mainFrame.getContentPane().add(zipCodeLabel);
//    }

    public void addStateAccountDetailLabel(){
        stateLabel = new JLabel("<html><b>State: </b><html>" + databaseOptions.getState(customerNumber));
        Font labelFont = stateLabel.getFont().deriveFont(Font.PLAIN, 35f);
        stateLabel.setFont(labelFont);
        stateLabel.setLocation(800,400);
        stateLabel.setSize(600,50);
        mainFrame.getContentPane().add(stateLabel);
    }

    public void addSecurityQuestionAccountDetailLabel(){
        securityQuestionLabel = new JLabel("<html><b>Security question: </b><html>");
        Font labelFont = securityQuestionLabel.getFont().deriveFont(Font.PLAIN, 35f);
        securityQuestionLabel.setFont(labelFont);
        securityQuestionLabel.setLocation(800,475);
        securityQuestionLabel.setSize(900,50);
        mainFrame.getContentPane().add(securityQuestionLabel);
    }

    public void addSecurityQuestionAccountDetailLabel2(){
        securityQuestionLabel2 = new JLabel(databaseOptions.getSecurityQuestion(customerNumber));
        Font labelFont = securityQuestionLabel2.getFont().deriveFont(Font.PLAIN, 35f);
        securityQuestionLabel2.setFont(labelFont);
        securityQuestionLabel2.setLocation(875,525);
        securityQuestionLabel2.setSize(900,50);
        mainFrame.getContentPane().add(securityQuestionLabel2);
    }

    public void addSecurityAnswerAccountDetailLabel(){
        securityAnswerLabel = new JLabel("<html><b>Security answer: </b><html>" + databaseOptions.getSecurityAnswer(customerNumber));
        Font labelFont = securityAnswerLabel.getFont().deriveFont(Font.PLAIN, 35f);
        securityAnswerLabel.setFont(labelFont);
        securityAnswerLabel.setLocation(800,600);
        securityAnswerLabel.setSize(600,50);
        mainFrame.getContentPane().add(securityAnswerLabel);
    }

    public void addPincodeAccountDetailLabel(){
        pincodeLabel = new JLabel("<html><b>Pincode: </b><html>" + databaseOptions.getPincode(customerNumber));
        Font labelFont = pincodeLabel.getFont().deriveFont(Font.PLAIN, 35f);
        pincodeLabel.setFont(labelFont);
        pincodeLabel.setLocation(800,675);
        pincodeLabel.setSize(600,50);
        mainFrame.getContentPane().add(pincodeLabel);
    }

    public void addSSNAccountDetailLabel(){
        sSNLabel = new JLabel("<html><b>SSN: </b><html>"  + databaseOptions.getSSN(customerNumber));
        Font labelFont = sSNLabel.getFont().deriveFont(Font.PLAIN, 35f);
        sSNLabel.setFont(labelFont);
        sSNLabel.setLocation(800,750);
        sSNLabel.setSize(600,50);
        mainFrame.getContentPane().add(sSNLabel);
    }

    public void addPasswordAccountDetailLabel(){
        passwordRegistrationLabel = new JLabel("<html><b>Password: </b><html>" + databaseOptions.getPassword(customerNumber));
        Font labelFont = passwordRegistrationLabel.getFont().deriveFont(Font.PLAIN, 35f);
        passwordRegistrationLabel.setFont(labelFont);
        passwordRegistrationLabel.setLocation(800,825);
        passwordRegistrationLabel.setSize(600,50);
        mainFrame.getContentPane().add(passwordRegistrationLabel);
    }



    //Balance page components
    public void addBalancePageTitle(){
        balancePageLabel = new JLabel();
        Font labelFont = balancePageLabel.getFont().deriveFont(Font.PLAIN, 75f);
        balancePageLabel.setFont(labelFont);
        balancePageLabel.setLocation(355,200);
        balancePageLabel.setSize(1000,200);
        balancePageLabel.setText("-CURRENT BALANCE-");
        mainFrame.getContentPane().add(balancePageLabel);
    }

    public void addCheckingsBalanceLabel(){
        checkingsBalanceLabel = new JLabel();
        Font labelFont = checkingsBalanceLabel.getFont().deriveFont(Font.PLAIN, 50f);
        checkingsBalanceLabel.setFont(labelFont);
        checkingsBalanceLabel.setLocation(300,350);
        checkingsBalanceLabel.setSize(500,200);
        checkingsBalanceLabel.setText("Checking's Account: ");
        mainFrame.getContentPane().add(checkingsBalanceLabel);
    }

    public void addSavingsBalanceLabel(){
        savingsBalanceLabel = new JLabel();
        Font labelFont = savingsBalanceLabel.getFont().deriveFont(Font.PLAIN, 50f);
        savingsBalanceLabel.setFont(labelFont);
        savingsBalanceLabel.setLocation(300,450);
        savingsBalanceLabel.setSize(500,200);
        savingsBalanceLabel.setText("Saving's Account: ");
        mainFrame.getContentPane().add(savingsBalanceLabel);
    }

    public void addCheckingsBalance(){
        checkingsBalanceActual = new JLabel();
        Font labelFont = checkingsBalanceActual.getFont().deriveFont(Font.PLAIN, 50f);
        checkingsBalanceActual.setFont(labelFont);
        checkingsBalanceActual.setLocation(800,350);
        checkingsBalanceActual.setSize(200,200);
        checkingsBalanceActual.setText("<html><Text color: <font color='green'>"+"$"+databaseOptions.getCheckingsBalanceString(customerNumber)+"</html>");//return checkings balance using databaseOptions
        mainFrame.getContentPane().add(checkingsBalanceActual);
    }

    public void addSavingsBalance(){
        savingsBalanceActual = new JLabel();
        Font labelFont = savingsBalanceActual.getFont().deriveFont(Font.PLAIN, 50f);
        savingsBalanceActual.setFont(labelFont);
        savingsBalanceActual.setLocation(760,450);
        savingsBalanceActual.setSize(200,200);
        savingsBalanceActual.setText("<html><Text color: <font color='green'>"+"$"+databaseOptions.getSavingsBalanceString(customerNumber)+"</html>");//return savings balance using databaseOptions
        mainFrame.getContentPane().add(savingsBalanceActual);
    }

    //Transactions page components
    public void addTransactionsPageTitle(){
        transactionsPageLabel = new JLabel();
        Font labelFont = transactionsPageLabel.getFont().deriveFont(Font.PLAIN, 75f);
        transactionsPageLabel.setFont(labelFont);
        transactionsPageLabel.setLocation(325,200);
        transactionsPageLabel.setSize(1000,200);
        transactionsPageLabel.setText("-PAST TRANSACTIONS-");
        mainFrame.getContentPane().add(transactionsPageLabel);
    }

    public void addTransactionsTable(){
        //int rows = 10;//variable to set to however many rows there are in database to display
        //there are always 6 columns
        databaseOptions.findTransactions(customerNumber);
        transactionTableData = databaseOptions.getTransactionTableData();
        int rows = databaseOptions.getTransactionTableRows();

        //Add what i need to add above the TableModel...^
        TableModel model = new DefaultTableModel(transactionTableData, transactionTableColumns)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };

        transactionsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        scrollPane.setLocation(100,400);
        int height = (rows * 50) + 52;//the + 52 is for the header
        if(height > 900){
            height = 900;
        }
        scrollPane.setSize(1300,height);//set size according to how long the data is until it hits its max size of 1300..then you just scroll
        Font tableFont = scrollPane.getFont().deriveFont(Font.PLAIN, 35f);
        transactionsTable.getTableHeader().setFont(tableFont);
        transactionsTable.setFont(tableFont);
        transactionsTable.setFillsViewportHeight(true);
        transactionsTable.setRowHeight(50);
        transactionsTable.getTableHeader().setReorderingAllowed(false);
        scrollPane.getViewport().setBackground(Color.white);

        //transactionTableData = {{"1928369300"}};
        mainFrame.getContentPane().add(scrollPane);
    }


    //Transfer page componenets
    public void addTransferPageTitle(){
        transferPageLabel = new JLabel();
        Font labelFont = transferPageLabel.getFont().deriveFont(Font.PLAIN, 75f);
        transferPageLabel.setFont(labelFont);
        transferPageLabel.setLocation(525,200);
        transferPageLabel.setSize(700,200);
        transferPageLabel.setText("-TRANSFER-");
        mainFrame.getContentPane().add(transferPageLabel);
    }

    public void addFinalTransferButton(){
        finalTransferButton = new JButton();
        FinalTransferButtonClass ftb = new FinalTransferButtonClass();
        finalTransferButton.addActionListener(ftb);
        Font buttonFont = finalTransferButton.getFont().deriveFont(Font.PLAIN, 50f);
        finalTransferButton.setFont(buttonFont);
        finalTransferButton.setLocation(1100,1375);
        finalTransferButton.setSize(300,80);
        finalTransferButton.setText("Transfer");
        mainFrame.getContentPane().add(finalTransferButton);
    }

    public void addTransferConfirmationLabel(){
        transactionConfirmationLabel = new JLabel("<html><br>"+"Are you sure you want to transfer $"+transferAmountStringActual+"");
        Font labelFont = transactionConfirmationLabel.getFont().deriveFont(Font.PLAIN, 45f);
        transactionConfirmationLabel.setFont(labelFont);
        transactionConfirmationLabel.setForeground(Color.WHITE);
        transactionConfirmationLabel.setLocation(45,0);
        transactionConfirmationLabel.setSize(800,200);
        basicPopupFrame.getContentPane().add(transactionConfirmationLabel);
    }

    public void addYesButtonOnTransferPopup(){
        yesButtonOnTransferPopup = new JButton("Yes");
        YesButtonOnTransferPopupClass ybtp = new YesButtonOnTransferPopupClass();
        yesButtonOnTransferPopup.addActionListener(ybtp);
        Font buttonFont = yesButtonOnTransferPopup.getFont().deriveFont(Font.PLAIN, 45f);
        yesButtonOnTransferPopup.setFont(buttonFont);
        yesButtonOnTransferPopup.setLocation(500,300);
        yesButtonOnTransferPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(yesButtonOnTransferPopup);
    }

    public void addTransferAmountLabel(){
        transferAmountLabel = new JLabel();
        Font labelFont = transferAmountLabel.getFont().deriveFont(Font.PLAIN, 50f);
        transferAmountLabel.setFont(labelFont);
        transferAmountLabel.setLocation(300,350);
        transferAmountLabel.setSize(300,200);
        transferAmountLabel.setText("Amount: ");
        mainFrame.getContentPane().add(transferAmountLabel);
    }

    public void addTransferAmountTF(){
        transferAmountTF = new DoubleJTextField();
        Font textFieldFont = transferAmountTF.getFont().deriveFont(Font.PLAIN, 45f);
        transferAmountTF.setFont(textFieldFont);
        transferAmountTF.setLocation(550,425);
        transferAmountTF.setSize(500,60);
        transferAmountTF.setText("0.00");
        mainFrame.getContentPane().add(transferAmountTF);
    }

    public void addTransferFromLabel(){
        transferFromLabel = new JLabel();
        Font labelFont = transferFromLabel.getFont().deriveFont(Font.PLAIN, 50f);
        transferFromLabel.setFont(labelFont);
        transferFromLabel.setLocation(150,475);
        transferFromLabel.setSize(300,200);
        transferFromLabel.setText("From: ");
        mainFrame.getContentPane().add(transferFromLabel);
    }

    public void addTransferFromCB(){
        transferFromCB = new WideComboBox(transferSelections);
        TransferSelectionClass ts = new TransferSelectionClass();
        transferFromCB.addActionListener(ts);
        Font comboBoxFont = transferFromCB.getFont().deriveFont(Font.PLAIN, 45f);//////////////////////////////////////////////////////////
        transferFromCB.setFont(comboBoxFont);
        transferFromCB.setLocation(325,550);
        transferFromCB.setSize(325,60);
        transferFromCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(transferFromCB);
    }

    public void addTransferToLabel(){
        transferToLabel = new JLabel();
        Font labelFont = transferToLabel.getFont().deriveFont(Font.PLAIN, 50f);
        transferToLabel.setFont(labelFont);
        transferToLabel.setLocation(850,475);
        transferToLabel.setSize(300,200);
        transferToLabel.setText("To: ");
        mainFrame.getContentPane().add(transferToLabel);
    }

    public void addTransferToCB(){//SET IT SO THAT THE USER CANT CHANGE IT AND IT CHANGES ITSELF
        transferToCB = new WideComboBox(transferSelections);
        Font comboBoxFont = transferToCB.getFont().deriveFont(Font.PLAIN, 45f);
        transferToCB.setFont(comboBoxFont);
        transferToCB.setLocation(975,550);
        transferToCB.setSize(325,60);
        transferToCB.setBackground(Color.WHITE);
        mainFrame.getContentPane().add(transferToCB);
        transferToCB.setSelectedIndex(1);
        transferToCB.setEnabled(false);
    }




    /////OTHER FUNCTIONS FOR PAGES..........

    //Registration Page 1 functions
    public void saveRegistrationPage1(){
        firstName = firstNameTF.getText().toLowerCase();
        middleName = middleNameTF.getText().toLowerCase();
        lastName = lastNameTF.getText().toLowerCase();

        birthMonthString = (String) monthCB.getSelectedItem();
        birthMonth = "";
        if(birthMonthString.equals("January")){birthMonth = "01";}
        if(birthMonthString.equals("February")){birthMonth = "02";}
        if(birthMonthString.equals("March")){birthMonth = "03";}
        if(birthMonthString.equals("April")){birthMonth = "04";}
        if(birthMonthString.equals("May")){birthMonth = "05";}
        if(birthMonthString.equals("June")){birthMonth = "06";}
        if(birthMonthString.equals("July")){birthMonth = "07";}
        if(birthMonthString.equals("August")){birthMonth = "08";}
        if(birthMonthString.equals("September")){birthMonth = "09";}
        if(birthMonthString.equals("October")){birthMonth = "10";}
        if(birthMonthString.equals("November")){birthMonth = "11";}
        if(birthMonthString.equals("December")){birthMonth = "12";}

        birthDayInt = (Integer) dayCB.getSelectedItem();
        birthDay = String.valueOf(birthDayInt);
        if(birthDay.equals("1")){birthDay = "01";}
        if(birthDay.equals("2")){birthDay = "02";}
        if(birthDay.equals("3")){birthDay = "03";}
        if(birthDay.equals("4")){birthDay = "04";}
        if(birthDay.equals("5")){birthDay = "05";}
        if(birthDay.equals("6")){birthDay = "06";}
        if(birthDay.equals("7")){birthDay = "07";}
        if(birthDay.equals("8")){birthDay = "08";}
        if(birthDay.equals("9")){birthDay = "09";}

        birthYearInt = (Integer) yearCB.getSelectedItem();
        birthYear = String.valueOf(birthYearInt);

        //Getting the date and comparing it to current date
        dateOfBirthString = birthYear + "/" + birthMonth + "/" + birthDay;
        format = new SimpleDateFormat("yyyy/MM/dd");
        dateOfBirth = null;
        currentDate = null;
        try {
            dateOfBirth = format.parse(dateOfBirthString);
            currentDate = new Date();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendarBirth = new GregorianCalendar();
        calendarBirth.setTime(dateOfBirth);
        calendarCurrent = new GregorianCalendar();
        calendarCurrent.setTime(currentDate);
        daysBetween = (int) ((currentDate.getTime() - dateOfBirth.getTime()) / (1000 * 60 * 60 * 24));

        email = emailRegistrationTF.getText().toLowerCase();
        emailConfirmation = emailConfirmationRegistrationTF.getText().toLowerCase();
    }

    public static boolean checkDateOfBirth(String date) {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            format.setLenient(false);
            format.parse(date);
            format.setLenient(true);
            return true;
        } catch (ParseException e) {
            format.setLenient(true);
            return false;
        }
    }

    public void displaySavedRegistrationPage1(){
            if(firstName == null){firstName = "";}
            firstNameTF.setText(firstName);
            if(middleName == null){middleName = "";}
            middleNameTF.setText(middleName);
            if(lastName == null){lastName = "";}
            lastNameTF.setText(lastName);
            if(birthMonthString == null){birthMonthString = "January";}
            monthCB.setSelectedItem(birthMonthString);
            if(birthDayInt == null){birthDayInt = 1;}
            dayCB.setSelectedItem(birthDayInt);
            if(birthYearInt == null){birthYearInt = currentYear;}
            yearCB.setSelectedItem(birthYearInt);
            if(email == null){email = "";}
            emailRegistrationTF.setText(email);
            if(emailConfirmation == null){emailConfirmation = "";}
            emailConfirmationRegistrationTF.setText(emailConfirmation);
    }

    public boolean checkRegistrationPage1(){
        int errorCount = 0;
        if(firstName.length() == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectFirstName + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }
        if(firstName.contains(" ")){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Incorrect first name, no spaces allowed" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }
        if(firstName.length() > 45 && errorCount == 0){
            firstNameTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "First name must be less than 20 characters in length" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }
        if(middleName.length() == 0  && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectMiddleName + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(middleName.contains(" ")){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Incorrect middle name, no spaces allowed" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(middleName.length() > 45 && errorCount == 0){
            middleNameTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Middle name must be less than 20 characters in length" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(lastName.length() == 0  && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectLastName + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(lastName.contains(" ")){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Incorrect last name, no spaces allowed" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(lastName.length() > 45 && errorCount == 0){
            lastNameTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Last name must be less than 45 characters in length" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(daysBetween < 6576 && errorCount == 0){//6,570 days in 18 years
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectDateOfBirth + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 720);
            errorCount++;
        }
        if(!checkDateOfBirth(dateOfBirthString)){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Date of birth does not exist" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 720);
            errorCount++;
        }
        if(email.length() == 0 && errorCount == 0 || !validateEmail(email) && errorCount == 0){
            emailRegistrationTF.setText("");
            emailConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectEmail + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 820);
            errorCount++;
        }
        if(email.length() > 60 && errorCount == 0){
            emailRegistrationTF.setText("");
            emailConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Email is too long" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 820);
            errorCount++;
        }
        if(!email.toLowerCase().equals(emailConfirmation.toLowerCase()) && errorCount == 0){
            emailConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectEmailConfirmation + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 920);
            errorCount++;
        }
        if(!databaseOptions.checkUniqueEmail(email) && errorCount == 0){
            emailRegistrationTF.setText("");
            emailConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Email address is already in use" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 920);
            errorCount++;
        }

        if(errorCount == 0){
            return true;
        }else{
            return false;
        }
    }

    public  boolean validateEmail(String emailString) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailString);
        return matcher.find();
    }


    //Registration Page 2 functions
    public void saveRegistrationPage2() {
        address = addressTF.getText().toLowerCase();
        partsOfAddress = address.split(" ");
        city = cityTF.getText().toLowerCase();
        zipCode = zipCodeTF.getText();
        state = (String) stateCB.getSelectedItem();//cant be saved as lower case because displaying it once saved wont work
        securityQuestion = (String) securityQuestionCB.getSelectedItem();//cant be saved as lower case because displaying it once saved wont work
        securityAnswer = securityAnswerTF.getText().toLowerCase();
    }

    public void displaySavedRegistrationPage2(){
            if(address == null){address = "";}
            addressTF.setText(address);
            if(city == null){city = "";}
            cityTF.setText(city);
            if(zipCode == null){zipCode = "";}
            zipCodeTF.setText(zipCode);
            if(state == null){state = "Alabama";}
            stateCB.setSelectedItem(state);
            if(securityQuestion == null){securityQuestion = "What was the color of your first car?";}
            securityQuestionCB.setSelectedItem(securityQuestion);
            if(securityAnswer == null){securityAnswer = "";}
            securityAnswerTF.setText(securityAnswer);
    }

    public boolean checkRegistrationPage2(){
        int errorCount = 0;
        URLReader reader = new URLReader();
        String stateURLString = state.replaceAll("\\s+","%20");

        if(address.length() == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + blankAddress + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }
        if(address.startsWith(" ") && errorCount == 0){
            addressTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + invalidAddressString + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }
        if(partsOfAddress.length == 3) {
            webAddress = "https://maps.googleapis.com/maps/api/geocode/json?address=" + partsOfAddress[0] +"+"+ partsOfAddress[1] +"+"+ partsOfAddress[2] +"+"+ /*stateURLString +*/ "&key=AIzaSyAe0eXlz5iO-a9a8ASu72-eeIfPIMUEj6w";
            if (!reader.addressChecker(webAddress) && errorCount == 0) {//4243+Chab+Drive+CA&key
                addressTF.setText("");
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + invalidAddressString + "</font><html>");
                incorrectRegistrationPageLabel.setLocation(800, 980);
                incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
                incorrectRegistrationPageAsterisk.setLocation(170, 420);
                errorCount++;
            }
        }else if(partsOfAddress.length == 4){
            webAddress = "https://maps.googleapis.com/maps/api/geocode/json?address=" + partsOfAddress[0] +"+"+ partsOfAddress[1] +"+"+ partsOfAddress[2] +"+"+ partsOfAddress[3] +"+"+ /*stateURLString +*/ "&key=AIzaSyAe0eXlz5iO-a9a8ASu72-eeIfPIMUEj6w";
            if (!reader.addressChecker(webAddress) && errorCount == 0) {//4243+Chab+Drive+CA&key
                addressTF.setText("");
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + invalidAddressString + "</font><html>");
                incorrectRegistrationPageLabel.setLocation(800, 980);
                incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
                incorrectRegistrationPageAsterisk.setLocation(170, 420);
                errorCount++;
            }
        }else if(partsOfAddress.length == 5){
            webAddress = "https://maps.googleapis.com/maps/api/geocode/json?address=" + partsOfAddress[0] +"+"+ partsOfAddress[1] +"+"+ partsOfAddress[2] +"+"+ partsOfAddress[3] +"+"+ partsOfAddress[4] +"+"+ /*stateURLString +*/ "&key=AIzaSyAe0eXlz5iO-a9a8ASu72-eeIfPIMUEj6w";
            if (!reader.addressChecker(webAddress) && errorCount == 0) {//4243+Chab+Drive+CA&key
                addressTF.setText("");
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + invalidAddressString + "</font><html>");
                incorrectRegistrationPageLabel.setLocation(800, 980);
                incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
                incorrectRegistrationPageAsterisk.setLocation(170, 420);
                errorCount++;
            }
        }else if(partsOfAddress.length == 6) {
            webAddress = "https://maps.googleapis.com/maps/api/geocode/json?address=" + partsOfAddress[0] + "+" + partsOfAddress[1] + "+" + partsOfAddress[2] + "+" + partsOfAddress[3] + "+" + partsOfAddress[4] + "+" + partsOfAddress[5] + "+" + /*stateURLString +*/ "&key=AIzaSyAe0eXlz5iO-a9a8ASu72-eeIfPIMUEj6w";
            if (!reader.addressChecker(webAddress) && errorCount == 0) {//4243+Chab+Drive+CA&key
                addressTF.setText("");
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + invalidAddressString + "</font><html>");
                incorrectRegistrationPageLabel.setLocation(800, 980);
                incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
                incorrectRegistrationPageAsterisk.setLocation(170, 420);
                errorCount++;
            }
        }else if(partsOfAddress.length > 6 && errorCount == 0 || partsOfAddress.length < 3 && partsOfAddress.length != 0 && errorCount == 0){
            addressTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + invalidAddressString + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800, 980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }

        if(city.length() == 0  && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + blankCity + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(!reader.cityChecker(city) && errorCount == 0){
            cityTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectCity + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(zipCode.length() == 0 && errorCount == 0){
            zipCodeTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + blankZipCode + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(zipCode.length() < 5 && zipCode.length() != 0 && errorCount == 0){
            zipCodeTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + shortZipCode + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(!reader.zipCodeChecker(zipCode) && errorCount == 0){
            zipCodeTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectZipCode + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(securityAnswer.length() == 0  && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectSecurityAnswer + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 920);
            errorCount++;
        }
        if(securityAnswer.length() > 80  && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Security answer is too long" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 920);
            errorCount++;
        }
        if(securityAnswer.startsWith(" ") && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Don't start answer with a space" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,980);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 920);
            errorCount++;
        }

        if(errorCount == 0){
            return true;
        }else{
            return false;
        }
    }

    //Registration Page 3 functions
    public void saveRegistrationPage3(){
        pincode = pincodeTF.getText();
        if(pincode.equals("")){pincode="1234";}//any number....it cant be empty
        pincodeInt = Integer.valueOf(pincode);//(String)
        ssnPart1 = sSNTF1.getText();
        ssnPart2 = sSNTF2.getText();
        ssnPart3 = sSNTF3.getText();
        ssn =  ssnPart1 + "-" + ssnPart2 + "-" + ssnPart3;
        password = passwordRegistrationTF.getText();
        passwordConfirmation = passwordConfirmationRegistrationTF.getText();
        if(emailAgreementCheckBox.isSelected()){
            emailAgreementCheckBoxStatus = true;
        }else{
            emailAgreementCheckBoxStatus = false;
        }
    }

    public void displaySavedRegistrationPage3(){
            if(pincode == null){pincode = "";}
            pincodeTF.setText(pincode);
            if(ssnPart1 == null){ssnPart1 = "";}
            sSNTF1.setText(ssnPart1);
            if(ssnPart2 == null){ssnPart2 = "";}
            sSNTF2.setText(ssnPart2);
            if(ssnPart3 == null){ssnPart3 = "";}
            sSNTF3.setText(ssnPart3);
            if(password == null){password = "";}
            passwordRegistrationTF.setText(password);
            if(passwordConfirmation == null){passwordConfirmation = "";}
            passwordConfirmationRegistrationTF.setText(passwordConfirmation);
            if(emailAgreementCheckBoxStatus){emailAgreementCheckBox.setSelected(true);}
            if(!emailAgreementCheckBoxStatus){emailAgreementCheckBox.setSelected(false);}
    }

    public boolean checkRegistrationPage3(){
        int errorCount = 0;

        if(pincode.length() < 4){
            pincodeTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectPincode + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 420);
            errorCount++;
        }
        if(ssnPart1.length() < 3 && errorCount == 0 || ssnPart2.length() < 2 && errorCount == 0 || ssnPart3.length() < 4 && errorCount == 0){
            sSNTF1.setText("");
            sSNTF2.setText("");
            sSNTF3.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectSSN + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(!databaseOptions.checkUniqueSSN(ssn) && errorCount == 0){
            sSNTF1.setText("");
            sSNTF2.setText("");
            sSNTF3.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "This SSN is already in use" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 520);
            errorCount++;
        }
        if(password.length() < 6 && errorCount == 0){
            passwordRegistrationTF.setText("");
            passwordConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectPassword + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(password.length() > 45 && errorCount == 0){
            passwordRegistrationTF.setText("");
            passwordConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Password is too long" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(password.contains(" ") && errorCount == 0){
            passwordRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Password cannot contain spaces" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 620);
            errorCount++;
        }
        if(!passwordConfirmation.equals(password) && errorCount == 0){
            passwordConfirmationRegistrationTF.setText("");
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + incorrectPasswordConfirmation + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(170, 720);
            errorCount++;
        }
        if(!emailAgreementCheckBox.isSelected() && errorCount == 0){
            incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Must agree to receive emails from Pacific Bank" + "</font><html>");
            incorrectRegistrationPageLabel.setLocation(800,780);
            incorrectRegistrationPageAsterisk.setText("<html><Text color: <font color='red'>" + "*" + "</font><html>");
            incorrectRegistrationPageAsterisk.setLocation(325, 975);
            errorCount++;
        }

        if(errorCount == 0){
            return true;
        }else{
            return false;
        }
    }


    //BUTTONS AND POPUP COMPONENTS
    public void addNoButtonOnExitPopup(){
        noButtonOnExitPopup = new JButton("No");
        NoOnExitPageClass nb = new NoOnExitPageClass();
        noButtonOnExitPopup.addActionListener(nb);
        Font buttonFont = noButtonOnExitPopup.getFont().deriveFont(Font.PLAIN, 45f);
        noButtonOnExitPopup.setFont(buttonFont);
        noButtonOnExitPopup.setLocation(200,300);
        noButtonOnExitPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(noButtonOnExitPopup);
    }

    public void addYesButtonOnExitPopup(){
        yesButtonOnExitPopup = new JButton("Yes");
        YesOnExitPageClass yb = new YesOnExitPageClass();
        yesButtonOnExitPopup.addActionListener(yb);
        Font buttonFont = yesButtonOnExitPopup.getFont().deriveFont(Font.PLAIN, 45f);
        yesButtonOnExitPopup.setFont(buttonFont);
        yesButtonOnExitPopup.setLocation(500,300);
        yesButtonOnExitPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(yesButtonOnExitPopup);
    }

    public void addExitLabel(){
        exitLabel = new JLabel("<html><div style='text-align: center;'>"+exitConfirmation+"</div></html>");
        Font labelFont = exitLabel.getFont().deriveFont(Font.PLAIN, 50f);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setFont(labelFont);
        exitLabel.setLocation(100,25);
        exitLabel.setSize(700,200);
        basicPopupFrame.getContentPane().add(exitLabel);
    }

    public void addNoButtonOnExitRegistrationPopup(){
        noButtonOnExitRegistrationPopup = new JButton("No");
        NoOnExitPageClass nb = new NoOnExitPageClass();//USING SAME BUTTON ACTION AS EXIT POPUP
        noButtonOnExitRegistrationPopup.addActionListener(nb);
        Font buttonFont = noButtonOnExitRegistrationPopup.getFont().deriveFont(Font.PLAIN, 45f);
        noButtonOnExitRegistrationPopup.setFont(buttonFont);
        noButtonOnExitRegistrationPopup.setLocation(200,300);
        noButtonOnExitRegistrationPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(noButtonOnExitRegistrationPopup);
    }

    public void addYesButtonOnExitRegistrationPopup(){
        yesButtonOnExitRegistrationPopup = new JButton("Yes");
        YesOnExitRegistrationPageClass yb = new YesOnExitRegistrationPageClass();
        yesButtonOnExitRegistrationPopup.addActionListener(yb);
        Font buttonFont = yesButtonOnExitRegistrationPopup.getFont().deriveFont(Font.PLAIN, 45f);
        yesButtonOnExitRegistrationPopup.setFont(buttonFont);
        yesButtonOnExitRegistrationPopup.setLocation(500,300);
        yesButtonOnExitRegistrationPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(yesButtonOnExitRegistrationPopup);
    }

    public void addExitRegistrationLabel(){
        exitLabel = new JLabel("<html><div style='text-align: center;'>"+exitRegistrationConfirmation+"</div></html>");
        Font labelFont = exitLabel.getFont().deriveFont(Font.PLAIN, 50f);
        exitLabel.setFont(labelFont);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setLocation(0,25);
        exitLabel.setSize(900,200);
        basicPopupFrame.getContentPane().add(exitLabel);
    }

    public void addFinishRegistrationPopupLabel(){
        finishRegistrationPopupLabel = new JLabel("<html><div style='text-align: center;'>"+ finishRegistrationLabel + email +"</div></html>");
        Font labelFont = finishRegistrationPopupLabel.getFont().deriveFont(Font.PLAIN, 35f);
        finishRegistrationPopupLabel.setFont(labelFont);
        finishRegistrationPopupLabel.setForeground(Color.WHITE);
        finishRegistrationPopupLabel.setLocation(0,0);
        finishRegistrationPopupLabel.setSize(900,110);
        basicPopupFrame.getContentPane().add(finishRegistrationPopupLabel);
    }

    public void addRegisterCodeTF(){
        registerCodeTF = new JTextField();
        Font textFieldFont = registerCodeTF.getFont().deriveFont(Font.PLAIN, 40f);
        registerCodeTF.setFont(textFieldFont);
        registerCodeTF.setLocation(75,150);
        registerCodeTF.setSize(300,60);
        DocumentFilter filter = new UppercaseDocumentFilter();
        ((AbstractDocument) registerCodeTF.getDocument()).setDocumentFilter(filter);
        basicPopupFrame.getContentPane().add(registerCodeTF);
    }

    public void addQuitRegistrationButton(){
        quitRegistrationButton = new JButton("Quit Registration");
        QuitRegistrationButtonClass qrb = new QuitRegistrationButtonClass();
        quitRegistrationButton.addActionListener(qrb);
        Font buttonFont = quitRegistrationButton.getFont().deriveFont(Font.PLAIN, 40f);
        quitRegistrationButton.setFont(buttonFont);
        quitRegistrationButton.setLocation(475,250);
        quitRegistrationButton.setSize(375,60);
        basicPopupFrame.getContentPane().add(quitRegistrationButton);
    }

    public void addCreateAccountButton(){
        createAccountButton = new JButton("Create Account");
        CreateAccountButtonClass cab = new CreateAccountButtonClass();
        createAccountButton.addActionListener(cab);
        Font buttonFont = createAccountButton.getFont().deriveFont(Font.PLAIN, 40f);
        createAccountButton.setFont(buttonFont);
        createAccountButton.setLocation(475,150);
        createAccountButton.setSize(375,60);
        basicPopupFrame.getContentPane().add(createAccountButton);
    }

    public void addNoOnExitRegistrationPopupButton2(){
        noButtonOnExitRegistrationPopup = new JButton("No");
        NoOnExitRegistrationPopupClass noer = new NoOnExitRegistrationPopupClass();//USING SAME BUTTON ACTION AS EXIT POPUP
        noButtonOnExitRegistrationPopup.addActionListener(noer);
        Font buttonFont = noButtonOnExitRegistrationPopup.getFont().deriveFont(Font.PLAIN, 45f);
        noButtonOnExitRegistrationPopup.setFont(buttonFont);
        noButtonOnExitRegistrationPopup.setLocation(200,300);
        noButtonOnExitRegistrationPopup.setSize(200,80);
        basicPopupFrame.getContentPane().add(noButtonOnExitRegistrationPopup);
    }

    public void addLogoutLabel(){
        logoutLabel = new JLabel("<html><div style='text-align: center;'>"+ logoutConfirmationString +"</div></html>");
        Font labelFont = logoutLabel.getFont().deriveFont(Font.PLAIN, 50f);
        logoutLabel.setFont(labelFont);
        logoutLabel.setForeground(Color.WHITE);
        logoutLabel.setLocation(70,15);
        logoutLabel.setSize(900,200);
        basicPopupFrame.getContentPane().add(logoutLabel);
    }

    public void addNoOnLogoutPopupButton(){
        noOnLogoutPopupButton = new JButton("No");
        NoOnLogoutPopupButtonClass nolp = new NoOnLogoutPopupButtonClass();//USING SAME BUTTON ACTION AS EXIT POPUP
        noOnLogoutPopupButton.addActionListener(nolp);
        Font buttonFont = noOnLogoutPopupButton.getFont().deriveFont(Font.PLAIN, 45f);
        noOnLogoutPopupButton.setFont(buttonFont);
        noOnLogoutPopupButton.setLocation(200,300);
        noOnLogoutPopupButton.setSize(200,80);
        basicPopupFrame.getContentPane().add(noOnLogoutPopupButton);
    }

    public void addYesOnLogoutPopupButton(){
        yesOnLogoutPopupButton = new JButton("Yes");
        YesOnLogoutPopupButtonClass yolp = new YesOnLogoutPopupButtonClass();
        yesOnLogoutPopupButton.addActionListener(yolp);
        Font buttonFont = yesOnLogoutPopupButton.getFont().deriveFont(Font.PLAIN, 45f);
        yesOnLogoutPopupButton.setFont(buttonFont);
        yesOnLogoutPopupButton.setLocation(500,300);
        yesOnLogoutPopupButton.setSize(200,80);
        basicPopupFrame.getContentPane().add(yesOnLogoutPopupButton);
    }




    //Exit button popup buttons
    public class ExitButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent eb) {
            if(currentPageButtons.equals("Basic Buttons")) {
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                nextButton.setEnabled(false);
            }else if(currentPageButtons.equals("Opening Page Buttons")){//ADD THESE ON AS I GO------------------------------------------------
                exitButton.setEnabled(false);
                openingRegisterButton.setEnabled(false);
                openingLoginButton.setEnabled(false);
            }else if(currentPageButtons.equals("Login Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                loginButton.setEnabled(false);
            }else if(currentPageButtons.equals("Registration Page 4 Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                finishRegistrationButton.setEnabled(false);
            }else if(currentPageButtons.equals("Main Bank Buttons")){
                exitButton.setEnabled(false);
                logoutButton.setEnabled(false);
                depositButton.setEnabled(false);
                withdrawButton.setEnabled(false);
                accountDetailsButton.setEnabled(false);
                balanceButton.setEnabled(false);
                transactionsButton.setEnabled(false);
                transferButton.setEnabled(false);
            }else if(currentPageButtons.equals("Deposit Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                depositCheckingsButton.setEnabled(false);
                depositSavingsButton.setEnabled(false);
                finalDepositButton.setEnabled(false);
            }else if(currentPageButtons.equals("Withdraw Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                depositCheckingsButton.setEnabled(false);
                depositSavingsButton.setEnabled(false);
                finalWithdrawButton.setEnabled(false);
                depositAmountTF.setEnabled(false);
            }else if(currentPageButtons.equals("Account Details Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
            }else if(currentPageButtons.equals("Balance Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
            }else if(currentPageButtons.equals("Transactions Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
            }else if(currentPageButtons.equals("Transfer Page Buttons")){
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                finalTransferButton.setEnabled(false);
            }

            popups.createExitPopup();
        }
    }

    public class NoOnExitPageClass implements ActionListener{
        public void actionPerformed(ActionEvent nb){
            if(currentPageButtons.equals("Basic Buttons")) {
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
                nextButton.setEnabled(true);
            }else if(currentPageButtons.equals("Opening Page Buttons")){//ADD THESE ON AS I GO------------------------------------------------
                openingRegisterButton.setEnabled(true);
                openingLoginButton.setEnabled(true);
                exitButton.setEnabled(true);
            }else if(currentPageButtons.equals("Login Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
                loginButton.setEnabled(true);
            }else if(currentPageButtons.equals("Registration Page 4 Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
                finishRegistrationButton.setEnabled(true);
            }else if(currentPageButtons.equals("Main Bank Buttons")){
                exitButton.setEnabled(true);
                logoutButton.setEnabled(true);
                depositButton.setEnabled(true);
                withdrawButton.setEnabled(true);
                accountDetailsButton.setEnabled(true);
                balanceButton.setEnabled(true);
                transactionsButton.setEnabled(true);
                transferButton.setEnabled(true);
            }else if(currentPageButtons.equals("")){

            }else if(currentPageButtons.equals("Deposit Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
                depositCheckingsButton.setEnabled(true);
                depositSavingsButton.setEnabled(true);
                finalDepositButton.setEnabled(true);
                depositAmountTF.setEnabled(true);
            }else if(currentPageButtons.equals("Withdraw Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
                depositCheckingsButton.setEnabled(true);
                depositSavingsButton.setEnabled(true);
                finalWithdrawButton.setEnabled(true);
                depositAmountTF.setEnabled(true);
            }else if(currentPageButtons.equals("Account Details Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
            }else if(currentPageButtons.equals("Balance Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
            }else if(currentPageButtons.equals("Transactions Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
            }else if(currentPageButtons.equals("Transfer Page Buttons")){
                exitButton.setEnabled(true);
                previousButton.setEnabled(true);
                finalTransferButton.setEnabled(true);
            }

            basicPopupFrame.dispose();
        }
    }

    public class NoOnExitRegistrationPopupClass implements ActionListener{
        public void actionPerformed(ActionEvent noer){
            basicPopupFrame.dispose();
            popups.createFinishRegistrationPopup();
        }
    }

    public class YesOnExitPageClass implements ActionListener{
        public void actionPerformed(ActionEvent yb) {
            if(customerNumber != 0){//if customer number doesnt = 0 then someone is signed in.
                exitLabel.setLocation(275,50);
                exitLabel.setSize(700,100);
                yesButtonOnExitPopup.setEnabled(false);
                noButtonOnExitPopup.setEnabled(false);
                Runnable r = new Runnable() {
                    int count = 0;
                    @Override
                    public void run() {
                        count++;
                        if(count == 1) {exitLabel.setText("<html><div style='text-align: center;'>Logging out.</div></html>");}
                        if(count == 2) {exitLabel.setText("<html><div style='text-align: center;'>Logging out..</div></html>");}
                        if(count == 3) {exitLabel.setText("<html><div style='text-align: center;'>Logging out...</div></html>");}
                        if(count == 4) {exitLabel.setText("<html><div style='text-align: center;'>Logging out....</div></html>");}
                        if(count == 5) {
                            System.exit(0);
                        }
                    }
                };
                TimerFunctions timerFunction = new TimerFunctions();
                ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                timerFunction.beginDelayedTimer(r, 5, 1, TimeUnit.SECONDS, executor);
            }else {
                System.exit(0);
            }

        }
    }

    //Popup button actions for quiting registration process
    public class YesOnExitRegistrationPageClass implements ActionListener{
        public void actionPerformed(ActionEvent yb) {
            deleteRegistrationInformation();
            basicPopupFrame.dispose();
            Pages.createOpeningPage(PageOptions.this);
        }
    }


    public class OpeningRegisterButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent orb){
            Pages.createRegistrationPage1(PageOptions.this);
            displaySavedRegistrationPage1();
        }
    }

    public class OpeningLoginButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent olb){
            Pages.createLoginPage(PageOptions.this);
        }
    }

    public class PreviousButtonClass implements ActionListener {
        public void actionPerformed(ActionEvent pb){
            exitTransferLoop = 1;//to exit the while loop when a button is pressed
            if (currentPage.equals("Login Page")){
                Pages.createOpeningPage(PageOptions.this);
                }
            else if(currentPage.equals("Registration Page 1") || currentPage.equals("Login Page")){//CREATE POPUP FRAME ASKING IF THEY WANT TO EXIT REGISTRATION;}
                Popups popupMenu = new Popups(PageOptions.this);
                popupMenu.createExitRegistrationPopup();
                previousButton.setEnabled(false);
                exitButton.setEnabled(false);
                nextButton.setEnabled(false);
            }
            else if(currentPage.equals("Registration Page 2")){
                saveRegistrationPage2();
                Pages.createRegistrationPage1(PageOptions.this);
                displaySavedRegistrationPage1();
            }
            else if(currentPage.equals("Registration Page 3")){
                saveRegistrationPage3();
                Pages.createRegistrationPage2(PageOptions.this);
                displaySavedRegistrationPage2();
            }
            else if(currentPage.equals("Registration Page 4")){
                Pages.createRegistrationPage3(PageOptions.this);
                displaySavedRegistrationPage3();
            }
            else if(currentPage.equals("Deposit Page")){
                depositAccountSelection = "";
                depositAmount = 0.00;
                Pages.createMainBankPage(PageOptions.this);
                //mainFrame.repaint();
            }
            else if(currentPage.equals("Withdraw Page")){
                depositAccountSelection = "";
                depositAmount = 0.00;
                Pages.createMainBankPage(PageOptions.this);
                //mainFrame.repaint();
            }else if(currentPage.equals("Transfer Page")){
                transferAmountString = "";
                transferAmount = 0;
                Pages.createMainBankPage(PageOptions.this);
            }
            else if(currentPage.equals("Account Details Page") || currentPage.equals("Balance Page") || currentPage.equals("Transactions Page")){
                Pages.createMainBankPage(PageOptions.this);
                //mainFrame.repaint();
            }

        }
    }

    public class NextButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent nb){
            while(true) {
                if (currentPage.equals("Registration Page 1")) {
                    saveRegistrationPage1();
                    if (checkRegistrationPage1()) {
                        Pages.createRegistrationPage2(PageOptions.this);
                        displaySavedRegistrationPage2();
                        break;
                    }else{break;}
                }
                if (currentPage.equals("Registration Page 2")) {
                    saveRegistrationPage2();
                    if (checkRegistrationPage2()) {
                        Pages.createRegistrationPage3(PageOptions.this);
                        displaySavedRegistrationPage3();
                        break;
                    }else{break;}
                }
                if (currentPage.equals("Registration Page 3")) {
                    saveRegistrationPage3();
                    if (checkRegistrationPage3()) {
                        Pages.createRegistrationPage4(PageOptions.this);
                        break;
                    }else{break;}
                }
            }
        }
    }

    public class FinishRegistrationButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent frb){
            exitButton.setEnabled(false);
            previousButton.setEnabled(false);
            finishRegistrationButton.setEnabled(false);
            //Send email with random code
            AlphaNumericCodeGenerator rCode = new AlphaNumericCodeGenerator();
            registerCode = rCode.nextSessionId().toUpperCase();
            //send email
            //MailOptions mailOptions = new MailOptions();
            mailOptions.sendRegistrationCode("Welcome to Pacific Bank, " + firstName + " " + lastName, registrationCodeEmailMessage1 + registerCode, email);//last parameter is who it is sent to

            popups.createFinishRegistrationPopup();
        }
    }

    public class QuitRegistrationButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent yb) {
            incorrectRegistrationPopupLabel.setText("");
            //basicPopupFrame.dispatchEvent(new WindowEvent(basicPopupFrame, WindowEvent.WINDOW_CLOSING));
            basicPopupFrame.dispose();
            popups.createExitRegistrationPopup2();
        }
    }

    public class CreateAccountButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent cab) {
            if(registerCodeTF.getText().equals(registerCode)){
                incorrectRegistrationPopupLabel.setText("");
                createAccountButton.setEnabled(false);
                quitRegistrationButton.setEnabled(false);
                Runnable r = new Runnable() {
                    int count = 0;
                    @Override
                    public void run() {
                        count++;
                        if(count == 1) {correctRegistrationPopupLabel.setText("<html><Text color: <font color='white'>" + "Creating Account." + "</font><html>");}
                        if(count == 2) {correctRegistrationPopupLabel.setText("<html><Text color: <font color='white'>" + "Creating Account.." + "</font><html>");}
                        if(count == 3) {correctRegistrationPopupLabel.setText("<html><Text color: <font color='white'>" + "Creating Account..." + "</font><html>");}
                        if(count == 4) {
                            basicPopupFrame.dispose();
                            Pages.createOpeningPage(PageOptions.this);
                            mainFrame.repaint();//not sure why i have to do this but if i dont, the text fields dont show up unless i click the empty space where they are supposed to be
                        }
                    }
                };
                TimerFunctions timerFunction = new TimerFunctions();
                ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                timerFunction.beginDelayedTimer(r, 4, 1, TimeUnit.SECONDS, executor);
                databaseOptions.createAccount(firstName, middleName, lastName, dateOfBirthString, email, address, city, state, securityQuestion, securityAnswer, pincodeInt, ssn, password);
                deleteRegistrationInformation();
            }else{
                registerCodeTF.setText("");
                incorrectRegistrationPopupLabel.setText("<html><Text color: <font color='red'>" + "Incorrect Code" + "</font><html>");
                //Incorrect message
            }
        }
    }

    public class LoginButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent lb){
            //CHECK DATABASE FOR CORRECT EMAIL AND PASSWORD
            String email = emailLoginTF.getText();
            String password = passwordLoginTF.getText();
            if(databaseOptions.checkLogin(email, password, customerNumber)){//if email/pass is correct
                customerNumber = databaseOptions.getCustomerNumber();
                //customerNumber is set to whoever's email/pass combo was correct.
                //Logging in animation label on screen and popup
                incorrectEmailOrPasswordLabel.setText("");
                emailLoginTF.setEnabled(false);
                passwordLoginTF.setEnabled(false);
                successfulLoginLabel.setText("<html><Text color: <font color='green'>" + successfulLoginString + "</font><html>");
                previousButton.setEnabled(false);
                exitButton.setEnabled(false);
                loginButton.setEnabled(false);
                //TimerFunctions timerFunction = new TimerFunctions();
                //timerFunction.beginLoginTimer(successfulLoginLabel, previousButton, exitButton, loginButton);

                Runnable r = new Runnable() {
                    int count = 0;
                    @Override
                    public void run() {
                        count++;
                        if(count == 1) {successfulLoginLabel.setText("<html><Text color: <font color='green'>Successful Login.</html>");}
                        if(count == 2) {successfulLoginLabel.setText("<html><Text color: <font color='green'>Successful Login..</html>");}
                        if(count == 3) {successfulLoginLabel.setText("<html><Text color: <font color='green'>Successful Login...</html>");}
                        if(count == 4) {successfulLoginLabel.setText("<html><Text color: <font color='green'>Successful Login....</html>");}
                        if(count == 5) {
                            Pages.createMainBankPage(PageOptions.this);
                            mainFrame.repaint();
                        }
                    }
                };
                TimerFunctions timerFunction = new TimerFunctions();
                ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                timerFunction.beginDelayedTimer(r, 5, 1, TimeUnit.SECONDS, executor);

            }else{
                emailLoginTF.setText("");
                passwordLoginTF.setText("");
                incorrectEmailOrPasswordLabel.setText("<html><Text color: <font color='red'>" + incorrectEmailOrPasswordString + "</font><html>");
            }
        }
    }

    public class LogoutButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent lb){
            popups.createLogoutPopup();
            logoutButton.setEnabled(false);
            exitButton.setEnabled(false);
            depositButton.setEnabled(false);
            withdrawButton.setEnabled(false);
            accountDetailsButton.setEnabled(false);
            balanceButton.setEnabled(false);
            transactionsButton.setEnabled(false);
            transferButton.setEnabled(false);
        }
    }

    public class NoOnLogoutPopupButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent nolb){
            basicPopupFrame.dispose();
            logoutButton.setEnabled(true);
            exitButton.setEnabled(true);
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            accountDetailsButton.setEnabled(true);
            balanceButton.setEnabled(true);
            transactionsButton.setEnabled(true);
            transferButton.setEnabled(true);
        }
    }

    public class YesOnLogoutPopupButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent yolb){
            customerNumber = 0;
            logoutLabel.setLocation(275,50);
            logoutLabel.setSize(700,100);
            noOnLogoutPopupButton.setEnabled(false);
            yesOnLogoutPopupButton.setEnabled(false);
            Runnable r = new Runnable() {
                int count = 0;
                @Override
                public void run() {
                    count++;
                    if(count == 1) {logoutLabel.setText("<html><div style='text-align: center;'>Logging out.</div></html>");}
                    if(count == 2) {logoutLabel.setText("<html><div style='text-align: center;'>Logging out..</div></html>");}
                    if(count == 3) {logoutLabel.setText("<html><div style='text-align: center;'>Logging out...</div></html>");}
                    if(count == 4) {logoutLabel.setText("<html><div style='text-align: center;'>Logging out....</div></html>");}
                    if(count == 5) {
                        basicPopupFrame.dispose();
                        Pages.createOpeningPage(PageOptions.this);
                        mainFrame.repaint();
                    }
                }
            };
            TimerFunctions timerFunction = new TimerFunctions();
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            timerFunction.beginDelayedTimer(r, 5, 1, TimeUnit.SECONDS, executor);
        }
    }

    public class DepositButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent db){
            Pages.createDepositPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class FinalDepositButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent fdb) {
            String depositAmountString = depositAmountTF.getText();//original string
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            if(!depositAmountString.equals("")){
                depositAmount = Double.parseDouble(depositAmountString);//original double created from the original string
                String tempVar = decimalFormat.format(depositAmount);
                depositAmount = Double.parseDouble(tempVar);//new double that only has 2 numbers after the decimal
                depositAmountStringActual = String.valueOf(depositAmount);//new string that only has 2 numbers after the decimal
                if(depositAmountStringActual.matches("[0-9]+\\.[0-9]")){//If the balance matches any # followed by a decimal point followed by any # followed by a white space
                    depositAmountStringActual = depositAmountStringActual + "0";//adding extra 0 at the end for looks
                }
            }

            if (depositAmountString.equals("") || depositAmount == 0) {
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Must enter an amount" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            } else if(depositAccountSelection.equals("")){
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Must select an account" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            }else if (depositAmountStringActual.length() > 16) {//16 is actually 12 since the string can add E6 or E2 etc.
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Too many digits" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            } else {
                incorrectRegistrationPageLabel.setText("");
                depositAmountTF.setEnabled(false);
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                depositCheckingsButton.setEnabled(false);
                depositSavingsButton.setEnabled(false);
                finalDepositButton.setEnabled(false);
                popups.createDepositConfirmationPopup();
                //mainFrame.repaint();
            }
        }
    }

    public class FinalWithdrawButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent fwb) {
            String withdrawAmountString = depositAmountTF.getText();//original string
            //depositAmountStringActual = "anythingButNothing";
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            if(!withdrawAmountString.equals("")){
                depositAmount = Double.parseDouble(withdrawAmountString);//original double created from the original string
                String tempVar = decimalFormat.format(depositAmount);
                depositAmount = Double.parseDouble(tempVar);//new double that only has 2 numbers after the decimal
                depositAmountStringActual = String.valueOf(depositAmount);//new string that only has 2 numbers after the decimal
                if(depositAmountStringActual.matches("[0-9]+\\.[0-9]")){//If the balance matches any # followed by a decimal point followed by any # followed by a white space
                    depositAmountStringActual = depositAmountStringActual + "0";//adding extra 0 at the end for looks
                }
            }

            if (withdrawAmountString.equals("") || depositAmount == 0) {
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Must enter an amount" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            } else if(depositAccountSelection.equals("")){
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Must select an account" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            }else if (depositAmountStringActual.length() > 16) {//16 is actually 12 since the string can add E6 or E2 etc.
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Too many digits" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            } else if(!databaseOptions.checkWithdrawAmount(depositAmount, customerNumber, depositType)){
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Not enough funds" + "</font><html>");//just using same incorrect label from registration
                incorrectRegistrationPageLabel.setLocation(600,765);
            } else {
                incorrectRegistrationPageLabel.setText("");
                depositAmountTF.setEnabled(false);
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                depositCheckingsButton.setEnabled(false);
                depositSavingsButton.setEnabled(false);
                finalWithdrawButton.setEnabled(false);
                popups.createWithdrawConfirmationPopup();
                //mainFrame.repaint();
            }
        }
    }


    public class DepositCheckingsButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent dcb){
            checkingsBalance = databaseOptions.getCheckingsBalance(customerNumber);
//            String checkingsBalanceString = Double.toString(checkingsBalance);
            currentBalanceDepositLabel.setText("<html>" + "Current Balance: " + "<Text color: <font color='green'>" +"$"+ databaseOptions.getCheckingsBalanceString(customerNumber) + "</font></html>");
            depositAccountSelection = "checkings";
            depositType = "c";
            //mainFrame.repaint();
        }
    }

    public class DepositSavingsButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent dcb){
            savingsBalance = databaseOptions.getSavingsBalance(customerNumber);
//            String savingsBalanceString = Double.toString(savingsBalance);
            currentBalanceDepositLabel.setText("<html>" + "Current Balance: " + "<Text color: <font color='green'>" +"$"+ databaseOptions.getSavingsBalanceString(customerNumber) + "</font></html>");
            depositAccountSelection = "savings";
            depositType = "s";
            //mainFrame.repaint();
        }
    }

    public class YesOnDepositPopupClass implements ActionListener{
        public void actionPerformed(ActionEvent yodb){

            //maybe say success and wait, and then take them back to the main bank page

            if(depositAccountSelection.equals("checkings")) {
                databaseOptions.deposit(depositAmount, checkingsBalance, "c", customerNumber);
            }
            if(depositAccountSelection.equals("savings")) {
                databaseOptions.deposit(depositAmount, savingsBalance, "s", customerNumber);
            }
            basicPopupFrame.dispose();

            depositAccountSelection = "";
            depositAmount = 0.00;

            Pages.createMainBankPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class YesOnWithdrawPopupClass implements ActionListener{
        public void actionPerformed(ActionEvent yowb){

            //maybe say success and wait, and then take them back to the main bank page

            if(depositAccountSelection.equals("checkings")) {
                databaseOptions.withdraw(depositAmount, checkingsBalance, "c", customerNumber);//deposit amount is same as withdraw amount
            }
            if(depositAccountSelection.equals("savings")) {
                databaseOptions.withdraw(depositAmount, savingsBalance, "s", customerNumber);
            }
            basicPopupFrame.dispose();

            depositAccountSelection = "";
            depositAmount = 0.00;

            Pages.createMainBankPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class WithdrawButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent wb){
            Pages.createWithdrawPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class AccountDetailsButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent wb){
            Pages.createAccountDetailsPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class BalanceButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent wb){
            Pages.createBalancePage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class TransactionsButtonClass implements ActionListener{
        public void actionPerformed(ActionEvent wb){
            Pages.createTransactionsPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }

    public class TransferButtonClass implements ActionListener {
        public void actionPerformed(ActionEvent wb) {

            Pages.createTransferPage(PageOptions.this);

        }
    }
    public class TransferSelectionClass implements ActionListener {
        public void actionPerformed(ActionEvent ts) {
            String selection = (String) transferFromCB.getSelectedItem();
            if(selection.equals("Checkings")){
                transferToCB.setSelectedIndex(1);
            } else{
                transferToCB.setSelectedIndex(0);
            }
        }
    }


    public class FinalTransferButtonClass implements ActionListener {
        public void actionPerformed(ActionEvent wb) {
//            transferAmountString = transferAmountTF.getText();
//            transferAmount = Double.parseDouble(transferAmountString);
            transferAmountString = transferAmountTF.getText();//original string
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            if (!transferAmountString.equals("")) {
                transferAmount = Double.parseDouble(transferAmountString);//original double created from the original string
                String tempVar = decimalFormat.format(transferAmount);
                transferAmount = Double.parseDouble(tempVar);//new double that only has 2 numbers after the decimal
                transferAmountStringActual = String.valueOf(transferAmount);//new string that only has 2 numbers after the decimal
                if (transferAmountStringActual.matches("[0-9]+\\.[0-9]")) {//If the balance matches any # followed by a decimal point followed by any # followed by a white space
                    transferAmountStringActual = transferAmountStringActual + "0";//adding extra 0 at the end for looks
                }
            }

            if (transferAmountString == "" || transferAmount == 0) {
                incorrectRegistrationPageLabel.setText("<html><Text color: <font color='red'>" + "Must enter an amount" + "</font><html>");
                incorrectRegistrationPageLabel.setLocation(550, 462);
            } else {
                exitButton.setEnabled(false);
                previousButton.setEnabled(false);
                finalTransferButton.setEnabled(false);

                popups.createTransferConfirmationPopup();
            }
        }
    }


    public class YesButtonOnTransferPopupClass implements ActionListener {
        public void actionPerformed(ActionEvent wb) {
            //actually transfer the money
            transferAmountString = transferAmountTF.getText();
            transferAmount = Double.parseDouble(transferAmountString);
            String from = (String) transferFromCB.getSelectedItem();
            String to = (String) transferToCB.getSelectedItem();

            databaseOptions.transfer(customerNumber,transferAmount,from, to);
            //reset the temporary amounts and selections as done in deposit/withdraw functions
            basicPopupFrame.dispose();
            Pages.createMainBankPage(PageOptions.this);
            //mainFrame.repaint();
        }
    }
}

