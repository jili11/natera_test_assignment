package triangle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

import static triangle.utils.TriangleConstants.DEFAULT_SEPARATOR;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Triangle {


    private String separator;

    private String input;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double firstSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double secondSide;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double thirdSide;

    public Triangle(String separator, String input) {
        this.separator = separator;
        this.input = input;
        if (input != null && separator != null) {
            String[] sides = input.split(separator);
            if (sides.length == 3) {
                this.firstSide = Double.valueOf(sides[0]);
                this.secondSide = Double.valueOf(sides[1]);
                this.thirdSide = Double.valueOf(sides[2]);
            }
        }
    }

    public Triangle(String input) {
        this.input = input;
        if (input != null) {
            String[] sides = input.split(DEFAULT_SEPARATOR);
            if (sides.length == 3) {
                this.firstSide = Double.valueOf(sides[0]);
                this.secondSide = Double.valueOf(sides[1]);
                this.thirdSide = Double.valueOf(sides[2]);
            }
        }
    }

    public Triangle() {
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(Double firstSide) {
        this.firstSide = firstSide;
    }

    public Double getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(Double secondSide) {
        this.secondSide = secondSide;
    }

    public Double getThirdSide() {
        return thirdSide;
    }

    public void setThirdSide(Double thirdSide) {
        this.thirdSide = thirdSide;
    }

    public Float calculatePerimeter() {
        String separator = this.separator == null ? DEFAULT_SEPARATOR : this.separator;
        return (float) Arrays.stream(input.split(separator)).mapToInt(Integer::valueOf).sum();
    }

    public Float calculateArea() {
        String separator = this.separator == null ? DEFAULT_SEPARATOR : this.separator;
        int[] sides = Arrays.stream(input.split(separator)).mapToInt(Integer::valueOf).toArray();
        int semiperimeter = IntStream.of(sides).sum() / 2;
        return (float) Math.sqrt(semiperimeter * (semiperimeter - sides[0]) * (semiperimeter - sides[1]) * (semiperimeter - sides[2]));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return firstSide.equals(triangle.firstSide) &&
                secondSide.equals(triangle.secondSide) &&
                thirdSide.equals(triangle.thirdSide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstSide, secondSide, thirdSide);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "separator='" + separator + '\'' +
                ", input='" + input + '\'' +
                ", id='" + id + '\'' +
                ", firstSide=" + firstSide +
                ", secondSide=" + secondSide +
                ", thirdSide=" + thirdSide +
                '}';
    }
}
