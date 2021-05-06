package Interface;

import java.awt.Color;
import java.awt.Dimension;

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
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

import javax.swing.JTextPane;

public class montagem extends JFrame {
	static JTextPane empresaLb = new JTextPane();
	static JTextPane processoLb = new JTextPane();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static montagem frame1;
	private static montagem frame2;
	private static montagem frame3;
	private JTextField tf_codBarras;
	boolean flag = true;
	public static int tamanho_fonte = 50;
	static String operacao = "";
	static String empresa = "";
	public static JLabel lblEmpresa;
	public static JLabel lblProcesso;
	public static JTextPane ref_usuario = new JTextPane();

	private void pegarResolucao() {
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension dimensao = t.getScreenSize();
		this.setSize((dimensao.width + 5), (dimensao.height - 38));

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
		String file = PastaCorrente() + "config_montagem.txt";
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
			processoLb.setText(operacao);
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
				frame1 = new montagem();
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
				frame2 = new montagem();
				frame2.setVisible(true);
			} catch (Exception e) {
				System.out.println("erro frame t2: " + e.getMessage());
			}
		}
	};

	public static Runnable t3 = new Runnable() {
		public void run() {
			try {
				frame3 = new montagem();
				frame3.setVisible(true);
			} catch (Exception e) {
				System.out.println("erro frame t3: " + e.getMessage());
			}
		}
	};

	public montagem() {
		setTitle("montagem (Versão 1.0.0)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1386, 712);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pegarResolucao();
		processoLb.setFont(new Font("Tahoma", Font.PLAIN, 50));

		processoLb.setBounds(903, 56, 309, 70);
		contentPane.add(processoLb);
		tf_codBarras = new JTextField();
		tf_codBarras.setEnabled(true);
		tf_codBarras.setFont(new Font("Tahoma", Font.BOLD, 50));
		tf_codBarras.setBounds(10, 371, 1332, 291);
		contentPane.add(tf_codBarras);
		tf_codBarras.setColumns(10);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblEmpresa.setBounds(98, 23, 223, 125);
		contentPane.add(lblEmpresa);

		lblProcesso = new JLabel("Processo:");
		lblProcesso.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblProcesso.setBounds(666, 23, 223, 125);
		contentPane.add(lblProcesso);
		empresaLb.setFont(new Font("Tahoma", Font.PLAIN, 50));

		empresaLb.setBounds(331, 56, 211, 70);
		contentPane.add(empresaLb);

		ref_usuario.setFont(new Font("Tahoma", Font.PLAIN, 50));
		ref_usuario.setBounds(214, 205, 547, 70);
		contentPane.add(ref_usuario);

		readConfig();

		tf_codBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					Pedido p = new Pedido();
					dao d = new dao();
					p.setEmpresa(empresa);
					p.setOperacao(operacao);
					String Refuser = tf_codBarras.getText().substring(0, 2);
					if (d.existeUsuario(Refuser, Refuser)) {
						String usuario = d.RefUsuario(Refuser);
						ref_usuario.setText(usuario);
						p.setPedido(tf_codBarras.getText().substring(2, tf_codBarras.getText().length()));
						p.setUsuario(usuario);
						if (!d.existePedido(p)) {
							d.inserir(p);
							if (flag) {
								contentPane.setBackground(Color.GREEN);
								flag = false;
							} else {
								contentPane.setBackground(Color.BLUE);
								flag = true;
							}
						} else {
							JLabel label = new JLabel("Esse código já foi inserido antes!!");
							label.setFont(new Font("Arial", Font.BOLD, 40));
							JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Esse usuario não existe!!");
					}
					tf_codBarras.setText("");
					tf_codBarras.requestFocus();
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
