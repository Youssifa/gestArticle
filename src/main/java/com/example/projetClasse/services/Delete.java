/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetClasse.services;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;

/**
 *
 * @author DG
 */
public class Delete {
    
    public static void main(String[] args) {
        final com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        try (mongoClient) {
            final MongoDatabase database = mongoClient.getDatabase("gestProduits");
            MongoCollection<Document> produitsCollection = database.getCollection("produits");

            // delete one document
            Bson filter = eq("id", 2);
            DeleteResult result = produitsCollection.deleteOne(filter);
            System.out.println(result);

            // findOneAndDelete operation
            filter = eq("id", 3);
            Document doc = produitsCollection.findOneAndDelete(filter);
            System.out.println(doc.toJson());

            // delete many documents
            filter = gte("prix", 10000);
            result = produitsCollection.deleteMany(filter);
            System.out.println(result);

            // delete the entire collection and its metadata (indexes, chunk metadata, etc).
            produitsCollection.drop();
        }
    }
}
