/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.projetClasse.services;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
/**
 *
 * @author DG
 */
public class Update {
  public static void main(String[] args) {
        final com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        try (mongoClient) {
            final MongoDatabase database = mongoClient.getDatabase("gestProduits");
            MongoCollection<Document> produitsCollection = database.getCollection("produits");

            // update one document
            Bson filter = eq("id", 10);
            Bson updateOperation = set("libelle", "LENOVO");
            UpdateResult updateResult = produitsCollection.updateOne(filter, updateOperation);
            System.out.println("=> Updating the doc with {\"id\":10}. Update libelle.");
            System.out.println(produitsCollection.find(filter).first().toJson());
            System.out.println(updateResult);

          
            filter = eq("libelle", "HP");
            updateResult = produitsCollection.updateMany(filter, updateOperation);
            System.out.println("\n=> Updating all the documents with {\"ID\":HP}.");
            System.out.println(updateResult);

            // findOneAndUpdate
            filter = eq("id", 10);
            Bson update1 = inc("prix", 10);
            Bson update2 = rename("libelle", "TINKPAD"); // rename variable "class_id" in "new_class_id".
            Bson update3 = mul("prix", 2); // multiply the first score in the array by 2.

            Bson updates = combine(update1, update2, update3);
            // returns the old version of the document before the update.
            Document oldVersion = produitsCollection.findOneAndUpdate(filter, updates);
            System.out.println("\n=> FindOneAndUpdate operation. Printing the old version by default:");
            System.out.println(oldVersion.toJson());

            // but I can also request the new version
            filter = eq("student_id", 10001);
            FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            Document newVersion = produitsCollection.findOneAndUpdate(filter, updates, optionAfter);
            System.out.println("\n=> FindOneAndUpdate operation. But we can also ask for the new version of the doc:");
            System.out.println(newVersion.toJson());
        }
    }  
}
