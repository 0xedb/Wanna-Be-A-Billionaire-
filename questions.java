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
public class questions {
    
    String id;
    
    String answer ;
    
    String possibleAnswers;
    
    String question ;
    
    database db;

    public questions(String id,String question,String ans,String possible ) {
        
        this.db =new database();
                
        this.id=id;
        
        this.question =question;
        
        this.answer =ans;
        
        this.possibleAnswers =possible ;
    }
    
       public questions(String id ) {
        
        this.db =new database();
        
        this.id=id;
        
        this.question =null;
        
        this.answer =null;
        
        this.possibleAnswers =null ;
    }
       
   public void askQuestion(){
   
   db.addQuestion(id,answer,possibleAnswers,question);
   
   }  
   
   public String getAns(String Id){
   
   return db.getQuestionAttribute( Id,"ans");
   }
    


public String getQuestion(String Id){
   
   return db.getQuestionAttribute( Id,"question");
   }


public String[] getPossibleAns(String Id){
   
    String [] a;
    
    a=db.getQuestionAttribute( Id,"question").split(",");
    
    return a;
    }
    

}