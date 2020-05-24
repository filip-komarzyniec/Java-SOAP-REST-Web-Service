/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pam2_client;

//import edu.pam.DoRegisterImgResponse;
import edu.pam.Image;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class PAM2_CLIENT {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        edu.pam.Image img = new edu.pam.Image();
        
        System.out.println("Please fill in the title");
        String title = input.nextLine();
        img.setTitle(title);
        System.out.println("Please fill in the description");
        String description = input.nextLine();
        img.setDescription(description);
        System.out.println("Please fill in the author");
        String author = input.nextLine();
        img.setAuthor(author);
        System.out.println("Please fill in the keywords");
        String keywords = input.nextLine();
        img.setKeywords(keywords);
        System.out.println("Please fill in the creation date");
        String creationDate = input.nextLine();
        img.setCreationDate(creationDate);
        System.out.println("Please fill in the storage date");
        String storageDate = input.nextLine();
        img.setStorageDate(storageDate);
        System.out.println("Please fill in the filename");
        String filename = input.nextLine();
        img.setFilename(filename);
        
        //System.out.println(doRegisterImg(img));
        
        List<edu.pam.Image> all_img = doListImg();
        
        for (edu.pam.Image item : all_img) {
            System.out.println("title: "+item.getTitle());
            System.out.println("description: "+item.getDescription());
            System.out.println("keywords: "+item.getKeywords());
            System.out.println("author: "+item.getAuthor());
            System.out.println("creation date: "+item.getCreationDate());
            System.out.println("storage date: "+item.getStorageDate());
            System.out.println("filename: "+item.getFilename());
            System.out.println();
        }
        
        List<edu.pam.Image> creation_list = doSearchByCreaDate(creationDate);
        
        for (edu.pam.Image item : creation_list) {
            System.out.println("title: "+item.getTitle());
            System.out.println("description: "+item.getDescription());
            System.out.println("keywords: "+item.getKeywords());
            System.out.println("author: "+item.getAuthor());
            System.out.println("creation date: "+item.getCreationDate());
            System.out.println("storage date: "+item.getStorageDate());
            System.out.println("filename: "+item.getFilename());
            System.out.println();
        }
        
        List<edu.pam.Image> title_list = doSearchByTitle(title);
        System.out.println("Searching by title:");
         for (edu.pam.Image item : title_list) {
            System.out.println("title: "+item.getTitle());
            System.out.println("description: "+item.getDescription());
            System.out.println("keywords: "+item.getKeywords());
            System.out.println("author: "+item.getAuthor());
            System.out.println("creation date: "+item.getCreationDate());
            System.out.println("storage date: "+item.getStorageDate());
            System.out.println("filename: "+item.getFilename());
            System.out.println();
        }
         edu.pam.Image id_img_search = doSearchById(4);
         System.out.println("Searching by ID:");
         System.out.println("title: "+ id_img_search.getTitle());
            System.out.println("description: "+ id_img_search.getDescription());
            System.out.println("keywords: "+ id_img_search.getKeywords());
            System.out.println("author: "+ id_img_search.getAuthor());
            System.out.println("creation date: "+ id_img_search.getCreationDate());
            System.out.println("storage date: "+ id_img_search.getStorageDate());
            System.out.println("filename: "+ id_img_search.getFilename());
            System.out.println();
    }

  

    private static int doRegisterImg(edu.pam.Image img) {
        edu.pam.RegisterImage_Service service = new edu.pam.RegisterImage_Service();
        edu.pam.RegisterImage port = service.getRegisterImagePort();
        return port.doRegisterImg(img);
    }

    private static java.util.List<edu.pam.Image> doListImg() {
        edu.pam.ListImage_Service service = new edu.pam.ListImage_Service();
        edu.pam.ListImage port = service.getListImagePort();
        return port.doListImg();
    }

    private static java.util.List<edu.pam.Image> doSearchByCreaDate(java.lang.String creaDate) {
        edu.pam.SearchImageByCreaDate_Service service = new edu.pam.SearchImageByCreaDate_Service();
        edu.pam.SearchImageByCreaDate port = service.getSearchImageByCreaDatePort();
        return port.doSearchByCreaDate(creaDate);
    }

    private static java.util.List<edu.pam.Image> doSearchByTitle(java.lang.String title) {
        edu.pam.SearchImageByTitle_Service service = new edu.pam.SearchImageByTitle_Service();
        edu.pam.SearchImageByTitle port = service.getSearchImageByTitlePort();
        return port.doSearchByTitle(title);
    }

    private static Image doSearchById(int id) {
        edu.pam.SearchImageById_Service service = new edu.pam.SearchImageById_Service();
        edu.pam.SearchImageById port = service.getSearchImageByIdPort();
        return port.doSearchById(id);
    }
    
}
