import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * /**
 * Suraj Sharma
 * Id # 109606910.
 * HomeWork 5 - Hashing.
 * CSE 214.
 *
 * This class contains the mailbox and the main method to run the program.
 *
 * It contains the following data values:
 *   private Folder inbox
 *      Stores the inbox,
 *      which is a special folder which should never be allowed
 *      or to be deleted or renamed. All new emails go here.
 *
 *   private Folder trash
 *     Stores the trash,
 *     which is a special folder which should
 *     never be allowed to be deleted or renamed.
 *     When an email is deleted, it is moved here.
 *
 *   private ArrayList<Folder> folders
 *      Stores all of the custom folders contained in the mailbox.
 *
 *   public static Mailbox mailbox
 *      Stores the main mailbox that will used by the application.
 *      This mailbox should be instantiated in the main method.
 */
public class Mailbox implements Serializable {
    private Folder inbox;
    private Folder trash;
    private ArrayList<Folder> folders;
    public static Mailbox mailbox;

    /**
     * The default construtor for the class.
     */
    public Mailbox() {
        inbox = new Folder("Inbox");
        trash = new Folder("Trash");
        folders = new ArrayList<Folder>();
    }

    /**
     * Adds the given folder to the list of custom folders.
     * Note: check to make sure a folder with that given name
     * does not already exist. If it does, return an error in some manner.
     * @param folder
     * Adds this folder to the list of folders.
     */
    public void addFolder(Folder folder) {
        for (int i = 0; i < folders.size(); i++)
            if (folders.get(i).getName().equalsIgnoreCase(folder.getName()))
                throw new IllegalArgumentException("The name is already taken");

        folders.add(folder);
    }

    /**
     * Removes the given folder from the list of custom folders
     *
     * @param name
     * The name of the folder you want to remove.
     *
     */
    public void deleteFolder(String name) {
        for (int i = 0; i < folders.size(); i++)
            if (folders.get(i).getName().equalsIgnoreCase(name)) {
                folders.remove(i);
                break;
            }
    }

    /**
     * Gets user input on the contents of the email and adds it to the inbox.
     */
    public void composeEmail() {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter recipient (To): ");
        String to = s.nextLine();
        System.out.print("Enter carbon copy recipients (CC): ");
        String cc = s.nextLine();
        System.out.print("Enter blind carbon copy recipients (BCC): ");
        String bcc = s.nextLine();
        System.out.print("Enter subject line: ");
        String subject = s.nextLine();
        System.out.print("Enter body: ");
        String body = s.nextLine();

        Date d2 = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d2);

        Email newEmail = new Email(to,cc,bcc,subject, body ,cal);
        mailbox.inbox.addEmail(newEmail);
        
    }

    /**
     * Moves the email to the trash.
     * @param email
     * The email that has to be removed.
     *
     * @throws EmailNotFoundException
     * If the email is not found.
     */
    public void deleteEmail(Email email) throws EmailNotFoundException {
            moveEmail(email, trash);
    }

    /**
     * Removes all emails from the trash folder.
     */
    public void clearTrash() {
        int count = 0;
        while (!trash.getEmails().isEmpty()) {
            trash.removeEmail(count);
            count++;
        }
    }

    /**
     * Takes the given email and puts in in the given folder.
     * If the folder cannot be found, instead move it to the Inbox.
     * @param email
     * The emial to be moved.
     * @param target
     * The new foler it has to be moved to.
     * @throws EmailNotFoundException
     * If the email is not found.
     */
    public void moveEmail(Email email, Folder target) throws EmailNotFoundException {

        Email temp = inbox.findEmail(email);

        if (temp == null)
            temp = trash.findEmail(email);

        int count = 0;
        while (temp == null && count < folders.size()) {
            temp = folders.get(count).findEmail(email);
            count++;
        }

        if (temp == null)
            throw new EmailNotFoundException("Email you are Looking for is not found!");

        for (int i = 0; i < folders.size(); i++)
            if (folders.get(i).getName().equalsIgnoreCase(target.getName())) {
                folders.get(i).addEmail(email);
                return;
            }

        if (target.getName().equalsIgnoreCase(trash.getName()))
            trash.addEmail(email);
        else
            inbox.addEmail(email);
    }

    /**
     * Returns a folder by folder name.
     * @param name
     * The name of the folder you are looking for
     * @return
     * The desired folder.
     */
    public Folder getFolder(String name) {
        if (name.equalsIgnoreCase(inbox.getName()))
            return inbox;
        else if (name.equalsIgnoreCase(trash.getName()))
            return trash;

        else {
            for (int i = 0; i < folders.size(); i++)
                if (folders.get(i).getName().equalsIgnoreCase(name))
                    return folders.get(i);

            return null;
        }

    }

    public static void main(String[] args) {
        mailbox = new Mailbox();
        try {
            try {
                //If file is found, open it
                FileInputStream file = new FileInputStream("mailbox.obj");
                ObjectInputStream fin = new ObjectInputStream(file);
                mailbox = (Mailbox) fin.readObject();
                file.close();
            } catch (IOException | ClassNotFoundException a) {
                System.out.println(a.getMessage());
            }


            Scanner s = new Scanner(System.in);
            String input = "";
            String answer = "";


            while (!input.equalsIgnoreCase("Q")) {

                System.out.println("MAILBOX: ");
                System.out.println(mailbox.inbox.getName());
                System.out.println(mailbox.trash.getName());
                for (int i = 0; i < mailbox.folders.size(); i++)
                    System.out.println(mailbox.folders.get(i).getName());


                System.out.println("Menu Options: ");
                System.out.println("A – Add folder");
                System.out.println("R – Remove folder");
                System.out.println("C – Compose email");
                System.out.println("F – View Folder");
                System.out.println("I – View Inbox");
                System.out.println("T – View Trash");
                System.out.println("E – Empty Trash");
                System.out.println("Q - Quit the program");

                System.out.print("Your Choice: ");
                input = s.next();


                if (input.equalsIgnoreCase("a")) {
                    System.out.print("Please Enter the name of your new Folder: ");
                    answer = s.next();
                    Folder folder = new Folder(answer);
                    mailbox.addFolder(folder);
                } else if (input.equalsIgnoreCase("r")) {
                    System.out.print("Please Enter the name of the folder you want to remove: ");
                    answer = s.next();
                    mailbox.deleteFolder(answer);

                } else if (input.equalsIgnoreCase("c")) {
                    mailbox.composeEmail();

                } else if (input.equalsIgnoreCase("f") || input.equalsIgnoreCase("i")
                        || input.equalsIgnoreCase("t")) {
                    Folder folder;
                    String subInput = "";
                    if (input.equalsIgnoreCase("i"))
                        folder = mailbox.inbox;

                    else if (input.equalsIgnoreCase("t"))
                        folder = mailbox.trash;

                    else {
                        folder = null;

                        if(mailbox.folders.isEmpty()){
                            System.out.println("There are no folders to display");
                            subInput = "r";
                        }
                        else {
                            while (folder == null) {
                                System.out.print("Please Enter the name of the folder you want to view: ");
                                answer = s.next();
                                folder = mailbox.getFolder(answer);

                                if (folder == null)
                                    System.out.println("Invalid input! Please Enter the NAME from the following list.");
                            }
                        }
                    }
                    while (!subInput.equalsIgnoreCase("r")) {
                        if(folder!=null)
                        folder.printFolder();
                        System.out.println();

                        System.out.println("M – Move email");
                        System.out.println("D – Delete email");
                        System.out.println("V – View email contents");
                        System.out.println("SA – Sort by subject ascending");
                        System.out.println("SD – Sort by subject descending");
                        System.out.println("DA – Sort by date ascending");
                        System.out.println("DD – Sort by date descending");
                        System.out.println("R – Return to main menu");
                        System.out.print("Your Choice: ");
                        subInput = s.next();

                        if (subInput.equalsIgnoreCase("M")) {
                            System.out.print("Please Enter the number of the E-mail you want to move: ");
                            int response = s.nextInt();
                            System.out.println("Please Enter the target Folder: ");
                            String target = s.next();
                            Folder targetFolder = mailbox.getFolder(target);

                            if(!(folder.getEmails().isEmpty())&& folder.getEmails().size()>=response)
                            mailbox.moveEmail(folder.getEmails().get(response), targetFolder);
                            else{
                                System.out.println("Please Check your Input and try again!");
                            }


                        } else if (subInput.equalsIgnoreCase("D")) {
                            System.out.print("Please Enter the number of the E-mail you want to remove: ");
                            int response = s.nextInt();

                            if(!(folder.getEmails().isEmpty())&& folder.getEmails().size()>=response)
                            mailbox.deleteEmail(folder.getEmails().get(response - 1));
                            else{
                                System.out.println("Please Check your Input and try again!");
                            }

                        } else if (subInput.equalsIgnoreCase("V")) {
                            System.out.print("Please Enter the number of the E-mail you want to view: ");
                            int response = s.nextInt();

                            if(!(folder.getEmails().isEmpty())&& folder.getEmails().size()>=response)
                            folder.getEmails().get(response-1).printContent();
                            else{
                                System.out.println("Please Check your Input and try again!");
                            }

                        } else if (subInput.equalsIgnoreCase("SA")) {
                            folder.sortBySubjectAscending();
                        } else if (subInput.equalsIgnoreCase("SD")) {
                            folder.sortBySubjectDescending();
                        } else if (subInput.equalsIgnoreCase("DA")) {
                            folder.sortByDateAscending();
                        } else if (subInput.equalsIgnoreCase("DD")) {
                            folder.sortByDateDescending();
                        } else if (subInput.equalsIgnoreCase("R")) {

                        } else {
                            System.out.println("Invalid Input! Please Check your input and try again");
                        }

                    }

                } else if (input.equalsIgnoreCase("e")) {
                    mailbox.clearTrash();

                } else if (input.equalsIgnoreCase("q")) {

                }else {
                    System.out.println("Invalid Input! please Check your input and try again");
                }
                System.out.println();
                    try {
                        FileOutputStream file = new FileOutputStream("mailbox.obj");
                        ObjectOutputStream fout = null;
                        try {
                            fout = new ObjectOutputStream(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                            fout.writeObject(mailbox);
                            fout.close();
                    } catch (IOException a) { /*Error*/ }
            }
        } catch (EmailNotFoundException e) {

        }
    }

}
