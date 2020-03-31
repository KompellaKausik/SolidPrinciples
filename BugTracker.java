package SolidPrinciples;

import java.util.ArrayList;
import java.util.List;


interface IDeveloper{

    public void checkBugReport(BugDetails b);
    public void fixProblem(BugDetails b);
    public void closeTicket(BugDetails b);

}


interface IfrontEndDeveloper {

    public void correctUI(BugDetails b);
    public void checkUI(BugDetails b);

}

interface ImiddlewareDeveloper{

    public void correctErrors(BugDetails b);
    public void checkFix(BugDetails b);

}

interface IbackendDeveloper {

    public void correctDataInconsistency(BugDetails b);
    public void checkData(BugDetails b);

}

class BugDetails{

    private int id;
    private String description;
    private String productVersion;
    private String bugClassification;
    private String status;
    BugDetails(int id, String description, String productVersion,String bugClassification,String status){
        this.id = id;
        this.description = description;
        this.productVersion = productVersion;
        this.bugClassification = bugClassification;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public String getBugClassification() {
        return bugClassification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

    }
}



class frontEndDeveloper implements IDeveloper,IfrontEndDeveloper{


    @Override
    public void checkBugReport(BugDetails b) {
        System.out.println("Checking the status of the bug id "+b.getId());
    }

    @Override
    public void fixProblem(BugDetails b) {
        correctUI(b);
        checkUI(b);
    }

    @Override
    public void closeTicket(BugDetails b) {
        System.out.println("closing ticket with bugid "+ b.getId());
        b.setStatus("Closed");
    }

    @Override
    public void correctUI(BugDetails b) {
        System.out.println("checking for the problem" + b.getDescription());
    }

    @Override
    public void checkUI(BugDetails b) {
        System.out.println("checking the patch for bug with bug id "+b.getId());
    }
}

class middlewareDeveloper implements IDeveloper,ImiddlewareDeveloper{

    @Override
    public void checkBugReport(BugDetails b) {
        System.out.println("Checking the status of the bug id "+b.getId());
    }

    @Override
    public void fixProblem(BugDetails b) {
        correctErrors(b);
        checkFix(b);
    }

    @Override
    public void closeTicket(BugDetails b) {
        System.out.println("closing the ticket with bugid "+b.getId());
        b.setStatus("Closed");
    }

    @Override
    public void correctErrors(BugDetails b) {
        System.out.println("correcting the errors");
    }

    @Override
    public void checkFix(BugDetails b) {
        System.out.println("checking the fix");
    }
}

class backendDeveloper implements IDeveloper,IbackendDeveloper {

    @Override
    public void checkBugReport(BugDetails b) {
        System.out.println("Checking bug report with bugid" + b.getId());
    }

    @Override
    public void fixProblem(BugDetails b) {
        correctDataInconsistency(b);
        checkData(b);
    }

    @Override
    public void closeTicket(BugDetails b) {
        System.out.println("closing the ticket with bugid "+b.getId());
        b.setStatus("Closed");
    }

    @Override
    public void correctDataInconsistency(BugDetails b) {
        System.out.println("correcting the data inconsistency");
    }

    @Override
    public void checkData(BugDetails b) {
        System.out.println("checking the Data");
    }
}

class BugAssign{

    void assignToDeveloper(IDeveloper d, BugDetails b){

        d.checkBugReport(b);
        d.fixProblem(b);
        d.closeTicket(b);

    }


}

class BugResolve{

    void resolve(ArrayList<BugDetails> list){
        BugAssign ba = new BugAssign();
        for(BugDetails b : list) {
            System.out.println(b.getBugClassification());
            if (b.getStatus().equals("open")) {
                if (b.getBugClassification().equals("UI")) {
                    ba.assignToDeveloper(new frontEndDeveloper(), b);
                }
                if (b.getBugClassification().equals("Middleware"))
                    ba.assignToDeveloper(new middlewareDeveloper(), b);
                if (b.getBugClassification().equals("backend"))
                    ba.assignToDeveloper(new backendDeveloper(), b);
            }
        }
    }

}
class BugRepo{

    ArrayList<BugDetails> al = new ArrayList<>();

    void addBug(BugDetails b){
        al.add(b);

    }

    ArrayList<BugDetails> getBugs() {
        return al;
    }


}


public class BugTracker {

    public static void main(String[] args) {
        BugDetails b = new BugDetails(12,"Error in layout of UI","1.1","UI","open");
        BugDetails b1 = new BugDetails(13,"Error in authentication","1.1","Middleware","open");
        BugResolve br = new BugResolve();
        BugRepo bugrepo= new BugRepo();
        bugrepo.addBug(b);
        bugrepo.addBug(b1);
        ArrayList l = bugrepo.getBugs();
        br.resolve(l);
        System.out.println("resolved");

    }

}
