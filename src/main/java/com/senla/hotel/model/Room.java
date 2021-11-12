package com.senla.hotel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room extends BaseEntity{
    private String number;
    private int capacity;
    private Status status;
    private int price;
    private Room(String number,int capacity,int price,Status status){
        this.number=number;
        this.capacity=capacity;
        this.price=price;
        this.status=status;
    }

    @Override
    public String toString() {
        return String.format("\nRoom:" +
                        "\r\n\tnumber: %s" +
                        "\r\n\tcapacity: %d" +
                        "\r\n\tstatus: %s" +
                        "\r\n\tprice: %d",
                number(),capacity,status,price);
    }

}
