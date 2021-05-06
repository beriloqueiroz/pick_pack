package Interface;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Pedido;
import base.lerArquivo;
import dados_db.dao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

import javax.swing.JTextPane;

public class excluir extends JFrame {
	static JTextPane empresaLb = new JTextPane();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static excluir frame1;
	private static excluir frame2;
	private static excluir frame3;
	private JTextField tf_codBarras_embalagem;
	private JTextField tf_codBarras_montagem;
	boolean flag = true;
	public static int tamanho_fonte = 50;
	static String operacao = "";
	static String empresa = "";
	public static JLabel lblEmpresa;
	public static JTextPane ref_usuario = new JTextPane();

	private void pegarResolucao() {
		this.setSize(758, 730);

	}

	public static String PastaCorrente() {
		try {
			String caminho = lerArquivo.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
			System.out.println(caminho);
			return caminho;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static int readConfig() {
		int a = -1;
		String file = PastaCorrente() + "config_excluir.txt";
		String[] aux = lerArquivo.ler(file).split(";");
		String empresa_aux = aux[1].replace("empresa:", "");
		String processo_aux = aux[2].replace("processo:", "");
		if (!empresa_aux.equals("2") && !empresa_aux.equals("1"))
			;
		else if (!processo_aux.equals("embalagem") && !processo_aux.equals("montagem"))
			;
		else {
			a = 1;
			operacao = processo_aux;
			empresa = empresa_aux;
			empresaLb.setText(empresa);
		}
		return a;
	}

	public static void main(String[] args) {
		try {
			Thread a = new Thread(t1);
			a.start();
		} catch (Exception e) {
			System.out.println("erro main: " + e.getMessage());
		}
	}

	public static Runnable t1 = new Runnable() {
		public void run() {
			try {
				frame1 = new excluir();
				frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame1.setVisible(true);
			} catch (Exception e) {
				System.out.println("erro frame t1: " + e.getMessage());
			}
		}
	};

	public static Runnable t2 = new Runnable() {
		public void run() {
			try {
				frame2 = new excluir();
				frame2.setVisible(true);
			} catch (Exception e) {
				System.out.println("erro frame t2: " + e.getMessage());
			}
		}
	};

	public static Runnable t3 = new Runnable() {
		public void run() {
			try {
				frame3 = new excluir();
				frame3.setVisible(true);
			} catch (Exception e) {
				System.out.println("erro frame t3: " + e.getMessage());
			}
		}
	};

	public excluir() {
		setTitle("excluir (Versão 1.0.0)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 712);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pegarResolucao();
		tf_codBarras_embalagem = new JTextField();
		tf_codBarras_embalagem.setEnabled(true);
		tf_codBarras_embalagem.setFont(new Font("Tahoma", Font.BOLD, 50));
		tf_codBarras_embalagem.setBounds(10, 530, 722, 132);
		contentPane.add(tf_codBarras_embalagem);
		tf_codBarras_embalagem.setColumns(10);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblEmpresa.setBounds(98, 42, 223, 92);
		contentPane.add(lblEmpresa);
		empresaLb.setForeground(Color.DARK_GRAY);
		empresaLb.setFont(new Font("Tahoma", Font.PLAIN, 50));

		empresaLb.setBounds(331, 56, 211, 70);
		contentPane.add(empresaLb);

		ref_usuario.setFont(new Font("Tahoma", Font.PLAIN, 50));
		ref_usuario.setBounds(98, 159, 547, 70);
		contentPane.add(ref_usuario);

		JLabel lblExcluirEmbalagem = new JLabel("Excluir Embalagem:");
		lblExcluirEmbalagem.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblExcluirEmbalagem.setBounds(71, 460, 505, 61);
		contentPane.add(lblExcluirEmbalagem);

		tf_codBarras_montagem = new JTextField();
		tf_codBarras_montagem.setForeground(Color.DARK_GRAY);
		tf_codBarras_montagem.setFont(new Font("Tahoma", Font.BOLD, 50));
		tf_codBarras_montagem.setEnabled(true);
		tf_codBarras_montagem.setColumns(10);
		tf_codBarras_montagem.setBounds(10, 317, 722, 132);
		contentPane.add(tf_codBarras_montagem);

		JLabel lblExcluirMontagem = new JLabel("Excluir Montagem:");
		lblExcluirMontagem.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblExcluirMontagem.setBounds(71, 247, 505, 61);
		contentPane.add(lblExcluirMontagem);

		readConfig();

		tf_codBarras_embalagem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					Pedido p = new Pedido();
					dao d = new dao();
					p.setEmpresa(empresa);
					p.setOperacao("embalagem");
					String Refuser = tf_codBarras_embalagem.getText().substring(0, 2);
					if (d.existeUsuario(Refuser, Refuser)) {
						String usuario = d.RefUsuario(Refuser);
						ref_usuario.setText(usuario);
						p.setPedido(tf_codBarras_embalagem.getText().substring(2,
								tf_codBarras_embalagem.getText().length()));
						p.setUsuario(usuario);
						if (d.existePedido(p)) {
							d.excluir(p);
							if (flag) {
								contentPane.setBackground(Color.GREEN);
								flag = false;
							} else {
								contentPane.setBackground(Color.BLUE);
								flag = true;
							}
							if (d.existePedido(p)) {
								JOptionPane.showMessageDialog(null, "Item não excluído (erro) ", "ERROR",
										JOptionPane.WARNING_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Item excluído (=D)", "ERROR",
										JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JLabel label = new JLabel("Esse pedido não foi bipado na embalagem!!");
							label.setFont(new Font("Arial", Font.BOLD, 40));
							JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Esse usuario não existe!!");
					}
					tf_codBarras_embalagem.setText("");
					tf_codBarras_embalagem.requestFocus();
				}
			}
		});

		tf_codBarras_montagem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					Pedido p = new Pedido();
					dao d = new dao();
					p.setEmpresa(empresa);
					p.setOperacao("montagem");
					String Refuser = tf_codBarras_montagem.getText().substring(0, 2);
					if (d.existeUsuario(Refuser, Refuser)) {
						String usuario = d.RefUsuario(Refuser);
						ref_usuario.setText(usuario);
						p.setPedido(
								tf_codBarras_montagem.getText().substring(2, tf_codBarras_montagem.getText().length()));
						p.setUsuario(usuario);
						if (d.existePedido(p)) {
							d.excluir(p);
							if (flag) {
								contentPane.setBackground(Color.GREEN);
								flag = false;
							} else {
								contentPane.setBackground(Color.BLUE);
								flag = true;
							}
							if (d.existePedido(p)) {
								JOptionPane.showMessageDialog(null, "Item não excluído (erro) ", "ERROR",
										JOptionPane.WARNING_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Item excluído (=D)", "ERROR",
										JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JLabel label = new JLabel("Esse pedido não foi bipado na montagem!!");
							label.setFont(new Font("Arial", Font.BOLD, 40));
							JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Esse usuario não existe!!");
					}
					tf_codBarras_montagem.setText("");
					tf_codBarras_montagem.requestFocus();
				}
			}
		});

	}

	public boolean isNumber(String x) {
		boolean ret = false;
		switch (x) {
			case "1":
				ret = true;
				break;
			case "2":
				ret = true;
				break;
			case "3":
				ret = true;
				break;
			case "4":
				ret = true;
				break;
			case "5":
				ret = true;
				break;
			case "6":
				ret = true;
				break;
			case "7":
				ret = true;
				break;
			case "8":
				ret = true;
				break;
			case "9":
				ret = true;
				break;
		}
		return ret;
	}
}
