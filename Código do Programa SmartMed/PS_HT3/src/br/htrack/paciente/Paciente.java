package br.htrack.paciente;

public class Paciente {
	private String nome;
	private long cpf;
	private String status;

	public Paciente(String nome, long cpf, String status) {
		this.nome = nome;
		this.cpf = cpf;
		this.status = status;
	}

	public String getDados() {
		String aux = "";
		aux += "Nome: " + nome + "\n";
		aux += "CPF: " + cpf + "\n";
		aux += "Status: " + status + "\n";
		return aux;
	}

	public String getNome() {
		return nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

}