package backend.cell;

public interface CellVisitor {
	
	public void visit(Floor f);
	public void visit(ShallowWater sw);
	public void visit(Switch s);
	public void visit(Tree t);
	public void visit(Water w);
	public void visit(Destination d);

}
