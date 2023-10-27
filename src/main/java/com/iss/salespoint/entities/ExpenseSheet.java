package com.iss.salespoint.entities;

import lombok.Data;

import java.util.Date;

@Data
public class ExpenseSheet {
    private Integer amountOfExpenses;

    private Boolean expenseWaste;

    private Date expenseAccrualDate;
}
