import java.util.Comparator;

/**
 * /**
 * Suraj Sharma
 * Id # 109606910.
 * HomeWork 5 - Hashing.
 * CSE 214.
 *
 * This collection of classes is just used to create the compareTo methods
 * The most of the code is provided in the homeWork
 * description and is accordingly altered as needed.
 *
 */
public class SubjectComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;
        return (e1.getSubject().compareTo(e2.getSubject()));
    }
}
class SubjectComparatorD implements Comparator {

    public int compare(Object o1, Object o2) {
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;
        return (e2.getSubject().compareTo(e1.getSubject()));
    }
}

class DateComparatorA implements Comparator {

    public int compare(Object o1, Object o2) {
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;
        return (e1.getTimestamp().compareTo(e2.getTimestamp()));
    }
}
class DateComparatorD implements Comparator {

    public int compare(Object o1, Object o2) {
        Email e1 = (Email) o1;
        Email e2 = (Email) o2;
        return (e2.getTimestamp().compareTo(e1.getTimestamp()));
    }
}



