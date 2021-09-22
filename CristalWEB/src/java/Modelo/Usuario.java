
package Modelo;

public class Usuario {
    
    private int id;
    private String email;
    private String pass;
    private int idPersona;
    private int idTipoUsuario;
    private boolean vigente;

    public Usuario(int id, String email, String pass, int idPersona, int idTipoUsuario,boolean vigente) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.idPersona = idPersona;
        this.idTipoUsuario = idTipoUsuario;
        this.vigente=vigente;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
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
