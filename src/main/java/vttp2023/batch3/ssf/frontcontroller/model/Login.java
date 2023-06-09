package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class Login {
    @NotNull(message="Please key in your username")
    @Size(min=2, message="username should be at least 2 char")
    private String user;

    @NotNull(message="Please key in your password")
    @Size(min=2, message="password should be at least 2 char")
    private String password;
    
    public Login() {
    }

    public Login(
            @NotNull(message = "Please key in your username") @Size(min = 2, message = "username should be at least 2 char") String user,
            @NotNull(message = "Please key in your password") @Size(min = 2, message = "password should be at least 2 char") String password) {
        this.user = user;
        this.password = password;
    }


    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login [user=" + user + ", password=" + password + "]";
    }

    public Login fromJson(String json){
        Login login = new Login();
      
        JsonReader r = Json.createReader(new StringReader(json));
        JsonObject o = r.readObject();  
        
        login.setUser(o.getString("username"));
        login.setPassword(o.getString("password"));
     
        return login;

     }

    // create a toJson method
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
        .add("username", this.getUser())
        .add("password", this.getPassword())
        .build();
    }
    
}
