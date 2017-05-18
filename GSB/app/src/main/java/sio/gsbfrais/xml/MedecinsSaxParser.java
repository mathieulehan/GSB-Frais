package sio.gsbfrais.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sio.gsbfrais.MedecinAdapter;
import sio.gsbfrais.objet.Medecin;

/**
 * Created by ferellec on 04/04/2017.
 * Lit et destructure le xml recuperé et transmis par LireMedecins et instancie les objets Medecin
 */
public class MedecinsSaxParser extends DefaultHandler {
    public static ArrayList<Medecin> listeMedecin = new ArrayList<>();
    public static MedecinAdapter adapter = new MedecinAdapter(listeMedecin);
    private Medecin medecin;
    private String valeur;

    public void parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(inputStream, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("item")) {
            medecin = new Medecin();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        valeur = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("item")) {
            listeMedecin.add(medecin);
        }
        else if (localName.equals("PRA_NUM")) {
            medecin.setNUM(Integer.parseInt(valeur));
        }
        else if (localName.equals("PRA_NOM")) {
            medecin.setNOM(valeur);
        }
        else if (localName.equals("PRA_PRENOM")) {
            medecin.setPRENOM(valeur);
        }
        else if (localName.equals("PRA_ADRESSE")) {
            medecin.setADRESSE(valeur);
        }
        else if (localName.equals("PRA_CP")) {
            medecin.setCP(valeur);
        }
        else if (localName.equals("PRA_VILLE")) {
            medecin.setVILLE(valeur);
        }
        else if (localName.equals("TYP_LIBELLE")) {
            medecin.setLIBELLE(valeur);
        }
        else if (localName.equals("PRA_COEFNOTORIETE")) {
            medecin.setCOEFNOTORIETE(Double.parseDouble(valeur));
        }

    }

    /**
     * Renvoie la liste des Medécins
     * @return
     */
    public ArrayList<Medecin> getLesMedecins() {
        return listeMedecin;
    }
}
