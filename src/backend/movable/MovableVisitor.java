package backend.movable;

public interface MovableVisitor {
	public void visit(IceCube iceCube);
	public void visit(Box box);
	public void visit(Player player);
}
