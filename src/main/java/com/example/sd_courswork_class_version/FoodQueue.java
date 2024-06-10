package com.example.sd_courswork_class_version;

import java.util.ArrayList;

public class FoodQueue {
    private ArrayList<Customer> customers;
    private int maxSize;

    public FoodQueue(int maxSize) {      //
        this.maxSize = maxSize;
        this.customers = new ArrayList(maxSize);
    }

    public boolean isEmpty() {            //boolean for check if queues empty
        return this.customers.isEmpty();
    }

    public int getSize() {          // function for get size
        return this.customers.size();
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void enqueue(Customer customer) {     //function for add customers for queues
        this.customers.add(customer);
    }

    public Customer dequeue(int i) {    //function for remove customer from queue
        return (Customer)this.customers.remove(i);
    }

    public ArrayList<Customer> getCustomers() {         //list for get customers
        return this.customers;
    }
}
