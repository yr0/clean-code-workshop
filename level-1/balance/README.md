# Balance Debtor Analyzer

The script parses the CSV file with transactions and outputs the ids of accounts that have negative total balance, along
with their debts.

### Run instructions

```
mvn compile
mvn exec:java -Dexec.mainClass=com.clean.balance.App
```