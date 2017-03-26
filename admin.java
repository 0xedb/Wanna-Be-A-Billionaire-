/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dywtbb;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kdegb_000
 */
public class admin extends user {
    
    public admin(String id, String password, String date) {
        super(id, password, date);
    }
    
    
    @Override
      public void signUp(){
  
     try {
         db.insert("admin","id","pass","dob",id,password,date);
     } catch (InstantiationException ex) {
         Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
     }
  }
      
    @Override
      public boolean login(){
      
     return db.authenticate("id","password","admin");
  
  }
      
      
}
