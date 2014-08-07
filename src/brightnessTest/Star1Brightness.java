/*
 * Made by Louis Lu in COSMOS for simulating light curves in research of Eclipsing Binaries
 * There is some very interesting geometry in these four classes
 *
 * This class is identical to Star2Brightness.java
 * They have the same purpose - store information for each star
 */

package brightnessTest;


public class Star1Brightness {
	//Some star information
	int slices = 0;
	double sliceLength=0;
	int polygonSides=0;
	double polygonSideLength = 0;
	double innerAngle = 0;
	double radius=0;
	int hemiSlices = 0;
	double angleStep=0;
	
	double mass=0;
	
	double[][] component;
	
	double sum=0;
	
	public Star1Brightness(){
	}
	
	public double calc(int numSlices, int polygons, double radiusIn, double temperature){
		
		slices = numSlices;//divides a sphere into slices. The more slices, the more accurate the calculation is and the slower it takes, the default is 1000
		polygonSides = polygons;//divides each slices' circular side to polygon the default is 1000
		radius = radiusIn;
		
		double anglePointer;
		
		int counterPoly=0;
		int counterSlice =0;
		
		double angleH=0;
		double angleV=0;
		
		int numFace=0;
		
		double magnitude = 0;
		double totalMagnitude = 0;
		
		angleStep = (360/((double)polygonSides));//how much angle to go by each step
		
		innerAngle = Math.PI-(2*Math.PI/polygonSides);//The inner angle of a polygon
		
		sliceLength = 2*radius/slices;//This is actually the width of the slice.........
		
		//Calculating total magnitude of all the faces using Stefan-Bolzmann law
		totalMagnitude = 4*(Math.PI)*(radius*radius)*(Math.pow(5.6704, -8))*(Math.pow(temperature,4));

		numFace = slices*polygonSides;
		//Find magnitude of the vector for each face
		magnitude = (double)totalMagnitude/numFace;
		
		//Make sure that there is a even number of slices so that the procedure can be easily duplicated because of symmetry
		if(slices%2==1){
			hemiSlices = (int)(slices/2)+1;
		}else{
			hemiSlices = slices/2;
		}
		
		slices = 2*hemiSlices;//Redefine number of slices
		
		/*
		 * Define an array that store the component of light coming towards us
		 * This is quite an important step since it can skip a lot of other complications...
		 *...like avoid using the inverse-squared law
		 *
		 *Btw, this array is expected to be quite big
		 */
		component = new double[slices][polygonSides/2+1];
		
		//Counting from the end to the middle
		for(counterSlice=0;counterSlice<hemiSlices;counterSlice++){
			counterPoly=0;
			for(anglePointer=0; anglePointer<=180; anglePointer=(double)(anglePointer+angleStep)){
				//Every angle step, find the polar angle(angleV) and azimuthal angle(angleH)
				angleH = (double)(Math.acos((double)(hemiSlices-1-counterSlice)/hemiSlices));
				angleV = Math.toRadians((Math.abs((double)(90-anglePointer))));
				//Takes the components of each vector
				component[counterSlice][counterPoly] = magnitude*(Math.sin(angleH))*(Math.cos(angleV));
				counterPoly++;
			}
		}
		//This is a duplicate of the previous for loop, just counting from the middle to the end
		for(counterSlice=hemiSlices;counterSlice<slices;counterSlice++){
			counterPoly=0;
			for(anglePointer=0; anglePointer<=180; anglePointer=(double)(anglePointer+angleStep)){
				angleH = (double)(Math.acos((double)(-hemiSlices+1+counterSlice)/hemiSlices));
				angleV = Math.toRadians((Math.abs((double)(90-anglePointer))));
				component[counterSlice][counterPoly] = magnitude*(Math.sin(angleH))*(Math.cos(angleV));
				counterPoly++;
			}
		}
		
		//sum up all the components
		for(int loopSlice=0;loopSlice<slices;loopSlice++){
			for(int loopSides=0;loopSides<(polygonSides/2+1);loopSides++){
				sum = sum+component[loopSlice][loopSides];
			}
		}
		return sum;
	}
}
