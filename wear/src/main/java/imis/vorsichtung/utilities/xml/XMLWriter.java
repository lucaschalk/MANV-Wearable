package imis.vorsichtung.utilities.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.util.Xml;

/**
 * Class that is used in order to write a xml file with information about the
 * current patient like the id, the category and a documentation of the answers
 * from the algorithm
 *
 * @author Henrik Berndt
 */
public class XMLWriter {

    // Initialize variables
    private XmlSerializer xmlSerializer;
    private FileOutputStream outputStream;

    /**
     * The constructor needs the patient id. This should usually be a number,
     * but it could also be a string
     *
     */

    public void writeToXML(String patientId, int classification,
                           LinkedList<String> patientDocu) {

        try {
            // Path to the file. Here the photo directory is used because this is
            // visible in the windows file explorer when glass is connected to a
            // computer
            File path = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            // The file name is patient_number.xml
            File xmlFile = new File(path, "patient_" + patientId + ".xml");
            // Create the file
            xmlFile.createNewFile();

            // Create a new FileOutputStream for the file
            outputStream = null;
            outputStream = new FileOutputStream(xmlFile);

            // Create a new serializer for xml and set the FileOutputStream for
            // output
            xmlSerializer = Xml.newSerializer();
            xmlSerializer.setOutput(outputStream, "UTF-8");

            // Start the document (creates the first line)
            xmlSerializer.startDocument(null, true);
            // Start the first tag
            // Start a tag patient and fill it with the patient id as attribute
            xmlSerializer.startTag(null, "patient");
            xmlSerializer.attribute(null, "ID", patientId);

            // write the category in a tag
            xmlSerializer.startTag(null, "category_pretriage");
            xmlSerializer.text("" + classification);
            xmlSerializer.endTag(null, "category_pretriage");

            // Start tag for the documentation of the pretriage
            xmlSerializer.startTag(null, "docu_pretriage");

            //fill in the questions and answers
            for (int i = 0; i < patientDocu.size(); i = i + 2) {
                xmlSerializer.startTag(null, "question");
                xmlSerializer.text(patientDocu.get(i));
                xmlSerializer.endTag(null, "question");
                xmlSerializer.startTag(null, "answer");
                xmlSerializer.text(patientDocu.get(i + 1));
                xmlSerializer.endTag(null, "answer");

            }
            // End tag for the documentation of the pretriage
            xmlSerializer.endTag(null, "docu_pretriage");

            // end and write the file
            xmlSerializer.endTag(null, "patient");

            xmlSerializer.endDocument();
            xmlSerializer.flush();
            outputStream.close();


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
