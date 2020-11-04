package br.htrack.main;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;
import br.htrack.funcoes.Funcoes;

public class Main {

	public static void main(String[] args) {
		int opcao = 0;
		Funcoes.iniciarFila();

		while (opcao != 6) {

			try {
				do {

					opcao = parseInt(showInputDialog(gerarMenu()));
					if (opcao < 1 || opcao > 6) {
						showMessageDialog(null, "Op��o inv�lida", "ERRO!", ERROR_MESSAGE);

					} else {
						switch (opcao) {
						case 1:
							try {
								Funcoes.cadastrarPaciente();
							} catch (Exception e) {
								showMessageDialog(null, "Digite um CPF v�lido.");

							}
							break;
						case 2:
							Funcoes.atenderPaciente();
							break;
						case 3:
							Funcoes.liberarPaciente();
							break;
						case 4:
							Funcoes.procurarPaciente();
							break;
						case 5:
							Funcoes.listarPacientes();
							break;
						case 6:
							showMessageDialog(null, "Saindo do programa...");
							break;
						default:
							showMessageDialog(null, "Op��o inv�lida!", "ERRO!", ERROR_MESSAGE);
							break;
						}
					}
				} while (opcao != 6);
			} catch (Exception e) {
				showMessageDialog(null, "Voc� deve escolher uma op��o.\nDigite um n�mero.");
			}
		}
	}

	public static String gerarMenu() {
		String aux = "";
		aux += "Ecolha uma op��o:\n";
		aux += "1. Cadastrar paciente\n";
		aux += "2. Atender paciente\n";
		aux += "3. Libera��o de paciente\n";
		aux += "4. Procurar paciente\n";
		aux += "5. Mostrar registro de pacientes\n";
		aux += "6. Finalizar\n";
		return aux;
	}
}
