package utils;

/**
 * A generic helper class for encapsulating response with data, status, and
 * messages.
 * 
 * @param <T> the type of data this payload contains
 */
public class Payload<T> {
	private T data;
	private boolean success;
	private String message;

	/**
	 * Constructs a new {@code Payload} object with the given data, success status,
	 * and message.
	 * 
	 * @param data    the data contained in the payload
	 * @param success {@code true} if the operation was successful; {@code false}
	 *                otherwise
	 * @param message a descriptive message about the operation
	 */
	public Payload(T data, boolean success, String message) {
		this.data = data;
		this.success = success;
		this.message = message;
	}

	// ===================================================
	// ================== GETTER-SETTER ==================
	// ===================================================

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
