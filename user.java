/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dywtbb;

/**
 *
 * @author kdegb_000
 */

import java.util.logging.Level;
import java.util.logging.Logger;



public class user {
    
 database db;   
  
  final String id;
    
  final String password;
  
  final String date ;
  
  
 
  
  public user(String id,String password,String date){
        this.db = new database();
  
  this.id=id;
  
  this.password=password;
  
  this.date = date; 
  }
  
  //for login purposes
   public user(String id ,String password){
  
      this.db =new database();
       
      this.id =id;
      this.password =password;
      this.date =null;
  }
  
 
  
  public void signUp(){
  
     try {
         db.insert("user","id","pass","dob",id,password,date);
     } catch (InstantiationException ex) {
         Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
     }
  }
  
  public boolean login(){
      
     return db.authenticate("id","password","user");
  
  }
 
  
}
