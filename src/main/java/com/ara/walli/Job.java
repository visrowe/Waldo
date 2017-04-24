package com.ara.walli;

/**
 * Created by r4kia on 4/20/2017.
 */

public class Job {
    private String name;
    private String jname;
    private String jdescription;
    private double pay;
    private String location;


    public Job(String name,String jname,String jdescription,double pay, String location)
    {
        this.setName(name);
        this.setJname(jname);
        this.setJdescription(jdescription);
        this.setPay(pay);
        this.setLocation(location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }

    public String getJdescription() {
        return jdescription;
    }

    public void setJdescription(String jdescription) {
        this.jdescription = jdescription;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
