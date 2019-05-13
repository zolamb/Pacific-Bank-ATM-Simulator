public class Pages{
    public static void createOpeningPage(PageOptions pageOptions){
        pageOptions.currentPage = "Opening Page";
        pageOptions.currentPageButtons = "Opening Page Buttons";//exit
        pageOptions.removeContents();
        PageOptions.addGreeting(pageOptions);
        pageOptions.addOpeningRegisterButton();
        PageOptions.addOpeningLoginButton(pageOptions);
        pageOptions.addExitButton();
    }

    public static void createRegistrationPage1(PageOptions pageOptions){
        pageOptions.currentPage = "Registration Page 1";
        pageOptions.currentPageButtons = "Basic Buttons";//prev, exit, next
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addNextButton();
        pageOptions.addRegistrationPageTitle();
        pageOptions.addFirstNameLabel();
        pageOptions.addFirstNameTF();
        pageOptions.addMiddleNameLabel();
        pageOptions.addMiddleNameTF();
        pageOptions.addLastNameLabel();
        pageOptions.addLastNameTF();
        pageOptions.addDateOfBirthLabel();
        pageOptions.addDateOfBirthCBs();
        pageOptions.addEmailRegistrationLabel();
        pageOptions.addEmailRegistrationTF();
        pageOptions.addEmailConfirmationRegistrationLabel();
        pageOptions.addEmailConfirmationRegistrationTF();
        pageOptions.addIncorrectRegistrationPageLabel();
        pageOptions.addIncorrectRegistrationPageAsterisk();
    }


    public static void createRegistrationPage2(PageOptions pageOptions){
        pageOptions.currentPage = "Registration Page 2";
        pageOptions.currentPageButtons = "Basic Buttons";
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addNextButton();
        pageOptions.addRegistrationPageTitle();
        pageOptions.addAddressLabel();
        pageOptions.addAddressTF();
        pageOptions.addCityLabel();
        pageOptions.addCityTF();
        pageOptions.addZipCodeLabel();
        pageOptions.addZipCodeTF();
        pageOptions.addStateLabel();
        pageOptions.addStateCB();
        pageOptions.addSecurityQuestionLabel();
        pageOptions.addSecurityQuestionCB();
        pageOptions.addSecurityAnswerLabel();
        pageOptions.addSecurityAnswerTF();
        pageOptions.addIncorrectRegistrationPageLabel();
        pageOptions.addIncorrectRegistrationPageAsterisk();
    }

    public static void createRegistrationPage3(PageOptions pageOptions){
        pageOptions.currentPage = "Registration Page 3";
        pageOptions.currentPageButtons = "Basic Buttons";
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addNextButton();
        pageOptions.addRegistrationPageTitle();
        pageOptions.addPincodeLabel();
        pageOptions.addPincodeTF();//4 numbers
        pageOptions.addSSNLabel();
        pageOptions.addSSNTF1();//3 numbers
        pageOptions.addSSNTF2();//2 numbers
        pageOptions.addSSNTF3();//4 numbers
        pageOptions.addSSNDashes();
        pageOptions.addPasswordRegistrationLabel();
        pageOptions.addPasswordRegistrationTF();
        pageOptions.addPasswordConfirmationRegistrationLabel();
        pageOptions.addPasswordConfirmationRegistrationTF();
        pageOptions.addEmailAgreementCheckBox();
        pageOptions.addIncorrectRegistrationPageLabel();
        pageOptions.addIncorrectRegistrationPageAsterisk();
    }

    public static void createRegistrationPage4(PageOptions pageOptions){
        pageOptions.currentPage = "Registration Page 4";
        pageOptions.currentPageButtons = "Registration Page 4 Buttons";//prev, exit, finish
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addFinishRegistrationButton();
        pageOptions.addRegistrationPageTitle();
        pageOptions.addFirstNameConfirmationLabel();
        pageOptions.addMiddleNameConfirmationLabel();
        pageOptions.addLastNameConfirmationLabel();
        pageOptions.addDateOfBirthConfirmationLabel();
        pageOptions.addEmailConfirmationLabel();
        pageOptions.addAddressConfirmationLabel();
        pageOptions.addCityConfirmationLabel();
        pageOptions.addZipcodeConfirmationLabel();
        pageOptions.addStateConfirmationLabel();
        pageOptions.addSecurityQuestionConfirmationLabel();
        pageOptions.addSecurityQuestionConfirmationLabel2();
        pageOptions.addSecurityAnswerConfirmationLabel();
        pageOptions.addPincodeConfirmationLabel();
        pageOptions.addSSNConfirmationLabel();
        pageOptions.addPasswordConfirmationLabel();
    }

    public static void createLoginPage(PageOptions pageOptions){
        pageOptions.currentPage = "Login Page";
        pageOptions.currentPageButtons = "Login Page Buttons";//prev, exit, login
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addLoginButton();
        pageOptions.addLoginLabel();
        pageOptions.addEmailLoginLabel();
        pageOptions.addPasswordLoginLabel();
        pageOptions.addEmailLoginTF();
        pageOptions.addPasswordLoginTF();
        pageOptions.addIncorrectEmailOrPasswordLabel();
        pageOptions.addSuccessfulLoginLabel();
    }

    public static void createMainBankPage(PageOptions pageOptions){
        pageOptions.currentPage = "Main Bank";
        pageOptions.currentPageButtons = "Main Bank Buttons";//logout, exit
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addLogoutButton();
        pageOptions.addMainBankTitle();
        pageOptions.addDepositButton();
        pageOptions.addWithdrawButton();
        pageOptions.addAccountDetailsButton();
        pageOptions.addBalanceButton();
        pageOptions.addTransactionsButton();
        pageOptions.addTransferButton();
    }

    public static void createDepositPage(PageOptions pageOptions){
        pageOptions.currentPage = "Deposit Page";
        pageOptions.currentPageButtons = "Deposit Page Buttons";//prev, exit, deposit
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addDepositPageTitle();
        pageOptions.addDepositCheckingsButton();
        pageOptions.addDepositSavingsButton();
        pageOptions.addCurrentBalanceDepositLabel();
        pageOptions.addDepositAmountLabel();
        pageOptions.addDepositAmountTF();
        pageOptions.addFinalDepositButton();
        pageOptions.addIncorrectRegistrationPageLabel();
    }

    public static void createWithdrawPage(PageOptions pageOptions){
        pageOptions.currentPage = "Withdraw Page";
        pageOptions.currentPageButtons = "Withdraw Page Buttons";//prev, exit, withdraw
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addWithdrawPageTitle();
        pageOptions.addDepositCheckingsButton();//same for withdraw
        pageOptions.addDepositSavingsButton();//same for withdraw
        pageOptions.addCurrentBalanceDepositLabel();//same for withdraw
        pageOptions.addWithdrawAmountLabel();
        pageOptions.addDepositAmountTF();//same for withdraw
        pageOptions.addFinalWithdrawButton();
        pageOptions.addIncorrectRegistrationPageLabel();
    }

    public static void createAccountDetailsPage(PageOptions pageOptions){
        pageOptions.currentPage = "Account Details Page";
        pageOptions.currentPageButtons = "Account Details Page Buttons";//prev, exit, withdraw
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addAccountDetailsPageTitle();
        //may need more than one page to display info...
        pageOptions.addFirstNameAccountDetailLabel();
        pageOptions.addMiddleNameAccountDetailLabel();
        pageOptions.addLastNameAccountDetailLabel();
        pageOptions.addDateOfBirthAccountDetailLabel();
        pageOptions.addEmailAccountDetailLabel();
        pageOptions.addAddressAccountDetailLabel();
        pageOptions.addCityAccountDetailLabel();
        //pageOptions.addZipcodeAccountDetailLabel();
        pageOptions.addStateAccountDetailLabel();
        pageOptions.addSecurityQuestionAccountDetailLabel();
        pageOptions.addSecurityQuestionAccountDetailLabel2();
        pageOptions.addSecurityAnswerAccountDetailLabel();
        pageOptions.addPincodeAccountDetailLabel();
        pageOptions.addSSNAccountDetailLabel();
        pageOptions.addPasswordAccountDetailLabel();
        //Change Password? (at bottom right)
    }

    public static void createBalancePage(PageOptions pageOptions){
        pageOptions.currentPage = "Balance Page";
        pageOptions.currentPageButtons = "Balance Page Buttons";//prev, exit, withdraw
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addBalancePageTitle();
        pageOptions.addCheckingsBalanceLabel();
        pageOptions.addSavingsBalanceLabel();
        pageOptions.addCheckingsBalance();
        pageOptions.addSavingsBalance();
    }

    public static void createTransactionsPage(PageOptions pageOptions){
        pageOptions.currentPage = "Transactions Page";
        pageOptions.currentPageButtons = "Transactions Page Buttons";//prev, exit, withdraw
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addTransactionsPageTitle();
        pageOptions.addTransactionsTable();
    }

    public static void createTransferPage(PageOptions pageOptions){
        pageOptions.currentPage = "Transfer Page";
        pageOptions.currentPageButtons = "Transfer Page Buttons";//prev, exit, withdraw
        pageOptions.removeContents();
        pageOptions.addExitButton();
        pageOptions.addPreviousButton();
        pageOptions.addTransferPageTitle();
        pageOptions.addFinalTransferButton();
        pageOptions.addTransferAmountLabel();
        pageOptions.addTransferAmountTF();
        pageOptions.addTransferFromLabel();
        pageOptions.addTransferFromCB();
        pageOptions.addTransferToLabel();
        pageOptions.addTransferToCB();
        pageOptions.addIncorrectRegistrationPageLabel();
    }
}
