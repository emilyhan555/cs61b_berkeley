public class NBody{
    /** create a method that can read the Radius of the universe. */
    public static double readRadius(String file){
        In in = new In(file);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    /** create a method that can read the Bodies in the universe. */
    public static Body[] readBodies(String file){
        In in = new In(file);
        int N = in.readInt();
        double R = in.readDouble();

        Body[] Bodies = new Body[N];

        /** use a for loop to create each Body in the Bodies array. */
        for (int i = 0; i< N; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Bodies[i] = new Body(xxPos, yyPos, xxVel,yyVel, mass, img);
        }
        return Bodies;
    }

    public static void main(String[] args){
        /** collect all needed Input from command line arguments. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        /** read Bodies from the file given */
        Body[] Bodies = readBodies(filename);

        /** read the universe radius from the file given */
        double R = readRadius(filename);

        /** Enables double buffering.
		  * A animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. */
		StdDraw.enableDoubleBuffering();

		/** Sets up the universe so it goes from */
		StdDraw.setScale(-R, R);

        /* Clears the drawing window. */
        StdDraw.clear();
        
        /** stamp the picture  */
        StdDraw.picture(0,0, "C:/Users/emily/cs61b/cs61b_berkeley/project0/images/starfield.jpg");

        /** show the drawing to the screen. */
        StdDraw.show();
        StdDraw.pause(10);

        /** draw all the Bodies. */
        for(Body planet: Bodies){
            planet.draw();
        }

        /** Automation */
        
        /** create a time variable and loop until this time variable reaches (and includes) the T from above. */
        for(double time = 0; time <= T; time=time+dt){
            /** create an xForces array and a yForces array. */
            double[] xForces = new double[Bodies.length];
            double[] yForces = new double[Bodies.length];

            /** calculate the net x and y forces for each body. Store these in the xForces and yForces array respectively. */
            for (int j = 0; j<Bodies.length; j++){
                xForces[j] = Bodies[j].calcNetForceExertedByX(Bodies);
                yForces[j] = Bodies[j].calcNetForceExertedByY(Bodies);
            }

            /** update on each of the bodies' position, belocity and acceleration. */
            for (int n = 0; n<Bodies.length; n++){
                Bodies[n].update(dt, xForces[n], yForces[n]);
            }

            /** draw the background image. */
            StdDraw.picture(0,0, "C:/Users/emily/cs61b/cs61b_berkeley/project0/images/starfield.jpg");

            /** draw all of the bodies. */
            for(Body planet: Bodies){
                planet.draw();
            }

            /** show the offscreen buffer. */
            StdDraw.show();

            /** pause the animation for 10 milliseconds. */
            StdDraw.pause(10);

            /** increase the time variable by dt each loop. */
        }
        
        /** print out the final state of the universe. */
        StdOut.printf("%d\n", Bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < Bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            Bodies[i].xxPos, Bodies[i].yyPos, Bodies[i].xxVel,
                            Bodies[i].yyVel, Bodies[i].mass, Bodies[i].imgFileName);   
        }
    }
}