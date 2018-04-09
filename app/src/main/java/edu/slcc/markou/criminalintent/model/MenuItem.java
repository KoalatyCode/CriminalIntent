package edu.slcc.markou.criminalintent.model;

import java.util.UUID;

/**
 * Created by athanasiosmarkou on 3/22/16.
 */
public class MenuItem
{
    UUID id;
    String description;
    Characteristics characteristics;
    double cost;
    double sell;
    final double salesTax =0.08;
}
