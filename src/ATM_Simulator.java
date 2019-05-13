/**
 * Created by ZLamb on 1/21/2017.
 * Finished on 4/12/2017
 *
 * EXTRA IDEAS:
 *      Add green check mark images to the registration pages for fields that have been entered in correctly
 *      Change the red asterisk to a red X symbol but it basically does the same thing (shows where the error is).
 *      Add hyperlink label to resend email with registration code
 *      Add black asterisks for login password textfield
 *      On the 'main bank' page, for first names that are longer than 7 letters, every letter after 7 will shift the 'Welcome, ' label a set amount to the left to keep it all centered
 *      For transactions, add a time column to be able to get the exact date AND time... not just the date.
 *      When you press enter in either the password or email field, it starts the login process.
 *      For Past transactions table, add a view for in the past month, year, week...etc.
 *      For past transactions table, change height of row based on how long the description is
 *      Add the ability to be able to hit 'enter' while logging in or registering to automatically hit the next/submit/login button (easier)
 */
import javax.swing.*;
public class ATM_Simulator{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                PageOptions pageOptions = new PageOptions();
                pageOptions.setupBasicFrame();
                Pages.createOpeningPage(pageOptions);
            }
        });
    }
}
//NEED A CHECK FOR IF THE DEPOSIT AMOUNT WILL MAKE THE DATABASE BALANCE GO BEYOND THE MAX 12 DIGITS LENGTH
//set a specific width for transaction table columns
//how will i use security questions(i need to allow for change of password)
