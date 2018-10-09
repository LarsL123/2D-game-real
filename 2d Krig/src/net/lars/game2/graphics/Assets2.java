 package net.lars.game2.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

import net.lars.game2.engine.textures.Texture;
import net.lars.game2.utils.fileUtils.MyFile;


/**
 * 	
 * @Info:
 *		All assets for my game.
 * @Date 21. juni 2018
 * @Author LarsL123
 *
 *
 * @TODO:
 * 		Remove and make something that is not always static. Something that that loads and unloads when needed.
 * 
 * @Suggestions:
 *
 */
public class Assets2 {
	
	public static final int tileSize = 16;
	public static final int invLength = 12;
	public static final int invCellSize = 25;
	public static final int invCellSpace = 4;
	public static final int invCellBorderSpace = 4;
	
	public static Font font28;
	public static Font font18;
	
	public static Font openSansRegular28;

	public static Texture dirtTile, stoneTile, grassTile;
	public static Texture[] grassTiles, hill, standardWater, caveEntrance;
	
	public static Texture noImage;
	
	public static Texture inventorey;
	public static Texture SelectionUI2D;
	public static Texture buttonImage1;
	public static Texture editTextIcon;
	
	public static Texture item1, item2;
	
	//TODO Jeg må fikse på caracter bildet sån at et ikke er så langt og jeg må putte inventory UI på det samme bildet så+ jeg kan ha det som et stort bilde !!
	// Jeg må også organisere res mappan i flare mapper

	public static Texture tile_cell;
	public static Texture tile_selected;
	public static Texture tree_one;
	public static Texture stone_one;
	public static Texture house_one;
	
	public static Texture[] currentTiles;
	
	public static Texture testTileSet;

	public static Texture caracterHoleImage;
	public static Texture[] player_down, player_up, player_left, player_right;
	public static Texture[] startButton;
	
	public static void init(){
		font28 = FontlLoader.loadFont("res/fonts/font1.ttf", 28);
		font18 = FontlLoader.loadFont("res/fonts/font1.ttf", 18);
		
		openSansRegular28 = FontlLoader.loadFont("res/fonts/open-sans/OpenSans-Regular.ttf", 28);
		
		tree_one = loadNearestTexture("res/textures/entities/tree_one.png");
		
		caracterHoleImage = loadNearestTexture("res/textures/entities/16x16Caracter.png");
		
		testTileSet = loadNearestTexture("res/textures/testTilesetPic.png");
//		BufferedImage image = ImageLoader.loadImage("res/textures/entities/tree_one.png");
		
		
//
//		
//		//Items
//		SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/items/Potion.png"));
//		item1 = items.crop(0, 0, tileSize, tileSize);
//		item2 = items.crop(tileSize, 0, tileSize, tileSize);
//		
//		//Inventorey
//		inventorey = ImageLoader.loadImage("/textures/uiStuff/Ineventorey.png");
//		
//		//UI
//		SelectionUI2D = ImageLoader.loadImage("/textures/uiStuff/2DSelectionUi.png");
//		buttonImage1 = ImageLoader.loadImage("/UIDesign/ButtonTexture1.png");
//		editTextIcon = ImageLoader.loadImage("/UIDesign/editIcon.png");
//		
//		
//		//Fonts
//		font28 = FontlLoader.loadFont("res/fonts/font1.ttf", 28);
//		font18 = FontlLoader.loadFont("res/fonts/font1.ttf", 18);
//		
//		openSansRegular28 = FontlLoader.loadFont("res/fonts/open-sans/OpenSans-Regular.ttf", 28);
//		
////		----------------------Textures--------------------------------------------
//		
//		//TODO Random Tree
//		tree_one = ImageLoader.loadImage("/textures/entities/tree_one.png");
//		
//		//Tile Sets
//		
//		SpriteSheet texturesImage = new SpriteSheet(ImageLoader.loadImage("/textures/tileSets/world1tiles.png"));
//		
////		ArrayList<BufferedImage> textures = new ArrayList<BufferedImage>();
////		
////		
////		textures.add(0,texturesImage.crop(0, 0, tileSize, tileSize));
////		textures.add(1,texturesImage.crop(1 * tileSize, 0, tileSize, tileSize));
////		textures.add(2,texturesImage.crop(2 * tileSize, 0, tileSize, tileSize));
////		textures.add(3,texturesImage.crop(0, 1, tileSize, tileSize));
////		textures.add(4,texturesImage.crop(1 * tileSize, 1 * tileSize, tileSize, tileSize));
////		textures.add(5,texturesImage.crop(2 * tileSize, 1 * tileSize, tileSize, tileSize));
////		textures.add(6,texturesImage.crop(0, 2 * tileSize, tileSize, tileSize));
////		textures.add(7,texturesImage.crop(1 * tileSize, 2 * tileSize, tileSize, tileSize));
////		textures.add(8,texturesImage.crop(2 * tileSize, 2 * tileSize, tileSize, tileSize));
//////		textures.add(e);
//////		textures.add(e);
//////		textures.add(e);
//		
//		
//		grassTiles = new BufferedImage[16];
//		
//		grassTiles[0] = texturesImage.crop(0, 0, tileSize, tileSize);
//		grassTiles[1] = texturesImage.crop(1 * tileSize, 0, tileSize, tileSize);
//		grassTiles[2] = texturesImage.crop(2 * tileSize, 0, tileSize, tileSize);
//		grassTiles[3] = texturesImage.crop(0, 1, tileSize, tileSize);
//		grassTiles[4] = texturesImage.crop(1 * tileSize, 1 * tileSize, tileSize, tileSize);
//		grassTiles[5] = texturesImage.crop(2 * tileSize, 1 * tileSize, tileSize, tileSize);
//		grassTiles[6] = texturesImage.crop(0, 2 * tileSize, tileSize, tileSize);
//		grassTiles[7] = texturesImage.crop(1 * tileSize, 2 * tileSize, tileSize, tileSize);
//		grassTiles[8] = texturesImage.crop(2 * tileSize, 2 * tileSize, tileSize, tileSize);
//		
//		hill = new BufferedImage[9];
//		
//		hill[0] = texturesImage.crop(3 * tileSize, 0, tileSize, tileSize);
//		hill[1] = texturesImage.crop(4 * tileSize, 0, tileSize, tileSize);
//		hill[2] = texturesImage.crop(4 * tileSize, 0, tileSize, tileSize);
//		hill[3] = texturesImage.crop(3 * tileSize, 1, tileSize, tileSize);
//		hill[4] = texturesImage.crop(4 * tileSize, 1 * tileSize, tileSize, tileSize);
//		hill[5] = texturesImage.crop(5 * tileSize, 1 * tileSize, tileSize, tileSize);
//		hill[6] = texturesImage.crop(3 * tileSize, 2 * tileSize, tileSize, tileSize);
//		hill[7] = texturesImage.crop(4 * tileSize, 2 * tileSize, tileSize, tileSize);
//		hill[8] = texturesImage.crop(4 * tileSize, 2 * tileSize, tileSize, tileSize);
//		
//		standardWater = new BufferedImage[9];
//		standardWater[0] = texturesImage.crop(0 * tileSize, 3 * tileSize, tileSize, tileSize);
//		standardWater[1] = texturesImage.crop(1 * tileSize, 3 * tileSize, tileSize, tileSize);
//		standardWater[2] = texturesImage.crop(2 * tileSize, 3 * tileSize, tileSize, tileSize);
//		standardWater[3] = texturesImage.crop(0 * tileSize, 4 * tileSize, tileSize, tileSize);
//		standardWater[4] = texturesImage.crop(1 * tileSize, 4 * tileSize, tileSize, tileSize);
//		standardWater[5] = texturesImage.crop(2 * tileSize, 4 * tileSize, tileSize, tileSize);
//		standardWater[6] = texturesImage.crop(0 * tileSize, 5 * tileSize, tileSize, tileSize);
//		standardWater[7] = texturesImage.crop(1 * tileSize, 5 * tileSize, tileSize, tileSize);
//		standardWater[8] = texturesImage.crop(2 * tileSize, 5 * tileSize, tileSize, tileSize);
//		
//		caveEntrance = new BufferedImage[6];
//		
//		caveEntrance[0] = texturesImage.crop(3 * tileSize, 3 * tileSize, tileSize, tileSize);
//		caveEntrance[1] = texturesImage.crop(4 * tileSize, 3 * tileSize, tileSize, tileSize);
//		caveEntrance[2] = texturesImage.crop(5 * tileSize, 3 * tileSize, tileSize, tileSize);
//		caveEntrance[3] = texturesImage.crop(3 * tileSize, 4 * tileSize, tileSize, tileSize);
//		caveEntrance[4] = texturesImage.crop(4 * tileSize, 4 * tileSize, tileSize, tileSize);
//		caveEntrance[5] = texturesImage.crop(5 * tileSize, 4 * tileSize, tileSize, tileSize);
//		
//		
//		
//		stoneTile = texturesImage.crop(tileSize, 0, tileSize, tileSize);
//		dirtTile = texturesImage.crop(tileSize * 2, 0, tileSize, tileSize);
//		
//		//The Error Image
//		noImage = texturesImage.crop(tileSize * 21, tileSize * 21, tileSize, tileSize);
//		
//		
//		//Ui stuff
//		SpriteSheet menuUi = new SpriteSheet(ImageLoader.loadImage("/textures/uiStuff/menuUi.png"));
//		
//		startButton = new BufferedImage[2];
//		startButton[0] = menuUi.crop(0, 0, 138, 24);
//		startButton[1] = menuUi.crop(128, 0, 138, 24);
//		
//		//Animations
//		SpriteSheet caracterImage = new SpriteSheet(ImageLoader.loadImage("/textures/entities/16x16Caracter.png"));
//		
//		player_down = new BufferedImage[4];
//		player_up = new BufferedImage[4];
//		player_left = new BufferedImage[4];
//		player_right = new BufferedImage[4];
//		
//		player_down[0] = caracterImage.crop(0, 0, tileSize, tileSize);
//		player_down[1] = caracterImage.crop(tileSize, 0, tileSize, tileSize);
//		player_down[2] = caracterImage.crop(tileSize *  2, 0, tileSize, tileSize);
//		player_down[3] = caracterImage.crop(tileSize * 3, 0, tileSize, tileSize);
//		
//		player_left[0] = caracterImage.crop(0, tileSize, tileSize, tileSize);
//		player_left[1] = caracterImage.crop(tileSize, tileSize, tileSize, tileSize);
//		player_left[2] = caracterImage.crop(tileSize *  2, tileSize, tileSize, tileSize);
//		player_left[3] = caracterImage.crop(tileSize * 3, tileSize, tileSize, tileSize);
//		
//		player_right[0] = caracterImage.crop(0, tileSize * 2, tileSize, tileSize);
//		player_right[1] = caracterImage.crop(tileSize, tileSize * 2, tileSize, tileSize);
//		player_right[2] = caracterImage.crop(tileSize *  2, tileSize * 2, tileSize, tileSize);
//		player_right[3] = caracterImage.crop(tileSize * 3, tileSize * 2, tileSize, tileSize);
//		
//		player_up[0] = caracterImage.crop(0, tileSize * 3, tileSize, tileSize);
//		player_up[1] = caracterImage.crop(tileSize, tileSize * 3, tileSize, tileSize);
//		player_up[2] = caracterImage.crop(tileSize *  2, tileSize * 3, tileSize, tileSize);
//		player_up[3] = caracterImage.crop(tileSize * 3, tileSize * 3, tileSize, tileSize);
//
	}

	private static Texture loadGuiTexture(String name) {
		return Texture.createNewTexture(new MyFile(name)).noFiltering().clampEdges().create();
	}
	
	private static Texture loadNearestTexture(String name) {
		return Texture.createNewTexture(new MyFile(name)).nearestFiltering().clampEdges().create();
	}

}

