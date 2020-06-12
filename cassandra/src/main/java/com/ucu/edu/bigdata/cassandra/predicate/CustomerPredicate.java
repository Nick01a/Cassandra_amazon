package com.ucu.edu.bigdata.cassandra.predicate;

import com.querydsl.jpa.impl.JPAQuery;
import com.ucu.edu.bigdata.cassandra.entity.Customer;
import com.ucu.edu.bigdata.cassandra.entity.QCustomer;
import com.ucu.edu.bigdata.cassandra.entity.QReview;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Component
public class CustomerPredicate {

    @PersistenceContext
    private EntityManager entityManager;

    QCustomer qCustomer = QCustomer.customer;
    QReview qReview = QReview.review;

    public List<Customer> getProductiveCustomers(LocalDate beginDate, LocalDate endDate, Integer limit) {
        return new JPAQuery<>(entityManager)
                .select(qCustomer)
                .from(qCustomer)
                .innerJoin(qCustomer.reviews, qReview)
                .where(qReview.date.between(beginDate, endDate)
                        .and(qReview.isVerified.isTrue()))
                .groupBy(qCustomer.id)
                .orderBy(qCustomer.reviews.size().desc())
                .limit(limit)
                .fetch();
    }

    public List<Customer> getHaters(LocalDate beginDate, LocalDate endDate, Integer limit) {
        return new JPAQuery<>(entityManager)
                .select(qCustomer)
                .from(qCustomer)
                .innerJoin(qCustomer.reviews, qReview)
                .where(qReview.date.between(beginDate, endDate)
                        .and(qReview.isVerified.isTrue())
                        .and(qReview.rating.eq(1).or(qReview.rating.eq(2))))
                .groupBy(qCustomer.id)
                .orderBy(qCustomer.reviews.size().desc())
                .limit(limit)
                .fetch();
    }

    public List<Customer> getBackers(LocalDate beginDate, LocalDate endDate, Integer limit) {
        return new JPAQuery<>(entityManager)
                .select(qCustomer)
                .from(qCustomer)
                .innerJoin(qCustomer.reviews, qReview)
                .where(qReview.date.between(beginDate, endDate)
                        .and(qReview.isVerified.isTrue())
                        .and(qReview.rating.eq(4).or(qReview.rating.eq(5))))
                .groupBy(qCustomer.id)
                .orderBy(qCustomer.reviews.size().desc())
                .limit(limit)
                .fetch();
    }
}
