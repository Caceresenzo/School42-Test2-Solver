package caceresenzo.app.solver.school42.test2.function;

import java.util.ArrayList;
import java.util.List;

import caceresenzo.app.solver.school42.test2.commands.CommandContainer;

public class Function {
	
	private String identifier;
	private int size;
	private List<CommandContainer> commands;
	
	public Function(String identifier, int size) {
		this.identifier = identifier;
		this.size = size;
		
		reset();
	}
	
	public void addCommand(CommandContainer command) {
		commands.add(command);
		
		if (commands.size() > size) {
			throw new IndexOutOfBoundsException("Can't add more command than function capacity.");
		}
	}
	
	public List<CommandContainer> commands() {
		return commands;
	}
	
	public void reset() {
		this.commands = new ArrayList<>(size);
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public int getSize() {
		return size;
	}
	
}