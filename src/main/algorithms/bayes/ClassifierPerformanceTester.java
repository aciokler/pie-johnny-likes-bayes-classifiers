package main.algorithms.bayes;

import java.util.List;
import java.util.Random;

public abstract class ClassifierPerformanceTester<CLASS, ATTR extends ElementAttribute, INPUT extends Element<CLASS, ATTR>> {

	public Double test(BayesClassifier<ATTR, CLASS, INPUT> classifier, List<INPUT> trainingSet,
			double testingSetPercentage) {

		long testingSetCount = Math.round(testingSetPercentage * (double) trainingSet.size());
		System.out.println("testing set count: " + testingSetCount);
		Random random = new Random(trainingSet.size());

		List<INPUT> availableSet = listProducerCopy(trainingSet);
		List<INPUT> testingSet = listProducer();
		do {
			int index = random.nextInt(availableSet.size());
			// System.out.println("index: " + index);
			testingSet.add(availableSet.remove(index));
		} while (testingSet.size() < testingSetCount);

		return evaluateClassifier(classifier, testingSet);
	}

	private Double evaluateClassifier(final BayesClassifier<ATTR, CLASS, INPUT> classifier, List<INPUT> testingSet) {
		long successCount = testingSet.parallelStream()
				.filter(test -> test.getClassification().equals(classifier.classifyExample(test))).count();

		// System.out.println("sucessFullCount: " + successCount + ", total: " +
		// testingSet.size());

		return 1.0 - ((double) successCount / (double) testingSet.size());
	}

	protected abstract List<INPUT> listProducerCopy(List<INPUT> original);

	protected abstract List<INPUT> listProducer();
}