package GestionContact.ihm;

import GestionContact.LLG.ContactManager;
import GestionContact.bo.Contact;
import GestionContact.data.ContactDOA;
import GestionContact.data.DataBaseException;
import GestionContact.data.GroupeDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class GUI extends javax.swing.JFrame {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Welcome To CHIT CHAT");
        frame.setSize(2000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(280, 1000));

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("src/GestionContact/ihm/Icons/chat mail.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(210, 210, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImage);
        logoLabel.setIcon(logoIcon);
        panel.add(logoLabel);

        Color c1 = new Color(49, 89, 150);
        Color c = new Color(71, 138, 238);
        Color c2 = new Color(2,3,129);
        panel.setBackground(Color.decode("#555CB3"));


        JButton button1 = new JButton("Ajouter un contact");
        button1.setPreferredSize(new Dimension(250, 50));
        button1.setFont(new Font("Verdana", Font.BOLD, 16));
        button1.setForeground(Color.white);
        button1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                button1.setOpaque(true); // Make the button opaque
                button1.setContentAreaFilled(true); // Fill the content area with the background color
                button1.setBackground(Color.decode("#EA6A47"));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                button1.setOpaque(true); // Make the button opaque
                button1.setContentAreaFilled(true); // Fill the content area with the background color
                button1.setBackground(Color.decode("#555CB3"));
            }
        });



        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {


                JPanel panelContact = new JPanel();
                panelContact.setBackground(Color.WHITE);
                panelContact.setPreferredSize(new Dimension(400, 1000));

                JLabel titleLabel = new JLabel("Ajouter Contact");
                titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel.setPreferredSize(new Dimension(150, 70));
                titleLabel.setForeground(Color.decode("#EA6A47"));

                JLabel nomLabel = new JLabel("Nom:");
                JTextField nomField = new JTextField(20);
                nomLabel.setForeground(Color.black);
                nomLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                nomField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel prenomPanel = new JLabel("Prenom:");
                JTextField prenomField = new JTextField(20);
                prenomPanel.setForeground(Color.black);
                prenomPanel.setFont(new Font("Georgia", Font.BOLD, 16));
                prenomField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel tele1Label = new JLabel("Telephone professionel:");
                JTextField tele1Field = new JTextField(20);
                tele1Label.setForeground(Color.black);
                tele1Label.setFont(new Font("Georgia", Font.BOLD, 16));
                tele1Field.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel tele2Label = new JLabel("Telephone personnel:");
                JTextField tele2Field = new JTextField(20);
                tele2Label.setForeground(Color.black);
                tele2Label.setFont(new Font("Georgia", Font.BOLD, 16));
                tele2Field.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel adresseLabel = new JLabel("Adresse:");
                JTextField adresseField = new JTextField(20);
                adresseLabel.setForeground(Color.black);
                adresseLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                adresseField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel emailperLabel = new JLabel("Email personnel :");
                JTextField emailperField = new JTextField(20);
                emailperLabel.setForeground(Color.black);
                emailperLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                emailperField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel emailproLabel = new JLabel("Email Professionel :");
                JTextField emailproField = new JTextField(20);
                emailproLabel.setForeground(Color.black);
                emailproLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                emailproField.setPreferredSize(new Dimension(nomField.getPreferredSize().width, 35));

                JLabel genreLabel = new JLabel("Genre :");
                String[] options = {"Femme", "Homme"};
                JComboBox<String> comboBox = new JComboBox<>(options);
                comboBox.setFont(new Font("Georgia", Font.PLAIN, 14));
                genreLabel.setForeground(Color.black);
                genreLabel.setFont(new Font("Georgia", Font.BOLD, 16));


                JButton btnAdd = new JButton("Submit");
                btnAdd.setFont(new Font("Georgia",1,14));

                btnAdd.setForeground(Color.decode("#EA6A47"));
                btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd.setBackground(Color.decode("#EA6A47"));
                        btnAdd.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setForeground(Color.decode("#EA6A47"));
                        btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd.setBackground(Color.WHITE);
                    }
                });

                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ContactManager contactManager = new ContactManager();
                        ContactDOA contactDOA = new ContactDOA();
                        // Get the values from the text fields
                        String nom = nomField.getText();
                        String prenom = prenomField.getText();
                        String telephone1 = tele1Field.getText();
                        String telephone2 = tele2Field.getText();
                        String adresse = adresseField.getText();
                        String emailPer = emailperField.getText();
                        String emailPro = emailproField.getText();
                        String selectedOption = comboBox.getSelectedItem().toString();

                        // Checking if any of the feilds is empty
                        if (nom.isEmpty() || prenom.isEmpty() || telephone1.isEmpty() || adresse.isEmpty() || emailPer.isEmpty() || emailPro.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty
                        }
                        if (!contactManager.validatePhoneNumber(telephone1) || !contactManager.validatePhoneNumber(telephone2)) {
                            JOptionPane.showMessageDialog(frame, "Veuillez entrer des numéros de téléphone valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any phone number is invalid
                                    // (the phone number doesn't start with (06-07-05) or has 8 number)
                        }

                        if (!contactManager.validateEmail(emailPer) || !contactManager.validateEmail(emailPro)) {
                            JOptionPane.showMessageDialog(frame, "Veuillez entrer des adresses e-mail valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any email address is invalid
                        }

                        switch (selectedOption) {
                            case "Femme":
                                Contact contact = new Contact(nom,prenom,telephone1,telephone2,adresse,emailPer,emailPro,"Femme");
                                try {
                                    contactDOA.create(contact);
                                    JOptionPane.showMessageDialog(frame, "Le contact a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                                } catch (Exception ex) {
                                    throw new RuntimeException(ex);
                                }
                                break;
                            case "Homme":
                                Contact pcontact = new Contact(nom,prenom,telephone1,telephone2,adresse,emailPer,emailPro,"Homme");
                                try {
                                    contactDOA.create(pcontact);
                                    JOptionPane.showMessageDialog(frame, "Le contact a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                                } catch (Exception ex) {
                                    throw new RuntimeException(ex);
                                }
                                break;
                            default:
                                // Handle invalid option
                                break;
                        }



                    }
                });

                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nomLabel)
                                        .addComponent(nomField, 300, 300, 300)) // Set the preferred width to 150
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(prenomPanel)
                                        .addComponent(prenomField, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tele1Label)
                                        .addComponent(tele1Field, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(tele2Label)
                                        .addComponent(tele2Field, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(adresseLabel)
                                        .addComponent(adresseField, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(emailperLabel)
                                        .addComponent(emailperField, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(emailproLabel)
                                        .addComponent(emailproField, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(genreLabel)
                                        .addComponent(comboBox, 100, 100, 100))
                                .addComponent(btnAdd,100,100,100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(titleLabel,80,80,80)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nomLabel)
                                        .addComponent(nomField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prenomPanel)
                                        .addComponent(prenomField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(tele1Label)
                                        .addComponent(tele1Field))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(tele2Label)
                                        .addComponent(tele2Field))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(adresseLabel)
                                        .addComponent(adresseField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailperLabel)
                                        .addComponent(emailperField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailproLabel)
                                        .addComponent(emailproField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(genreLabel)
                                        .addComponent(comboBox, 40, 40, 40))
                                .addComponent(btnAdd,40,40,40)
                );


                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);


                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();
                gbcContent.gridx = 1;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 0.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.anchor = GridBagConstraints.CENTER; // Align the panel to the center
                gbcContent.insets = new Insets(10, 0, 10, 10);

                // Create a new JPanel for centering the contact panel
                JPanel centerPanel = new JPanel(new GridBagLayout());
                centerPanel.setBackground(Color.WHITE);
                centerPanel.add(panelContact, gbcContent);

                mainPanel.add(centerPanel, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
            });



        JButton button2 = new JButton("Liste des contacts");
        button2.setPreferredSize(new Dimension(250, 50));
        button2.setFont(new Font("Verdana", Font.BOLD, 16));
        button2.setForeground(Color.white);
        button2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                button2.setBackground(Color.decode("#EA6A47"));
                button2.setOpaque(true); // Make the button opaque
                button2.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button2.setBackground(Color.decode("#555CB3"));
                button2.setOpaque(true); // Make the button opaque
                button2.setContentAreaFilled(true);
            }
        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Create and configure the layout for the main panel
                GridBagLayout mainLayout = new GridBagLayout();
                mainPanel.setLayout(mainLayout);

                // Create the panel for adding a contact
                JPanel panelContact = new JPanel();
                panelContact.setBackground(Color.WHITE);

                // Create the table model with column names and initial data
                String[] columnNames = {"id","Nom", "Prenom", "Phone Professionnel", "Phone Personnel", "Adresse","E-mail Personnel", "Email Professionel", "Genre"};
                Object[][] data = {};
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                // Create the JTable with the table model
                JTable contactTable = new JTable(tableModel);
                contactTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                contactTable.setIntercellSpacing(new java.awt.Dimension(2, 1));
                contactTable.setRowHeight(18);

                // Set table appearance and behavior
                contactTable.setFillsViewportHeight(true);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(contactTable);


                // Button to refresh
                JButton refreshBtn = new JButton();
                refreshBtn.setText("Refresh");
                refreshBtn.setFont(new Font("Verdana",1,14));

                refreshBtn.setForeground(Color.decode("#EA6A47"));
                refreshBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                refreshBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        refreshBtn.setOpaque(true); // Make the button opaque
                        refreshBtn.setContentAreaFilled(true); // Fill the content area with the background color
                        refreshBtn.setBackground(Color.decode("#EA6A47"));
                        refreshBtn.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        refreshBtn.setOpaque(true); // Make the button opaque
                        refreshBtn.setForeground(Color.decode("#EA6A47"));
                        refreshBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        refreshBtn.setBackground(Color.WHITE);
                    }
                });



                refreshBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ContactDOA contactDOA = new ContactDOA();
                        try {
                            List<Contact> contacts = contactDOA.AllContact();

                            // Clear the table model
                            DefaultTableModel tableModel = (DefaultTableModel) contactTable.getModel();
                            tableModel.setRowCount(0);

                            // Populate the table model with the retrieved data
                            for (Contact contact : contacts) {
                                Object[] rowData = {
                                        contact.getID(),
                                        contact.getNom(),
                                        contact.getPrenom(),
                                        contact.getTelephone1(),
                                        contact.getTelephone2(),
                                        contact.getAdresse(),
                                        contact.getEmailPer(),
                                        contact.getEmailPro(),
                                        contact.getGenre()
                                };
                                tableModel.addRow(rowData);
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }


                    }

                });


                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scrollPane)
                                .addComponent(refreshBtn,100,100,100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(scrollPane)
                                .addComponent(refreshBtn,40,40,40)
                );

                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();
                gbcContent.gridx = 0;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 1.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 0, 10, 10);
                mainPanel.add(panelContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();

                // Adjust the preferred size of panelAddContact based on available space
                panelContact.setPreferredSize(new Dimension(1000, 70));
            }

        });

        JButton button3 = new JButton("Supprimer un Contact");
        button3.setPreferredSize(new Dimension(150, 50));
        button3.setFont(new Font("Verdana", Font.BOLD, 16));
        button3.setForeground(Color.white);
        button3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button3.setBackground(Color.decode("#EA6A47"));
                button3.setOpaque(true); // Make the button opaque
                button3.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button3.setBackground(Color.decode("#555CB3"));
                button3.setOpaque(true); // Make the button opaque
                button3.setContentAreaFilled(true);
            }
        });

        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // Create and configure the panel for adding a contact
                JPanel panelAddContact = new JPanel();
                panelAddContact.setPreferredSize(new Dimension(1000, 1000));
                panelAddContact.setBackground(Color.WHITE);

                JLabel titleLabel = new JLabel("Supprimer Contact");
                titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel.setForeground(Color.decode("#EA6A47"));

                // Create and configure the text fields and labels for the contact details
                JLabel IDLabel = new JLabel("ID Contact :");
                JTextField IDField = new JTextField(20);
                IDLabel.setForeground(Color.BLACK);
                IDLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                IDField.setPreferredSize(new Dimension(IDField.getPreferredSize().width, 35));

                // Add a button to submit
                JButton btnAdd = new JButton("Submit");
                btnAdd.setFont(new Font("Georgia",1,14));

                btnAdd.setForeground(Color.decode("#EA6A47"));
                btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd.setBackground(Color.decode("#EA6A47"));
                        btnAdd.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setForeground(Color.decode("#EA6A47"));
                        btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd.setBackground(Color.WHITE);
                    }
                });

                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ContactDOA contactDOA = new ContactDOA();
                        // Get the values from the text fields
                        String pId = IDField.getText();

                        // Checking if the idfeild is empty
                        if (pId.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir le champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty
                        }

                        int id = Integer.parseInt(pId);
                        try {
                            contactDOA.delete(id);
                            JOptionPane.showMessageDialog(frame, "Le contact a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelAddContact);
                panelAddContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(IDLabel)
                                        .addComponent(IDField,200,200,200))
                                .addComponent(btnAdd,100,100,100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(titleLabel,100,100,100)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(IDLabel)
                                        .addComponent(IDField))
                                .addComponent(btnAdd,40,40,40)
                );

                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();

                gbcContent.gridx = 1;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 0.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 0, 10, 10);
                mainPanel.add(panelAddContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JButton button4 = new JButton("Modifier un Contact");
        button4.setPreferredSize(new Dimension(150, 50));
        button4.setFont(new Font("Verdana", Font.BOLD, 16));
        button4.setForeground(Color.white);
        button4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button4.setBackground(Color.decode("#EA6A47"));
                button4.setOpaque(true); // Make the button opaque
                button4.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                button4.setBackground(Color.decode("#555CB3"));
                button4.setOpaque(true); // Make the button opaque
                button4.setContentAreaFilled(true);
            }
        });

        button4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // Create and configure the panel for adding a contact
                JPanel panelContact = new JPanel();
                panelContact.setBackground(Color.WHITE);
                panelContact.setPreferredSize(new Dimension(600,1000));

                JLabel titleLabel = new JLabel("Modifier Contact");
                titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel.setForeground(Color.decode("#EA6A47"));


                JLabel IDLabel = new JLabel("ID Contact :");
                JTextField IDField = new JTextField(20);
                IDLabel.setForeground(Color.BLACK);
                IDLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                IDField.setPreferredSize(new Dimension(IDField.getPreferredSize().width, 35));

                JLabel valueLabel = new JLabel("Nouvelle Valeur :");
                JTextField valueField = new JTextField(20);
                valueLabel.setForeground(Color.BLACK);
                valueLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                valueField.setPreferredSize(new Dimension(valueField.getPreferredSize().width, 35));


                // Create and configure the JComboBox
                String[] options = {"Nom", "Prenom","Telephone Personnel","Telephone Professionnel", "Adresse","E-mail Profesionnel", "E-mail Personnel"};
                JComboBox<String> comboBox = new JComboBox<>(options);
                comboBox.setFont(new Font("Georgia", Font.PLAIN, 14));


                // Add a button to submit
                JButton btnAdd = new JButton("Submit");
                btnAdd.setFont(new Font("Georgia",1,14));

                btnAdd.setForeground(Color.decode("#EA6A47"));
                btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd.setBackground(Color.decode("#EA6A47"));
                        btnAdd.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setForeground(Color.decode("#EA6A47"));
                        btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd.setBackground(Color.WHITE);
                    }
                });

                // Add an ActionListener to the submit button
                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // Get the selected option from the JComboBox
                        String selectedOption = comboBox.getSelectedItem().toString();

                        // Get the ID and corresponding new value from the text field
                        String idFeild = IDField.getText();
                        String newValue = valueField.getText(); // Replace this with the appropriate text field for the new value

                        // check the empty feilds
                        if(idFeild.isEmpty() | newValue.isEmpty()){
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty
                        }


                        int contactID = Integer.parseInt(idFeild);

                        // Update the contact information based on the selected option
                        try {
                            ContactDOA contactDOA = new ContactDOA();

                            switch (selectedOption) {

                                case "Nom":
                                    contactDOA.updateName(newValue, contactID);
                                    break;

                                case "Prenom":
                                    contactDOA.updatePrenom(newValue, contactID);
                                    break;

                                case "Adresse":
                                    contactDOA.updateAdresse(newValue, contactID);
                                    break;

                                case "Telephone Personnel":
                                    contactDOA.updateTelephone2(newValue,contactID);
                                    break;

                                case "Telephone Professionnel":
                                    contactDOA.updateTelephone1(newValue,contactID);
                                    break;

                                case "E-mail Profesionnel":
                                    contactDOA.updateEmail2(newValue, contactID);
                                    break;

                                case "E-mail Personnel":
                                    contactDOA.updateEmail1(newValue,contactID);

                                default:
                                    // Handle invalid option
                                    break;
                            }

                            // Display a success message or perform any other necessary actions
                            JOptionPane.showMessageDialog(frame, "Contact information updated successfully.");
                        } catch (Exception ex) {
                            // Handle any exceptions that occur during the update process
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "Error updating contact information: " + ex.getMessage());
                        }
                    }
                });




                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(IDLabel)
                                        .addComponent(IDField,300,300,300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(valueLabel)
                                        .addComponent(valueField,300,300,300))
                                .addComponent(comboBox,200,200,200)
                                .addComponent(btnAdd,100,100,100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(titleLabel,100,100,100)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(IDLabel)
                                        .addComponent(IDField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(valueLabel)
                                        .addComponent(valueField))
                                .addComponent(comboBox,40,40,40)
                                .addComponent(btnAdd,40,40,40)
                );

                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();

                gbcContent.gridx = 1;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 0.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 0, 10, 10);
                mainPanel.add(panelContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JButton button5 = new JButton("Rechercher un Contact");
        button5.setPreferredSize(new Dimension(150, 50));
        button5.setFont(new Font("Verdana", Font.BOLD, 16));
        button5.setForeground(Color.white);
        button5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button5.setBackground(Color.decode("#EA6A47"));
                button5.setOpaque(true); // Make the button opaque
                button5.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                button5.setBackground(Color.decode("#555CB3"));
                button5.setOpaque(true); // Make the button opaque
                button5.setContentAreaFilled(true);
            }
        });

        button5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Create and configure the layout for the main panel
                GridBagLayout mainLayout = new GridBagLayout();
                mainPanel.setLayout(mainLayout);

                // Create the panel for adding a contact
                JPanel panelContact = new JPanel();
                panelContact.setBackground(Color.WHITE);

                // Title
                JLabel titleLabel = new JLabel("Rechercher Contact");
                titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel.setForeground(Color.decode("#EA6A47"));

                // Create and configure the text fields and labels for the contact details
                JLabel NomLabel = new JLabel("Valeur de recherche :");
                JTextField NomField = new JTextField(20);
                NomLabel.setForeground(Color.BLACK);
                NomLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                NomField.setPreferredSize(new Dimension(NomField.getPreferredSize().width, 35));

                // Create and configure the JComboBox
                String[] options = {"Nom", "Telephone Personnel","Telephone Professionnel"};
                JComboBox<String> comboBox = new JComboBox<>(options);
                comboBox.setFont(new Font("Georgia", Font.PLAIN, 14));


                // Create the table model with column names and initial data
                String[] columnNames = {"Id","Nom", "Prenom", "Phone Professionnel", "Phone Personnel", "Adresse","E-mail Personnel", "Email Professionel", "Genre"};
                Object[][] data = {};
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                // Create the JTable with the table model
                JTable contactTable = new JTable(tableModel);
                contactTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                contactTable.setIntercellSpacing(new java.awt.Dimension(2, 1));
                contactTable.setRowHeight(18);

                // Set table appearance and behavior
                contactTable.setFillsViewportHeight(true);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(contactTable);

                JButton btnAdd = new JButton("Submit");
                btnAdd.setFont(new Font("Georgia",1,14));

                btnAdd.setForeground(Color.decode("#EA6A47"));
                btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd.setBackground(Color.decode("#EA6A47"));
                        btnAdd.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setForeground(Color.decode("#EA6A47"));
                        btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd.setBackground(Color.WHITE);
                    }
                });

                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String selectedOption = comboBox.getSelectedItem().toString();
                        ContactDOA contactDOA = new ContactDOA();
                        List<Contact> contacts;
                        DefaultTableModel tableModel;
                        String pNom = NomField.getText();

                        if(pNom.isEmpty()){
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir le champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty
                        }
                        // Create a new Contact object
                        try {
                            switch (selectedOption) {
                                case "Nom":
                                contacts = contactDOA.findContactSoundex(pNom);
                                // Clear the table model
                                tableModel = (DefaultTableModel) contactTable.getModel();
                                tableModel.setRowCount(0);

                                // Populate the table model with the retrieved data
                                for (Contact contact : contacts) {
                                    Object[] rowData = {
                                            contact.getID(),
                                            contact.getNom(),
                                            contact.getPrenom(),
                                            contact.getTelephone1(),
                                            contact.getTelephone2(),
                                            contact.getAdresse(),
                                            contact.getEmailPer(),
                                            contact.getEmailPro(),
                                            contact.getGenre()
                                    };
                                    tableModel.addRow(rowData);
                                }
                                break;
                                case "Telephone Personnel":

                                    contacts = contactDOA.findContactPhonePer(pNom);
                                    // Clear the table model
                                    tableModel = (DefaultTableModel) contactTable.getModel();
                                    tableModel.setRowCount(0);

                                    // Populate the table model with the retrieved data
                                    for (Contact contact : contacts) {
                                        Object[] rowData = {
                                                contact.getID(),
                                                contact.getNom(),
                                                contact.getPrenom(),
                                                contact.getTelephone1(),
                                                contact.getTelephone2(),
                                                contact.getAdresse(),
                                                contact.getEmailPer(),
                                                contact.getEmailPro(),
                                                contact.getGenre()
                                        };
                                        tableModel.addRow(rowData);
                                    }

                                    break;
                                case "Telephone Professionnel":

                                    contacts = contactDOA.findContactPhonePro(pNom);
                                    // Clear the table model
                                    tableModel = (DefaultTableModel) contactTable.getModel();
                                    tableModel.setRowCount(0);

                                    // Populate the table model with the retrieved data
                                    for (Contact contact : contacts) {
                                        Object[] rowData = {
                                                contact.getID(),
                                                contact.getNom(),
                                                contact.getPrenom(),
                                                contact.getTelephone1(),
                                                contact.getTelephone2(),
                                                contact.getAdresse(),
                                                contact.getEmailPer(),
                                                contact.getEmailPro(),
                                                contact.getGenre()
                                        };
                                        tableModel.addRow(rowData);
                                    }

                                    break;
                                default:
                                    break;
                            }
                        }catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(NomLabel)
                                        .addComponent(NomField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(titleLabel)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(NomLabel)
                                        .addComponent(NomField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addComponent(comboBox,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );


                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();
                gbcContent.gridx = 1;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 1.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 5, 10, 10);
                mainPanel.add(panelContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();

                // Adjust the preferred size of panelAddContact based on available space
                Dimension availableSpace = mainPanel.getSize();
                panelContact.setPreferredSize(new Dimension(1000, 70));
            }

        });

        JButton button6 = new JButton("Créer un groupe");
        button6.setPreferredSize(new Dimension(150, 50));
        button6.setFont(new Font("Verdana", Font.BOLD, 16));
        button6.setForeground(Color.white);
        button6.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button6.setBackground(Color.decode("#EA6A47"));
                button6.setOpaque(true); // Make the button opaque
                button6.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button6.setBackground(Color.decode("#555CB3"));
                button6.setOpaque(true); // Make the button opaque
                button6.setContentAreaFilled(true);
            }
        });

        button6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // Create and configure the panel for adding a contact
                JPanel panelContact = new JPanel();
                panelContact.setBackground(Color.WHITE);
                panelContact.setPreferredSize(new Dimension(1000, 1000));


                JLabel titleLabel = new JLabel("Créer Groupe");
                titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel.setForeground(Color.decode("#EA6A47"));

                // Create and configure the text fields and labels for the contact details
                JLabel nomGroupeLabel = new JLabel("Nom de Groupe :");
                JTextField nomGroupeField = new JTextField(20);
                nomGroupeLabel.setForeground(Color.BLACK);
                nomGroupeLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                nomGroupeField.setPreferredSize(new Dimension(nomGroupeField.getPreferredSize().width, 35));

                // Add a button to submit
                JButton btnAdd = new JButton("Submit");
                btnAdd.setFont(new Font("Georgia",1,14));
                btnAdd.setForeground(Color.decode("#EA6A47"));
                btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd.setBackground(Color.decode("#EA6A47"));
                        btnAdd.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setForeground(Color.decode("#EA6A47"));
                        btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd.setBackground(Color.WHITE);
                    }
                });

                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GroupeDAO groupeDAO = new GroupeDAO();
                        // Get the values from the text fields
                        String pNomGroupe = nomGroupeField.getText();
                        if(pNomGroupe.isEmpty()){
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir le champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty
                        }
                        // Create a new Contact object
                        try {
                            groupeDAO.createGrp(pNomGroupe);
                            // Display a success message or perform any other necessary actions
                            JOptionPane.showMessageDialog(frame, "The groupe "+pNomGroupe+ " inserted successfully.");
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                JLabel titleLabel1 = new JLabel("Ajouter Un Contact Dans Un Groupe");
                titleLabel1.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel1.setForeground(Color.decode("#EA6A47"));
                // Create and configure the text fields and labels for the contact details
                JLabel IDLabel = new JLabel("Nom du Groupe :");
                JTextField IDField= new JTextField(18);
                IDLabel.setForeground(Color.BLACK);
                IDLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                IDField.setPreferredSize(new Dimension(IDField.getPreferredSize().width, 35));

                JLabel IDContactLabel = new JLabel("ID Contact :");
                JTextField IDContactField = new JTextField(18);
                IDContactLabel.setForeground(Color.BLACK);
                IDContactLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                IDContactField.setPreferredSize(new Dimension(IDContactField.getPreferredSize().width, 35));


                // Add a button to submit
                JButton btnAdd1 = new JButton("Submit");
                btnAdd1.setFont(new Font("Georgia",1,14));
                btnAdd1.setForeground(Color.decode("#EA6A47"));
                btnAdd1.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd1.setOpaque(true); // Make the button opaque
                        btnAdd1.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd1.setBackground(Color.decode("#EA6A47"));
                        btnAdd1.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd1.setOpaque(true); // Make the button opaque
                        btnAdd1.setForeground(Color.decode("#EA6A47"));
                        btnAdd1.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd1.setBackground(Color.WHITE);
                    }
                });

                btnAdd1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the values from the text fields
                        GroupeDAO groupeDAO = new GroupeDAO();

                        String idGroupe = IDField.getText();

                        String idCntfield = IDContactField.getText();

                        if(idGroupe.isEmpty() | idCntfield.isEmpty()){
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty
                        }


                        int id = Integer.parseInt(idCntfield);

                        try {
                            // Call the AddContactGroup method
                            groupeDAO.AddContactGroup(idGroupe,id);
                            JOptionPane.showMessageDialog(frame, "Contact information inserted successfully in "+ idGroupe);
                        } catch (Exception ex) {
                            // Handle SQLException
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "Error updating contact information: " + ex.getMessage());
                        }
                    }
                });

                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(nomGroupeLabel)
                                        .addComponent(nomGroupeField, 300, 300, 300))
                                .addComponent(btnAdd, 100, 100, 100)
                                .addComponent(titleLabel1)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(IDContactLabel)
                                        .addComponent(IDContactField, 300, 300, 300))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(IDLabel)
                                        .addComponent(IDField, 300, 300, 300))
                                .addComponent(btnAdd1, 100, 100, 100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(titleLabel,100,100,100)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nomGroupeLabel)
                                        .addComponent(nomGroupeField))
                                .addComponent(btnAdd, 40, 40, 40)
                                .addComponent(titleLabel1,100,100,100)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(IDContactLabel)
                                        .addComponent(IDContactField))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(IDLabel)
                                        .addComponent(IDField))
                                .addComponent(btnAdd1, 40, 40, 40)
                );


                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();

                gbcContent.gridx = 1;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 0.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 10, 10, 10);
                mainPanel.add(panelContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JButton button7 = new JButton("Supprimer un groupe");
        button7.setPreferredSize(new Dimension(150, 50));
        button7.setFont(new Font("Verdana", Font.BOLD, 16));
        button7.setForeground(Color.white);
        button7.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button7.setBackground(Color.decode("#EA6A47"));
                button7.setOpaque(true); // Make the button opaque
                button7.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button7.setBackground(Color.decode("#555CB3"));
                button7.setOpaque(true); // Make the button opaque
                button7.setContentAreaFilled(true);
            }
        });

        button7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // Create and configure the panel for adding a contact
                JPanel panelContact = new JPanel();
                panelContact.setPreferredSize(new Dimension(1000, 1000));
                panelContact.setBackground(Color.WHITE);

                JLabel titleLabel = new JLabel("Supprimer Groupe");
                titleLabel.setFont(new Font("Georgia", Font.BOLD, 30));
                titleLabel.setForeground(Color.decode("#EA6A47"));

                // Create and configure the text fields and labels for the contact details
                JLabel IDLabel = new JLabel("Nom de Groupe :");
                JTextField IDField = new JTextField(20);
                IDLabel.setForeground(Color.BLACK);
                IDLabel.setFont(new Font("Georgia", Font.BOLD, 16));
                IDField.setPreferredSize(new Dimension(IDField.getPreferredSize().width, 35));

                // Add a button to submit
                JButton btnAdd = new JButton("Submit");
                btnAdd.setFont(new Font("Georgia",1,14));

                btnAdd.setForeground(Color.decode("#EA6A47"));
                btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                btnAdd.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setContentAreaFilled(true); // Fill the content area with the background color
                        btnAdd.setBackground(Color.decode("#EA6A47"));
                        btnAdd.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        btnAdd.setOpaque(true); // Make the button opaque
                        btnAdd.setForeground(Color.decode("#EA6A47"));
                        btnAdd.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        btnAdd.setBackground(Color.WHITE);
                    }
                });

                btnAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GroupeDAO grp = new GroupeDAO();
                        // Get the values from the text fields
                        String pId = IDField.getText();

                        // Checking if the idfeild is empty
                        if (pId.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Veuillez remplir le champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if any field is empty

                        }

                        try {
                            grp.DeleteGroup(pId);
                            JOptionPane.showMessageDialog(frame, "Le groupe a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(titleLabel)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(IDLabel)
                                        .addComponent(IDField,300,300,300))
                                .addComponent(btnAdd,100,100,100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(titleLabel,100,100,100)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(IDLabel)
                                        .addComponent(IDField,40,40,40))
                                .addComponent(btnAdd,40,40,40)
                );

                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();

                gbcContent.gridx = 1;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 0.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 0, 10, 10);
                mainPanel.add(panelContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });


        JButton button8 = new JButton("Afficher les groupes");
        button8.setPreferredSize(new Dimension(150, 50));
        button8.setFont(new Font("Verdana", Font.BOLD, 16));
        button8.setForeground(Color.white);
        button8.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        button8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button8.setBackground(Color.decode("#EA6A47"));
                button8.setOpaque(true); // Make the button opaque
                button8.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button8.setBackground(Color.decode("#555CB3"));
                button8.setOpaque(true); // Make the button opaque
                button8.setContentAreaFilled(true);
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Remove the existing content panel
                JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(1);
                mainPanel.remove(1);

                // Create and configure the layout for the main panel
                GridBagLayout mainLayout = new GridBagLayout();
                mainPanel.setLayout(mainLayout);

                // Create the panel for adding a contact
                JPanel panelContact = new JPanel();
                panelContact.setBackground(Color.WHITE);


                // Create the table model with column names and initial data
                String[] columnNames = {"Nom de Groupe","Nom", "Prenom", "Phone Professionnel", "Phone Personnel", "Adresse","E-mail Personnel", "Email Professionel", "Genre"};
                Object[][] data = {};
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

                // Create the JTable with the table model
                JTable contactTable = new JTable(tableModel);
                contactTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
                contactTable.setIntercellSpacing(new java.awt.Dimension(2, 1));
                contactTable.setRowHeight(18);

                // Set table appearance and behavior
                contactTable.setFillsViewportHeight(true);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(contactTable);
                // Button to refresh
                JButton refreshBtn = new JButton();
                refreshBtn.setText("Refresh");
                refreshBtn.setFont(new Font("Georgia",1,14));
                refreshBtn.setForeground(Color.decode("#EA6A47"));
                refreshBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));


                refreshBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        refreshBtn.setOpaque(true); // Make the button opaque
                        refreshBtn.setContentAreaFilled(true); // Fill the content area with the background color
                        refreshBtn.setBackground(Color.decode("#EA6A47"));
                        refreshBtn.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        refreshBtn.setOpaque(true); // Make the button opaque
                        refreshBtn.setForeground(Color.decode("#EA6A47"));
                        refreshBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#EA6A47"), 1));
                        refreshBtn.setBackground(Color.WHITE);
                    }
                });

                refreshBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        GroupeDAO groupeDAO = new GroupeDAO();
                        try {
                            // Call the AllGroups method to fetch data
                            List<Object> groups = groupeDAO.AllGroups();

                            // Clear the table model
                            tableModel.setRowCount(0);

                            // Populate the table model with the fetched data
                            for (int i = 0; i < groups.size(); i += 2) {
                                String groupName = (String) groups.get(i);
                                Contact contact = (Contact) groups.get(i + 1);

                                Object[] row = new Object[] {
                                        groupName,
                                        contact.getNom(),
                                        contact.getPrenom(),
                                        contact.getTelephone1(),
                                        contact.getTelephone2(),
                                        contact.getAdresse(),
                                        contact.getEmailPer(),
                                        contact.getEmailPro(),
                                        contact.getGenre()
                                };

                                tableModel.addRow(row);
                            }
                        } catch (SQLException ex) {
                            // Handle SQLException
                            ex.printStackTrace();
                        } catch (DataBaseException ex) {
                            // Handle DataBaseException
                            ex.printStackTrace();
                        }
                    }



                });


                // Create and configure the layout for the panel
                GroupLayout layout = new GroupLayout(panelContact);
                panelContact.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                // Set the horizontal and vertical layout groups
                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scrollPane)
                                .addComponent(refreshBtn,100,100,100)
                );

                layout.setVerticalGroup(
                        layout.createSequentialGroup()
                                .addComponent(scrollPane)
                                .addComponent(refreshBtn,40,40,40)
                );

                // Add the panel for adding a contact to the main panel
                GridBagConstraints gbcContent = new GridBagConstraints();
                gbcContent.gridx = 0;
                gbcContent.gridy = 1;
                gbcContent.weightx = 1.0;
                gbcContent.weighty = 1.0;
                gbcContent.fill = GridBagConstraints.BOTH;
                gbcContent.insets = new Insets(10, 0, 10, 10);
                mainPanel.add(panelContact, gbcContent);

                // Repaint the main panel to update the changes
                mainPanel.revalidate();
                mainPanel.repaint();

                // Adjust the preferred size of panelContact based on available space
                panelContact.setPreferredSize(new Dimension(1000, 70));
            }

        });


        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 10, 0);

        panel.add(button1, gbc);
        panel.add(button2, gbc);
        panel.add(button3, gbc);
        panel.add(button4, gbc);
        panel.add(button5, gbc);
        panel.add(button6, gbc);
        panel.add(button7, gbc);
        panel.add(button8, gbc);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel titreLabel = new JLabel("C-Chat: Your Contact Management");
        titreLabel.setPreferredSize(new Dimension(titreLabel.getPreferredSize().width, 100));
        titreLabel.setFont(new Font("Georgia", Font.BOLD, 30));
        titreLabel.setForeground(Color.BLACK);
        titreLabel.setHorizontalAlignment(JLabel.CENTER);

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 0.0;
        gbcMain.fill = GridBagConstraints.HORIZONTAL;
        gbcMain.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(titreLabel, gbcMain);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);

        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 200));
        imagePanel.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("src/GestionContact/ihm/Icons/Untitled design.png"));

        JPanel imagePane = new JPanel();
        imagePane.setBackground(Color.WHITE);
        imagePane.add(imageLabel);

        GridBagConstraints gbcImage = new GridBagConstraints();
        gbcImage.gridx = 0;
        gbcImage.gridy = 0;
        gbcImage.weightx = 0.0;
        gbcImage.weighty = 1.0;
        gbcImage.anchor = GridBagConstraints.WEST;
        gbcImage.insets = new Insets(10, 10, 10, 10);
        contentPanel.add(imagePane, gbcImage);

        // Add rectangle panel with text fields
        JPanel rectanglePanel = new JPanel();
        rectanglePanel.setPreferredSize(new Dimension(300, 200));
        rectanglePanel.setBackground(Color.WHITE);
        rectanglePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbcRectangle = new GridBagConstraints();
        gbcRectangle.gridx = 0;
        gbcRectangle.gridy = GridBagConstraints.RELATIVE;
        gbcRectangle.weightx = 1.0;
        gbcRectangle.weighty = 1.0;
        gbcRectangle.fill = GridBagConstraints.HORIZONTAL;
        gbcRectangle.insets = new Insets(10, 10, 10, 10);


        JLabel titleLabel = new JLabel("Maîtrisez vos Contacts avec Efficacité");
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));
        titleLabel.setPreferredSize(new Dimension(titleLabel.getPreferredSize().width, 50));
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        JTextArea textLabel = new JTextArea("Notre application est conçue pour faciliter l'organisation,\n" +
                "le stockage et l'accès aux informations relatives aux contacts.\n" +
                "Elle permet aux utilisateurs de gérer de manière efficace\n" +
                "et centralisée leurs listes de contacts, qu'il s'agisse de contacts\n" +
                "personnels ou professionnels.\n" +
                "Elle offre la possibilité d'ajouter de nouveaux contacts en saisissant\n" +
                "les informations pertinentes telles que le nom, l'adresse,\n" +
                "le numéro de téléphone,  l'adresse e-mail, etc.\n" +
                "L'application permet également de regrouper les contacts\n" +
                "par catégories ou étiquettes, ce qui facilite\n" +
                "leur classification et leur recherche ultérieure.");

        textLabel.setFont(new Font("Verdana", 0, 15));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.WHITE);
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(textLabel, BorderLayout.CENTER);

        rectanglePanel.add(textPanel, gbcRectangle);


        JPanel TitlePane = new JPanel();
        TitlePane.setPreferredSize(new Dimension(300, 30));
        TitlePane.setBackground(Color.WHITE);

        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 1;
        gbcTitle.weightx = 0.0;
        gbcTitle.weighty = 0.0;
        gbcTitle.fill = GridBagConstraints.HORIZONTAL;
        gbcTitle.insets = new Insets(10, 10, 10, 10);


        JTextArea text = new JTextArea("© Cette application est dévelopée par Houda Moudni | Java");
        text.setFont(new Font("Calibri", Font.BOLD, 13));
        TitlePane.add(text,gbcTitle);

        GridBagConstraints gbcContent = new GridBagConstraints();
        gbcContent.gridx = 1;
        gbcContent.gridy = 0;
        gbcContent.weightx = 1.0;
        gbcContent.weighty = 0.0;
        gbcContent.fill = GridBagConstraints.BOTH;
        gbcContent.insets = new Insets(10, 0, 10, 10);
        contentPanel.add(rectanglePanel, gbcContent);
        contentPanel.add(TitlePane, gbcTitle);

        GridBagConstraints gbcMainContent = new GridBagConstraints();
        gbcMainContent.gridx = 0;
        gbcMainContent.gridy = 1;
        gbcMainContent.weightx = 1.0;
        gbcMainContent.weighty = 1.0;
        gbcMainContent.fill = GridBagConstraints.BOTH;
        gbcMainContent.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(contentPanel, gbcMainContent);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.WEST);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
