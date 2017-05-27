import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;

/**
 * Performs a single run of cross-validation.
 *
 * Command-line parameters:
 * <ul>
 *    <li>-t filename - the dataset to use</li>
 *    <li>-x int - the number of folds to use</li>
 *    <li>-s int - the seed for the random number generator</li>
 *    <li>-W classifier - classname and options, enclosed by double quotes; 
 *    the classifier to cross-validate</li>
 * </ul>
 *
 * Example command-line:
 * <pre>
 * java -classpath weka.jar:./ ClassificationCrossValidation -t ../car-eval/car.arff -s 1 -x 10 -W "weka.classifiers.functions.SMO <opt1> ... "
 * </pre>
 *
 */
public class ClassificationCrossValidation {

	public static void main(String[] args) throws Exception{
		  // loads data and set class index
	    Instances inputData = DataSource.read(Utils.getOption("t", args));
	    
	    // the last attribute is the class
	    inputData.setClassIndex(inputData.numAttributes() - 1);
	    
	    // classifier
	    String[] tmpOptions;
	    String classname;
	    tmpOptions     = Utils.splitOptions(Utils.getOption("W", args));
	    classname      = tmpOptions[0];
	    tmpOptions[0]  = "";
	    Classifier cls = (Classifier) Utils.forName(Classifier.class, classname, tmpOptions);
	    
	    // other options
	    int seed  = Integer.parseInt(Utils.getOption("s", args));
	    int folds = Integer.parseInt(Utils.getOption("x", args));
	    
	    // randomize data
	    Random rand = new Random(seed);
	    Instances randData = new Instances(inputData);
	    randData.randomize(rand);
	    if (randData.classAttribute().isNominal())
	      randData.stratify(folds);
	    /* In stratified k-fold cross-validation, the folds are selected so that the mean response value 
	       is approximately equal in all the folds. In the case of a dichotomous classification, 
	       this means that each fold contains roughly the same proportions of the two types of class labels. */

	    // perform cross-validation
	    Evaluation eval = new Evaluation(randData);
	    double correctClassified = 0;
	    double lastCorrectNum = 0;
	    double lastIncorrectNum = 0;
	    double incorrectClassified = 0;
	    double foldSize = Math.round((double)inputData.numInstances()/folds);
	    double correct_perc = 0;
	    System.out.println("Folds: "+folds + "\tNum instances: " + inputData.numInstances() + "\t Fold size: " + foldSize);
	    for (int n = 0; n < folds; n++) {
	      Instances train = randData.trainCV(folds, n);
	      Instances test = randData.testCV(folds, n);

	      // build and evaluate classifier
	      Classifier clsCopy = Classifier.makeCopy(cls);
	      clsCopy.buildClassifier(train);
	      eval.evaluateModel(clsCopy, test);
	      
	      // setting statistics for current fold
	      correctClassified = eval.correct() - lastCorrectNum;
	      incorrectClassified = eval.incorrect() - lastIncorrectNum;
	      correct_perc = correctClassified * 100 / (correctClassified+incorrectClassified);
	      lastCorrectNum = eval.correct();
	      lastIncorrectNum = eval.incorrect();
	      
	      System.out.println("Correct/incorrect classifications at fold" + n + ": " + correctClassified + " --- " + incorrectClassified + " ("+ correct_perc  + "%)");
	    }
	    
	    // printing summary information
	    System.out.println(eval.toSummaryString());
	    System.out.println(eval.toClassDetailsString());
	}
	
}
