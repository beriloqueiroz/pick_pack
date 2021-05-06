package base;

import java.util.Date;

public class Pedido {
	private String ID, Usuario, Pedido, Operacao, Empresa;
	private Date DataHora;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String user) {
		Usuario = user;
	}
	public String getPedido() {
		return Pedido;
	}
	public void setPedido(String referencia) {
		Pedido = referencia;
	}
	public String getOperacao() {
		return Operacao;
	}
	public void setOperacao(String operacao) {
		Operacao = operacao;
	}
	public String getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}
	public Date getDataHora() {
		return DataHora;
	}
	public void setDataHora(Date dataHora) {
		DataHora = dataHora;
	} 
	
	
}
