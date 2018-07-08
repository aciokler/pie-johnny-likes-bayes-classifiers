package main.algorithms.bayes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BayesClassifier<ATTR extends ElementAttribute, CLASS, INPUT extends Element<CLASS, ATTR>> {

	private Map<CLASS, Map<ATTR, Integer>> attributeCountPerClassMap = new HashMap<>();
	private Map<CLASS, Integer> countPerClassificationMap = new HashMap<>();
	private Integer trainingSetSize = null;

	public BayesClassifier(List<INPUT> trainingSet) {
		trainingSetSize = trainingSet.size();
		for (INPUT trainingExample : trainingSet) {
			Map<ATTR, Integer> attrCountMap = null;
			if ((attrCountMap = attributeCountPerClassMap.get(trainingExample.getClassification())) == null) {
				attrCountMap = new HashMap<>();
				attributeCountPerClassMap.put(trainingExample.getClassification(), attrCountMap);
			}

			for (ATTR attr : trainingExample.getAttributes()) {
				Integer count = null;
				if ((count = attrCountMap.get(attr)) == null) {
					count = 1;
				}
				attrCountMap.put(attr, ++count);
			}

			registerClassOccurance(trainingExample);
		}

		System.out.println(attributeCountPerClassMap);
	}

	private void registerClassOccurance(INPUT item) {
		Integer count = countPerClassificationMap.get(item.getClassification());
		if (count == null) {
			count = 1;
		} else {
			count++;
		}
		countPerClassificationMap.put(item.getClassification(), count);
	}

	public CLASS classifyExample(INPUT example) {
		CLASS classification = null;
		Double maxProbablitySoFar = 0.0;
		for (Map.Entry<CLASS, Map<ATTR, Integer>> classEntry : attributeCountPerClassMap.entrySet()) {
			Integer classCount = countPerClassificationMap.get(classEntry.getKey());

			Double probablityOfAttributesForClass = 1.0;

			// probabilities of attribute vector in a class
			for (ATTR attr : example.getAttributes()) {
				Integer attrCount = null;
				if ((attrCount = classEntry.getValue().get(attr)) == null) {
					attrCount = 0;
				}
				double attrProb = ((double) attrCount / (double) classCount);
				probablityOfAttributesForClass *= attrProb;
			}

			// probabilities of class in the training set
			double classProb = (double) classCount / (double) trainingSetSize;

			probablityOfAttributesForClass *= classProb;

			// change classification if probability is higher for this class
			if (probablityOfAttributesForClass > maxProbablitySoFar) {
				classification = classEntry.getKey();
				maxProbablitySoFar = probablityOfAttributesForClass;
			}
		}

		// System.out.println("classification: " + classification + ", items: "
		// + example.getClassification());
		return classification;
	}
}
