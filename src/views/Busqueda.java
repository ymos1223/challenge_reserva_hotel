package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloReserva;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		modeloReserva.addColumn("Numero de Reserva");
		modeloReserva.addColumn("Fecha Check In");
		modeloReserva.addColumn("Fecha Check Out");
		modeloReserva.addColumn("Valor");
		modeloReserva.addColumn("Forma de Pago");
		cargarTablaReservas();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);
		

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		cargarTablaHuespedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		
		scroll_tableHuespedes.setVisible(true);
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseClicked(MouseEvent e) {
	                if (panel.getSelectedIndex() == 0) {
	                    limpiarTabla(modeloReserva);
	                    buscarReserva(txtBuscar.getText());
	                } else if (panel.getSelectedIndex() == 1) {
	                    limpiarTabla(modeloHuesped);
	                    buscarHuesped(txtBuscar.getText());
	                }
	            }
	        });
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panel.getSelectedIndex() == 0) {
                    editarReserva();
                    limpiarTabla(modeloReserva);
                    cargarTablaReservas();
                    
                } else if (panel.getSelectedIndex() == 1) {
                    editarHuesped(); 
                    limpiarTabla(modeloHuesped);
                    cargarTablaHuespedes();
                }
            }
        });
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		 btnEliminar.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (panel.getSelectedIndex() == 0) {
	                    eliminarReserva();
	                } else if (panel.getSelectedIndex() == 1) {
	                    eliminarHuesped(); 
	                }
	            }
	        });
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void cargarTablaHuespedes() {
		var huespedes = this.huespedController.listarHuespedes();

		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(),
				huesped.getNumeroTelefono(), huesped.getReservaId() }));
	}

	private void cargarTablaReservas() {
		var reservas = this.reservaController.listarReservas();

		reservas.forEach(reserva -> modeloReserva.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
				reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaDePago() }));
	}
	
	private void limpiarTabla(DefaultTableModel modelo) {
		modelo.getDataVector().clear();
    }

    private boolean tieneFilaElegida(JTable tabla) {
        return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
    }
    
    private void limpiarTxtBuscar() {
        txtBuscar.setText("");
        txtBuscar.requestFocus();
    }
    
    private boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private void buscarReserva(String cadena) {

        if (isNumeric(cadena)) {

            int idBusqueda = Integer.valueOf(cadena);

            var reservas = this.reservaController.listarPorBusquedaId(idBusqueda);

            if (reservas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El número de reserva no existe!", "WARNING", JOptionPane.WARNING_MESSAGE);
                limpiarTabla(modeloReserva);
                cargarTablaReservas();
                limpiarTxtBuscar();

            } else {
                reservas.forEach(reserva -> modeloReserva.addRow(
                        new Object[]{
                            reserva.getId(),
                            reserva.getFechaEntrada(),
                            reserva.getFechaSalida(),
                            reserva.getValor(),
                            reserva.getFormaDePago()
                        }
                ));
            }

        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un criterio de busqueda valido!", "WARNING", JOptionPane.WARNING_MESSAGE);
            limpiarTabla(modeloReserva);
            cargarTablaReservas();
            limpiarTxtBuscar();
        }

    }

    private void buscarHuesped(String cadena) {

        if (!cadena.equals("")) {

            var huespedes = this.huespedController.buscarHuesped(cadena);

            if (huespedes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No existen huespedes con este apellido!", "WARNING", JOptionPane.WARNING_MESSAGE);
                limpiarTabla(modeloHuesped);
                cargarTablaHuespedes();
                limpiarTxtBuscar();
            } else {

                huespedes.forEach(huesped -> modeloHuesped.addRow(
                        new Object[]{
                            huesped.getId(),
                            huesped.getNombre(),
                            huesped.getApellido(),
                            huesped.getFechaNacimiento(),
                            huesped.getNacionalidad(),
                            huesped.getNumeroTelefono(),
                            huesped.getReservaId()
                        }
                ));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un criterio de busqueda valido!", "WARNING", JOptionPane.WARNING_MESSAGE);
            limpiarTabla(modeloHuesped);
            cargarTablaHuespedes();
            limpiarTxtBuscar();
        }
    }
    
    private void eliminarHuesped() {
        if (tieneFilaElegida(tbHuespedes)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un Registro");
           
        } else {
        	if(JOptionPane.showConfirmDialog(this, "¿Deseas eliminar la reserva?", "QUESTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        		try {
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

                    var filasModificadas = this.huespedController.eliminarHuesped(id);

                    modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

                    JOptionPane.showMessageDialog(this,
                            String.format("%d Huesped eliminado con éxito!", filasModificadas));
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        		} catch (Exception e) {
        			JOptionPane.showMessageDialog(this, "Ha ocurrido un error en el proceso!", "ERROR", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        		}
    }
    
    private void eliminarReserva() {
        if (tieneFilaElegida(tbReservas)) {
            JOptionPane.showMessageDialog(this, "Por favor, elije una reserva");
        } else {
        	if(JOptionPane.showConfirmDialog(this, "¿Deseas eliminar la reserva?", "QUESTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        		try {
        			
        			Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                        Integer id = Integer.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0).toString());

                        var filasModificadas = this.reservaController.eliminarReserva(id);

                        modeloReserva.removeRow(tbReservas.getSelectedRow());

                        JOptionPane.showMessageDialog(this,
                                String.format("%d Reserva eliminada con éxito!", filasModificadas));
                    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
        			
        		}catch (Exception e) {
        			JOptionPane.showMessageDialog(this, "Ha ocurrido un error en el proceso!", "ERROR", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        }

        
    }
    
    private void editarReserva() {
    	if (tieneFilaElegida(tbReservas)) {
    		JOptionPane.showMessageDialog(this, "Por favor, elije una reserva a modificar!");
    	} else {
    		if(JOptionPane.showConfirmDialog(this, "¿Deseas modificar la reserva?", "QUESTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    			try {
    				Optional.ofNullable(modeloReserva.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                        int idReserva = (int) modeloReserva.getValueAt(tbReservas.getSelectedRow(), 0);
                        String entrada = modeloReserva.getValueAt(tbReservas.getSelectedRow(), 1).toString().trim();
                        Date fechaEntrada = Date.valueOf(entrada);
                        String salida = modeloReserva.getValueAt(tbReservas.getSelectedRow(), 2).toString().trim();
                        Date fechaSalida = Date.valueOf(salida);
                        Double valor = Double.valueOf(modeloReserva.getValueAt(tbReservas.getSelectedRow(), 3).toString().trim());
                        String formaPago = modeloReserva.getValueAt(tbReservas.getSelectedRow(), 4).toString();
                       
                        var filasModificadas = this.reservaController.modificarReserva(idReserva,fechaEntrada, fechaSalida, valor, formaPago);

                        JOptionPane.showMessageDialog(this,
                                String.format("%d Reserva Modificada con éxito!", filasModificadas));
                    }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    				
    			} catch (Exception e) {
    				JOptionPane.showMessageDialog(this, "Ha ocurrido un error en el proceso!", "ERROR", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	}
    	
    	
    }
         		
    private void editarHuesped() {
    	if (tieneFilaElegida(tbHuespedes)) {
    		JOptionPane.showMessageDialog(this, "Por favor, elije un huesped a modificar!");
    	} else {
    		if(JOptionPane.showConfirmDialog(this, "¿Deseas modificar los datos del Huesped?", "QUESTION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    			try {
    				Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                    .ifPresentOrElse(fila -> {
                    	int idHuesped = (int) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0);
                        String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
                        String apellido= modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
                        String fechaN = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
                        Date fechaNacimiento = Date.valueOf(fechaN);
                        String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
                        String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
                        int idReserva = (int) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6);
                   
                        var filasModificadas = this.huespedController.modificarHuesped(idHuesped,idReserva,nombre,apellido,fechaNacimiento,nacionalidad,telefono);

                        JOptionPane.showMessageDialog(this,
                                String.format("%d Huesped Modificado con éxito!", filasModificadas));
                   }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    				
    			} catch (Exception e) {
    				JOptionPane.showMessageDialog(this, "Ha ocurrido un error en el proceso!", "ERROR", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	}
    }
}
