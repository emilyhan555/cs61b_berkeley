public class Body {
	/** create a constant that represent Gravitation. */
	public static final double gravity = 6.67e-11; //Math.pow(10,-11);

	/** create 6 instance variables*/
	/** Its current x position. */
	public double xxPos;

	/** Its current y position. */
	public double yyPos;

	/** Its current velocity in the x direction. */
	public double xxVel;

	/** Its current velocity in the y direction. */
	public double yyVel;

	/** Its mass */
	public double mass;
	
	/** The name of the file that corresponds to the image that depicts the body. */
	public String imgFileName;

	/** create a Body constructor that can initialize an instance of the Body class. */
	public Body(double xP,  double yP,  double xV,  double yV,  double m,  String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**
	 * create Body copy constructor that can initialize an instance of the Body
	 * class.
	 */
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/**
	 * add a method to calculate the distance between two Bodys. This method will
	 * take in a single Body and return a double.
	 */
	public double calcDistance(Body b) {
		 double dx = b.xxPos - this.xxPos;
		 double dy = b.yyPos - this.yyPos;
		 double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	/**
	 * create a method to return a double describing the force exerted on the body
	 * by a given body.
	 */
	public double calcForceExertedBy(Body b) {
		 double r = this.calcDistance(b);
		 double mThis = this.mass;
		 double mb = b.mass;
		 double F = (gravity * mThis * mb) / (r * r);
		return F;
	}

	/**
	 * create two method to return a double that describing the force exerted in the
	 * X and Y directions, respectively.
	 */
	public double calcForceExertedByX(Body b) {
		 double F = this.calcForceExertedBy(b);
		 double dx = b.xxPos - this.xxPos;
		 double r = this.calcDistance(b);
		 double Fx = F * dx / r;
		return Fx;
	}

	public double calcForceExertedByY(Body b) {
		 double F = this.calcForceExertedBy(b);
		 double dy = b.yyPos - this.yyPos;
		 double r = this.calcDistance(b);
		 double Fy = F * dy / r;
		return Fy;
	}

	/** create two methods that return a double that describing the net force exerted in the X and Y directions, respectively. */
	public double calcNetForceExertedByX(Body[] allBodys) {
		double netForceX = 0.00;
		for (int i = 0; i < allBodys.length; i++) {
			if (this.equals(allBodys[i])) {
				continue;
			}
			double forceX = this.calcForceExertedByX(allBodys[i]);
			netForceX = netForceX + forceX;
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] allBodys) {
		double netForceY = 0.00;
		for (int i = 0; i < allBodys.length; i++) {
			if (this.equals(allBodys[i])) {
				continue;
			}
			double forceY = this.calcForceExertedByY(allBodys[i]);
			netForceY = netForceY + forceY;
		}
		return netForceY;
	}

	/** create a method to update the position, velocity and accelaration. */
	public void update(double dt, double fx, double fy){
		double m = this.mass;
		double ax = fx/m;
		double ay = fy/m;
		this.xxVel = this.xxVel + dt * ax;
		this.yyVel = this.yyVel + dt * ay;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	/** create method to draw each one of the bodies in the Bodies array. */
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "C:/Users/emily/cs61b/cs61b_berkeley/project0/images/"+this.imgFileName);
	}

}