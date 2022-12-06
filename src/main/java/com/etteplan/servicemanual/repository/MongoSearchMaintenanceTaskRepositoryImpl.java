package com.etteplan.servicemanual.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.etteplan.servicemanual.model.MaintenanceTask;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongoSearchMaintenanceTaskRepositoryImpl implements MongoSearchMaintenanceTaskRepository {

    @Autowired
    MongoClient mongoClient;
    @Autowired
    MongoConverter mongoConverter;

    // Testing Mongodb Atlas cloud aggregations
    public List<MaintenanceTask> findByDescriptionText(String text) {

        final List<MaintenanceTask> mlist = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("backendtest");
        MongoCollection<Document> collection = database.getCollection("maintenancetasks");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", text)
                                .append("path", Arrays.asList("description")))),
                new Document("$sort",
                        new Document("exp", 1L))));

        result.forEach(document -> mlist.add(mongoConverter.read(MaintenanceTask.class, document)));
        return mlist;
    }
}
