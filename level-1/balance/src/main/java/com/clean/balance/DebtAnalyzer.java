package com.clean.balance;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DebtAnalyzer {
  public String debts() throws IOException {
    // This file has to have two columns: account id and the transaction amount
    String transactions = "src/main/resources/transactions.csv";
    List<String[]> rows;
    // We will use it later to store the balances and determine which one is negative
    Map<String, Double> balances = new HashMap<>();
    String finalMessage = "";

    try (var r = new CSVReader(new FileReader(transactions, StandardCharsets.UTF_8))) {
       rows = r.readAll();
    } catch (CsvException e) {
      return finalMessage;
    }

    for (String[] row: rows) {
      // System.out.println(row[0] + ": " + row[1]);
      if (!row[0].matches("[0-9]+")) {
        // This is the first row - ignore
        continue;
      }

      if (balances.get(row[0]) == null) {
        balances.put(row[0], Double.parseDouble(row[1]));
      } else {
        balances.put(row[0], balances.get(row[0]) + Double.parseDouble(row[1]));
      }
    }

    for (Map.Entry<String, Double> entry : balances.entrySet()) {
      if (entry.getValue() < 0) {
        finalMessage += "Account " + entry.getKey() + " has debt of " + entry.getValue() + "; ";
      }
    }

    return finalMessage;
  }
}
