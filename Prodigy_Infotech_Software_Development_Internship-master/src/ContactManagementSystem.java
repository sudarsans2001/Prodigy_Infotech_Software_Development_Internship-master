import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ContactManagementSystem extends JFrame {
    private List<Contact> contacts = new ArrayList<>();
    private JList<String> contactList;
    private DefaultListModel<String> listModel;

    public ContactManagementSystem() {
        setTitle("Contact Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Contact");
        JButton editButton = new JButton("Edit Contact");
        JButton deleteButton = new JButton("Delete Contact");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog("Enter the name:");
        if (name != null && !name.isEmpty()) {
            String phoneNumber = JOptionPane.showInputDialog("Enter the phone number:");
            String email = JOptionPane.showInputDialog("Enter the email address:");
            Contact contact = new Contact(name, phoneNumber, email);
            contacts.add(contact);
            listModel.addElement(contact.toString()); // Display the complete contact info
        }
    }

    private void editContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Contact contact = contacts.get(selectedIndex);
            String newName = JOptionPane.showInputDialog("Enter the new name:", contact.getName());
            String newPhoneNumber = JOptionPane.showInputDialog("Enter the new phone number:", contact.getPhoneNumber());
            String newEmail = JOptionPane.showInputDialog("Enter the new email address:", contact.getEmail());

            contact.setName(newName);
            contact.setPhoneNumber(newPhoneNumber);
            contact.setEmail(newEmail);

            listModel.set(selectedIndex, contact.toString()); // Update the complete contact info
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to edit.");
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex >= 0) {
            listModel.remove(selectedIndex);
            contacts.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a contact to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ContactManagementSystem app = new ContactManagementSystem();
                app.setVisible(true);
            }
        });
    }
}

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}
