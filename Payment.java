public class Payment {
    private int payment;
    private boolean paymentStatus;

    public Payment(int payment, boolean paymentStatus) {
        this.payment = payment;
        this.paymentStatus = paymentStatus;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
