Banking Management System in Core Java
This is a Banking Management System implemented in Core Java that allows users to perform essential banking operations such as money withdrawal, crediting, and debiting. The system also includes functionalities to add new members and create separate accounts for each, storing and managing individual user data.

Features:
Account Creation:

Allows the creation of new bank accounts with unique account numbers.
Stores user information such as name, contact details, and account balance.
Deposit Money:

Enables users to credit money into their bank accounts.
The balance is updated in real-time after every deposit.
Withdraw Money:

Users can withdraw money from their bank accounts.
Ensures that the withdrawal amount does not exceed the current balance (no overdraft allowed).
View Balance:

Users can check their current account balance at any time.
Transaction Management:

Keeps track of deposits, withdrawals, and balance updates for individual accounts.
Error Handling:

Prevents actions such as overdrawing accounts or creating duplicate users with the same credentials.
Technology Stack:

Java: Core Java (OOP concepts, exception handling, collections)
Data Storage: The system uses in-memory data structures (such as HashMap) to manage user accounts and their details.
Command Line Interface: Users interact with the system via a simple text-based interface.

How to Use:
Create a new account:

.Input user details like name and initial deposit.
.Each account is assigned a unique account number.

Perform Transactions:

Deposit and withdraw money by entering the account number and the transaction amount.
View Account Information:
Retrieve user information and balance using the account number.

Future Enhancements:
Add database connectivity for persistent storage of user accounts and transactions.
Implement advanced security measures like password protection for accounts.
Add more transaction types such as fund transfers between accounts.
