package br.com.template.core.services;

import br.com.template.api.exceptions.NotFoundException;
import br.com.template.core.Crud;
import br.com.template.infra.entities.EntityBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public abstract class CrudService<D, E extends EntityBase<Integer>, R extends JpaRepository> implements Crud<D, Integer> {

    @Autowired
    private R repository;

    protected abstract E toEntity(D model);
    protected abstract D toModel(E entity);
    protected abstract List<D> toModel(List<E> entity);

    @Override
    public void create(final D model) {
        final E entity = toEntity(model);
        repository.save(entity);
    }

    @Override
    public void update(Integer id, final D model) {
        final E entity = toEntity(model);
        entity.setId(id);
        this.create(model);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<D> findAll() {
        return toModel(repository.findAll());
    }

    @Override
    public D get(final Integer id) {
        try{
           E entity = (E) repository.findById(id).orElseThrow();
           return toModel(entity);
        }catch (NoSuchElementException e) {
            throw new NotFoundException("Nenhum registro encontrato para o usuário: "+ id);
        }
    }
}
