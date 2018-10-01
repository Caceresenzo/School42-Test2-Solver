package caceresenzo.app.solver.school42.test2;

import java.io.File;

import caceresenzo.app.solver.school42.test2.codec.implementations.SimpleWorldCodec;
import caceresenzo.app.solver.school42.test2.logic.LogicController;
import caceresenzo.libs.logger.Logger;
import caceresenzo.libs.parse.ParseUtils;

public class Bootstrap {
	
	public static boolean DISABLE_DRAW = false;
	
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			Logger.error("No file(1) provided and/or DISABLE_DRAW(2) provided.");
			System.exit(1);
		}
		
		DISABLE_DRAW = ParseUtils.parseBoolean(args[1], false);
		
		new LogicController(new SimpleWorldCodec().read(new File(args[0]))).find();
	}
	
}