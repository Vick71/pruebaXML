
package pruebaxml;

import formularios.forma1;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

 

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PruebaXML {

   
    public static void main(String[] args) {
        
        //forma1 Formulario = new forma1();
        //Formulario.setVisible(true);
        
        //generaXml();
        String Valor = leeXml("usuarior");
        System.out.println(Valor);
    }
    
    /**
     * Metodo para leer un atributo del XML
     */
    public static String leeXml(String TagName){
        
        String Atributo = "";
        try {
            DocumentBuilderFactory Dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = Dbf.newDocumentBuilder();
            Document Documento = documentBuilder.parse("configuracion.xml");
            Documento.getDocumentElement().normalize();
            
            // Obtengo el elemento Raiz del documento
            Element Raiz = Documento.getDocumentElement();            
            // Obtengo lista de elementos que contiene el elemento raiz
            NodeList ListaConexion = Raiz.getElementsByTagName(TagName);
            // Me posiciono en el elemento encontrado
            Node NodoConexion = ListaConexion.item(0);
            //Obtengo el atributo del elemento seleccionado
            Atributo = NodoConexion.getTextContent();           
           
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            Logger.getLogger(PruebaXML.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return Atributo;
                         
    }    
    
    
    /**
     * Metodo para leer un atributo del XML
     */
    public static void leeXml3(){
                    
        try {
            DocumentBuilderFactory Dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = Dbf.newDocumentBuilder();
            Document Documento = documentBuilder.parse("configuracion.xml");
            Documento.getDocumentElement().normalize();
            
            // Obtengo el elemento Raiz del documento
            Element Raiz = Documento.getDocumentElement();
            
            // Obtengo lista de elementos que contiene el elemento raiz
            NodeList ListaConexion = Raiz.getElementsByTagName("passwordl");
            NodeList ListaGenerales = Raiz.getElementsByTagName("generales");
            
            //System.out.println(ListaConexion.getLength());
            //System.out.println(ListaGenerales.getLength());    
            
            
            Node NodoConexion = ListaConexion.item(0);
            //NodeList NodosConexion = NodoConexion.getChildNodes();
            System.out.println(NodoConexion.getTextContent());
            
            
            
            /*
            System.out.println(NodosConexion.getLength());
            for(int i=0;i<NodosConexion.getLength();i++){
                Node NodoE = NodosConexion.item(i);
                System.out.println(NodoE.getNodeName() + NodoE.getTextContent());
            }
            */
            
           
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            Logger.getLogger(PruebaXML.class.getName()).log(Level.SEVERE, null, ex);
        } 
                         
    }    
    
    /**
     * Metodo para leer un atributo del XML
     */
    public static void leeXml2(){
                    
        try {
            DocumentBuilderFactory Dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = Dbf.newDocumentBuilder();
            Document Document = documentBuilder.parse("configuracion.xml");
            Document.getDocumentElement().normalize();
            
            // Obtengo raiz del archivo
            System.out.println("Elemento raiz:" + Document.getDocumentElement().getNodeName());
            
            // Me posiciono en los elementos conexion ygenerales
            NodeList Conexion = Document.getElementsByTagName("conexion");            
            NodeList Generales = Document.getElementsByTagName("generales");
            
            // Obtengo los nombres de los elementos
            Node Nodo = Conexion.item(0);
            System.out.println("Elemento:" + Nodo.getNodeName());            
            Nodo = Generales.item(0);
            System.out.println("Elemento:" + Nodo.getNodeName());

            
            Element Elemento = (Element)Nodo;
            String Puerto = Elemento.getAttribute("retardo");
            System.out.println("Port:" + Puerto);
        } catch (Exception ex) {
            Logger.getLogger(PruebaXML.class.getName()).log(Level.SEVERE, null, ex);
        } 
                         
    }
    
    /**
     * Metodo para generar archivo XML
    */
    public static void generaXml(){

	try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // elemento raiz configuracion
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("configuracion");
            doc.appendChild(rootElement);
            
            // elemento conexion
            Element Conexion = doc.createElement("conexion");
            rootElement.appendChild(Conexion);
            
            // elemento generales
            Element Generales = doc.createElement("generales");
            rootElement.appendChild(Generales);
            
            /*
            // atributo del elemento empleado
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            empleado.setAttributeNode(attr);
            */
            
            // servidor
            Element Servidor = doc.createElement("servidor");
            Servidor.appendChild(doc.createTextNode("localhost"));
            Conexion.appendChild(Servidor);
            
            // puerto
            Element Puerto = doc.createElement("puerto");
            Puerto.appendChild(doc.createTextNode("3306"));
            Conexion.appendChild(Puerto);
            
            // retardo
            Element Retardo = doc.createElement("retardo");
            Retardo.appendChild(doc.createTextNode("10"));
            Generales.appendChild(Retardo);
            
            // volumetricos
            Element Volumetricos = doc.createElement("volumetricos");
            Volumetricos.appendChild(doc.createTextNode("si"));
            Generales.appendChild(Volumetricos);
            
            // escribimos el contenido en un archivo .xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("configuracion.xml"));
            //StreamResult result = new StreamResult(new File("C:\\archivo.xml"));
                
            //StreamResult result = new StreamResult(new File("archivo.xml"));
            // Si se quiere mostrar por la consola...
            // StreamResult result = new StreamResult(System.out);
            
            transformer.transform(source, result); 
            System.out.println("File saved!");
            
	} catch (ParserConfigurationException pce) {
            pce.printStackTrace();
	} catch (TransformerException tfe) {
            tfe.printStackTrace();

	}        
          
    }
    
}
