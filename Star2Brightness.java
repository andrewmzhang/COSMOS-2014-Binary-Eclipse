/*
 * Made by Louis Lu in COSMOS for simulating light curves in research of Eclipsing Binaries
 * There is some very interesting geometry in these four classes
 *
 * This class is identical to Star1Brightness.java
 * They have the same purpose - store information for each star
 * 
 * If you are looking for comments, go to Star1Brightness.java
 */
package brightnessTest;

public class Star2Brightness {
	
	int slices = 0;
	double sliceLength=0;
	int polygonSides=0;
	double polygonSideLength=0;
	double innerAngle = 0;
	double radius=0;
	int hemiSlices = 0;
	double angleStep=0;
	double sum=0;
	
	double mass=0;
	
	double[][] component;
	
	public Star2Brightness(){
	}
	
	public double calc(int numSlices, int polygons, double radiusIn, double temperature){
		slices = numSlices;
		polygonSides = polygons;
		radius = radiusIn;
		
		double anglePointer;
		
		
		
		int counterPoly=0;
		int counterSlice =0;
		
		double angleH=0;
		double angleV=0;
		
		int numFace=0;
		
		double magnitude = 0;
		double totalMagnitude = 0;
		
		angleStep = (360/((double)polygonSides));
		
		innerAngle = Math.PI-(2*Math.PI/polygonSides);
		
		sliceLength = 2*radius/slices;
		
		//Calculating total magnitude of all the faces using Stefan-Bolzmann law
		totalMagnitude = 4*(Math.PI)*(radius*radius)*(Math.pow(5.6704, -8))*(Math.pow(temperature,4));

		numFace = slices*polygonSides;
		//Find magnitude of the vector for each face
		magnitude = (double)totalMagnitude/numFace;
		
		//System.out.println("Calculating...  ");
		
		if(slices%2==1){
			hemiSlices = (int)(slices/2)+1;
		}else{
			hemiSlices = slices/2;
		}
		
		slices = 2*hemiSlices;
		
		component = new double[slices][polygonSides/2+1];
		
		for(counterSlice=0;counterSlice<hemiSlices;counterSlice++){
			counterPoly=0;
			for(anglePointer=0; anglePointer<=180; anglePointer=(double)(anglePointer+angleStep)){
				angleH = (double)(Math.acos((double)(hemiSlices-1-counterSlice)/hemiSlices));
				angleV = Math.toRadians((Math.abs((double)(90-anglePointer))));
				component[counterSlice][counterPoly] = magnitude*(Math.sin(angleH))*(Math.cos(angleV));
				counterPoly++;
			}
		}
		for(counterSlice=hemiSlices;counterSlice<slices;counterSlice++){
			counterPoly=0;
			for(anglePointer=0; anglePointer<=180; anglePointer=(double)(anglePointer+angleStep)){
				angleH = (double)(Math.acos((double)(-hemiSlices+1+counterSlice)/hemiSlices));
				angleV = Math.toRadians((Math.abs((double)(90-anglePointer))));
				component[counterSlice][counterPoly] = magnitude*(Math.sin(angleH))*(Math.cos(angleV));
				counterPoly++;
			}
		}
		
		for(int loopSlice=0;loopSlice<slices;loopSlice++){
			for(int loopSides=0;loopSides<(polygonSides/2+1);loopSides++){
				sum = sum+component[loopSlice][loopSides];
			}
		}
		return sum;
	}
}