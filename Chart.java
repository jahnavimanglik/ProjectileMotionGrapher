import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
public class Chart extends Application {
    double velocity, angle,g = 9.8,time=0,t=0,vx=0,vy=0,x=0,y=0,VY=0,VX=0,h=0,r=0;
    double Y[] = new double[100];
    double X[] = new double[100];
    @Override public void start(Stage stage) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 1 to throw ball from ground");
        System.out.println("Enter 2 to throw ball from cliff");
        int n = in.nextInt();
        int q=0;
        switch(n){
            case 1:
        q=0;
        break;
            case 2:
        q=-500;
        break;
            default:
                System.exit(0);
        }
        System.out.println("Enter Velocity");
        velocity = in.nextDouble();
        System.out.println("Enter Angle");
        double an = in.nextDouble();
        angle = Math.toRadians(an);
        vx = velocity* Math.cos(angle);
        vy = velocity*Math.sin(angle);
        t = (2*vy)/g; 
        h = (velocity*velocity*Math.sin(angle)*Math.sin(angle))/2*g;
        r= (velocity*velocity*Math.sin(2*angle))/g;
        int c=0;
        while(time<=t){
            VY = vy - g*time;
            VX = vx;
            x = VX*time;
            X[c] = x;
            y = (VY*time)- (0.5*time*time);
            Y[c] = y;
            c++;
            time++;
        }
        stage.setTitle("Projectile Motion");
        final NumberAxis xAxis = new NumberAxis(0,1000,2);
        final NumberAxis yAxis = new NumberAxis(q,1000,2);
        xAxis.setLabel("Range");
        yAxis.setLabel("Height");
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);
        lineChart.setTitle("Projectile Path");
        XYChart.Series series = new XYChart.Series();
        series.setName("\t\t\t\t\t\t\t\t\t\tVelocity: "+velocity+"m/s, Angle: "+an+"º\n"+"Time of flight: "+t+"s, Maximum height: "+h+"m, Range: "+r+" m");
        for(int i=0;i<c;i++){
        series.getData().add(new XYChart.Data(X[i],Y[i]));
        }
        Scene scene = new Scene(lineChart,8000,6000);
        lineChart.getData().add(series);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }   
}
