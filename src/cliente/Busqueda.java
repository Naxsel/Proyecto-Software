/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.border.EmptyBorder;
import util.*;
import java.util.Vector;
import java.util.Comparator;
import java.util.Collections;
import java.io.File;
import java.net.MalformedURLException;

/**
 *
 * @author adrian
 */
public class Busqueda extends javax.swing.JFrame {
	
	private Menu padre;
	
	private Vector<util.Books> actualVec;
	private Vector<util.Books> seleccion;
	private Vector<JCheckBox> checkVec;
	
    /**
     * Creates new form ClienteUI
     */
    public Busqueda(Menu p) {
        initComponents();
        padre = p;
    }

    /**
     * Añade un vector de libros
     * @param vec: vector de libros a mostrar
     * @throws java.io.IOException
     */
    public void NuevoLibro(Vector<util.Books> vec) throws IOException {
        Image img;
        
        // instanciamos los vectores
        actualVec = new Vector<util.Books>();
        seleccion = new Vector<util.Books>();
        checkVec = new Vector<JCheckBox>();
        
        // Guardar vector actual
        actualVec = vec;
        
        // Crear un jPanel
        JPanel panel = new JPanel();
        // Definir alineación vertical
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Definir margenes
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Añadir todos los libros del vector
        boolean primera = true; // para evitar el separador la primera vez
        for (util.Books libro : vec) {
            // Cargar la url de la imagen
			
			try{
            	URL url = new URL(libro.getImg());
				img = ImageIO.read(url);
			} catch(MalformedURLException e){
				System.out.println("Error en la imagen a cargar.");
				File url = new File("config" + 
					System.getProperty("file.separator") + "default_img.png");
				img = ImageIO.read(url);
			};

            // Crear el JLabel con la imagen y el texto
            JLabel label = new JLabel(libro.toHtml(), new ImageIcon(img.getScaledInstance(125, 150, 2)), 2);
		    
		    // Instanciar el checkbox correspondiente
		    checkVec.add(new JCheckBox("Añadir", false));
		    
            // Si no es la primera, añadir el separador al JPanel
            if (!primera) {
                panel.add(Box.createVerticalStrut(20));
                panel.add(new JSeparator(SwingConstants.HORIZONTAL));
                panel.add(Box.createVerticalStrut(20));
            } else {
                primera = false;
            }
            
            // Añadir el JLabel al JPanel
            panel.add(label);
            
            // Añadir el JCheckBox
            panel.add(checkVec.lastElement());
        }
        
        // Mostrar el nuevo JPanel en el JScrollPane
        jScrollPane1.setViewportView(panel);
        
    }
    
    /**
     * Muestra una ventana emergente con un mensaje
     * @param mensaje: string a mostrar
     * @throws java.io.IOException
     */
    public void NuevoMensaje(String mensaje) throws IOException {
        
        // Crear un jPanel
        JPanel panel = new JPanel();
        // Definir alineación vertical
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Definir margenes
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Crear el JLabel con el mensaje
        JLabel label = new JLabel(mensaje);
        
        // Añadir el JLabel al JPanel
        panel.add(label);
        
        // Mostrar el nuevo JPanel en el JScrollPane
        jScrollPane1.setViewportView(panel);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        //jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        boton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(this.HIDE_ON_CLOSE);

        jToolBar1.setRollover(true);

        //jLabel1.setText("Puede reordenar su búsqueda:");
        //jToolBar1.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { 
			"ISBN", "Título", "Autor", "Editorial", "Género", "Fecha" }));
        jToolBar1.add(jComboBox1);

        jButton1.setText("Reordenar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        
        // Separación horizontal
        jToolBar1.add(Box.createHorizontalStrut(20));
        
        //configurar el botón para añadir a la lista
        boton.setText("Añadir Selección a la lista");
	    boton.setFocusable(false);
	    boton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	    boton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
	    boton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            botonActionPerformed(evt);
	        }
	    });
	    // Añadir el botón
        jToolBar1.add(boton);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 
					30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 
					264, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!actualVec.isEmpty()) {
        	try {
				int seleccion = jComboBox1.getSelectedIndex();
				switch (seleccion) {
				    case 0: Collections.sort(actualVec, new Comparator<util.Books>(){
								public int compare(util.Books primero, util.Books segundo) {
									 return primero.getIsbn().compareTo(segundo.getIsbn());
								}
							});
				            break;
					case 1: Collections.sort(actualVec, new Comparator<util.Books>(){
								public int compare(util.Books primero, util.Books segundo) {
									 return primero.getTitle().compareTo(segundo.getTitle());
								}
							});
				            break;
					case 2: Collections.sort(actualVec, new Comparator<util.Books>(){
								public int compare(util.Books primero, util.Books segundo) {
									 return primero.getAuthor().compareTo(segundo.getAuthor());
								}
							});
				            break;
					case 3: Collections.sort(actualVec, new Comparator<util.Books>(){
								public int compare(util.Books primero, util.Books segundo) {
									 return primero.getPublisher().compareTo(segundo.getPublisher());
								}
							});
				            break;
					case 4: Collections.sort(actualVec, new Comparator<util.Books>(){
								public int compare(util.Books primero, util.Books segundo) {
									 return primero.getGenre().compareTo(segundo.getGenre());
								}
							});
				            break;
				    default: Collections.sort(actualVec, new Comparator<util.Books>(){
								public int compare(util.Books primero, util.Books segundo) {
									 return primero.getDate().compareTo(segundo.getDate());
								}
							});
				            break;
				}
        		NuevoLibro(actualVec);
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void botonActionPerformed(java.awt.event.ActionEvent evt) {
    	for (int i = 0; i < checkVec.size(); i++) {
    		if (checkVec.get(i).isSelected()) {
    			seleccion.add(actualVec.get(i));
    		}
    	}
    	padre.AddSeleccion(seleccion);
    }
    
    /**
     * @param args the command line arguments
     */
     
//    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
/*        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(
				java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(
				java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(
				java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda.class.getName()).log(
				java.util.logging.Level.SEVERE, null, ex);
        }
*/
        /* Create and display the form */
/*        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Busqueda(this).setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton boton;
    private javax.swing.JComboBox jComboBox1;
    //private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
