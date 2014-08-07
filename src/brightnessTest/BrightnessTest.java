/*
 * Made by Louis Lu in COSMOS for simulating light curves in research of Eclipsing Binaries
 * There is some very interesting geometry in these four classes
 */
package brightnessTest;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BrightnessTest {
	
	static GUISetup go = new GUISetup();
	
	//This is the speed at which how fast the star is moving, due to time limit, I never got to figure out orbits; I assumed the orbit is perfectly circular
	static final double speed = 5;
	static double totalSum = 0;
	
	static double sum1;
	static double sum2;
	
	static Star1Brightness star1;
	static Star1Brightness star2;
	
	static double[] star1Heights;
	static double[] star2Heights;
	
	static double sumTotal=0;
	static int waitingTime=0;
	static int counter=0;
	
	static double totalHeight = 0;
	static double currentHeight = 0;
	

	//for eclipse
	static int startingSlice = 0;
	static int endingSlice = 0;
	static double startingPoint = 0;
	static double endingPoint=0;
	
	static int[] sliceAssignment1;
	
	public static void main(String[] args){
		//There isn't really anything in main method, much of the interactions are in GUISetup class
		//setting up GUI
		go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		go.setSize(1132,700);
		go.setIconImage(go.img.getImage());
		go.setVisible(true);
	}
	
	public static double eclipse(int sliceStart, int sliceEnd, int starCovered){
		/*
		 * This method figures out which faces are covered by the other star.
		 * The math behind this is quite interesting, it's quite hard to explain all these in a comment, but it's...
		 * ...definitely worth checking out.
		 */
		
		sumTotal = star1.sum+star2.sum;
		if(starCovered==1){
			for(int i=sliceStart;i>=sliceEnd;i--){
				for(int sideIndex=0;sideIndex<star1.polygonSides/2+1;sideIndex++){
					star1.polygonSideLength = 2*Math.PI*getHeightOfSlice2(sliceAssignment1[Math.abs(i-sliceStart)])/star1.polygonSides;
					currentHeight = star1.polygonSideLength*Math.sin(Math.abs(star1.innerAngle-sideIndex*Math.toRadians(star1.angleStep)));
					totalHeight = totalHeight + currentHeight; 
					
					if(totalHeight>(getHeightOfSlice1(i)-getHeightOfSlice2(sliceAssignment1[Math.abs(i-sliceStart)]))/2 || totalHeight<(getHeightOfSlice1(i)-getHeightOfSlice2(sliceAssignment1[Math.abs(i-sliceStart)]))/2+(getHeightOfSlice2(sliceAssignment1[Math.abs(i-sliceStart)]))){
						sumTotal = sumTotal-star1.component[i][sideIndex];
					}
				}
			}
		}
		if(starCovered==2){
			for(int i=sliceStart;i>=sliceEnd;i--){
				for(int sideIndex=0;sideIndex<star2.polygonSides/2+1;sideIndex++){
					star2.polygonSideLength = 2*Math.PI*getHeightOfSlice1(sliceAssignment1[Math.abs(i-sliceStart)])/star2.polygonSides;
					currentHeight = star2.polygonSideLength*Math.sin(Math.abs(star2.innerAngle-sideIndex*Math.toRadians(star2.angleStep)));
					totalHeight = totalHeight + currentHeight; 
					
					if(totalHeight>(getHeightOfSlice2(i)-getHeightOfSlice1(sliceAssignment1[Math.abs(i-sliceStart)]))/2 || totalHeight<(getHeightOfSlice2(i)-getHeightOfSlice1(sliceAssignment1[Math.abs(i-sliceStart)]))/2+(getHeightOfSlice1(sliceAssignment1[Math.abs(i-sliceStart)]))){
						sumTotal = sumTotal-star2.component[i][sideIndex];
						
					}
				}
			}
		}
		return sumTotal;
	}
	
	public static double getHeightOfSlice1(int sliceIndex){
		//Get the height of a certain slice from star 1's pseudo-sphere
		return 2*star1Heights[sliceIndex];
	}
	
	public static double getHeightOfSlice2(int sliceIndex){
		//Get the height of a certain slice from star 2's pseudo-sphere
		return 2*star2Heights[sliceIndex];
	}
	
	public static void setHeightOfSlice(){
		//Assign each slice a height of both stars or pseudo-spheres
		int xCoord;
		for(int sliceIndex1=0; sliceIndex1<star1.slices;sliceIndex1++){
			xCoord = Math.abs(star1.hemiSlices-sliceIndex1);
			if(xCoord==0){
				star1Heights[sliceIndex1]=star1.radius;
			}else{
				star1Heights[sliceIndex1] = Math.sqrt((star1.radius)*(star1.radius)-Math.pow((star1.radius-(star1.radius/xCoord)), 2));
			}
		}
		
		for(int sliceIndex2=0; sliceIndex2<star2.slices;sliceIndex2++){
			
			xCoord = Math.abs(star2.hemiSlices-sliceIndex2);
			if(xCoord==0){
				star2Heights[sliceIndex2]=star2.radius;
			}else{
				star2Heights[sliceIndex2] = Math.sqrt((star2.radius)*(star2.radius)-Math.pow((star2.radius-(star2.radius/xCoord)), 2));
			}
		}
	}
	
	public static int getEclipseTime(){
		/*
		 * What this method does is that it figures out the eclipse time or a quarter of the period in this case
		 */
		int eclipseCounter=0;
		endingPoint = 0;
		
		for(startingPoint=0; endingPoint<2*star1.radius; startingPoint=startingPoint+speed){
			if(startingPoint<=2*star2.radius){
				endingPoint = 0;
			}
			if(startingPoint>2*star2.radius){
				endingPoint = endingPoint+speed;
			}
			
			eclipseCounter++;
		}
		return eclipseCounter;
	}
	
	public static void generateGraph(){
		/*
		 * This is a somewhat complicated part
		 * In this particular case I am considering that the two orbits of those stars are perfectly circular...
		 * ...meaning that the eclipse time is exactly a quarter of the period. so its a lot easier to generate...
		 * ...the graph. Also I am assuming that the light source is very far away, so that some star distance...
		 * ...changes don't matter.
		 * 
		 * The basic math behind this is that I am trying to figure out which slice is being covered. If you...
		 * ...are interested in this, please check the code in this method.
		 */
		star1 = new Star1Brightness();
		star2 = new Star1Brightness();
		
		counter = 0;
		
		double counterPhase = 0;
		System.out.println("Starting Light Total Calculation");
		sum1=star1.calc(go.sliceNum, go.sliceNum, go.star1Radius, go.star1Temp);
		System.out.println("Star One Complete!");
		sum2=star2.calc(go.sliceNum, go.sliceNum, go.star2Radius, go.star2Temp);
		System.out.println("Star Two Complete!");
		
		star1Heights = new double[star1.slices];
		star2Heights = new double[star2.slices];
		
		XYSeries series = new XYSeries("XYGraph");
		
		System.out.println("This may take a while...");
		System.out.println("Calculating Heights");
		setHeightOfSlice();
		totalSum = star1.sum+star2.sum;
		
		System.out.println("Calculating Eclipse Time...");
		waitingTime = getEclipseTime();
		System.out.println("Calculating Plot Points...");
		for(int loop0=0;loop0<waitingTime/2;loop0++){
			counterPhase = (double)(counter/(double)(waitingTime*4-1));
			series.add(counterPhase,totalSum);
			go.percentageLabel.setText(Integer.toString((int)(100*counter/(waitingTime*4-1)))+"%");
			counter++;
		}
		
		endingPoint=0;
		for(startingPoint=0;endingPoint<2*star1.radius;startingPoint=startingPoint+speed){
			if(startingPoint<=2*star2.radius){
				endingPoint = 0;
			}
			if(startingPoint>2*star2.radius){
				endingPoint = endingPoint+speed;
			}
			
			startingSlice = (int)(startingPoint/star1.radius/2*star1.slices);
			endingSlice = (int)(endingPoint/star1.radius/2*star1.slices+0.5);
			if(startingPoint>=2*star1.radius){
				startingSlice=star1.slices-1;
			}
			sliceAssignment1 = new int[Math.abs(endingSlice-startingSlice)+1];
			for(int i=0;i<Math.abs(endingSlice-startingSlice)+1;i++){
				sliceAssignment1[i]=(int) (star2.slices-1-star2.slices*(i*star2.sliceLength)/star2.radius/2);
			}
			counterPhase = (double)(counter/(double)(waitingTime*4-1));
			series.add(counterPhase,eclipse(startingSlice,endingSlice,1));
			go.percentageLabel.setText(Integer.toString((int)(100*counter/(waitingTime*4-1)))+"%");
			counter++;

		}
		for(int loop1=0;loop1<waitingTime;loop1++){
			counterPhase = (double)(counter/(double)(waitingTime*4-1));
			series.add(counterPhase,sumTotal);
			go.percentageLabel.setText(Integer.toString((int)(100*counter/(waitingTime*4-1)))+"%");
			counter++;

		}
		endingPoint = 0;
		for(startingPoint=0;endingPoint<2*star2.radius;startingPoint=startingPoint+speed){
			if(startingPoint<=2*star1.radius){
				endingPoint = 0;
			}
			if(startingPoint>2*star1.radius){
				endingPoint = endingPoint+speed;
			}
			
			
			startingSlice = (int)(startingPoint/star2.radius/2*star2.slices);
			endingSlice = (int)(endingPoint/star2.radius/2*star2.slices+0.5);
			if(startingPoint>=2*star2.radius){
				startingSlice=star1.slices-1;
			}
			sliceAssignment1 = new int[Math.abs(endingSlice-startingSlice)+1];
			for(int i=0;i<Math.abs(endingSlice-startingSlice)+1;i++){
				sliceAssignment1[i]=(int) (star1.slices-1-star1.slices*(i*star1.sliceLength)/star1.radius/2);
			}
			counterPhase = (double)(counter/(double)(waitingTime*4-1));
			series.add(counterPhase,eclipse(startingSlice,endingSlice,2));
			go.percentageLabel.setText(Integer.toString((int)(100*counter/(waitingTime*4-1)))+"%");
			counter++;

		}
		for(int loop2=0;loop2<waitingTime/2;loop2++){
			counterPhase = (double)(counter/(double)(waitingTime*4-1));
			series.add(counterPhase,totalSum);
			go.percentageLabel.setText(Integer.toString((int)(100*counter/(waitingTime*4-1)))+"%");
			counter++;

		}
		go.percentageLabel.setText("Finalizing...");
		
		XYSeriesCollection dataset = new XYSeriesCollection(); //Initialize the graph
		dataset.addSeries(series); //Add dataset
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart(
		"Luminous Intensity vs. Phase", // Title
		"Phase", // x-axis Label
		"Luminous Intensity", // y-axis Label
		dataset, // Dataset
		PlotOrientation.VERTICAL, // Plot Orientation
		true, // Show Legend
		true, // Use tooltips
		false // Configure chart to generate URLs?
		);
		
		try {
			System.out.println("Creating Graph...");
			//Create the chart
			ChartUtilities.saveChartAsJPEG(new File(GUISetup.dir+"/"+GUISetup.filename), chart, go.imageWidth, go.imageHeight);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
			go.percentageLabel.setText("Failed to generate graph"); //opps a problem
			go.generateBtn.setEnabled(true);
			go.browseBtn.setEnabled(true);
			return;
		}
		go.loadImage(); //Reload the image
		go.generateBtn.setEnabled(true);
		go.browseBtn.setEnabled(true);
		go.percentageLabel.setText("Done!"); //done!
		System.out.println("Done!"); //done!
	}
}

