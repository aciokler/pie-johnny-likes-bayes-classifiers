package test.algorithms.piesjohnnylikes;

import org.junit.Assert;
import org.junit.Test;

import algorithms.piesjohnnylikes.Pie;
import algorithms.piesjohnnylikes.Pie.CrustShades;
import algorithms.piesjohnnylikes.Pie.CrustSizes;
import algorithms.piesjohnnylikes.Pie.FillingShades;
import algorithms.piesjohnnylikes.Pie.FillingSizes;
import algorithms.piesjohnnylikes.Pie.Shapes;

public class PieSmallTest {

	@Test
	public void equals_compareSamePies_bothAreEqual() {
		Pie pie = new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				true);
		Pie pie2 = new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				true);
		Assert.assertTrue(pie.equals(pie2));
	}

	@Test
	public void equals_compareCircleWithTriangle_notEqual() {
		Pie pie = new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				true);
		Pie pie2 = new Pie(Shapes.TRIANGLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				true);
		Assert.assertFalse(pie.equals(pie2));
	}

	@Test
	public void equals_compareWithDifferentClassification_bothAreEqual() {
		Pie pie = new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				true);
		Pie pie2 = new Pie(Shapes.CIRCLE, CrustSizes.Thick, CrustShades.Gray, FillingSizes.Thick, FillingShades.Dark,
				false);
		Assert.assertTrue(pie.equals(pie2));
	}
}
