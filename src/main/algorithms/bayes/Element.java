package main.algorithms.bayes;

import java.util.List;

public interface Element<CLASS, ATTR extends ElementAttribute> {

	public boolean hasAttribute(ATTR attr);

	public List<ATTR> getAttributes();

	public CLASS getClassification();

}
