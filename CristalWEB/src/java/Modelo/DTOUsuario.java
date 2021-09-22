
package Modelo;

public class DTOUsuario {
    
    private int id;
    private String email;
    private String pass;
    private Persona persona;
    private int idTipoUsuario;

    public DTOUsuario(int id, String email, String pass, Persona persona, int idTipoUsuario) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.persona = persona;
        this.idTipoUsuario = idTipoUsuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    
    
    
}
