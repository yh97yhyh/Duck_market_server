package com.example.demo.recommend;

import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecommendService {
    @Autowired
    private DataSource dataSource;

    public List<Recommend> recommend(long userId) throws TasteException {
        //interest table column명에 맞게 수정
        JDBCDataModel model=new MySQLJDBCDataModel(dataSource,"interest","user_id",
                "merchandise_id","prefer","null");
        ReloadFromJDBCDataModel dataModel=new ReloadFromJDBCDataModel(model);
        UserSimilarity similarity=new PearsonCorrelationSimilarity(dataModel);
        UserNeighborhood neighborhood=new ThresholdUserNeighborhood(0,similarity,dataModel);
        UserBasedRecommender recommender=new GenericUserBasedRecommender(dataModel,neighborhood,similarity);
        List<RecommendedItem> recommendedItems=recommender.recommend(userId,5);

        List<Recommend> recommends=new ArrayList<>();
        for(RecommendedItem recommendedItem:recommendedItems){
            log.debug("{}",recommendedItem.getItemID());
            log.debug("{}",recommendedItem.getValue());
            Recommend recommend=new Recommend(recommendedItem.getItemID(),recommendedItem.getValue());
            recommends.add(recommend);
        }
        return recommends;
    }
}
