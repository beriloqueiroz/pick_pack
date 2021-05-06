package base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class lerArquivo {
	public static void main(String[] args) {
	}

	public static String ler(String file) {
		try {
			FileReader arq = new FileReader(file);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			String ret = ";";
			while (linha != null) {
				linha = lerArq.readLine();
				ret = ret + linha;
			}
			arq.close();
			return ret;
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			return null;
		}
	}

}