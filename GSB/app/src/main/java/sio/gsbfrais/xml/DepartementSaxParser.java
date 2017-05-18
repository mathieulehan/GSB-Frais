package sio.gsbfrais.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import sio.gsbfrais.objet.Departement;
import sio.gsbfrais.objet.Medecin;

/**
 * Created by ferellec on 04/04/2017.
 * Lit et destructure le xml recuperé et transmis par LireDepartements et instancie les objets départements
 */
public class DepartementSaxParser extends DefaultHandler {
    public static ArrayList<Departement> listeDepartement = new ArrayList<>();

    private Departement departement;
    private String valeur;

    public void parse(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(inputStream, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("item")) {
            departement = new Departement();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        valeur = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("item")) {
            listeDepartement.add(departement);
        }
        else if (localName.equals("DEPARTEMENT")) {
            departement.setNumeroDepartement(valeur);
        }

    }

    /**
     * Renvoie la liste des départements
     * @return
     */
    public ArrayList<Departement> getLesDepartements() {
        return listeDepartement;
    }
}