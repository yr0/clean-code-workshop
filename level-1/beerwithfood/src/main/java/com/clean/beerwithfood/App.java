package com.clean.beerwithfood;

import com.clean.beerwithfood.Pairer;
import java.io.IOException;

public class App 
{
    public static void main(String[] args) throws IOException {
        String food = "Turkey";
        Pairer p = new Pairer();
        System.out.println(p.exec(food));
    }
}
