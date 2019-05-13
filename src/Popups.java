public class Popups {
    private PageOptions pageOptions;
    public Popups(PageOptions pageOptions) {
        this.pageOptions = pageOptions;
    }

    public void createExitPopup() {
        pageOptions.setupBasicPopup("Exit");
        pageOptions.addNoButtonOnExitPopup();
        pageOptions.addYesButtonOnExitPopup();
        pageOptions.addExitLabel();
    }

    public void createExitRegistrationPopup() {
        pageOptions.setupBasicPopup("Exit Registration");
        pageOptions.addNoButtonOnExitRegistrationPopup();
        pageOptions.addYesButtonOnExitRegistrationPopup();
        pageOptions.addExitRegistrationLabel();
    }

    public void createFinishRegistrationPopup() {
        pageOptions.setupBasicPopup("Finish Registration");
        pageOptions.addFinishRegistrationPopupLabel();
        pageOptions.addRegisterCodeTF();
        pageOptions.addQuitRegistrationButton();
        pageOptions.addCreateAccountButton();
        pageOptions.addIncorrectRegistrationPopupLabel();
        pageOptions.addCorrectRegistrationPopupLabel();
    }

    public void createExitRegistrationPopup2() {
        pageOptions.setupBasicPopup("Exit Registration");
        pageOptions.addNoOnExitRegistrationPopupButton2();
        pageOptions.addYesButtonOnExitRegistrationPopup();
        pageOptions.addExitRegistrationLabel();
    }

    public void createLogoutPopup() {
        pageOptions.setupBasicPopup("Logout");
        pageOptions.addLogoutLabel();
        pageOptions.addNoOnLogoutPopupButton();
        pageOptions.addYesOnLogoutPopupButton();
    }

    public void createDepositConfirmationPopup(){
        pageOptions.setupBasicPopup("Deposit");
        pageOptions.addDepositConfirmationLabel();
        pageOptions.addNoButtonOnExitPopup();
        pageOptions.addYesButtonOnDepositPopup();
    }

    public void createWithdrawConfirmationPopup(){
        pageOptions.setupBasicPopup("Withdraw");
        pageOptions.addWithdrawConfirmationLabel();
        pageOptions.addNoButtonOnExitPopup();
        pageOptions.addYesButtonOnWithdrawPopup();
    }

    public void createTransferConfirmationPopup(){
        pageOptions.setupBasicPopup("Transfer");
        pageOptions.addTransferConfirmationLabel();
        pageOptions.addNoButtonOnExitPopup();
        pageOptions.addYesButtonOnTransferPopup();
    }
}
