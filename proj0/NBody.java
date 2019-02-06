public class NBody {
    public static String bgImage = "/images/starfield.jpg";
    public static double readRadius(String path){
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String path){
        In in = new In(path);
        int numOfBodies = in.readInt();
        Body[] bodies = new Body[numOfBodies];
        in.readDouble();
        for(int i = 0;i < numOfBodies;i++){
            bodies[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        in.close();
        return bodies;
    }

    public static void main(String[] args){
        double T = Double.valueOf(args[0]), dt = Double.valueOf(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename),time = 0;
        int size = bodies.length;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,bgImage);

        while (time < T){
            double[] xForces = new double[size],yForces = new double[size];

            for(int i =0;i < size;i++){

                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i =0;i < size;i++){
                bodies[i].draw();
                bodies[i].update(dt,xForces[i],yForces[i]);
            }
            time += dt;
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
    /*TODO: draw the universal (background and bodies)
        Then add the audio.
     */


}
