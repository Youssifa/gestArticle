/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetClasse.services;

import com.mongodb.MongoClientURI;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author DG
 */
public class Create {


    public static void main(String[] args) {
        final com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
         
        try (mongoClient) {

           final MongoDatabase database = mongoClient.getDatabase("gestProduits");
            MongoCollection<Document> produitsCollection = database.getCollection("produits");

            insertOneDocument(produitsCollection, "TOSHIBA",generateNewCategorie("Ordinateur"));
            List<String> listLibelle = new ArrayList<>();
            listLibelle.add("HP");
            listLibelle.add("Azur");
            listLibelle.add("TOSHIBA");
            insertManyDocuments(produitsCollection,listLibelle,generateNewCategorie("Ordinateur"));
        }
    }

    private static void insertOneDocument(MongoCollection<Document> produitsCollection,String libelle, Document categorie) {
        produitsCollection.insertOne(generateNewProduit(libelle,categorie,20000));
        System.out.println("One grade inserted for studentId 10000.");
    }

    private static void insertManyDocuments(MongoCollection<Document> produitsCollection,List<String> libelles, Document categorie) {
        List<Document> produits = new ArrayList<>();
        libelles.forEach(libelle->{
            produits.add(generateNewProduit(libelle,categorie,10000));
        });



        produitsCollection.insertMany(produits, new InsertManyOptions().ordered(false));
        System.out.println("Insertion des Produits.");
    }


    private static Document generateNewProduit( String libelle, Document categorie,Integer prix) {

        return new Document("_id", new ObjectId()).append("libelle", libelle)
                .append("prix", prix)
                .append("categorie", categorie);
    }
    private static Document generateNewCategorie( String libelle) {

        return new Document("_id", new ObjectId()).append("libelle", libelle);
    }
}
