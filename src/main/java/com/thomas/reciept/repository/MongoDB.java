package com.thomas.reciept.repository;

import java.util.List;

import com.thomas.reciept.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDB{

    private MongoTemplate mongoTemplate;
    @Autowired
    public MongoDB(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    public void updateQuantity(int id, int quantity){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.fields().include("_id");
        query.fields().include("quantity");
        Item item= mongoTemplate.findOne(query, Item.class);
        int newQuantity= item.getQuantity()+quantity;
        Update update = new Update();
        update.set("quantity",newQuantity);
        mongoTemplate.updateFirst(query,update,Item.class);
    }

    public void updateExportItemQuantity(int id, int quantity){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        query.fields().include("_id");
        query.fields().include("quantity");
        Item item= mongoTemplate.findOne(query, Item.class);
        int newQuantity= item.getQuantity()-quantity;
        Update update = new Update();
        update.set("quantity",newQuantity);
        mongoTemplate.updateFirst(query,update,Item.class);
    }

    public Page<Item> findItems(Pageable pageable){
        Query query = new Query();
        long count = this.mongoTemplate.count(query, Item.class);
        query.with(pageable);
        List<Item> items = mongoTemplate.find(query, Item.class);
        return PageableExecutionUtils.getPage(items, pageable, () -> count);
    }
}
