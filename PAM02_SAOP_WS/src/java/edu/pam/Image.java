/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pam;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;





/**
 *
 * @author root
 */
@XmlRootElement(name="Image")
public class Image {
    private String title;
    private String description;
    private String keywords;
    private String author;
    private String creationDate;
    private String storageDate;
    private String filename;
    
   // constructors
    public Image() {}
    public Image(String title, String description, String keywords, String author, 
            String creationDate, String storageDate, String filename) {
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.author = author;
        this.creationDate = creationDate;
        this.storageDate = storageDate;
        this.filename = filename;
    }
    // getters
    @XmlElement(name="Title")
    public String getTitle() {
        return this.title;
    }
    @XmlElement(name="Description")
    public String getDescription() {
        return this.description;
    }
    @XmlElement(name="Keywords")
    public String getKeywords() {
        return this.keywords;
    }
    @XmlElement(name="Author")
    public String getAuthor() {
        return this.author;
    }
    @XmlElement(name="CreationDate")
    public String getCreationDate() {
        return this.creationDate;
    }
    @XmlElement(name="StorageDate")
    public String getStorageDate() {
        return this.storageDate;
    }
    @XmlElement(name="Filename")
    public String getFilename() {
        return this.filename;
    }
    // setters
    public void setTitle(String value) {
        this.title = value;
    }
    public void setDescription(String value) {
        this.description = value;
    }
    public void setKeywords(String value) {
        this.keywords = value;
    }
    public void setAuthor(String value) {
        this.author = value;
    }
    public void setCreationDate(String value) {
        this.creationDate = value;
    }
    public void setStorageDate(String value) {
        this.storageDate = value;
    }
    public void setFilename(String value) {
        this.filename = value;
    }
    
}
