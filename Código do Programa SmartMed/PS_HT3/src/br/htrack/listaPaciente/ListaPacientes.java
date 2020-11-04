package br.htrack.listaPaciente;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import br.htrack.paciente.Paciente;

public class ListaPacientes {
	public class NO {
		Paciente dado;
		NO prox;

		public Paciente getDado() {
			return dado;
		}
	}

	private NO lista = null;

	public boolean isEmpty() {
		if (lista == null)
			return true;
		else
			return false;
	}

	public void add(Paciente elem) {
		NO novo = new NO();
		novo.dado = elem;
		if (isEmpty()) {
			lista = novo;
			novo.prox = null;
		} else if (novo.dado.getCpf() < lista.dado.getCpf()) {
			novo.prox = lista;
			lista = novo;
		} else {
			NO aux = lista;
			boolean achou = false;
			while (aux.prox != null && !achou) {
				if (aux.prox.dado.getCpf() < novo.dado.getCpf())
					aux = aux.prox;
				else
					achou = true;
			}
			novo.prox = aux.prox;
			aux.prox = novo;
		}
	}

	public boolean remove(Paciente elem) {
		boolean ok = false;
		if (!isEmpty()) {
			if (elem.getCpf() == lista.dado.getCpf()) {
				lista = lista.prox;
				ok = true;
			} else {
				NO aux = lista;
				boolean achou = false;
				while (aux.prox != null && !achou) {
					if (aux.prox.dado.getCpf() != elem.getCpf()) {
						aux = aux.prox;
					} else
						achou = true;
				}
				if (achou) {
					aux.prox = aux.prox.prox;
					ok = true;
				}
			}
		}
		return ok;
	}

	public int count() {
		int cont = 0;
		NO aux = lista;
		while (aux != null) {
			cont++;
			aux = aux.prox;
		}
		return cont;
	}

	public void show() {
		NO aux = lista;
		String aux2 = "";
		while (aux != null) {
			aux2 += ("Nome: " + aux.dado.getNome() + "   |   ");
			aux2 += ("CPF: " + aux.dado.getCpf() + "   |   ");
			aux2 += ("Status: " + aux.dado.getStatus() + "\n");

			aux = aux.prox;
		}
		showMessageDialog(null, "************Lista de Pacientes************\n" + aux2);
	}

	public String select(long cpf) {
		NO aux = lista;
		while (aux != null) {
			if (aux.dado.getCpf() == cpf)
				return aux.dado.getDados();
			aux = aux.prox;
		}
		return "CPF NÃO ENCONTRADO!";
	}

	public String confere(long cpf) {
		NO aux = lista;
		while (aux != null) {
			if (aux.dado.getCpf() == cpf) {
				return "Paciente já foi cadastrado.";
			}

			aux = aux.prox;
		}

		return "ok";

	}

	public boolean ganhaCPF(long cpfPesq) {
		NO aux = lista;
		while (aux != null) {
			if (aux.dado.getCpf() == cpfPesq) {
				return true;
			}

			aux = aux.prox;
		}
		return false;

	}

	public String ganhaStatus(long cpfPesq) {
		NO aux = lista;
		while (aux != null) {
			if (aux.dado.getCpf() == cpfPesq) {
				return aux.dado.getStatus();
			}

			aux = aux.prox;
		}
		return "";
	}

	public void alteraStatus(long cpfPesq, String opcaoLib) {
		NO aux = lista;
		boolean achou = false;
		while (aux != null) {
			if (aux.dado.getCpf() == cpfPesq) {
				if (opcaoLib.equalsIgnoreCase("1")) {
					aux.dado.setStatus("Óbito");
					showMessageDialog(null, "Status alterado");
					achou = true;
				} else if (opcaoLib.equalsIgnoreCase("2")) {
					aux.dado.setStatus("Em alta");
					showMessageDialog(null, "Status alterado");
					achou = true;
				} else {
					aux.dado.setStatus("Desvinculado");
					showMessageDialog(null, "Status alterado");
					achou = true;
				}
			}

			aux = aux.prox;
		}
		if (!achou) {
			showMessageDialog(null, "CPF não encontrado!", "ERRO!", ERROR_MESSAGE);
		}
	}

}
