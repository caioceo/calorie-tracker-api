package dev.caio.fitsy.model.Enum;

public enum Role {
    FREEMIUM("freemium"),
    PREMIUM("premium");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
