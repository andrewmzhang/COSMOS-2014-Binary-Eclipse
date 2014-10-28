/*
 * Made by Louis Lu in COSMOS for simulating light curves in research of Eclipsing Binaries
 * There is some very interesting geometry in these four classes
 *
 * This section has been revised and debugged by Andrew Zhang. Math has been corrected, UI improved, and bugs fixed!
 */

package brightnessTest;


public class Star1Brightness {
    //Some star information
    int slices = 0;
    double sliceLength = 0;
    int polygonSides = 0;
    double polygonSideLength = 0;
    double innerAngle = 0;
    double radius = 0;
    int hemiSlices = 0;
    double angleStep = 0;
    double sum = 0;
    double mass = 0;
    double totalMagnitude;

    double[][] component;

    public Star1Brightness() {
    }

    public void arrayCalc() {
        double anglePointer;

        int counterPoly = 0;
        int counterSlice = 0;

        double angleH = 0;
        double angleV = 0;

        int numFace = 0;

        double magnitude = 0;

        component = new double[slices][polygonSides / 2 + 1];
        numFace = slices * polygonSides;
        //Find magnitude of the vector for each face
        magnitude = (double) totalMagnitude / numFace;

        for (counterSlice = 0; counterSlice < hemiSlices; counterSlice++) {
            counterPoly = 0;
            for (anglePointer = 0; anglePointer <= 180; anglePointer = (double) (anglePointer + angleStep)) {
                //Every angle step, find the polar angle(angleV) and azimuthal angle(angleH)
                angleH = (double) (Math.acos((double) (hemiSlices - 1 - counterSlice) / hemiSlices));
                angleV = Math.toRadians((Math.abs((double) (90 - anglePointer))));
                //Takes the components of each vector
                component[counterSlice][counterPoly] = magnitude * (Math.sin(angleH)) * (Math.cos(angleV));
                counterPoly++;
            }
        }
        //This is a duplicate of the previous for loop, just counting from the middle to the end
        for (counterSlice = hemiSlices; counterSlice < slices; counterSlice++) {
            counterPoly = 0;
            for (anglePointer = 0; anglePointer <= 180; anglePointer = (double) (anglePointer + angleStep)) {
                angleH = (double) (Math.acos((double) (-hemiSlices + 1 + counterSlice) / hemiSlices));
                angleV = Math.toRadians((Math.abs((double) (90 - anglePointer))));
                component[counterSlice][counterPoly] = magnitude * (Math.sin(angleH)) * (Math.cos(angleV));
                counterPoly++;
            }
        }
    }

    //System.out.println(totalMagnitude/4);
    public double calc(int numSlices, int polygons, double radiusIn, double temperature) {

        // This is just some variable setting and making sure some numbers are even or odd, no worries!
        slices = numSlices;//divides a sphere into slices. The more slices, the more accurate the calculation is and the slower it takes, the default is 1000
        polygonSides = polygons;//divides each slices' circular side to polygon the default is 1000
        radius = radiusIn;
        angleStep = (360 / ((double) polygonSides));//how much angle to go by each step
        innerAngle = Math.PI - (2 * Math.PI / polygonSides);//The inner angle of a polygon
        sliceLength = 2 * radius / slices;//This is actually the width of the slice.........
        if (slices % 2 == 1) {
            hemiSlices = (int) (slices / 2) + 1;
        } else {
            hemiSlices = slices / 2;
        }
        slices = 2 * hemiSlices;//Redefine number of slices
        // Here is the calculation, it is explained below!
        totalMagnitude = 4 * (Math.PI) * (radius * radius) * (Math.pow(5.6704, -8)) * (Math.pow(temperature, 4));
        sum = totalMagnitude / 4;
        return sum;
    }
        /*
		 * Above:
		 * 
		 * 		The totalMagnitude (or StefanBolsmans Law) divided by 4 returns visible magnitude, this allows us to avoid inverse square law AND math involving
		 * arc-cosines, azimuthal angles, polar angles, vectors, etc etc
		 * 
		 * PS Andy is very proud of figuring out that large for loops and complex math could be condensed by dividing totalMagnitude by 4 :D 
		 * 
		 * Louis's code is still used to calculate the matrix values for the strength of each slice-face, its just not used to find the sum value, 
		 * as that can be done much much easier 
		 * 
		 */
}

