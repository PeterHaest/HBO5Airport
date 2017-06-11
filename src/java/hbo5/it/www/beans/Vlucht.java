/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.beans;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author c1040604
 */
public class Vlucht {
    private int id;
    private String code;
    private Timestamp vertrektijd;
    private Timestamp aankomsttijd;
    private int vliegtuig_id;
    private int vertrekluchthaven_id;
    private int aankomstluchthaven_id;
    private Luchtvaartmaatschappij luchtvaarmaatschappij;
    private Luchthaven aankomstluchthaven;
    private Luchthaven vertrekluchthaven;
    private Vliegtuig vliegtuig;
    private Vliegtuigtype vliegtype;

    public Luchtvaartmaatschappij getLuchtvaarmaatschappij() {
        return luchtvaarmaatschappij;
    }

    public void setLuchtvaarmaatschappij(Luchtvaartmaatschappij luchtvaarmaatschappij) {
        this.luchtvaarmaatschappij = luchtvaarmaatschappij;
    }

    public Luchthaven getAankomstluchthaven() {
        return aankomstluchthaven;
    }

    public void setAankomstluchthaven(Luchthaven aankomstluchthaven) {
        this.aankomstluchthaven = aankomstluchthaven;
    }

    public Luchthaven getVertrekluchthaven() {
        return vertrekluchthaven;
    }

    public void setVertrekluchthaven(Luchthaven vertrekluchthaven) {
        this.vertrekluchthaven = vertrekluchthaven;
    }

    public Vliegtuig getVliegtuig() {
        return vliegtuig;
    }

    public void setVliegtuig(Vliegtuig vliegtuig) {
        this.vliegtuig = vliegtuig;
    }

    public Vliegtuigtype getVliegtype() {
        return vliegtype;
    }

    public void setVliegtype(Vliegtuigtype vliegtype) {
        this.vliegtype = vliegtype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getVertrektijd() {
        return vertrektijd;
    }

    public void setVertrektijd(Timestamp vertrektijd) {
        this.vertrektijd = vertrektijd;
    }

    public Date getAankomsttijd() {
        return aankomsttijd;
    }

    public void setAankomsttijd(Timestamp aankomsttijd) {
        this.aankomsttijd = aankomsttijd;
    }

    public int getVliegtuig_id() {
        return vliegtuig_id;
    }

    public void setVliegtuig_id(int vliegtuig_id) {
        this.vliegtuig_id = vliegtuig_id;
    }

    public int getVertrekluchthaven_id() {
        return vertrekluchthaven_id;
    }

    public void setVertrekluchthaven_id(int vertrekluchthaven_id) {
        this.vertrekluchthaven_id = vertrekluchthaven_id;
    }

    public int getAankomstluchthaven_id() {
        return aankomstluchthaven_id;
    }

    public void setAankomstluchthaven_id(int aankomstluchthaven_id) {
        this.aankomstluchthaven_id = aankomstluchthaven_id;
    }
    
    
}
