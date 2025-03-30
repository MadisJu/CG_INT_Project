package com.Lennud.proj.dbmodels.CompositeKeys;

import java.io.Serializable;

public class SeatingID implements Serializable 
{
    public long flight;
    public int seat;

    public SeatingID() {}

    public SeatingID(long fID, int seat)
    {
        this.flight = fID;
        this.seat = seat;
    }
}
