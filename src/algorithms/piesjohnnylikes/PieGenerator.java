package algorithms.piesjohnnylikes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithms.piesjohnnylikes.Pie.CrustShades;
import algorithms.piesjohnnylikes.Pie.CrustSizes;
import algorithms.piesjohnnylikes.Pie.FillingShades;
import algorithms.piesjohnnylikes.Pie.FillingSizes;
import algorithms.piesjohnnylikes.Pie.Shapes;

public class PieGenerator {

	int numberOfExamples;
	long quitFactor;

	public PieGenerator(int numberOfExamples) {
		this.numberOfExamples = numberOfExamples;
		this.quitFactor = Math.round((double) numberOfExamples * 0.1);
	}

	public List<Pie> generateCuratedTrainingSet() {
		List<Pie> allPies = new ArrayList<>();
		List<Pie> piesJohnnyLike = new ArrayList<>();
		List<Pie> piesJohnnyDoesNotLike = new ArrayList<>();

		// Johnny likes
		piesJohnnyLike.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick,
				FillingShades.Dark, true));
		piesJohnnyLike.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick,
				FillingShades.Dark, true));
		piesJohnnyLike.add(new Pie(Shapes.TRIANGLE, CrustSizes.Thick, CrustShades.Dark, FillingSizes.Thick,
				FillingShades.Gray, true));
		piesJohnnyLike.add(new Pie(Shapes.CIRCLE, CrustSizes.Thin, CrustShades.White, FillingSizes.Thin,
				FillingShades.Dark, true));
		piesJohnnyLike.add(new Pie(Shapes.SQUARE, CrustSizes.Thick, CrustShades.Dark, FillingSizes.Thin,
				FillingShades.White, true));
		piesJohnnyLike.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thin,
				FillingShades.Dark, true));

		// Johnny doesn't like
		piesJohnnyDoesNotLike.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick,
				FillingShades.White, false));
		piesJohnnyDoesNotLike.add(new Pie(Shapes.SQUARE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick,
				FillingShades.Gray, false));
		piesJohnnyDoesNotLike.add(new Pie(Shapes.TRIANGLE, CrustSizes.Thin, CrustShades.Gray, FillingSizes.Thin,
				FillingShades.Dark, false));
		piesJohnnyDoesNotLike.add(new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Dark, FillingSizes.Thick,
				FillingShades.White, false));
		piesJohnnyDoesNotLike.add(new Pie(Shapes.SQUARE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick,
				FillingShades.Dark, false));
		piesJohnnyDoesNotLike.add(new Pie(Shapes.TRIANGLE, CrustSizes.Thick, CrustShades.White, FillingSizes.Thick,
				FillingShades.Gray, false));

		numberOfPiesJohnnyIsUnsureOf(piesJohnnyLike, piesJohnnyDoesNotLike);
		calculateBiasFactor(piesJohnnyLike, piesJohnnyDoesNotLike);

		allPies.addAll(piesJohnnyLike);
		allPies.addAll(piesJohnnyDoesNotLike);
		return allPies;
	}

	public List<Pie> generateTrueRandomTrainingSet(double negativePercent) {
		List<Pie> allPies = new ArrayList<>();
		List<Pie> piesJohnnyLike = new ArrayList<>();
		List<Pie> piesJohnnyDoesNotLike = new ArrayList<>();

		Random rand = new Random();

		long negativeExamples = Math.round((double) numberOfExamples * negativePercent);

		for (int i = 0; i < numberOfExamples - negativeExamples; i++) {
			piesJohnnyLike.add(makePie(true, rand));
		}

		for (int i = 0; i < negativeExamples; i++) {
			piesJohnnyDoesNotLike.add(makePie(false, rand));
		}

		numberOfPiesJohnnyIsUnsureOf(piesJohnnyLike, piesJohnnyDoesNotLike);
		calculateBiasFactor(piesJohnnyLike, piesJohnnyDoesNotLike);

		allPies.addAll(piesJohnnyLike);
		allPies.addAll(piesJohnnyDoesNotLike);
		return allPies;
	}

	public List<Pie> generateBiasedRandomTrainingSet(double negativePercent) {
		List<Pie> allPies = new ArrayList<>();
		List<Pie> piesJohnnyLike = new ArrayList<>();
		List<Pie> piesJohnnyDoesNotLike = new ArrayList<>();

		Random rand = new Random();

		long negativeExamples = Math.round((double) numberOfExamples * negativePercent);

		if (rand.nextBoolean()) {
			makePiesJohnnyLikesFirst(piesJohnnyLike, piesJohnnyDoesNotLike, numberOfExamples, negativeExamples, rand);
		} else {
			makePiesJohnnyDoesNotLikeFirst(piesJohnnyLike, piesJohnnyDoesNotLike, numberOfExamples, negativeExamples,
					rand);
		}

		numberOfPiesJohnnyIsUnsureOf(piesJohnnyLike, piesJohnnyDoesNotLike);
		calculateBiasFactor(piesJohnnyLike, piesJohnnyDoesNotLike);

		allPies.addAll(piesJohnnyLike);
		allPies.addAll(piesJohnnyDoesNotLike);

		return allPies;
	}

	public void makePiesJohnnyLikesFirst(List<Pie> piesJohnnyLike, List<Pie> piesJohnnyDoesNotLike,
			int numberOfExamples, long negativeExamples, Random rand) {

		// Johnny likes
		for (int i = 0; i < numberOfExamples - negativeExamples; i++) {
			piesJohnnyLike.add(makePie(true, rand));
		}

		// calculateBiasedFactorForPiesJohnnyLikes(piesJohnnyLike);

		for (int i = 0; i < negativeExamples; i++) {
			// Pie pie = makeAndComparePie(false, piesJohnnyLike, rand);
			// if (pie == null && !piesJohnnyDoesNotLike.isEmpty()) {
			// pie =
			// piesJohnnyDoesNotLike.get(rand.nextInt(piesJohnnyDoesNotLike.size())).getCopy();
			// }
			piesJohnnyDoesNotLike.add(makeAndComparePie(false, piesJohnnyLike, rand));
		}

		// calculateBiasedFactorForPiesJohnnyDoesNotLike(piesJohnnyDoesNotLike);
	}

	public void makePiesJohnnyDoesNotLikeFirst(List<Pie> piesJohnnyLike, List<Pie> piesJohnnyDoesNotLike,
			int numberOfExamples, long negativeExamples, Random rand) {

		for (int i = 0; i < negativeExamples; i++) {
			piesJohnnyDoesNotLike.add(makePie(false, rand));
		}

		// calculateBiasedFactorForPiesJohnnyDoesNotLike(piesJohnnyDoesNotLike);

		// Johnny likes
		for (int i = 0; i < numberOfExamples - negativeExamples; i++) {
			piesJohnnyLike.add(makeAndComparePie(true, piesJohnnyDoesNotLike, rand));
		}

		// calculateBiasedFactorForPiesJohnnyLikes(piesJohnnyLike);
	}

	public Pie makeAndComparePie(boolean johnnyLikes, List<Pie> referenceComparisonList, Random rand) {
		int quitCount = 0;

		Pie pie = null;
		do {
			quitCount++;
			pie = makePie(johnnyLikes, rand);
		} while (referenceComparisonList.contains(pie) && quitCount < quitFactor);

		//
		pie = referenceComparisonList.get(rand.nextInt(referenceComparisonList.size())).getCopy();
		return pie;
	}

	public Pie makePie(boolean johnnyLikes, Random rand) {
		return new Pie(Shapes.values()[rand.nextInt(Shapes.values().length)],
				CrustSizes.values()[rand.nextInt(CrustSizes.values().length)],
				CrustShades.values()[rand.nextInt(CrustShades.values().length)],
				FillingSizes.values()[rand.nextInt(FillingSizes.values().length)],
				FillingShades.values()[rand.nextInt(FillingShades.values().length)], johnnyLikes);
	}

	public long howManyRepeatedItems(List<Pie> pies) {
		List<Pie> copyOfPies = new ArrayList<>(pies);
		List<Pie> repeatedPies = new ArrayList<>();
		pies.stream().forEach(pie -> {
			copyOfPies.remove(pie);
			if (copyOfPies.contains(pie) && !repeatedPies.contains(pie)) {
				repeatedPies.add(pie);
			}
		});
		return repeatedPies.size();
	}

	private void numberOfPiesJohnnyIsUnsureOf(List<Pie> piesJohnnyLike, List<Pie> piesJohnnyDoesNotLike) {
		System.out.println("Number of pies johnny is unsure of: "
				+ piesJohnnyLike.parallelStream().filter(pie -> piesJohnnyDoesNotLike.contains(pie)).count());
	}

	// private void printBias(List<Pie> piesJohnnyLike, List<Pie>
	// piesJohnnyDoesNotLike) {
	// calculateBiasedFactorForPiesJohnnyLikes(piesJohnnyLike);
	// calculateBiasedFactorForPiesJohnnyDoesNotLike(piesJohnnyDoesNotLike);
	// }
	//
	// private void calculateBiasedFactorForPiesJohnnyLikes(List<Pie>
	// piesJohnnyLike) {
	// System.out.println("Biased factor towards pies johnny likes: " +
	// howManyRepeatedItems(piesJohnnyLike));
	// }
	//
	// private void calculateBiasedFactorForPiesJohnnyDoesNotLike(List<Pie>
	// piesJohnnyDoesNotLike) {
	// System.out.println(
	// "Biased factor towards pies johnny does not like: " +
	// howManyRepeatedItems(piesJohnnyDoesNotLike));
	// }

	private void calculateBiasFactor(List<Pie> piesJohnnyLike, List<Pie> piesJohnnyDoesNotLike) {
		System.out.println(
				"Bias factor: " + (howManyRepeatedItems(piesJohnnyLike) - howManyRepeatedItems(piesJohnnyDoesNotLike))
						+ " (negative factor means bias is towards pies johnny doesn't like)");
	}

}
