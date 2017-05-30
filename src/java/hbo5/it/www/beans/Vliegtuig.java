/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.beans;

/**
 *
 * @author c1040604
 */
public class Vliegtuig {
    private int id;
    private int vliegtuigtype_id;
    private int leasemaatschappij_id;
    private int luchtvaartmaatschappij_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVliegtuigtype_id() {
        return vliegtuigtype_id;
    }

    public void setVliegtuigtype_id(int vliegtuigtype_id) {
        this.vliegtuigtype_id = vliegtuigtype_id;
    }

    public int getLeasemaatschappij_id() {
        return leasemaatschappij_id;
    }

    public void setLeasemaatschappij_id(int leasemaatschappij_id) {
        this.leasemaatschappij_id = leasemaatschappij_id;
    }

    public int getLuchtvaartmaatschappij_id() {
        return luchtvaartmaatschappij_id;
    }

    public void setLuchtvaartmaatschappij_id(int luchtvaartmaatschappij_id) {
        this.luchtvaartmaatschappij_id = luchtvaartmaatschappij_id;
    }
    
    
}
