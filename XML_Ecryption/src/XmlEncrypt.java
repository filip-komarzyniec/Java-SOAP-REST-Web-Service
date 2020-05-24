

import org.apache.xml.security.encryption.XMLCipher;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;


public class XmlEncrypt {
    private File xml_file;
    private String enc_algorithm;
    private Element root_elem;
    private SecretKey secretKey;

// constructor
   public XmlEncrypt(File xml_file, String enc_algorithm) {
       // initializing the library
       org.apache.xml.security.Init.init();
       this.xml_file = xml_file;
       this.enc_algorithm = enc_algorithm;
   }

   public Document xml_parser(File xml_file) {
       try {
           // preparing w3c Document instance to parse File
           DocumentBuilderFactory xml_builder_factory = DocumentBuilderFactory.newInstance();
           DocumentBuilder xml_builder = xml_builder_factory.newDocumentBuilder();
           Document xml_file_dom = xml_builder.parse(xml_file); // w3c Document xml_file
           xml_file_dom.getDocumentElement().normalize();

           Element root_elem = xml_file_dom.getDocumentElement();   // root Element to be encrypted
           this.root_elem = root_elem;


           return xml_file_dom;

       } catch (javax.xml.parsers.ParserConfigurationException | org.xml.sax.SAXException e) {
           System.out.println("XML parse error: " + e.getMessage());
       } catch (IOException e) {
           System.out.println("I/O exception: " + e.getMessage());
       }
       return null;
   }

   public Document EncryptDocument(Document xml_document, Element elem_to_encrypt) {

       try {
           // generating a secret key
           KeyGenerator keyGen = KeyGenerator.getInstance("AES");
           keyGen.init(128);
           SecretKey secretKey = keyGen.generateKey();  // generating key not from custom String as it is in instruction
           this.secretKey = secretKey;

           XMLCipher keyCipher = XMLCipher.getInstance(this.enc_algorithm);
           keyCipher.init(XMLCipher.ENCRYPT_MODE, this.secretKey);
           Document encrypted_xml = keyCipher.doFinal(xml_document, elem_to_encrypt, false);
           return encrypted_xml;
       } catch(org.apache.xml.security.encryption.XMLEncryptionException e) {
           System.out.println("XML encryption problem: "+ e.getMessage());
       } catch (NoSuchAlgorithmException e ) {
           System.out.println("Cannot find such algorithm " + e.getMessage());
       }
       catch (Exception e) {
           System.out.println("some very broad problem" + e.getMessage());
       }
    return null;
   }

   public Document DecryptDocument(Document xml_encrypted) {
       try {
           XMLCipher keyCipher = XMLCipher.getInstance(this.enc_algorithm);
           keyCipher.init(XMLCipher.DECRYPT_MODE, this.secretKey);
           Document decrypted_xml = keyCipher.doFinal(xml_encrypted,xml_encrypted.getDocumentElement());
           return decrypted_xml;
       } catch(org.apache.xml.security.encryption.XMLEncryptionException e) {
           System.out.println("XML encryption problem: "+ e.getMessage());
       } catch (NoSuchAlgorithmException e ) {
           System.out.println("Cannot find such algorithm " + e.getMessage());
       }
       catch (Exception e) {
           System.out.println("some very broad problem " + e.getMessage());
       }
       return null;
   }

    public String getDocumentAsString(Document document) {
        String output = null;
        try
        {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            output = writer.getBuffer().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

   // getters

    public File getXml_file() {
        return xml_file;
    }

    public String getEnc_algorithm() {
        return enc_algorithm;
    }

    public Element getRoot_elem() {
        return root_elem;
    }
}
