package de.hsrm.mi.web.projekt.messaging;

import java.util.Objects;

public class FotoMessage {
    public static final String FOTO_GESPEICHERT = "fotoGespeichert";
    public static final String FOTO_GELOESCHT = "fotoGeloescht";
    private String operation;
    private long id;

    public FotoMessage() {

    }
        

    public FotoMessage(String operation, long id) {
        this.operation = operation;
        this.id = id;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FotoMessage operation(String operation) {
        setOperation(operation);
        return this;
    }

    public FotoMessage id(long id) {
        setId(id);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FotoMessage)) {
            return false;
        }
        FotoMessage fotoMessage = (FotoMessage) o;
        return Objects.equals(operation, fotoMessage.operation) && id == fotoMessage.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, id);
    }

    @Override
    public String toString() {
        return "{" +
            " operation='" + getOperation() + "'" +
            ", id='" + getId() + "'" +
            "}";
    }
    
}