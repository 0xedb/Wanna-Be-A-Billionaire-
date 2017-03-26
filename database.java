/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dywtbb;

import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kdegb_000
 */
public class database {
    
    /**
     *
     * @param tableName
     * @param attribute1
     * @param attribute2
     * @param attribute3
     * @param attribute1Value
     * @param attribute2Value
     * @param attribute3Value
     * @throws java.lang.InstantiationException
     */
    public void insert(String tableName,String attribute1,String attribute2,String attribute3,String attribute1Value,String attribute2Value,String attribute3Value) throws InstantiationException
  {
  
    try{ 
   Class.forName("com.mysql.jdbc.Driver").newInstance();  
  
    Connection conn = java.sql.DriverManager.getConnection(    "jdbc:mysql://localhost/dyb?user=root&password=root"); 
    
    String a ="Insert Into ";
    
    String b= tableName;
    
    String c =" set ";
    
    String d = attribute1+ "=?,";
    
    String e =attribute2+ "=?,";
    
    String f =attribute3+ "=?";
    
    
   PreparedStatement p= conn.prepareStatement(a+b+c+d+e+f);    
  //  p.setString(1, tableValue);   
    p.setString(1, attribute1Value );    
    p.setString(2, attribute2Value);   
    p.setString(3,attribute3Value) ;
    p.execute();  
//use execute if no results expected back   
  }catch(SQLException e){         System.out.println("Error"+e.toString());         
 }       catch (IllegalAccessException | ClassNotFoundException ex) { 
             Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
         }
  
} 
  
    /**
     *
     * @param userId
     * @param userPass
     * @param tableName
     * @return
     */
    public boolean authenticate(String userId,String userPass,String tableName){
        
    boolean statement =false;
    java.sql.Connection conn = null;
    
    String a =tableName;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
             
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost/DYB?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

   try {
            java.sql.Statement s = conn.createStatement();

            java.sql.ResultSet r = s.executeQuery("SELECT * FROM"+" "+a);

            while (r.next()) {

                /*System.out.println (    r.getString("titel") + " " +    r.getString("interpret") + 
              " " +    r.getString("jahr") ); */
                if (r.getString("id").equals(userId) && r.getString("pass").equals(userPass)) {
                  statement=true;
                }
                     }
            
                    
            
            r.close();
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
   return statement;
}
  
  //for select all command
 // public String[] getAll();
    
    public void addQuestion(String id,String ans,String possibleAns,String question){
     try{ 
   Class.forName("com.mysql.jdbc.Driver").newInstance();  
  
    Connection conn = java.sql.DriverManager.getConnection(    "jdbc:mysql://localhost/dyb?user=root&password=root"); 
    
    String a ="Insert Into ";
    
    String b= "question";
    
    String c =" set ";
    
    String d = "id"+ "=?,";
    
    String e ="ans"+ "=?,";
    
    String f ="possible"+ "=?,";
    
    String g ="question"+ "=?";
    
    
   PreparedStatement p= conn.prepareStatement(a+b+c+d+e+f+g);    
  //  p.setString(1, tableValue);   
    p.setString(1, id );    
    p.setString(2, ans);   
    p.setString(3,possibleAns) ;
    p.setString(4,question) ;
    p.execute();  
//use execute if no results expected back   
  }catch(SQLException e){         System.out.println("Error"+e.toString());         
 }       catch (IllegalAccessException | ClassNotFoundException ex) { 
             Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
  
} 
    
  public String getQuestionAttribute(String Id,String attribute){
  
  java.sql.Connection conn = null;
    String statement =null;
    String a =Id;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
             
            conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost/DYB?user=root&password=root");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

   try {
            java.sql.Statement s = conn.createStatement();

            java.sql.ResultSet r = s.executeQuery("SELECT" +" "+ attribute  +" "+ "FROM question where id ="+a);

            while (r.next()) {

                /*System.out.println (    r.getString("titel") + " " +    r.getString("interpret") + 
              " " +    r.getString("jahr") ); */
                statement=r.getString(attribute);
                     }
            
                    
            
            r.close();
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
   return statement;
  
  
  }  
  //get answer
 // get possible ans
 
  //un comment to test
 /* public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
         java.sql.Connection conn = null; 
//namanquah@ashesi.edu.gh 2015 3  
  
 System.out.println("This program demos DB connectivity");  
 
try {   Class.forName("com.mysql.jdbc.Driver").newInstance();  
  
  conn = java.sql.DriverManager.getConnection(    "jdbc:mysql://localhost/dyb?user=root&password=root");  
}  
catch (  ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) 
  
{   System.out.println(e);   System.exit(0);  } 

System.out.println("Connection established");

         try { 
            insert("user","id","pass","dob", "kevo","kevo",null);
         } catch (InstantiationException ex) {
                Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         System.out.println(authenticate("kevo","kevo","user"));
         
       //  addQuestion("1","cat","dog,cat,hey","what is cute?");
       
       System.out.println(getQuestionAttribute("1","question"));
       System.out.println(getQuestionAttribute("1","ans"));
       System.out.println(getQuestionAttribute("1","possible"));
 }*/
}
