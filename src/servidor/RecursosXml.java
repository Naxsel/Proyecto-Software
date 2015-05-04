package servidor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class RecursosXml {
    // Ruta depende de donde lo tengas alojado el fichero XML
    private final String RUTA
                    =  System.getProperty("user.dir")
                                + System.getProperty("file.separator") +
                                "config" + System.getProperty("file.separator") +
                                "conexion.xml";
    private Document documento;

    //Atributos a cambiar segun lo que quieras leer
    private String host;
    private int puerto;
    //conexion BBDD
    private String hostBBDD;
    private String driver;
    private String user;
    private String passwd;

    private Map<String,String> listaIdioma = new HashMap<>();
    ///////////////////////////////////////////////
    public RecursosXml() {
        try {


            BufferedReader br = new BufferedReader(new FileReader(RUTA));
            String entrada;
            String cadena = "";

            while ((entrada = br.readLine()) != null) {
                cadena = cadena + entrada;
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource archivo = new InputSource();
            archivo.setCharacterStream(new StringReader(cadena));

            documento = db.parse(archivo);
            documento.getDocumentElement().normalize();
            // Partes que modificar en caso de querer leer otras cosas
            parserConexion();
            parserIdioma();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Partes que modificar en caso de querer leer otras cosas
    private void parserConexion() {
        NodeList nodeLista = documento.getElementsByTagName("conexion");


        Node primerNodo = nodeLista.item(0);

        if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

            Element primerElemento = (Element) primerNodo;

            NodeList primerNombreElementoLista
                    = primerElemento.getElementsByTagName("host");
            Element primerNombreElemento
                    = (Element) primerNombreElementoLista.item(0);
            NodeList primerNombre = primerNombreElemento.getChildNodes();
            host = ((Node) primerNombre.item(0)).getNodeValue();

            NodeList segundoNombreElementoLista
                    = primerElemento.getElementsByTagName("puerto");
            Element segundoNombreElemento
                    = (Element) segundoNombreElementoLista.item(0);
            NodeList segundoNombre = segundoNombreElemento.getChildNodes();
            puerto = Integer.parseInt(((Node) segundoNombre.item(0)).getNodeValue());

            NodeList tercerNombreElementoLista
                    = primerElemento.getElementsByTagName("hostBBDD");
            Element tercerNombreElemento
                    = (Element) tercerNombreElementoLista.item(0);
            NodeList tercerNombre = tercerNombreElemento.getChildNodes();
            hostBBDD = ((Node) tercerNombre.item(0)).getNodeValue();

            NodeList cuartoNombreElementoLista
                    = primerElemento.getElementsByTagName("driver");
            Element cuartoNombreElemento
                    = (Element) cuartoNombreElementoLista.item(0);
            NodeList cuartoNombre = cuartoNombreElemento.getChildNodes();
            driver = ((Node) cuartoNombre.item(0)).getNodeValue();

            NodeList quintoNombreElementoLista
                    = primerElemento.getElementsByTagName("user");
            Element quintoNombreElemento
                    = (Element) quintoNombreElementoLista.item(0);
            NodeList quintoNombre = quintoNombreElemento.getChildNodes();
            user = ((Node) quintoNombre.item(0)).getNodeValue();

            NodeList sextoNombreElementoLista
                    = primerElemento.getElementsByTagName("passwd");
            Element sextoNombreElemento
                    = (Element) sextoNombreElementoLista.item(0);
            NodeList sextoNombre = sextoNombreElemento.getChildNodes();
            try {
                passwd = ((Node) sextoNombre.item(0)).getNodeValue();
            } catch (NullPointerException ex) {
                passwd = "";
            }


        }

    }

    public void parserIdioma() {
        NodeList nodeLista = documento.getElementsByTagName("etiqueta");

        for (int s = 0; s < nodeLista.getLength(); s++) {

            Node primerNodo = nodeLista.item(s);
            String id;
            String texto;
            if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

                Element primerElemento = (Element) primerNodo;

                NodeList primerNombreElementoLista
                        = primerElemento.getElementsByTagName("id");
                Element primerNombreElemento
                        = (Element) primerNombreElementoLista.item(0);
                NodeList primerNombre = primerNombreElemento.getChildNodes();
                id = ((Node) primerNombre.item(0)).getNodeValue();


                NodeList segundoNombreElementoLista
                        = primerElemento.getElementsByTagName("texto");
                Element segundoNombreElemento
                        = (Element) segundoNombreElementoLista.item(0);
                NodeList segundoNombre = segundoNombreElemento.getChildNodes();

                texto = ((Node) segundoNombre.item(0)).getNodeValue();
                listaIdioma.put(id, texto);
            }
        }




    }

    public String getTexto(String clave) {
        return listaIdioma.get(clave);
    }

    public String getHost() {
        return host;
    }

    public int getPuerto() {
        return puerto;
    }

    public String getHostBBDD() {
        return hostBBDD;
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }


}
