package com.a2;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class chat {
    public static void main(String []args){
		       final JFrame frame = new JFrame("Hospital chat");
		       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		       frame.setSize(400,400);
		       JMenuBar mb = new JMenuBar();
		        JTextArea textA=new JTextArea("",5,10);

		        //Creating the panel at bottom and adding components
		        JPanel panel = new JPanel(); // the panel is not visible in output
		        JLabel label = new JLabel("Enter Text");
		        final JTextField tf = new JTextField(10); // accepts up to 10 characters
		        JButton send = new JButton("Send");
		        JButton reset = new JButton("Reset");
		        panel.add(label); // Components Added using Flow Layout
		        panel.add(tf);
		        panel.add(send);
		        panel.add(reset);
		        // Text Area at the Center
		        final JTextArea ta = new JTextArea();
		    	

		        //Adding Components to the frame.
		        frame.getContentPane().add(BorderLayout.SOUTH, panel);
		        frame.getContentPane().add(BorderLayout.NORTH, mb);
		        frame.getContentPane().add(BorderLayout.CENTER, ta);

		        frame.setVisible(true);
		        final dialog dia = new dialog();
		        dia.initial();
		        
		        send.addActionListener(new ActionListener() {  
		            public void actionPerformed(ActionEvent e) {
		            	String content = tf.getText();
		            	ta.append(content + "\n");
		            	String result = dia.answer(content);
		            	ta.append(result + "\n");
		            	tf.setText("");
		            	if (content.equals("quit")){
		            		frame.dispose();
		            	}
		            }
		        });		    
    }
}
