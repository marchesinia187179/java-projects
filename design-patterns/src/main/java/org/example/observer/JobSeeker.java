package org.example.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JobSeeker implements Observer {
    private String name;

    @Override
    public void onJobPosted(JobPost job) {
        System.out.println("Hi " + name + "! New job posted: " + job.getTitle());
    }
}
