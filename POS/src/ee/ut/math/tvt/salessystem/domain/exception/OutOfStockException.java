package ee.ut.math.tvt.salessystem.domain.exception;

/**
 * Thrown when trying to add more of an item to an order than is available in
 * the warehouse.
 */
public class OutOfStockException extends Exception {

	private static final long serialVersionUID = 1L;

	public OutOfStockException() {
		super();
	}

	public OutOfStockException(final String message) {
		super(message);
	}
}
