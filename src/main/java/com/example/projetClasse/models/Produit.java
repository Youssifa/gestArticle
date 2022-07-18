/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetClasse.models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


import java.util.Objects;
/**
 *
 * @author DG
 */
public class Produit {
     private ObjectId id;
    private String libelle;
    private Integer prix;
    @BsonProperty(value = "categorie_id")
    private Categorie categorie;

    public Produit(ObjectId id, String libelle, Categorie categorie) {
        this.id = id;
        this.libelle = libelle;
        this.categorie = categorie;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Categorie getCategorieId() {
        return categorie;
    }

    public void setCategorieId(Categorie categorieId) {
        this.categorie = categorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit)) return false;
        Produit produit = (Produit) o;
        return Objects.equals(getId(), produit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
