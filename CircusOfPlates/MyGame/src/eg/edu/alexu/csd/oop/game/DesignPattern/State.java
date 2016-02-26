package eg.edu.alexu.csd.oop.game.DesignPattern;

import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public interface State {

	public void doAction(String action);
	public String getAction();
	public String toString();
	public GameController getController();
}
