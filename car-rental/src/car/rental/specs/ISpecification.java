package car.rental.specs;

public interface ISpecification<T> {
    boolean isSatisfied(T item);

    default ISpecification<T> and(ISpecification<T> other) {
        return item -> this.isSatisfied(item) && other.isSatisfied(item);
    }

    default ISpecification<T> or(ISpecification<T> other) {
        return item -> this.isSatisfied(item) || other.isSatisfied(item);
    }
}
