package unrefined.media.graphics;

import unrefined.io.BinaryInput;
import unrefined.io.BinaryOutput;
import unrefined.io.BundleInput;
import unrefined.io.BundleOutput;
import unrefined.io.Savable;

import java.io.IOException;

public class Point implements Savable {

    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPoint(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void writePortable(BinaryOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
    }

    @Override
    public void readPortable(BinaryInput in) throws IOException {
        x = in.readInt();
        y = in.readInt();
    }

    @Override
    public void to(Object dst) {
        ((Point) dst).setPoint(this);
    }

    @Override
    public void from(Object src) {
        setPoint((Point) src);
    }

    @Override
    public void swap(Object o) {
        Point that = (Point) o;
        int x = that.x;
        int y = that.y;
        that.setPoint(this);
        setPoint(x, y);
    }

    @Override
    public Point clone() {
        try {
            return (Point) super.clone();
        }
        catch (CloneNotSupportedException e) {
            return copy();
        }
    }

    @Override
    public Point copy() {
        return new Point(this);
    }

    @Override
    public void reset() {
        x = y = 0;
    }

    @Override
    public boolean isIdentity() {
        return x == 0 && y == 0;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Point that = (Point) object;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + '{' +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public void writeToBundle(BundleOutput out) throws IOException {
        out.putInt("x", x);
        out.putInt("y", y);
    }

    @Override
    public void readFromBundle(BundleInput in) throws IOException {
        x = in.getInt("x", 0);
        y = in.getInt("y", 0);
    }

}
