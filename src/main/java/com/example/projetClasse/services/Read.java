/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetClasse.services;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.descending;
/**
 *
 * @author DG
 */
public class Read {
    public static void main(String[] args) {
        final com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        try (mongoClient) {
            final MongoDatabase database = mongoClient.getDatabase("gestProduits");
            MongoCollection<Document> produitsCollection = database.getCollection("produits");

            // find one document with new Document
            Document produit1 = produitsCollection.find(new Document("id", 1)).first();
            System.out.println("id->1: " + produit1.toJson());

            // find one document with Filters.eq()
            Document produit2 = produitsCollection.find(eq("libelle", "HP")).first();
            System.out.println("LIBELLE -> HP: " + produit2.toJson());

            // find a list of documents and iterate throw it using an iterator.
            FindIterable<Document> iterable = produitsCollection.find(gte("prix", 1000));
            MongoCursor<Document> cursor = iterable.iterator();
            System.out.println("Student list with a cursor: ");
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }

            // find a list of documents and use a List object instead of an iterator
            List<Document> produitList = produitsCollection.find(gte("prix", 10000)).into(new ArrayList<>());
            System.out.println("Student list with an ArrayList:");
            for (Document produit : produitList) {
                System.out.println(produit.toJson());
            }

            // find a list of documents and print using a consumer
            System.out.println("Produit list using a Consumer:");
            Consumer<Document> printConsumer = document -> System.out.println(document.toJson());
            produitsCollection.find(gte("prix", 12000)).forEach(printConsumer);

            // find a list of documents with sort, skip, limit and projection
            List<Document> docs = produitsCollection.find(and(eq("prix", 1000), lte("prix", 2000)))

                                                  .sort(descending("prix"))
                                                  .skip(2)
                                                  .limit(10)
                                                  .into(new ArrayList<>());

            System.out.println("Student sorted, skipped, limited and projected: ");
            for (Document produit : docs) {
                System.out.println(produit.toJson());
            }
        }
    } 
}
