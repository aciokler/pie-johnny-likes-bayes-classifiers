package algorithms.piesjohnnylikes;

import java.util.Arrays;
import java.util.List;

import main.algorithms.bayes.Element;

public class Pie implements Element<Boolean, PieAttribute> {

	private Shapes shape;
	private CrustSizes crustSize;
	private CrustShades crustShade;
	private FillingSizes fillingSize;
	private FillingShades fillingShade;

	private Boolean pieJohnnyLikes = false;

	private List<PieAttribute> internalAttributeList;

	public Pie(Shapes shape, CrustSizes crustSize, CrustShades crustShade, FillingSizes fillingSize,
			FillingShades fillingShade, boolean pieJohnnyLikes) {
		this.shape = shape;
		this.crustSize = crustSize;
		this.crustShade = crustShade;
		this.fillingSize = fillingSize;
		this.fillingShade = fillingShade;
		this.pieJohnnyLikes = pieJohnnyLikes;
	}

	public Pie getCopy() {
		return new Pie(this.getShape(), this.getCrustSize(), this.getCrustShade(), this.getFillingSize(),
				this.getFillingShade(), this.getClassification());
	}

	public Shapes getShape() {
		return shape;
	}

	public void setShape(Shapes shape) {
		this.shape = shape;
	}

	public CrustSizes getCrustSize() {
		return crustSize;
	}

	public void setCrustSize(CrustSizes crustSize) {
		this.crustSize = crustSize;
	}

	public CrustShades getCrustShade() {
		return crustShade;
	}

	public void setCrustShade(CrustShades crustShade) {
		this.crustShade = crustShade;
	}

	public FillingSizes getFillingSize() {
		return fillingSize;
	}

	public void setFillingSize(FillingSizes fillingSize) {
		this.fillingSize = fillingSize;
	}

	public FillingShades getFillingShade() {
		return fillingShade;
	}

	public void setFillingShade(FillingShades fillingShade) {
		this.fillingShade = fillingShade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (obj instanceof Pie) {
			Pie pie = (Pie) obj;
			return this.getCrustShade() == pie.getCrustShade() && this.getCrustSize() == pie.getCrustSize()
					&& this.getShape() == pie.getShape() && this.getFillingShade() == pie.getFillingShade()
					&& this.getFillingSize() == pie.getFillingSize();
		}

		return false;
	}

	@Override
	public int hashCode() {
		return this.getCrustShade().hashCode() + this.getCrustSize().hashCode() + this.getFillingShade().hashCode()
				+ this.getFillingSize().hashCode() + this.getShape().hashCode();
	}

	@Override
	public boolean hasAttribute(PieAttribute attr) {
		return getAttributes().contains(attr);
	}

	@Override
	public List<PieAttribute> getAttributes() {
		if (internalAttributeList == null) {
			internalAttributeList = Arrays.asList(this.getShape(), this.getCrustShade(), this.getCrustSize(),
					this.getFillingShade(), this.getFillingSize());
		}
		return internalAttributeList;
	}

	@Override
	public Boolean getClassification() {
		return this.pieJohnnyLikes;
	}

	@Override
	public String toString() {
		return this.getAttributes().toString();
	}

	public enum Shapes implements PieAttribute {
		CIRCLE {
			@Override
			public String toString() {
				return "Shape == Circle";
			}
		},
		TRIANGLE {
			@Override
			public String toString() {
				return "Shape == Triangle";
			}
		},
		SQUARE {
			@Override
			public String toString() {
				return "Shape == Square";
			}
		};
	}

	public enum CrustSizes implements PieAttribute {
		Thick {
			@Override
			public String toString() {
				return "Crust.Size == Thick";
			}
		},
		Thin {
			@Override
			public String toString() {
				return "Crust.Size == Thin";
			}
		};
	}

	public enum CrustShades implements PieAttribute {
		Gray {
			@Override
			public String toString() {
				return "Crust.Shade == Gray";
			}
		},
		White {
			@Override
			public String toString() {
				return "Crust.Shade == White";
			}
		},
		Dark {
			@Override
			public String toString() {
				return "Crust.Shade == Dark";
			}
		};
	}

	public enum FillingSizes implements PieAttribute {
		Thick {
			@Override
			public String toString() {
				return "Filling.Size == Thick";
			}
		},
		Thin {
			@Override
			public String toString() {
				return "Filling.Size == Thin";
			}
		};
	}

	public enum FillingShades implements PieAttribute {
		Gray {
			@Override
			public String toString() {
				return "Filling.Shade == Gray";
			}
		},
		White {
			@Override
			public String toString() {
				return "Filling.Shade == White";
			}
		},
		Dark {
			@Override
			public String toString() {
				return "Filling.Shade == Dark";
			}
		};
	}

}
