import org.apache.xml.security.encryption.XMLCipher;
import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;

public class Main {

    public static void main(String[] args) {

        String encryption_algorithm = XMLCipher.AES_128;
        URL url = Main.class.getResource("digital_object.xml");
        File xml_file = new File(url.getFile());

        // initializing the XMLEncrypt Object
        XmlEncrypt encryptor = new XmlEncrypt(xml_file, encryption_algorithm);

        Document xml_file_dom = encryptor.xml_parser(encryptor.getXml_file());

        System.out.println("That is the original xml document: ");
        String res = encryptor.getDocumentAsString(xml_file_dom);
        res = res.replaceAll("\n\\s+\n", "\n");
        System.out.println(res);

        System.out.println("That is the encrypted XML document: ");
        Document encrypted_xml = encryptor.EncryptDocument(xml_file_dom, encryptor.getRoot_elem());
        res = encryptor.getDocumentAsString(encrypted_xml);

        System.out.println(res);

        System.out.println("That is the decrypted XML document: ");
        Document decrypted_xml = encryptor.DecryptDocument(encrypted_xml);
        res = encryptor.getDocumentAsString(decrypted_xml);
        res = res.replaceAll("\n\\s+\n", "\n");
        System.out.println(res);
    }
}
