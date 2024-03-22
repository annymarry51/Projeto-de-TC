package utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Arquivo {
	private String caminho;
	
	public String getCaminho() {
		return caminho;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	public static Automato carregaArquivo(String caminho) {
		try {
            File arquivoEntrada = new File(caminho);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(arquivoEntrada);
            doc.getDocumentElement().normalize();

            Automato automato = new Automato();

            NodeList listaEstados = doc.getElementsByTagName("state");
            for (int i = 0; i < listaEstados.getLength(); i++) {
                Node estadoNo = listaEstados.item(i);
                if (estadoNo.getNodeType() == Node.ELEMENT_NODE) {
                    Element estadoElemento = (Element) estadoNo;
                    int id = Integer.parseInt(estadoElemento.getAttribute("id"));
                    String name = estadoElemento.getAttribute("name");
                    double x = Double.parseDouble(estadoElemento.getElementsByTagName("x").item(0).getTextContent());
                    double y = Double.parseDouble(estadoElemento.getElementsByTagName("y").item(0).getTextContent());
                    boolean isInitial = estadoElemento.getElementsByTagName("initial").getLength() > 0;
                    boolean isFinal = estadoElemento.getElementsByTagName("final").getLength() > 0;

                    Estado estado = new Estado(id, name, x, y, isInitial, isFinal);
                    automato.addEstado(estado);

                    NodeList listaTransicoes = doc.getElementsByTagName("transition");
                    for (int j = 0; j < listaTransicoes.getLength(); j++) {
                        Node noTransicao = listaTransicoes.item(j);
                        if (noTransicao.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoTransicao = (Element) noTransicao;
                            int from = Integer.parseInt(elementoTransicao.getElementsByTagName("from").item(0).getTextContent());
                            int to = Integer.parseInt(elementoTransicao.getElementsByTagName("to").item(0).getTextContent());
                            String read = elementoTransicao.getElementsByTagName("read").item(0).getTextContent();
                            
                            if (automato.getEstados().get(i).getId() == from) {
                            	Transicao transicao = new Transicao(from, to, read);
                            	estado.addTransicao(transicao);
                            }
                        }
                    }
                }
            }
            return automato;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
}
