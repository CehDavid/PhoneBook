
package phonebook;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    
   private final SimpleStringProperty name;
   private final SimpleStringProperty phone;
   private final SimpleStringProperty email;

    public String getName() {
        return name.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getEmail() {
        return email.get();
    }
    
    public void setName(String Name){
        name.set(Name);
    }
    public void setPhone(String Phone){
        phone.set(Phone);
    }
    public void setEmail(String Email){
        email.set(Email);
    }
    public Person() {
        this.name = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
    }
    public Person(String name, String phone, String email) {
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
    }

}
