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
public class Crew extends Vlucht {
    private String naam;
    private String Familienaam;
    private String Maatschappij;
    private String Functie;

    public String getNaam() {
        return naam;
    }

        public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getFamilienaam() {
        return Familienaam;
    }

    public void setFamilienaam(String Familienaam) {
        this.Familienaam = Familienaam;
    }

    public String getMaatschappij() {
        return Maatschappij;
    }

    public void setMaatschappij(String Maatschappij) {
        this.Maatschappij = Maatschappij;
    }

    public String getFunctie() {
        return Functie;
    }

    public void setFunctie(String Functie) {
        this.Functie = Functie;
    }
}
