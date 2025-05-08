package car.rental.requests;

public record ConfirmPaymentRequest(String bookingId, String paymentId) {

}
