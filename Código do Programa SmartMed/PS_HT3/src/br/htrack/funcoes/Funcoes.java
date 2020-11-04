package br.htrack.funcoes;

import static java.lang.Long.parseLong;
import static javax.swing.JOptionPane.*;
import br.htrack.listaPaciente.ListaPacientes;
import br.htrack.paciente.Paciente;
import br.htrack.filaPaciente.FilaPacientes;

public class Funcoes {
	private static Paciente[] filaAtendimento = new Paciente[10];//Altere aqui o número de pacientes a serem atendidos dentro do hospital.
	private static ListaPacientes listaPaciente = new ListaPacientes();
	private static FilaPacientes filaInternacao = new FilaPacientes();
	private static Paciente[] internacao = new Paciente[5];//Altere aqui o número de vagas de internação.
	private static int controleAtendimento = 0;
	private static int controleInternacao = 0;
	private static Paciente guardaPaciente;

	public static void iniciarFila() {
		filaInternacao.init();
	}

	public static void cadastrarPaciente() {

		String nome;
		long cpf;
		String auxCpf;
		String status = "Fila de Atendimento";
		Paciente elem;
		String teste;

		if (controleAtendimento < filaAtendimento.length) {
			nome = showInputDialog("Digite o nome do paciente: ");
			auxCpf = showInputDialog("Digite o CPF do paciente (11 dígitos): ");
			while (auxCpf.length() != 11) {
				showMessageDialog(null, "CPF inválido!", "ERRO!", ERROR_MESSAGE);
				auxCpf = showInputDialog("Digite o CPF do paciente (11 dígitos): ");
			}
			cpf = parseLong(auxCpf);
			elem = new Paciente(nome, cpf, status);
			teste = listaPaciente.confere(cpf);
			if (teste.equalsIgnoreCase("ok")) {
				filaAtendimento[controleAtendimento] = elem;
				controleAtendimento++;
				listaPaciente.add(elem);
				showMessageDialog(null, "Paciente cadastrado!");
			} else {
				showMessageDialog(null, teste);
			}
		} else {
			showMessageDialog(null, "Fila de Pacientes cheia, procure outra unidade ou volte mais tarde.");
		}

	}

	public static void atenderPaciente() {
		int pontos = 0;
		String resposta;
		if (controleAtendimento != 0) {
			if (filaAtendimento[0].getStatus().equalsIgnoreCase("Desvinculado")
					|| filaAtendimento[0].getStatus().equalsIgnoreCase("Óbito")) {
				for (int i = 0; i <= controleAtendimento - 1; i++) {
					filaAtendimento[i] = filaAtendimento[i + 1];
					atenderPaciente();
				}
			} else {
				if (controleAtendimento == 0) {
					showMessageDialog(null, "Fila de atendimento está vazia", "ERRO!", ERROR_MESSAGE);
				} else {
					guardaPaciente = filaAtendimento[0];
					guardaPaciente.setStatus("Sendo atendido");
					controleAtendimento--;

					for (int i = 0; i <= controleAtendimento - 1; i++) {
						filaAtendimento[i] = filaAtendimento[i + 1];
					}

					showMessageDialog(null, "Paciente sendo atendido:\n" + guardaPaciente.getDados());

					resposta = showInputDialog("O paciente tem o sintoma TOSSE? (s / n)");
					while (!(resposta.equalsIgnoreCase("s")) && !(resposta.equalsIgnoreCase("n"))) {
						resposta = showInputDialog("O paciente tem o sintoma TOSSE? (s / n)");
					}
					if (resposta.equalsIgnoreCase("s")) {
						pontos += 1;
					}

					resposta = showInputDialog("O paciente tem o sintoma FEBRE? (s / n)");
					while (!(resposta.equalsIgnoreCase("s")) && !(resposta.equalsIgnoreCase("n"))) {
						resposta = showInputDialog("O paciente tem o sintoma FEBRE? (s / n)");
					}
					if (resposta.equalsIgnoreCase("s")) {
						pontos += 2;
					}

					resposta = showInputDialog("O paciente tem o sintoma FADIGA? (s / n)");
					while (!(resposta.equalsIgnoreCase("s")) && !(resposta.equalsIgnoreCase("n"))) {
						resposta = showInputDialog("O paciente tem o sintoma FADIGA? (s / n)");
					}
					if (resposta.equalsIgnoreCase("s")) {
						pontos += 1;
					}

					resposta = showInputDialog("O paciente tem o sintoma DIFICULDADE DE RESPIRAR? (s / n)");
					while (!(resposta.equalsIgnoreCase("s")) && !(resposta.equalsIgnoreCase("n"))) {
						resposta = showInputDialog("O paciente tem o sintoma DIFICULDADE DE RESPIRAR? (s / n)");
					}
					if (resposta.equalsIgnoreCase("s")) {
						pontos += 4;
					}

					resposta = showInputDialog("O paciente tem o sintoma DOR DE GARGANTA? (s / n)");
					while (!(resposta.equalsIgnoreCase("s")) && !(resposta.equalsIgnoreCase("n"))) {
						resposta = showInputDialog("O paciente tem o sintoma DOR DE GARGANTA? (s / n)");
					}
					if (resposta.equalsIgnoreCase("s")) {
						pontos += 2;
					}

					if (pontos >= 4) {
						showMessageDialog(null, "Paciente detectado com covid-19");

						if (controleInternacao >= internacao.length) {
							showMessageDialog(null,
									"Não há mais vagas na internação. Paciente movido para a fila de internação");
							filaInternacao.enqueue(guardaPaciente);
							guardaPaciente.setStatus("Fila de internação");
						} else {
							internacao[controleInternacao] = guardaPaciente;
							guardaPaciente.setStatus("Internado");
							showMessageDialog(null, "Paciente foi internado");
							controleInternacao++;
						}
					} else {
						showMessageDialog(null, "Sintomas insuficientes - Paciente liberado");
						guardaPaciente.setStatus("Liberado");
					}

				}
			}
		}

	}

	public static void listarPacientes() {
		listaPaciente.show();
	}

	public static void procurarPaciente() {
		long cpf = parseLong(showInputDialog("Digite o CPF do paciente:"));
		showMessageDialog(null, listaPaciente.select(cpf));
	}

	public static void liberarPaciente() {
		long cpfPesq;
		cpfPesq = parseLong(showInputDialog("Digite o CPF do paciente a ser liberado: "));
		String opcaoLib = "0";
		if (listaPaciente.ganhaCPF(cpfPesq) == true) {
			if (listaPaciente.ganhaStatus(cpfPesq).equalsIgnoreCase("Óbito")
					|| listaPaciente.ganhaStatus(cpfPesq).equalsIgnoreCase("Em alta")
					|| listaPaciente.ganhaStatus(cpfPesq).equalsIgnoreCase("Desvinculado")
					|| listaPaciente.ganhaStatus(cpfPesq).equalsIgnoreCase("Liberado")) {
				showMessageDialog(null, "Opção inválida!\nPaciente já foi liberado.\n" + listaPaciente.select(cpfPesq),
						"ERRO!", ERROR_MESSAGE);

			} else {
				opcaoLib = showInputDialog(
						"Escolha uma opção de liberação: \n1.Óbito\n2.Alta\n3.Desvinculado do hospital");
				while (!(opcaoLib.equalsIgnoreCase("1")) & !(opcaoLib.equalsIgnoreCase("2"))
						& !(opcaoLib.equalsIgnoreCase("3"))) {
					showMessageDialog(null, "Opção inválida!", "ERRO!", ERROR_MESSAGE);
					opcaoLib = showInputDialog(
							"Escolha uma opção de liberação: \n1.Óbito\n2.Alta\n3.Desvinculado do hospital");
				}
				boolean auxStatus = false;

				int auxContInt = controleInternacao;
				auxContInt--;

				for (int i = 0; i <= auxContInt; i++) {

					if (listaPaciente.ganhaCPF(cpfPesq) == true) {
						if (listaPaciente.ganhaStatus(cpfPesq).equalsIgnoreCase("Fila de Atendimento")
								|| listaPaciente.ganhaStatus(cpfPesq).equalsIgnoreCase("Fila de Internação")) {
							auxStatus = true;
							break;
						}
					}
				}

				listaPaciente.alteraStatus(cpfPesq, opcaoLib);

				if (auxStatus == false) {
					if (controleInternacao == 0) {
						showMessageDialog(null, "Internação está vazia - Não há como liberar pacientes");
					} else {
						controleInternacao--;
						for (int i = 0; i <= controleInternacao - 1; i++) {
							if (internacao[i].getStatus().equalsIgnoreCase("Óbito")
									|| internacao[i].getStatus().equalsIgnoreCase("Em alta")
									|| internacao[i].getStatus().equalsIgnoreCase("Desvinculado")) {
								int auxRet = i;
								while (auxRet <= controleInternacao) {
									internacao[i] = internacao[i + 1];
									auxRet++;
								}
							}
						}
						if (!(filaInternacao.isEmpty())) {

							while (filaInternacao.first().getStatus().equalsIgnoreCase("Óbito")
									|| filaInternacao.first().getStatus().equalsIgnoreCase("Desvinculado")) {
								filaInternacao.dequeue();

								if (filaInternacao.isEmpty()) {
									break;
								}
							}

							internacao[controleInternacao] = filaInternacao.dequeue();
							internacao[controleInternacao].setStatus("Internado");
							showMessageDialog(null, "Paciente da fila de internação movido para a internação:\n"
									+ internacao[controleInternacao].getDados());
							controleInternacao++;
						}
					}
				}

			}
		}
	}
}
