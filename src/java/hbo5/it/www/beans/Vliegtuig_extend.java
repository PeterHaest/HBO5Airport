/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbo5.it.www.beans;

/**
 *
 * @author steve
 */
public class Vliegtuig_extend extends Vliegtuig {
    
    private String type_naam;
    private String maatschappij_naam;
    private String lease_maatschappij_naam;
    private boolean leased;

    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }

    public String getType_naam() {
        return type_naam;
    }

    public void setType_naam(String type_naam) {
        this.type_naam = type_naam;
    }

    public String getMaatschappij_naam() {
        return maatschappij_naam;
    }

    public void setMaatschappij_naam(String maatschappij_naam) {
        this.maatschappij_naam = maatschappij_naam;
    }

    public String getLease_maatschappij_naam() {
        return lease_maatschappij_naam;
    }

    public void setLease_maatschappij_naam(String lease_maatschappij_naam) {
        this.lease_maatschappij_naam = lease_maatschappij_naam;
    }
    
    
    
    
    
    
    
}
