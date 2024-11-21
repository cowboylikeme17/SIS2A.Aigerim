class Circle extends Shape {
    private double r;
    public Circle(double r) {
        this.r = r;
    }
    @Override
    public double calculateArea() {
        return Math.PI * r * r;
    }
    @Override
    public String toString() {
        return "Circle with params " + r;
    }
}