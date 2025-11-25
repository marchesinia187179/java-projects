package org.example.observer;

public class ObserverMain {
    public static void main(String[] args) {
        // Create jobseekers
        JobSeeker mickeyMouse = new JobSeeker("Michael (Mickey) Theodore Mouse");
        JobSeeker donaldDuck = new JobSeeker("Donald Fauntleroy Duck");

        // Create employment agency and register jobseekers
        EmploymentAgency agency = new EmploymentAgency();
        agency.attach(mickeyMouse);
        agency.attach(donaldDuck);

        // Post two new jobs and notify all jobseekers
        agency.addJob(new JobPost("Software Engineer"));
        agency.detach(donaldDuck);
        agency.addJob(new JobPost("Electrical Engineer"));
    }
}
