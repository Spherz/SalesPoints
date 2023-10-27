package com.iss.salespoint.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private String INN;

    private Date workingRegime; // сделать boolean

    private Integer amountPerExit;

    private Boolean isWorking;

//    private List<ExpenseSheet> expenseSheet;


    public SalePoint(String name, String address,
                     String phone, String INN,
                     Date workingRegime, Integer amountPerExit,
                     Boolean isWorking) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.INN = INN;
        this.workingRegime = workingRegime;
        this.amountPerExit = amountPerExit;
        this.isWorking = isWorking;
    }
}
