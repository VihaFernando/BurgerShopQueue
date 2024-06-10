package com.example.sd_courswork_class_version;

import javafx.scene.control.ListView;

import java.util.ArrayList;

public class WaitingCircularQueue {
    private int size, front, rear;
    private ArrayList<Customer> waitingQ = new ArrayList<Customer>();

    public ArrayList<Customer> getWaitingQ() {
        return waitingQ;
    }

    public void enqueue(Customer customer) {
        // Condition if queue is full.
        if ((front == 0 && rear == size - 1) ||
                (rear == (front - 1) % (size - 1))) {
            System.out.print("Queue is Full");
        }

        // condition for empty queue.
        else if (front == -1) {
            front = 0;
            rear = 0;
            waitingQ.add(rear, customer);
        } else if (rear == size - 1 && front != 0) {
            rear = 0;
            waitingQ.set(rear, customer);
        } else {
            rear = (rear + 1);

            // Adding a new element if
            if (front <= rear) {
                waitingQ.add(rear, customer);
            }

            // Else updating old value
            else {
                waitingQ.set(rear, customer);
            }
        }

    }

    public int dequeue() {
        int temp;
        if (front == -1) {
            return -1;
        }
        temp = front;
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        }
        else{
            front += 1;
        }
        return temp;
    }



    public WaitingCircularQueue(int size) {
        this.size = size;
        front = -1;
        rear = -1;
    }
    public void showQueue(ListView listView){
        if (front == -1){
            return;
        }
        if (rear >= front){

            for(int i = front; i <= rear; i++){
                listView.getItems().add("Customer Name:   " + waitingQ.get(i).getFullName() + "         " + "Burgers Required:  " + String.valueOf(waitingQ.get(i).getBurgersRequired()));

            }

        }
    }


}