public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    private double G = 6.67e-11;
    public  Body(double xP,double yP,double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public boolean equals(Body obj) {
        if(xxPos == obj.xxPos && yyPos == obj.yyPos && mass == obj.mass && xxVel == obj.xxVel && yyVel == obj.yyVel)
            return true;
        return false;
    }
    private double disHelper(Body b){
        return (b.xxPos - xxPos)*(b.xxPos - xxPos) + (b.yyPos - yyPos)*(b.yyPos - yyPos);
    }
    public double calcDistance(Body b){
        return Math.sqrt(disHelper(b));
    }

    public double calcForceExertedBy(Body b){
        return G*mass*b.mass / disHelper(b);
    }

    public double calcForceExertedByX(Body b){
        return calcForceExertedBy(b)*(b.xxPos - xxPos) / calcDistance(b);
    }
    public double calcForceExertedByY(Body b){
        return calcForceExertedBy(b)*(b.yyPos - yyPos) / calcDistance(b);
    }    

    public double calcNetForceExertedByX(Body[] bodies){
        double xNetForce = 0;
        for(Body b: bodies){
            if(!this.equals(b))
                xNetForce += calcForceExertedByX(b);
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double yNetForce = 0;
        for(Body b: bodies){
            if(!this.equals(b))
                yNetForce += calcForceExertedByY(b);
        }
        return yNetForce;
    }

    public void update(double timePeriod, double xForce,double yForce){
        xxVel += xForce * timePeriod / mass;
        yyVel += yForce * timePeriod / mass;
        xxPos += xxVel * timePeriod;
        yyPos += yyVel * timePeriod;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"/images/"+imgFileName);
    }
}