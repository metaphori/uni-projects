java -classpath weka.jar:./ ClassificationCrossValidation -t ../car-eval/car.arff -s 1 -x 10 -W "weka.classifiers.functions.MultilayerPerceptron"
