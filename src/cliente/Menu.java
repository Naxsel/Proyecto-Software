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
import java.lang.NumberFormatException;

/**
 *
 * @author adrian
 */
public class Menu extends javax.swing.JFrame {
	
	private Vector<util.Books> actualVec;
	private Vector<util.Books> seleccion;
	private Vector<JCheckBox> checkVec;

	private static String host;
	private static int port;
	
    /**
     * Creates new form ClienteUI
     */
    public Menu() {
        initComponents();
        actualVec = new Vector<util.Books>();
    }
     
    /**
     * Añade libros a la selección
     * @param vec: vector de libros a añadir
     */
    public void AddSeleccion(Vector<util.Books> vec) {
    	for (util.Books libro : vec) {
    		if (!actualVec.contains(libro)) {
    			actualVec.add(libro);
    		}
    	}
    	try {
    		MuestraSeleccion(actualVec);
    	} catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
    
    /**
     * Borra libros de la selección
     * @param vec: vector de libros a borrar
     * @throws java.lang.ClassCastException, java.lang.NullPointerException
     */
    public void DelSeleccion(Vector<util.Books> vec) throws ClassCastException, NullPointerException {
    	actualVec.removeAll(vec);
    	try {
    		MuestraSeleccion(actualVec);
    	} catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
    
    /**
     * Muestra un vector de libros
     * @param vec: vector de libros a mostrar
     * @throws java.io.IOException
     */
    public void MuestraSeleccion(Vector<util.Books> vec) throws IOException {
        Image img;
        
        // instanciamos los vectores
        seleccion = new Vector<util.Books>();
        checkVec = new Vector<JCheckBox>();
        
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
		    checkVec.add(new JCheckBox("Eliminar", false));
		    
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        boton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { 
			"ISBN", "Título", "Autor", "Genero", "Editorial", "Fecha" }));
        jToolBar1.add(jComboBox1);

        jTextField1.setPreferredSize(new java.awt.Dimension(100, 28));
        jToolBar1.add(jTextField1);

        jButton1.setText("Buscar");
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
        boton.setText("Eliminar selección");
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
                //.addGap(0, 270, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Generación del Request
        String texto = jTextField1.getText();
        int seleccion = jComboBox1.getSelectedIndex();
        util.Request<String> peticion;
        switch (seleccion) {
            case 0: peticion = new util.Request<>(util.Request.TypeRequest.ISBN,
						util.Request.Sort.NONE, texto);
                    break;
			case 1: peticion = new util.Request<>(util.Request.TypeRequest.TITLE,
						util.Request.Sort.NONE, texto);
                    break;
			case 2: peticion = new util.Request<>(util.Request.TypeRequest.AUTHOR,
						util.Request.Sort.NONE, texto);
                    break;
			case 3: peticion = new util.Request<>(util.Request.TypeRequest.GENRE,
						util.Request.Sort.NONE, texto);
                    break;
			case 4: peticion = new util.Request<>(util.Request.TypeRequest.PUBLISHER,
						util.Request.Sort.NONE, texto);
                    break;
            default: peticion = new util.Request<>(util.Request.TypeRequest.DATE,
						util.Request.Sort.NONE, texto);
                    break;
        }

        try {
            // Generación del proceso cliente
            util.ClientSend dummy = new util.ClientSend("localhost", 8080);
            // Envío del request y recepción de reply
            util.Reply respuesta = dummy.send2Server(peticion);
            // PARA COMPROBACIONES
            // System.out.println("**" + (Vector)respuesta.getDummy());
            // tratamiento de la respuesta
            if (respuesta.getReply() == util.Reply.TypeReply.OK) {
                Busqueda nuevaBusqueda = new Busqueda(this);
                nuevaBusqueda.NuevoMensaje("<html>No se han encontrado libros correspondientes<br />a su búsqueda.</html>");
                nuevaBusqueda.setVisible(true);
            } else if (respuesta.getReply() == util.Reply.TypeReply.BOOK) {
                Busqueda nuevaBusqueda = new Busqueda(this);
                Vector<util.Books> vec = (Vector<util.Books>)respuesta.getDummy();
                nuevaBusqueda.NuevoLibro(vec);
                nuevaBusqueda.setVisible(true);
            } else if (respuesta.getReply() == util.Reply.TypeReply.VECTOR) {
                Busqueda nuevaBusqueda = new Busqueda(this);
                Vector<util.Books> vec = (Vector<util.Books>)respuesta.getDummy();
                if (vec.size() == 0) {
                	nuevaBusqueda.NuevoMensaje("<html>No se han encontrado libros correspondientes<br />a su búsqueda.</html>");
                } else {
                	nuevaBusqueda.NuevoLibro(vec);
                }
                nuevaBusqueda.setVisible(true);
            } else {
            	Busqueda nuevaBusqueda = new Busqueda(this);
            	nuevaBusqueda.NuevoMensaje("<html>Se produjo un error al acceder a la base de datos.<br />Por favor, inténtelo de nuevo.</html>");
                nuevaBusqueda.setVisible(true);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void botonActionPerformed(java.awt.event.ActionEvent evt) {
    	for (int i = 0; i < checkVec.size(); i++) {
    		if (checkVec.get(i).isSelected()) {
    			seleccion.add(actualVec.get(i));
    		}
    	}
    	DelSeleccion(seleccion);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
		if (args.length != 2){
			System.out.println("Número de parametros erroneos.");
			return;
		}
		host = args[0];
		try{
			port = Integer.parseInt(args[1]);
		} catch(NumberFormatException e){
			System.out.println("Error en el segundo parametro.");
			return;
		}
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton boton;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
