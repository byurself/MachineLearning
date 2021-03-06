package com.lpc.linearregression;

import java.util.Random;

/**
 * @author byu_rself
 * @date 2022/5/15 19:41
 */
public class LinearRegression {

    public static void main(String[] args) {
        // y=3*x1+4*x2+5*x3+10
        Random random = new Random();
        double[] results = new double[100];
        double[][] features = new double[100][3];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < features[i].length; j++) {
                features[i][j] = random.nextDouble();
            }
            results[i] = 3 * features[i][0] + 4 * features[i][1] + 5 * features[i][2] + 10;
        }
        double[] parameters = new double[]{1.0, 1.0, 1.0, 1.0};
        double learningRate = 0.01;
        for (int i = 0; i < 3000; i++) {
            SGD(features, results, learningRate, parameters);
        }
        parameters = new double[]{1.0, 1.0, 1.0, 1.0};
        System.out.println("==========================");
        for (int i = 0; i < 3000; i++) {
            BGD(features, results, learningRate, parameters);
        }
    }

    /**
     * 随机梯度下降法
     *
     * @param features
     * @param results
     * @param learningRate
     * @param parameters
     */
    private static void SGD(double[][] features, double[] results, double learningRate, double[] parameters) {
        for (int j = 0; j < results.length; j++) {
            double gradient = (parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]) * features[j][0];
            parameters[0] = parameters[0] - 2 * learningRate * gradient;

            gradient = (parameters[0] * features[j][0] + parameters[1] * features[j][1] + parameters[2] * features[j][2]
                    + parameters[3] - results[j]) * features[j][1];
            parameters[1] = parameters[1] - 2 * learningRate * gradient;

            gradient = (parameters[0] * features[j][0] + parameters[1] * features[j][1] + parameters[2] * features[j][2]
                    + parameters[3] - results[j]) * features[j][2];
            parameters[2] = parameters[2] - 2 * learningRate * gradient;

            gradient = (parameters[0] * features[j][0] + parameters[1] * features[j][1] + parameters[2] * features[j][2]
                    + parameters[3] - results[j]);
            parameters[3] = parameters[3] - 2 * learningRate * gradient;
        }

        double totalLoss = 0;
        for (int j = 0; j < results.length; j++) {
            totalLoss = totalLoss + Math.pow((parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]), 2);
        }
        System.out.println(parameters[0] + " " + parameters[1] + " " + parameters[2] + " " + parameters[3]);
        System.out.println("[SGD]totalLoss:" + totalLoss);
    }

    /**
     * 批量梯度下降法
     *
     * @param features
     * @param results
     * @param learningRate
     * @param parameters
     */
    private static void BGD(double[][] features, double[] results, double learningRate, double[] parameters) {
        double sum = 0;
        for (int j = 0; j < results.length; j++) {
            sum = sum + (parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]) * features[j][0];
        }
        double updateValue = 2 * learningRate * sum / results.length;
        parameters[0] = parameters[0] - updateValue;

        sum = 0;
        for (int j = 0; j < results.length; j++) {
            sum = sum + (parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]) * features[j][1];
        }
        updateValue = 2 * learningRate * sum / results.length;
        parameters[1] = parameters[1] - updateValue;

        sum = 0;
        for (int j = 0; j < results.length; j++) {
            sum = sum + (parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]) * features[j][2];
        }
        updateValue = 2 * learningRate * sum / results.length;
        parameters[2] = parameters[2] - updateValue;

        sum = 0;
        for (int j = 0; j < results.length; j++) {
            sum = sum + (parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]);
        }
        updateValue = 2 * learningRate * sum / results.length;
        parameters[3] = parameters[3] - updateValue;

        double totalLoss = 0;
        for (int j = 0; j < results.length; j++) {
            totalLoss = totalLoss + Math.pow((parameters[0] * features[j][0] + parameters[1] * features[j][1]
                    + parameters[2] * features[j][2] + parameters[3] - results[j]), 2);
        }
        System.out.println(parameters[0] + " " + parameters[1] + " " + parameters[2] + " " + parameters[3]);
        System.out.println("[BGD]totalLoss:" + totalLoss);
    }
}
