package br.edu.impacta.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class AgendaApp {
	static Scanner entrada = new Scanner(System.in);
	static List<Contato> contatos = new ArrayList<>();
	public static IContatoDao dao = new ContatoDao();
	
	public static void main (String[] args){
		boolean sair = false;
		while (!sair) {
			int opcao = apresentarMenuPrincipal();
			switch (opcao){
			case 1: inserirContato(); break;
			case 2: buscarContato(); break;
			case 3: sair = true;break;
			default: out.println("ERRO: opção inválida!");
			}
		out.println("\nFim do Programa!");
		}	
		}
		
		public static int apresentarMenuPrincipal(){
			boolean inteiro = false;
			int opcao = 0;
			while (!inteiro){
				out.println("\nAGENDA TELEFONICA!");
				out.println("(1) Inserir");
				out.println("(2) Buscar");
				out.println("(3) Sair");
				out.println("Escolha uma opção: ");
				String s = entrada.nextLine();
				try {
					opcao = Integer.parseInt(s);
					inteiro = true;
				} catch(Exception e){
					out.println ("ERRO: opção deve ser um valor inteiro!");
				}
			}
			return opcao;
		}
		
		public static void inserirContato(){
			out.println("\nINSERÇÃO DE NOVO CONTATO:");
			String nome = lerNome();
			String telefone = lerTelefone();
			Contato c = new Contato (nome,telefone);
			//if (contatos.contains(c)){
			if (dao.existe(c)){
				out.println("Este contato já está cadastrado!");
			}
			else {
				//contatos.add(c);
				dao.inserir(c);
				out.println("Contato inserido!");
			}
		}
		
		public static String lerNome(){
			boolean valido = false;
			String nome = "";
			while (!valido){
				out.print("Nome: ");
				nome = entrada.nextLine();
				if (nome.length()==0 || nome.length()>200){
					out.println ("ERRO: nome de tamnho inválido");
				}else { valido = true;
				}
			}
			return nome;
		}
		
		public static String lerTelefone(){
			boolean valido = false;
			String telefone = "";
			while (!valido){
				out.print("Telefone: ");
				telefone = entrada.nextLine();
				if (telefone.length()==0 || telefone.length()>25){
					out.println ("ERRO: telefone de tamnho inválido");
				}else { valido = true;
				}
			}
			return telefone;
		}
		
		public static void buscarContato(){
			out.println("\nBusca de Contatos:");
			String nome = lerNome();
			
		//	List <Contato> resultado = new ArrayList<>(); 
		//	for (Contato c: contatos){
		//		if (nome.equals(c.getNome())){
		//			resultado.add(c);
		//		}
		//	}
			
			List<Contato> resultado = dao.buscar(nome);
			if (resultado.size() ==0){
				out.println("Não há contato com este nome!");
			} else {
				out.println ("\nResultado da busca:");
				for (Contato c: resultado){
					out.println(c);
			}
		}
	}
}
	
