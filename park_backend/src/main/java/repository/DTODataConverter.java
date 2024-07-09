package repository;

/**
 * Interface to convert any object to database entities.
 *
 * @param <X> type of the DTO object.
 * @param <Y> type of the object to convert to and from DTO object.
 */
public interface DTODataConverter<X, Y> {
  X convertToDTO(Y object);
  
  X convertToDTO(Y object, X dto);
}
