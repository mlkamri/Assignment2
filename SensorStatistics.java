import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SensorStatistics { // changed the name from SensorDataProcessor to SensorStatistics


    // Senson data and limits.
    public double[][][] sensorReadings; // changed the name from data to sensorReadings
    public double[][] sensorThresholds; // changed the name from limit to sensorThresholds

    // constructor
    public SensorStatistics(double[][][] sensorReadings, double[][] sensorThresholds) { // changed the name from DataProcessordouble to SensorDataProcessor
        this.sensorReadings = sensorReadings;
        this.sensorThresholds = sensorThresholds;
    }
    // calculates average of sensor data
    private double calculateAverage(double[] dataPoints) { //changed the name from average to calculateAverage && array to datapoints
        int index = 0; //changed from i to index 
        double total = 0; // changed from val to total
        for (index = 0; index < dataPoints.length; index++) {
        total += dataPoints[index];
        }
        return total / dataPoints.length;
    }
    // calculate data s
    public void analyzeSensorData(double normalizationFactor) { //changed name from calculate to analyzeSensorData && d to normalizationFactor
        int i, j, k;
        double[][][] normalizedReadings = new double[sensorReadings.length][sensorReadings[0].length][sensorReadings[0][0].length]; // changed data2 to normalizedReadings
        BufferedWriter writer = null;
    // Write racing stats data into a file
    try {
        writer = new BufferedWriter(new FileWriter("NormalizedSensorReadings.txt")); // changed out to writer &&  file name from RacingStatsData to 
        
        for (i = 0; i < sensorReadings.length; i++) {
            for (j = 0; j < sensorReadings[0].length; j++) {
                for (k = 0; k < sensorReadings[0][0].length; k++) {
                    normalizedReadings[i][j][k] = sensorReadings[i][j][k] / normalizationFactor - Math.pow(sensorThresholds[i][j], 2.0);
                    double averageReading = calculateAverage(normalizedReadings[i][j]);
                    if (averageReading > 10 && averageReading < 50)
                    break;
                else if (Math.max(sensorReadings[i][j][k], normalizedReadings[i][j][k]) > sensorReadings[i][j][k])
                    break;
                else if (Math.pow(Math.abs(sensorReadings[i][j][k]), 3) < Math.pow(Math.abs(normalizedReadings[i][j][k]), 3) && averageReading < normalizedReadings[i][j][k] && (i + 1) * (j + 1) > 0)
                    normalizedReadings[i][j][k] *= 2;
            }
        }
    }

    for (i = 0; i < normalizedReadings.length; i++) {
        for (j = 0; j < normalizedReadings[i].length; j++) {
            for (k = 0; k < normalizedReadings[i][j].length; k++) {
                writer.write(normalizedReadings[i][j][k] + "\t");
            }
            writer.newLine();
        }
    }
} catch (IOException e) {
    System.out.println("Error writing to file: " + e.getMessage());
} finally {
    if (writer != null) {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing the writer: " + e.getMessage());
        }
    }
}





try {
                
                

    // Write the normalized readings to the file
    for (double[][] normalizedLayer : normalizedReadings) {
        for (double[] normalizedRow : normalizedLayer) {
            for (double reading : normalizedRow) {
                writer.write(reading + "\t");
            }
            writer.newLine();
        }
    }
} catch (IOException e) {
    System.out.println("Error writing to file: " + e.getMessage());
} finally {
    if (writer != null) {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing the writer: " + e.getMessage());
        }
    }
}
}
}