package imis.vorsichtung.utilities.xml;

import android.os.Environment;
import android.util.Log;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import imis.vorsichtung.utilities.Constants;


public class XMLReader {

    ArrayList<String> xmlFiles;

    int[] manvInfo;


    //https://github.com/melardev/TutsStorage/blob/master/app/src/main/java/com/melardev/tutsstorage/ActivityXMLDemo.java
    public void readFromXML() {

        try {
            Document doc = null;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;

            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File[] listOfFiles = folder.listFiles();
            xmlFiles = new ArrayList<>();

            for (int k = 0; k < listOfFiles.length; k++) {
                String filename = listOfFiles[k].getName();
                if (filename.endsWith(".xml") || filename.endsWith(".XML")) {
                    Long lastModified = listOfFiles[k].lastModified();
                    if (lastModified + 10000 < System.currentTimeMillis()) {
                        listOfFiles[k].delete();
                    } else {
                        xmlFiles.add(filename);
                        Log.d("XML_FILENAME", xmlFiles.get(k));
                    }
                }
            }

            manvInfo = new int[5];
            manvInfo[Constants.XML_LAST_PATIENT_ID] = xmlFiles.size();

            for (int i = 0; i < xmlFiles.size(); i++) {
                FileInputStream fis = new FileInputStream(new File(folder, xmlFiles.get(i)));
                db = dbf.newDocumentBuilder();
                doc = db.parse(fis);

                NodeList patient = doc.getElementsByTagName("patient");
                Node patientNode = patient.item(0);
                NodeList patientInfo = patientNode.getChildNodes();
                Node info = patientInfo.item(0);

                Log.d("CATEGORY_PRETRIAGE", "Category " + info.getTextContent() + " for patient " + patientNode.getAttributes().getNamedItem("ID").getNodeValue());

                switch (info.getTextContent()) {
                    case "0":
                        manvInfo[Constants.CATEGORY_BLACK]++;
                        break;
                    case "1":
                        manvInfo[Constants.CATEGORY_RED]++;
                        break;
                    case "2":
                        manvInfo[Constants.CATEGORY_YELLOW]++;
                        break;
                    case "3":
                        manvInfo[Constants.CATEGORY_GREEN]++;
                        break;
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public int[] getManvInfo() {
        return manvInfo;
    }

}

