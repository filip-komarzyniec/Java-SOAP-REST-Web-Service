/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pam;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author root
 */
public class ImageMarshalling {
    public static Image unmarshal(String xml) {

        JAXBContext jc;
        Image img = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Image.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Image retr = (Image) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            img = retr;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    return img;
    }
}
