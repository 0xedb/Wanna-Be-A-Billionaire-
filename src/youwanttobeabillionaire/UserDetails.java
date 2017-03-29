
package youwanttobeabillionaire;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bruno
 */
public class UserDetails {
    private final StringProperty user;
    private final StringProperty email;
    private final IntegerProperty money;
    
    public UserDetails (String user, String email, int money) {
        this.user = new SimpleStringProperty(user);
        this.email = new SimpleStringProperty(email);
        this.money = new SimpleIntegerProperty(money);
    }
    
    public String getUser() {
        return user.get();
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public int getMoney() {
        return money.get();
    }
    
    public void setUser(String value) {
        user.set(value);
    }
    
    public void setEmail(String value) {
        email.set(value);
    }
    
    public void setMoney(int value) {
        money.set(value);
    }
   
    public StringProperty userProperty() {
        return user;
    }
    
    public StringProperty emailProperty() {
        return email;
    }
    
    public IntegerProperty moneyProperty() {
        return money;
    }
}
