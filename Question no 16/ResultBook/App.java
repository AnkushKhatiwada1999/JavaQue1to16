
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class App extends JFrame {
    JMenuBar menuBar;
    JPanel dataPanel;
    JPanel buttonPanel;
    JPanel secondPanel;
    DefaultTableModel model;
    JTable table;
    JTextField txtName;
    JTextField txtAddress;
    JRadioButton rdMale;
    JRadioButton rdFemale;
    JRadioButton rdunspecified;
    ButtonGroup bgGroup;
    JCheckBox chkPositive;
    JButton btnSave;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnClear;
    UserRegistration reg;
    int selectedRow;

    public App() {
        this.setTitle("Result Book");
        this.setVisible(true);
        this.setMinimumSize(new Dimension(800, 800));
        this.setDefaultCloseOperation(3);
        this.setJMenuBar(this.getMenu());
        this.setLayout(new GridLayout(1, 0));
        JPanel subPanel = new JPanel(new GridLayout(2, 1));
        subPanel.add(this.dataUI());
        subPanel.add(this.buttonUI());
        this.add(subPanel);
        this.add(this.secondUI());
        reg=new UserRegistration();
        refreshTable();
        this.setLocationRelativeTo(null);
    }

    private JMenuBar getMenu() {
        this.menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        this.menuBar.add(fileMenu);
        this.menuBar.add(editMenu);
        this.menuBar.add(viewMenu);
        return this.menuBar;

    }

    private JPanel dataUI() {
        this.dataPanel = new JPanel();
        this.dataPanel.setLayout(new GridBagLayout());
        this.dataPanel.setBorder(BorderFactory.createTitledBorder("Data Entry"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.dataPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.dataPanel.add(new JLabel("Address"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.dataPanel.add(new JLabel("Gender"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.dataPanel.add(new JLabel("COVID19"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.txtName = new JTextField(20);
        this.dataPanel.add(this.txtName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.txtAddress = new JTextField(20);
        this.dataPanel.add(this.txtAddress, gbc);
        JPanel radioPanel = new JPanel(new FlowLayout(3));
        this.rdMale = new JRadioButton("Male");
        this.rdFemale = new JRadioButton("Female");
        this.rdunspecified = new JRadioButton("Unspecified");
        this.bgGroup = new ButtonGroup();
        this.bgGroup.add(this.rdMale);
        this.bgGroup.add(this.rdFemale);
        this.bgGroup.add(this.rdunspecified);
        radioPanel.add(this.rdMale);
        radioPanel.add(this.rdFemale);
        radioPanel.add(this.rdunspecified);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.dataPanel.add(radioPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.chkPositive = new JCheckBox();
        this.dataPanel.add(this.chkPositive, gbc);
        return this.dataPanel;
    }

    private JPanel secondUI() {
        this.secondPanel = new JPanel(new BorderLayout());
        this.secondPanel.setBorder(BorderFactory.createTitledBorder("List of data"));
        this.model = new DefaultTableModel();
        this.model.setColumnIdentifiers(new String[]{"ID","Name", "Address", "Gender", "COVID-19"});
        this.table = new JTable(this.model);
        JScrollPane scrollPane = new JScrollPane(this.table);
        this.secondPanel.add(scrollPane);
        this.table.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                selectedRow = App.this.table.getSelectedRow();

                App.this.txtName.setText(App.this.model.getValueAt(selectedRow, 1).toString());
                App.this.txtAddress.setText(App.this.model.getValueAt(selectedRow, 2).toString());
                App.this.rdMale.setSelected(App.this.model.getValueAt(selectedRow, 3).toString().equals("Male"));
                App.this.rdFemale.setSelected(App.this.model.getValueAt(selectedRow, 3).toString().equals("Female"));
                App.this.rdFemale.setSelected(App.this.model.getValueAt(selectedRow, 3).toString().equals("Unspecified"));
                App.this.chkPositive.setSelected(App.this.model.getValueAt(selectedRow, 4).toString().equals("Positive"));
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        return this.secondPanel;
    }

    private JPanel buttonUI() {
        this.buttonPanel = new JPanel(new GridLayout(1, 0));
        this.buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        this.btnSave = new JButton("Save");
        this.btnUpdate = new JButton("Update");
        this.btnDelete = new JButton("Delete");
        this.btnClear = new JButton("Clear");
        this.buttonPanel.add(this.btnSave);
        this.buttonPanel.add(this.btnUpdate);
        this.buttonPanel.add(this.btnDelete);
        this.buttonPanel.add(this.btnClear);
        this.btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedID=Integer.parseInt(model.getValueAt(selectedRow,0).toString());
                reg.delete(selectedID);
                refreshTable();

            }
        });
        this.btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                App.this.txtName.setText("");
                App.this.txtAddress.setText("");
                App.this.rdMale.setSelected(false);
                App.this.rdFemale.setSelected(false);
                App.this.rdunspecified.setSelected(false);
                App.this.bgGroup.clearSelection();
                App.this.table.clearSelection();
                App.this.chkPositive.setSelected(false);
            }
        });
        this.btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = App.this.txtName.getText().trim();
                String address = App.this.txtAddress.getText().trim();
                String gender = App.this.rdMale.isSelected() ? "Male" : "Female";
                String positive = App.this.chkPositive.isSelected() ? "Positive" : "Negative";
                if (!name.isEmpty() && !address.isEmpty()) {
                    reg.insert(name, address, gender, positive);
                    refreshTable();
                    App.this.btnClear.doClick();

                    JOptionPane.showMessageDialog(App.this.buttonPanel, "New record is added successfully", "Success", 1);
                } else {
                    JOptionPane.showMessageDialog(App.this.buttonPanel, "Some of the fields are empty", "Error", 0);
                }

            }
        });
        this.btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = App.this.table.getSelectedRow();
                String name = App.this.txtName.getText().trim();
                String address = App.this.txtAddress.getText().trim();
                String gender = App.this.rdMale.isSelected() ? "Male" : "Female";
                String positive = App.this.chkPositive.isSelected() ? "Positive" : "Negative";
                if (!name.isEmpty() && !address.isEmpty()) {
                    App.this.model.setValueAt(name, selectedRow, 1);
                    App.this.model.setValueAt(address, selectedRow, 2);
                    App.this.model.setValueAt(gender, selectedRow, 3);
                    App.this.model.setValueAt(positive, selectedRow, 4);
                    try {
                        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                        reg.update(id, name, address, gender, positive);
                        refreshTable();
                    } catch (Exception q) {
                        JOptionPane.showMessageDialog(null, "Update a row");
                    }

                    App.this.btnClear.doClick();
                    JOptionPane.showMessageDialog(App.this.buttonPanel, "Record at row " + selectedRow + " is updated successfully", "Success", 1);
                } else {
                    JOptionPane.showMessageDialog(App.this.buttonPanel, "SOME fields are empty", "Error", 0);
                }

            }
        });

        return this.buttonPanel;
    }

    public void refreshTable() {
        model.setRowCount(0);
        try {
            ResultSet resultSet = reg.get();
            while(resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getInt("Id"),
                        resultSet.getString("Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Gender"),
                        resultSet.getString("Covid19")});
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new App();
    }
}
