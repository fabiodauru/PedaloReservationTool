package com.pedaloreservation;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;

public class DBAccess {

    private String DatabaseName = "Pedalo";
    private String ConnectionString = "mongodb://localhost:27017";
    private MongoDatabase db;

    CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );

    public void connect(){
        var client = MongoClients.create(ConnectionString);
        db = client.getDatabase(DatabaseName);
    }

    public void save(Reservation reservation){
        connect();
        MongoCollection<Document> collection = db.getCollection("reservations");
        InsertOneResult result = collection.insertOne(new Document()
                .append("date", reservation.getDate())
                .append("startTime", reservation.getStartTime())
                .append("duration", reservation.getDuration())
                .append("pedalSize", reservation.getPedalSize())
        );
    }

    public List<Reservation> getAllReservations(){
        connect();
        MongoCollection<Document> collection = db.getCollection("reservations");
        FindIterable<Document> res = collection.find(lt("pedalSize", 9));
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Document doc : res){
            reservations.add(pojoCodecRegistry.get(Reservation.class).decode(new BsonDocumentReader(doc.toBsonDocument()), DecoderContext.builder().build()));
        }
        return reservations;
    }

    public void deleteReservation(Reservation reservation){
        connect();
        MongoCollection<Document> collection = db.getCollection("reservations");
        collection.findOneAndDelete(eq("_id", reservation.getId()));
    }



}
