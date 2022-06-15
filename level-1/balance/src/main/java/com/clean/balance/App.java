package com.clean.balance;

import com.clean.balance.DebtAnalyzer;
import java.io.IOException;

public class App 
{
    public static void main(String[] args) throws IOException
    {
        DebtAnalyzer da = new DebtAnalyzer();
        System.out.println(da.debts());
    }
}
