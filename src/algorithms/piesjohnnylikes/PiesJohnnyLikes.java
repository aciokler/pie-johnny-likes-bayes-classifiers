package algorithms.piesjohnnylikes;

import java.util.ArrayList;
import java.util.List;

import main.algorithms.bayes.BayesClassifier;
import main.algorithms.bayes.ClassifierPerformanceTester;

public class PiesJohnnyLikes {

	public static void main(String[] args) {

		final int nrOfExamplesToGenerate = 1000;
		PieGenerator generator = new PieGenerator(nrOfExamplesToGenerate);

		runWithTrainingSet(generator.generateCuratedTrainingSet());

		runWithTrainingSet(generator.generateTrueRandomTrainingSet(0.5));

		runWithTrainingSet(generator.generateBiasedRandomTrainingSet(0.5));
	}

	private static void runWithTrainingSet(List<Pie> trainingSet) {
		BayesClassifier<PieAttribute, Boolean, Pie> classifier = new BayesClassifier<>(trainingSet);

		Tester tester = new Tester();
		Double errorRate = tester.test(classifier, trainingSet, 0.5);
		System.out.println("errorRate: " + errorRate);
		System.out.println("");
	}

	public static class Tester extends ClassifierPerformanceTester<Boolean, PieAttribute, Pie> {
		@Override
		protected List<Pie> listProducerCopy(List<Pie> original) {
			return new ArrayList<>(original);
		}

		@Override
		protected List<Pie> listProducer() {
			return new ArrayList<>();
		}
	}

}
