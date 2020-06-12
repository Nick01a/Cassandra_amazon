package com.ucu.edu.bigdata.cassandra.predicate;

import com.querydsl.jpa.impl.JPAQuery;
import com.ucu.edu.bigdata.cassandra.entity.Product;
import com.ucu.edu.bigdata.cassandra.entity.QProduct;
import com.ucu.edu.bigdata.cassandra.entity.QReview;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Component
public class ProductPredicate {

    @PersistenceContext
    private EntityManager entityManager;

    QProduct qProduct = QProduct.product;
    QReview qReview = QReview.review;


    public List<Product> getFavoriteProducts(LocalDate beginDate, LocalDate endDate, Integer limit) {
        return new JPAQuery<>(entityManager)
                .select(qProduct)
                .from(qProduct)
                .innerJoin(qProduct.reviews, qReview)
                .where(qReview.date.between(beginDate, endDate))
                .groupBy(qProduct.id)
                .orderBy(qProduct.reviews.size().desc())
                .limit(limit)
                .fetch();
    }

    public List<Product> getBestProducts(Integer limit) {
        return new JPAQuery<>(entityManager)
                .select(qProduct)
                .from(qProduct)
                .innerJoin(qProduct.reviews, qReview)
                .where(qReview.isVerified.isTrue().and(qReview.rating.eq(5)))
                .groupBy(qProduct.id, qReview.isVerified)
                .orderBy(qReview.isVerified.count().desc(), qProduct.reviews.size().desc())
                .having(qReview.isVerified.count().goe(100))
                .limit(limit)
                .fetch();
    }
}
