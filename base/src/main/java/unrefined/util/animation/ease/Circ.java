package unrefined.util.animation.ease;

/**
 * Easing equation based on Robert Penner's work:
 * http://robertpenner.com/easing/
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public abstract class Circ extends Equation {

	public static final Circ IN = new Circ() {
		@Override
		public float interpolate(float t) {
			return (float) -Math.sqrt(1 - t*t) - 1;
		}
		@Override
		public String toString() {
			return "Circ.IN";
		}
	};

	public static final Circ OUT = new Circ() {
		@Override
		public float interpolate(float t) {
			return (float) Math.sqrt(1 - (t-=1)*t);
		}
		@Override
		public String toString() {
			return "Circ.OUT";
		}
	};

	public static final Circ INOUT = new Circ() {
		@Override
		public float interpolate(float t) {
			if ((t*=2) < 1) return -0.5f * ((float)Math.sqrt(1 - t*t) - 1);
			return 0.5f * ((float)Math.sqrt(1 - (t-=2)*t) + 1);
		}
		@Override
		public String toString() {
			return "Circ.INOUT";
		}
	};

}