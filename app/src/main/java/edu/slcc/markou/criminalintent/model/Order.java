package edu.slcc.markou.criminalintent.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by athanasiosmarkou on 3/22/16.
 */
public class Order
{
    ArrayList<MenuItem> order;
    UUID id;
    double total;
    String description = "Welcome to McDonalds's";
    String waitor ;
}
