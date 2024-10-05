public class Passenger {
    private String passengerName;
    private int age;
    private String phone;
    private String idProof;

    public Passenger(String passengerName, int age, String phone, String idProof) {
        this.passengerName = passengerName;
        this.age = age;
        this.phone = phone;
        this.idProof = idProof;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerName='" + passengerName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", idProof='" + idProof + '\'' +
                '}';
    }

    public void displayPassengerDetails() {
        System.out.println(this);
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }
}
