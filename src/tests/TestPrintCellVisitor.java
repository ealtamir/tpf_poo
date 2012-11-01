package tests;

import backend.cell.*;
import backend.movable.*;
import java.awt.Point;

public class TestPrintCellVisitor implements CellVisitor {
	
	private class TestPrintMovableVisitor implements MovableVisitor {
		@Override
		public void visit(Box box) {
			System.out.print("B ");
		}
		
		@Override
		public void visit(IceCube iceCube) {
			System.out.print("C ");
		}
		
		@Override
		public void visit(Player player) {
			System.out.print("@ ");
			
		}
	}
	
	private Point lastCellPosition = new Point(0, 0);
	private TestPrintMovableVisitor testPrintMovableVisitor = new TestPrintMovableVisitor();
	
	private void printCell(Cell c, String ident) {
		Movable movable = c.getMovable();
		Point cellPosition = c.getPosition();
		
		if (lastCellPosition.y != cellPosition.y) {
			System.out.println();
		}
		
		if (movable == null) {
			System.out.print(ident + " ");
		}
		else {
			movable.accept(testPrintMovableVisitor);
		}
		

		lastCellPosition = cellPosition;
		
		
	}
	
	@Override
	public void visit(Destination d) {
		printCell(d, (d.isVisible() ? "G" : "g"));
	}
	
	@Override
	public void visit(Floor f) {
		printCell(f, " ");
	}
	
	@Override
	public void visit(ShallowWater sw) {
		printCell(sw, "S");
	}
	
	@Override
	public void visit(Switch s) {
		printCell(s, "K");
	}
	
	@Override
	public void visit(Tree t) {
		printCell(t, "T");
	}
	
	@Override
	public void visit(Water w) {
		printCell(w, "#");
	}
	
}
