package com.tweetster.mapper;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import com.tweetster.dto.datatype.BaseEntity;
import com.tweetster.dto.datatype.Reference;
import com.tweetster.exception.ReferencedEntityNotFoundException;

@Component
public class ReferenceMapper {

    private final EntityManager entityManager;

    public ReferenceMapper(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public <Entity extends BaseEntity<Id>, Id extends Serializable> Entity toEntity(Reference<Entity, Id> reference, @TargetType Class<Entity> entityClass) {
        if(reference != null) {
        	Entity result = entityManager.find( entityClass, reference.getId() );
        	if(result == null)
        		throw new ReferencedEntityNotFoundException(entityClass, reference.getId());
        	return result;
        }
		return null;
    }

    public <Entity extends BaseEntity<Id>, Id extends Serializable> Reference<Entity, Id> toReference(Entity entity) {
        return entity != null ? new Reference<>( entity.getId() ) : null;
    }

}