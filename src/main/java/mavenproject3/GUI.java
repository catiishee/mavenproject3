/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author user
 */
public class GUI extends JFrame {

    private JButton importButton;
    private JTree fileTree;
    private ApplicationService service = new ApplicationService();
    private JTable dataTable;

    public GUI() {
        super("Импорт файлов");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        importButton = new JButton("Импортировать файл");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleImportButton();
            }
        });

        JButton createDatabaseButton = new JButton("Создать БД");
        createDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateDatabaseButton();
            }
        });

        dataTable = new JTable(new DefaultTableModel(new Object[]{"Column1", "Column2", "Column3"}, 0));
        JScrollPane tableScroll = new JScrollPane(dataTable);

        JButton filterRegionButton = new JButton("По региону");
        filterRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFilterRegionButton();
            }
        });

        JButton filterCountryButton = new JButton("По стране");
        filterCountryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFilterCountryButton();
            }
        });

        JButton filterCompanyButton = new JButton("По компании");
        filterCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFilterCompanyButton();
            }
        });

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1, 3));
        filterPanel.add(filterRegionButton);
        filterPanel.add(filterCountryButton);
        filterPanel.add(filterCompanyButton);

        fileTree = new JTree();
        fileTree.setModel(new DefaultTreeModel(null));
        JScrollPane treeScroll = new JScrollPane(fileTree);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, tableScroll);
        splitPane.setDividerLocation(400);

        getContentPane().setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.add(importButton);
        northPanel.add(createDatabaseButton);

        getContentPane().add(northPanel, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        getContentPane().add(filterPanel, BorderLayout.SOUTH);
    }

    private void handleImportButton() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON, XML & YAML Files", "json", "xml", "yaml");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(GUI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                service.handle(file);
                fileTree.setModel(new DefaultTreeModel(service.getNodes()));
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Не удалось прочитать файл!");
            }
        }
    }

    private void handleCreateDatabaseButton() {
        service.createDatabase();
    }

    private void handleFilterRegionButton() {
        DefaultTableModel model = service.showConsumptionByRegion();
        dataTable.setModel(model);
    }

    private void handleFilterCountryButton() {
        DefaultTableModel model = service.showConsumptionByCountry();
        dataTable.setModel(model);
    }

    private void handleFilterCompanyButton() {
        DefaultTableModel model = service.showConsumptionByOperator();
        dataTable.setModel(model);
    }
}