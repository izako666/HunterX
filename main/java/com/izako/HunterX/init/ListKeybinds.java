package com.izako.HunterX.init;


import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ListKeybinds {

	public static KeyBinding one;
	public static KeyBinding two;
	public static KeyBinding three;
	public static KeyBinding four;
	public static KeyBinding five;
	public static KeyBinding six;
	public static KeyBinding seven;
	public static KeyBinding eight;
	public static KeyBinding nine;
	public static KeyBinding activate;
	public static KeyBinding openSelection;
    public static void register()
    {
        one = new KeyBinding("key.1", Keyboard.KEY_1, "key.categories.hntx");
        two = new KeyBinding("key.2", Keyboard.KEY_2, "key.categories.hntx");
        three = new KeyBinding("key.3", Keyboard.KEY_3, "key.categories.hntx");
        four = new KeyBinding("key.4", Keyboard.KEY_4, "key.categories.hntx");
        five = new KeyBinding("key.5", Keyboard.KEY_5, "key.categories.hntx");
        six = new KeyBinding("key.6", Keyboard.KEY_6, "key.categories.hntx");
        seven = new KeyBinding("key.7", Keyboard.KEY_7, "key.categories.hntx");
        eight = new KeyBinding("key.8", Keyboard.KEY_8, "key.categories.hntx");
        nine = new KeyBinding("key.9", Keyboard.KEY_9, "key.categories.hntx");
        activate = new KeyBinding("key.activate", Keyboard.KEY_GRAVE, "key.categories.hntrx");
        openSelection = new KeyBinding("key.openSelection", Keyboard.KEY_R, "key.categories.hntrx");

        ClientRegistry.registerKeyBinding(one);
        ClientRegistry.registerKeyBinding(two);
        ClientRegistry.registerKeyBinding(three);
        ClientRegistry.registerKeyBinding(four);
        ClientRegistry.registerKeyBinding(five);
        ClientRegistry.registerKeyBinding(six);
        ClientRegistry.registerKeyBinding(seven);
        ClientRegistry.registerKeyBinding(eight);
        ClientRegistry.registerKeyBinding(nine);
        ClientRegistry.registerKeyBinding(activate);
        ClientRegistry.registerKeyBinding(openSelection);


    }


}
