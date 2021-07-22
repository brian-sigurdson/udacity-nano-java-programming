package inheritanceExample;

public class StudentEmployee extends Student {
    private double rateOfPayPerHour;
    private String employeeId;

    public StudentEmployee (String firstName, String lastName, String studentId, String employeeId, double rateOfPayPerHour) {
        super(firstName, lastName, studentId);
        this.employeeId = employeeId;
        this.rateOfPayPerHour = rateOfPayPerHour;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public double getRateOfPayPerHour() {
        return rateOfPayPerHour;
    }

    public void setRateOfPayPerHour(double rateOfPayPerHour) {
        this.rateOfPayPerHour = rateOfPayPerHour;
    }

    public String toString() {
        return super.toString() + "; Employee ID: " + employeeId + " rate: " + rateOfPayPerHour;
    }
}
