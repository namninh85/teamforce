package com.nin.service;

import com.nin.model.Category;
import com.nin.model.Product;
import com.nin.repository.CategoryRepository;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    EntityManager em;


    @Override
    public List<Category> findAllCategory() {
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c WHERE c.isActive = true AND c.isDeleted = false ORDER BY c.productCategoryId", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Category> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Category> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Category> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Category getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> S save(S s) {
        return null;
    }

    @Override
    public Optional<Category> findById(Long aLong) {
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
    public void delete(Category category) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Category> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Category> boolean exists(Example<S> example) {
        return false;
    }
}
