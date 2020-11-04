package br.htrack.filaPaciente;

import br.htrack.paciente.Paciente;

public class FilaPacientes {

	private class NO {
		Paciente dado;
		NO prox;
	}

	private NO ini;
	private NO fim;

	public void init() {
		ini = fim = null;
	}

	public boolean isEmpty() {
		if (ini == null && fim == null)
			return true;
		else
			return false;
	}

	public void enqueue(Paciente elem) {
		NO novo = new NO();
		novo.dado = elem;
		novo.prox = null;
		if (isEmpty())
			ini = novo;
		else
			fim.prox = novo;
		fim = novo;
	}

	public Paciente dequeue() {
		Paciente valor = ini.dado;
		ini = ini.prox;
		if (ini == null) {
			fim = null;
		}
		return valor;
	}

	public Paciente first() {
		Paciente valor = ini.dado;
		return valor;
	}

}
