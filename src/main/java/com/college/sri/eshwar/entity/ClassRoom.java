package com.college.sri.eshwar.entity;

public class ClassRoom {
    int class_no;
    int capacity;
    int dept_id;

    public int getId()
    {
        return class_no;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDept_id()
    {
        return dept_id;
    }

    public String toString()
    {
        return "class No : "+class_no+" department id"+dept_id+" capacity : "+capacity;
    }

}
