package br.edu.ifg.cliente;

import br.edu.ifg.interf.*;

import java.io.ObjectInputStream.GetField;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AplicacaoChat {

	private AplicacaoChat() {
		
	}

	public static void main(String[] args) {
		try {
			
			Registry registry  = LocateRegistry.getRegistry("localhost",Constant.RMI_PORT);
			final InterfaceDNS remote = (InterfaceDNS) registry.lookup(Constant.RMI_ID);			
			InetAddress ip = InetAddress.getLocalHost();
	        String hostname = ip.getHostAddress();
			
	        String nick = JOptionPane.showInputDialog("Digite o nick"); 
	        System.out.println(nick);
	        

	        
	    	//if (remote.autentica("Euripedes", hostname)) {
			if (remote.autentica(nick, hostname)) {
				System.out.println("Usuario adicionado com sucesso!");
			} else {
				System.out.print("Erro ao adicionar!");
			}
			ArrayList<String> lista = new ArrayList<>();
			
			lista = remote.obterListaUsuariosOnline();
			for (String nomeOnline : lista) {
				System.out.println("Nome dos usuários On line: "+nomeOnline);
			}
			
			System.out.println("O valor da chave: ALdo é: "+remote.obterIP("Aldo"));
			
			
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
		
	}
	
}