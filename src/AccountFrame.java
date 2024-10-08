import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AccountFrame extends JFrame {
    JLabel accNoLBL, ownerLBL, balanceLBL,cityLBL,genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT,amountTXT;
    JComboBox<City> cititesCMB;

    JButton newBTN,saveBTN,showBTN,quitBTN,depositBTN,withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;

    JList<Account> accountsLST;
    JPanel p1,p2,p3,p4,p5;

    Set<Account> accountSet = new TreeSet<>();
    Account acc,x;
    boolean newRec = true;

    // Combobox Data
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;


    // Table Data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> transList = new ArrayList<>();

    public AccountFrame()  {
        super("Account Operations");
        setLayout(null);
        setSize(600,400);

        // Adding Components to the Frame

        // 1 --> LBLS
        accNoLBL = new JLabel("Account No.");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");

        // 2 --> TextFields
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150,25));

        // ComboBox
        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new City("New Delhi","India"));
        citiesCMBMDL.addElement(new City("Noida","India"));
        citiesCMBMDL.addElement(new City("Bangalore","India"));
        citiesCMBMDL.addElement(new City("Chennai", "India"));

        // Adding data to JCOMBOBOX
        cititesCMB = new JComboBox<City>(citiesCMBMDL);

        // 4 --> Radio Buttons
        maleRDB = new JRadioButton("Male",true);
        femaleRDB = new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);


        // 5 --> Buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("WithDraw");

        // 6 --> Table
        accountsLSTMDL = new DefaultListModel<>();
        accountsLST = new JList<>(accountsLSTMDL);

        // 7 --> Pannels
        p1 = new JPanel(); p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5,155,300,40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5,195,600,40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel(); p4.setBounds(305,5,300,190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());


        // Adding Components to Pannel
        p1.add(accNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(cititesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountsLST);



        // Adding Panels to Frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        // Table Creation
        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("TrsnNo");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollPane = new JScrollPane(table);
        p5.add(scrollPane);

        /******** Funtionality *********/
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accNoTXT.setText("");
                ownerTXT.setText("");
                citiesCMBMDL.setSelectedItem(0);
                maleRDB.setSelected(true);
                balanceTXT.setText("");
                amountTXT.setText("");
                newRec = true;
            }
        });

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Saving the New Data
                if(newRec){
                    if (ownerTXT.getText().length() != 0){
                        acc = new Account(
                                ownerTXT.getText(),
                                (City)cititesCMB.getSelectedItem(),
                                maleRDB.isSelected()? 'M' : 'F');
                        accNoTXT.setText(String.valueOf(acc.accNo));
                        accountSet.add(acc);
                        accountsLSTMDL.addElement(acc);
                        newRec = false;
                    }else{
                        JOptionPane.showMessageDialog(null,"Please Fill All Fields");
                    }
                }else {
                    // Update the Data
                    accountSet.remove(acc);

                    int a = Integer.parseInt(accNoTXT.getText());
                    String o = ownerTXT.getText();
                    City c = (City) cititesCMB.getSelectedItem();

                    char g = maleRDB.isSelected()? 'M' : 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountSet.add(acc);
                    accountsLSTMDL.setElementAt(acc,accountsLST.getSelectedIndex());
                    newRec = false;

                }
                saveBTN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String ownerName = ownerTXT.getText();
                        
                        // Check if owner name is empty
                        if (ownerName.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Please Fill All Fields");
                            return;
                        }
                
                        // Check if user already exists
                        for (Account existingAcc : accountSet) {
                            if (existingAcc.owner.equals(ownerName)) {
                                JOptionPane.showMessageDialog(null, "This user already exists");
                                return;
                            }
                        }
                
                        if (newRec) {
                            // Creating a new Account
                            acc = new Account(
                                ownerName,
                                (City) cititesCMB.getSelectedItem(),
                                maleRDB.isSelected() ? 'M' : 'F'
                            );
                            accNoTXT.setText(String.valueOf(acc.accNo));
                            accountSet.add(acc);
                            accountsLSTMDL.addElement(acc);
                            newRec = false;
                        } else {
                            // Update existing Account
                            accountSet.remove(acc);
                
                            int a = Integer.parseInt(accNoTXT.getText());
                            City c = (City) cititesCMB.getSelectedItem();
                            char g = maleRDB.isSelected() ? 'M' : 'F';
                            double b = Double.parseDouble(balanceTXT.getText());
                            acc = new Account(a, ownerName, c, g, b);
                            accountSet.add(acc);
                            accountsLSTMDL.setElementAt(acc, accountsLST.getSelectedIndex());
                            newRec = false;
                        }
                    }
                });
                saveBTN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String ownerName = ownerTXT.getText();
                        
                        // Check if owner name is empty
                        if (ownerName.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Please Fill All Fields");
                            return;
                        }
                
                        // Check if user already exists
                        for (Account existingAcc : accountSet) {
                            if (existingAcc.owner.equals(ownerName)) {
                                JOptionPane.showMessageDialog(null, "This user already exists");
                                return;
                            }
                        }
                
                        if (newRec) {
                            // Creating a new Account
                            acc = new Account(
                                ownerName,
                                (City) cititesCMB.getSelectedItem(),
                                maleRDB.isSelected() ? 'M' : 'F'
                            );
                            accNoTXT.setText(String.valueOf(acc.accNo));
                            accountSet.add(acc);
                            accountsLSTMDL.addElement(acc);
                            newRec = false;
                        } else {
                            // Update existing Account
                            accountSet.remove(acc);
                
                            int a = Integer.parseInt(accNoTXT.getText());
                            City c = (City) cititesCMB.getSelectedItem();
                            char g = maleRDB.isSelected() ? 'M' : 'F';
                            double b = Double.parseDouble(balanceTXT.getText());
                            acc = new Account(a, ownerName, c, g, b);
                            accountSet.add(acc);
                            accountsLSTMDL.setElementAt(acc, accountsLST.getSelectedIndex());
                            newRec = false;
                        }
                    }
                });
                

            }
        });

        showBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Account> it = accountSet.iterator();
                while (it.hasNext()){
                    s += it.next().toString() + "\n";
                    JOptionPane.showMessageDialog(null,s);

                }
            }
        });

        depositBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!newRec && amountTXT.getText().length() != 0){

                    // Adding Transaction to Table
                    Transaction t = new Transaction(acc, LocalDate.now(),
                            'D',Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionsInTable(t);

                    // Perform deposit from account
                    acc.depoist(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }
            }
        });

        withdrawBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!newRec && amountTXT.getText().length() != 0){

                    // Adding Transaction to table
                    Transaction  t = new Transaction(
                            acc, LocalDate.now(),
                            'W',
                            Double.parseDouble(amountTXT.getText()));

                    DisplayTransactionsInTable(t);

                    // perform withdrawal on account
                    acc.withdraw(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));




                }
            }
        });

        accountsLST.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                acc = x = accountsLST.getSelectedValue();

                accNoTXT.setText(String.valueOf(acc.accNo));
                ownerTXT.setText(acc.owner);
                cititesCMB.setSelectedItem(acc.city);

                if (acc.gender == 'M') maleRDB.setSelected(true);
                else femaleRDB.setSelected(true);

                balanceTXT.setText(String.valueOf(acc.balance));
                amountTXT.setEnabled(true);
                depositBTN.setEnabled(true);
                newRec = false;

                // Clear Table
                for (int i = tableModel.getRowCount() - 1; i >=0 ; i-- ){
                    tableModel.removeRow(i);
                }

                // Get Transaction to Selected Account
                SearchTransactionList(acc.accNo);

            }
        });


    }

    private void SearchTransactionList(int accNo) {
        List<Transaction> filteredList = new ArrayList<>();

        // iterate through the list
        for(Transaction t : transList){

            // filter values that contains trsNo
            if(t.getAcc().accNo == accNo){
                filteredList.add(t);
            }
        }

        // Display the Filtered List
        for (int i = 0; i < filteredList.size();i++){
            // Displaying the Data into table
            tableModel.addRow(new Object[]{
                    filteredList.get(i).getTrsNo(),
                    filteredList.get(i).getDate(),
                    filteredList.get(i).getOperation(),
                    filteredList.get(i).getOperation(),
                    filteredList.get(i).getAmount()
            });
        }
    }

    private void DisplayTransactionsInTable(Transaction t) {

        // Display data into table
        tableModel.addRow(new Object[]{
                t.getTrsNo(),
                t.getDate(),
                t.getOperation(),
                t.getAmount()
        });

        // Adding object to arraylist
        transList.add(t);

    }

    public static void main(String[] args) {
        AccountFrame af = new AccountFrame();
        af.setVisible(true);
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
