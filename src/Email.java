import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * Suraj Sharma
 * Id # 109606910.
 * HomeWork 5 - Hashing.
 * CSE 214.
 *
 *
 *This class  contains the following information about each email.
 * It contains the following instance variables.
 * In order for the program to be able to save Email objects to a file,
 * all objects in it are serializeable, so this class implements Serializable.
 *
 * private String to.
 * private String cc
 * private String bcc
 * private String subject
 * private String body
 * private GregorianCalendar timestamp
 *
 */
public class Email implements Serializable {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     *
     * @param to
     * The String literal which stores the “to” field.
     *
     * @param cc
     * The String literal which stores the “cc” field.
     *
     * @param bcc
     * The String literal which stores the “bcc” field.
     *
     * @param subject
     * The String literal which stores the “subject” field.
     *
     * @param body
     * The String literal which stores all of the text in the email’s body.
     *
     * @param timestamp
     *  Represents the time that this email was created.
     *
     * This is the constructor used to make the email object.
     * Since, the default constructor was never used hence, I didn't create one.
     *
     */
    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timestamp) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    /**
     * The accessor method for the variable to
     * @return
     * the value of to.
     */
    public String getTo() {
        return to;
    }

    /**
     * The Accessor method for the variable to.
     *
     * @param to
     * Sets the value of variable to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * The accessor method for the variable cc
     * @return
     * the value of cc.
     */
    public String getCc() {
        return cc;
    }

    /**
     * The Accessor method for the variable cc.
     *
     * @param cc
     * Sets the value of variable cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * The accessor method for the variable bcc
     * @return
     * the value of bcc.
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * The Accessor method for the variable bcc.
     *
     * @param bcc
     * Sets the value of variable bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * The accessor method for the variable subject
     * @return
     * the value of subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * The Accessor method for the variable subject.
     *
     * @param subject
     * Sets the value of variable subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * The accessor method for the variable body
     * @return
     * the value of body.
     */
    public String getBody() {
        return body;
    }

    /**
     * The Accessor method for the variable body.
     *
     * @param body
     * Sets the value of variable body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /*
     * The accessor method for the variable timeStamp
     * @return
     * the value of timestamp.
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * The Accessor method for the variable timestamp.
     *
     * @param timestamp
     * Sets the value of variable timestamp
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    public boolean equals(Email e) {
        return (this.to.equalsIgnoreCase(e.to) && this.cc.equalsIgnoreCase(e.cc)
                && this.bcc.equalsIgnoreCase(e.bcc) && this.subject.equalsIgnoreCase(e.subject)
                && this.body.equalsIgnoreCase(e.body) && this.timestamp.compareTo(e.getTimestamp()) == 0);
    }


    /**
     * This method is just a basic representation of the object which will be diplayed.
     * Note: This is not the detailed description of the object.
     *
     * @return
     * It returns the string representation of the object email.
     *
     */
    @Override
    public String toString() {
        return (String.format("%-45s%-10s", this.timestamp.getTime(), this.subject));
    }

    /**
     * This method is used to print the detailed information that the email contains.
     */
    public void printContent() {
        System.out.println("The content of the email is as follows: ");
        System.out.println("This e-mail was sent on " + timestamp.getTime());
        System.out.println("To: " + to);
        System.out.println("CC: " + cc);
        System.out.println("BCC: " + bcc);
        System.out.println("Subject: " + subject);
        System.out.println("Message: "+body);

    }

}
