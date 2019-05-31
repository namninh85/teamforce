package com.nin.service;

import com.nin.model.Product;
import com.nin.model.Store;
import com.nin.repository.StoreRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class StoreRepositoryImpl implements StoreRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Store> findByProductId(String productId, String storeName, String street) {
        TypedQuery<Store> query = em.createQuery("SELECT s,u FROM Store s " +
                "INNER JOIN Store_utility u ON s.store_id = u.store_id " +
                "WHERE s.store_id =(SELECT store_id FROM product_in_store WHERE product_id=:productId) " +
                "AND s.name LIKE :storeName AND s.street LIKE :street", Store.class);
        query.setParameter("productId", productId);
        query.setParameter("storeName", "%" + storeName + "%");
        query.setParameter("street", "%" + street + "%");
        return query.getResultList();
    }

    @Override
    public List<Store> findAll() {
        return null;
    }

    @Override
    public List<Store> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Store> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public <S extends Store> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Store> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Store> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Store getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Store> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Store> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Store> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Store> S save(S s) {
        return null;
    }

    @Override
    public Optional<Store> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Store store) {

    }

    @Override
    public void deleteAll(Iterable<? extends Store> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Store> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Store> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Store> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Store> boolean exists(Example<S> example) {
        return false;
    }
}
