/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author user
 */
public class GUI extends JFrame {

    private JButton importButton;
    private JTree fileTree;
    private ApplicationService service = new ApplicationService();

    public GUI() {
        super("Импорт файлов");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        importButton = new JButton("Импортировать файл");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });

        fileTree = new JTree();
        fileTree.setModel(new DefaultTreeModel(null));
        JScrollPane treeScroll = new JScrollPane(fileTree);

        getContentPane().add(importButton, BorderLayout.NORTH);
        getContentPane().add(treeScroll, BorderLayout.CENTER);
    }
}
