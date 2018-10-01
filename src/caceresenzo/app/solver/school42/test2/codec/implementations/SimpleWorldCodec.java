package caceresenzo.app.solver.school42.test2.codec.implementations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import caceresenzo.app.solver.school42.test2.codec.WorldCodec;
import caceresenzo.app.solver.school42.test2.commands.Interpreter;
import caceresenzo.app.solver.school42.test2.function.Function;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.player.PlayerOrientation;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.tiles.StarTile;
import caceresenzo.app.solver.school42.test2.world.tiles.Tile;
import caceresenzo.app.solver.school42.test2.world.tiles.TileColor;
import caceresenzo.libs.string.StringUtils;

public class SimpleWorldCodec extends WorldCodec {
	
	public static final String PROPERTY_COLOR = "color";
	public static final String PROPERTY_TYPE = "type";
	
	@Override
	public World read(File file) throws Exception {
		List<String> lines = new ArrayList<>(Arrays.asList(StringUtils.fromFile(file).split("\n")));
		
		Tile[][] worldTiles = new Tile[World.WORLD_MAX_SIZE][World.WORLD_MAX_SIZE];
		Player player = null;
		List<Function> functions = new ArrayList<>();
		List<Interpreter> availableCommands = new ArrayList<>();
		
		for (String line : new ArrayList<>(lines)) {
			if (!line.startsWith("#")) {
				continue;
			}
			
			lines.remove(line);
			
			if (line.startsWith("#FUNCTION")) {
				String[] functionData = line.split(";");
				
				String functionId = functionData[1].split("id=")[1];
				int functionSize = Integer.parseInt(functionData[2].split("size=")[1]);
				
				functions.add(new Function(functionId, functionSize));
			} else if (line.startsWith("#ACTIONS")) {
				String[] availableCommandsArray = line.split(";")[1].split("\\,");
				
				for (String commandString : availableCommandsArray) {
					availableCommands.add(Interpreter.fromString(commandString));
				}
			}
		}
		
		for (int x = 0; x < lines.size(); x++) {
			int y = 0;
			
			Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(lines.get(x));
			while (matcher.find()) {
				y++;
				
				Class<? extends Tile> type = Tile.class;
				TileColor color = null;
				
				String[] tileProperties = matcher.group(1).split("\\,");
				for (String property : tileProperties) {
					if (!StringUtils.validate(property)) {
						continue;
					}
					
					if (property.contains("=")) {
						String[] split = property.split("=");
						
						String key = split[0];
						String value = split[1];
						
						switch (key) {
							case PROPERTY_COLOR: {
								color = TileColor.fromString(value);
								break;
							}
							
							case PROPERTY_TYPE: {
								switch (value.toUpperCase()) {
									case "STAR": {
										type = StarTile.class;
										break;
									}
									
									default: {
										throw new IllegalStateException("Unknown tile property: [type]=" + property);
									}
								}
								break;
							}
							
							default: {
								throw new IllegalStateException("Unknown tile property: " + property);
							}
						}
					} else {
						if (property.toUpperCase().startsWith("PLAYER")) {
							PlayerOrientation orientation = PlayerOrientation.valueOf(property.toUpperCase().split("#")[1]);
							player = new Player(x, y, orientation);
						} else {
							throw new IllegalStateException("Unknown tile property: " + property);
						}
					}
				}
				
				if (color != null) {
					Tile tile = type.newInstance();
					tile.setColor(color);
					
					worldTiles[y][x] = tile;
				}
			}
		}
		
		return new World(worldTiles, player, functions, availableCommands);
	}
	
}