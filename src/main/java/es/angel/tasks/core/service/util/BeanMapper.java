package es.angel.tasks.core.service.util;

import java.util.List;

import org.springframework.data.domain.Page;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Mapper Util based on Orika mapper factory.
 *
 * Its purpose is to bring a simple and static way to map fields easily between
 * objects.
 *
 * @author Everis
 */
public final class BeanMapper {

    /**
     * Instantiates a new bean mapper.
     */
    private BeanMapper() {
    }

    /** The Constant MAPPER_FACTORY. */
    private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    /**
     * Create and return a new instance of type D mapped with the properties of
     * <code>sourceObject</code>.
     *
     * @param                  <S> type of the source object
     * @param                  <D> type of the destination class
     * @param sourceObject     the object to map from
     * @param destinationClass the class of the new object to return
     * @return a new instance of type D mapped with the properties of
     *         <code>sourceObject</code>
     */
    public static final <S, D> D transform(S sourceObject, Class<D> destinationClass) {
        return MAPPER_FACTORY.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * Maps the source Iterable into a new List parameterized by
     * <code>destinationClass</code>.
     *
     * @param                  <S> type of elements in the iterable source
     * @param                  <D> type of the destination class
     * @param source           the Iterable from which to map
     * @param destinationClass the class of elements to be contained in the returned
     *                         Set.
     * @return a new List containing elements of type <code>destinationClass</code>
     *         mapped from the elements of <code>source</code>.
     */
    public static final <S, D> List<D> transform(Iterable<S> source, Class<D> destinationClass) {

        return MAPPER_FACTORY.getMapperFacade().mapAsList(source, destinationClass);
    }

    /**
     * Maps de sorce Page into a new Page parameterized by
     * <code>destinationClass</code>.
     *
     * @param                  <S> type of the source
     * @param                  <D> type of the destination class
     * @param source           the page of objects to map from
     * @param destinationClass the class of the new object to return
     * @return the page
     */
    public static final <S, D> Page<D> transform(Page<S> source, Class<D> destinationClass) {
        return source.map(data -> transform(data, destinationClass));
    }
}
