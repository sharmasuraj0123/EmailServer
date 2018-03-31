import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * /**
 * Suraj Sharma
 * Id # 109606910.
 * HomeWork 5 - Hashing.
 * CSE 214.
 *
 * This class represents an email folder and
 * handles all of the logic for adding and removing emails.
 * In order for the program to be able to save Email objects to a file,
 * all objects in it are serializeable, so this class implements Serializable.
 *
 * It contains the following data values:
 *  private ArrayList<Email> emails
 *  private String name
 *  private String currentSortingMethod
 */
public class Folder implements Serializable {
    private ArrayList<Email> emails;
    private String name;
    private String currentSortingMethod;

    /**
     * This is the constructor used to create the Folder Object.
     * Since, the default constructor is never used,
     * I did not create it.
     *
     * @param name
     * Stores the name of the folder.
     */
    public Folder(String name) {
        this.name = name;
        currentSortingMethod = "date";
        emails = new ArrayList<Email>();
    }

    /**
     * The  mutator method for the variable.
     * @return
     * Stores all of the emails contained in this folder.
     *
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * The  mutator method for the variable.
     * @return
     * Stores the name of the folder.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * The accessor method for the variable name
     * @param name
     * Sets the name to this value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The  mutator method for the variable.
     * @return
     * Stores the current sorting method (however that you see fit)
     * so that emails added can be properly sorted
     * without having to first resort the folder.
     * Notes: Default is date descending.
     *
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * Adds an email to the folder according to the current sorting method.
     * @param email
     * Add this email to inbox.
     */
    public void addEmail(Email email) {
        if (emails.isEmpty()) {
            emails.add(email);
            return;
        }

        if (currentSortingMethod.equalsIgnoreCase("DateA")) {
            for (int i = 0; i < emails.size(); i++)
                if (emails.get(i).getTimestamp().compareTo(email.getTimestamp()) >= 0) {
                    emails.add(i, email);
                    return;
                }
        } else if (currentSortingMethod.equalsIgnoreCase("SubjectA")) {
            for (int i = 0; i < emails.size(); i++)
                if (emails.get(i).getSubject().compareTo(email.getSubject()) >= 0) {
                    emails.add(i, email);
                    return;
                }
        } else if (currentSortingMethod.equalsIgnoreCase("SubjectD")) {
            for (int i = 0; i < emails.size(); i++)
                if (emails.get(i).getSubject().compareTo(email.getSubject()) <= 0) {
                    emails.add(i, email);
                    return;
                }

        } else {
            for (int i = 0; i < emails.size(); i++)
                if (emails.get(i).getTimestamp().compareTo(email.getTimestamp()) <= 0) {
                    emails.add(i, email);
                    return;
                }
        }
        emails.add(email);
    }

    /**
     * Removes an email from the folder by index.
     *
     * @param index
     * The index at which email exists in the list.
     *
     * @return
     * Returns the email object after removing it from the list.
     */
    public Email removeEmail(int index) {
        return emails.remove(index);
    }

    /**
     *  Sorts the emails alphabetically by subject in ascending order.
     *
     */
    public void sortBySubjectAscending() {
        if (emails.isEmpty()) {
            System.out.println("It is an empty List");
        } else {
            currentSortingMethod = "SubjectA";
            Collections.sort(emails, new SubjectComparator());
        }
    }

    /**
     * Sorts the emails alphabetically by subject in descending order.
     */
    public void sortBySubjectDescending() {
        if (emails.isEmpty()){
            System.out.println("It is an empty List");
        }
        else {
            currentSortingMethod = "SubjectD";
            Collections.sort(emails, new SubjectComparatorD());
        }
    }

    /**
     * Sorts the emails by date in ascending order.
     */
    public void sortByDateAscending() {
        if (emails.isEmpty()){
            System.out.println("It is an empty List");
        }
        else {
            currentSortingMethod = "DateA";
            Collections.sort(emails, new DateComparatorA());
        }
    }

    /**
     * Sorts the emails by date in descending order.
     */
    public void sortByDateDescending() {
        if (emails.isEmpty()){
            System.out.println("It is an empty List");
        }
        else {
            currentSortingMethod = "DateD";
            Collections.sort(emails, new DateComparatorD());
        }
    }

    /**
     * It is a custom method created to find an email in the list.
     *
     * @param email
     * The email you are looking for.
     *
     * @return
     * The email with the specific index.
     *
     */
    public Email findEmail(Email email) {
        for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).equals(email)) {
               return removeEmail(i);
            }
        }

        return null;
    }

    /**
     * Prints the list of all the emails in the list.
     */
    public void printFolder() {

        System.out.println(String.format("%-17s%-35s%-10s", "Index", "Time", "Subject"));

        for (int i = 0; i < emails.size(); i++) {
            System.out.printf("%-7d %-55s", i + 1, emails.get(i).toString());
            System.out.println();
        }
    }
}
