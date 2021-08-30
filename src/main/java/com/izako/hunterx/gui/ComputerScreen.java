package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.AnimatedButton.SpriteTemplate;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.ActivateComputerPacket;
import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class ComputerScreen extends Screen {

	/* GOAL: make a buy button and a list of itemsWanted */
	
	public float originx = this.width / 2 - (330 - 2);
	public float originy = this.height / 2 - (240 / 2);
	public static final ResourceLocation BACKGROUND = new ResourceLocation(Main.MODID, "textures/gui/computer_background.png");
	public static final ResourceLocation FOREGROUND = new ResourceLocation(Main.MODID, "textures/gui/computer_foreground.png");
	
	public static final ResourceLocation BACKWARD_BUTTON = new ResourceLocation(Main.MODID, "textures/gui/backwardbutton.png");
	public static final ResourceLocation FORWARD_BUTTON = new ResourceLocation(Main.MODID, "textures/gui/forwardbutton.png");


	
	public SequencedString openingStatement = new SequencedString("You lookin ta know somethin'?.... I've also got some peculiar goods if it fancies ya...", 100, 100);

	public boolean isTalking = false;
	public boolean isShopping = false;
	
	public boolean startedShopping = false;
	public ItemListSlider ITEMS;
	public List<PCEntry> itemsWanted = new ArrayList<>();
	public MovingButton bartender;
	public AnimatedButton buy;
	
	//constructor variables
	List<PCEntry> hStock;
	List<PCEntry> nStock;
	boolean isHunter;
	public ComputerScreen(List<PCEntry> hStock, List<PCEntry> nStock, boolean isHunter) {
		super(new StringTextComponent(""));

		this.hStock = hStock;
		this.nStock = nStock;
		this.isHunter = isHunter;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {

		this.renderBackground();
		RenderSystem.pushMatrix();

		//this.getMinecraft().getTextureManager().bindTexture(BACKGROUND);
		//GuiUtils.drawTexturedModalRect((int)originx, (int)originy, 0, 0, 360, 240, 0);
		Helper.drawIMG(BACKGROUND, (int)originx, (int)originy, 0, 0, 360, 240, 0, 360 / 2, 240 / 2);


		this.buttons.forEach(b -> {b.render(mouseX, mouseY, partialTicks);});

		RenderSystem.popMatrix();
		if(isTalking) {
			RenderSystem.pushMatrix();
			RenderSystem.translated(originx + 160, originy+60, 0);
			RenderSystem.scaled(1.5, 1.5, 1);
		    Helper.drawIMG(FOREGROUND, 0, 0, 162, 0, 75, 55, 0, 75, 55);
			RenderSystem.scaled(1, 1, 1);
			RenderSystem.popMatrix();
			openingStatement.render((int)(originx+165),(int)(originy+65));

		}
		
		if(isShopping) {
			
			RenderSystem.pushMatrix();
			int shopScreenX = (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1);
			int shopScreenY = (int)(originy + (15 / 1.5));

		

		    Helper.drawIMG(FOREGROUND, (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1), (int)(originy + (15 / 1.5)), 0, 0, (int)(161 * 1.5), (int)(121 * 1.5), 0, 161, 121);

			this.ITEMS.render(mouseX, mouseY, partialTicks);

			WyHelper.drawStringWithBorder(font,"Name", shopScreenX+100 - font.getStringWidth("Name") / 2,  shopScreenY+5, Color.WHITE.getRGB());
			WyHelper.drawStringWithBorder(font,"Price", shopScreenX+180 - font.getStringWidth("Price") / 2,  shopScreenY+5, Color.WHITE.getRGB());

			if(this.ITEMS.selectedEntry != null)  {
				int count = this.itemsWanted.get(this.ITEMS.ENTRIES.indexOf(this.ITEMS.selectedEntry)).getCount();
				WyHelper.drawStringWithBorder(font,String.valueOf(count), shopScreenX+35 - font.getStringWidth(String.valueOf(count)) / 2,  shopScreenY+5, Color.BLUE.getRGB());

			}
		

			IHunterData hData = HunterDataCapability.get(Minecraft.getInstance().player);
			RenderSystem.popMatrix();
			this.buttons.forEach(b -> {b.render(mouseX, mouseY, partialTicks);});
			WyHelper.drawStringWithBorder(font,"Purchase Total: " + String.valueOf(this.calculatePurchase(itemsWanted)), shopScreenX+100 - font.getStringWidth("Purchase Total: " + String.valueOf(this.calculatePurchase(itemsWanted))) / 2,  shopScreenY+190, Color.WHITE.getRGB());
			WyHelper.drawStringWithBorder(font,"Your current balance: " + String.valueOf(hData.getJenny()), shopScreenX+100 - font.getStringWidth("Your current balance: " + String.valueOf(hData.getJenny())) / 2,  shopScreenY+210, Color.WHITE.getRGB());

		}

	}

	@Override
	public void init() {
		 originx = this.width / 2 - (330 / 2);
		 originy = this.height / 2 - (240 / 2);

		int shopScreenX = (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1);
		int shopScreenY = (int)(originy + (60 / 1.5));

		if((bartender != null && bartender.active) || bartender == null) {
		 bartender = new MovingButton((int)(originx + 125),(int)(originy + 120), 70, 118,0,123, FOREGROUND, new Button.IPressable() {
			
			@Override	
			public void onPress(Button p_onPress_1_) {
				((ComputerScreen)Minecraft.getInstance().currentScreen).isTalking = true;
			}
		});
		 this.addButton(bartender);
		}
		boolean hasBeenInit = !(this.ITEMS == null);
		this.ITEMS = new ItemListSlider(minecraft, (int)((160*1.5)-2), 160, (int)((originx) + ((321 / 2) - (160*1.5) / 2)), (int)(originy + 29));
		

		if(this.isHunter) {
			this.ITEMS.ENTRIES = this.hStock;
		} else {
			this.ITEMS.ENTRIES = this.nStock;
		}
		

		
		this.ITEMS.onInitClick = new ItemListSlider.onInitialClickEntry() {
			
			@Override
			public void onInitClick(PCEntry entry, ItemListSlider slider) {
				
				((ComputerScreen)Minecraft.getInstance().currentScreen).startedShopping=true;
			}
		};
		
	 this.itemsWanted = new ArrayList<>();
	 this.ITEMS.ENTRIES.forEach(e -> {
		 List<String> infoString = null;
		 if(e.getInfo() != null) {
		  infoString = new ArrayList<>();
		 for(SequencedString infoSq : e.getInfo()) {
			 infoString.add(infoSq.string);
		 }
		 }
		 this.itemsWanted.add(new PCEntry(e.getItem(), e.getPrice(), infoString,e.getName(),e.getCount()));
	 });
	 this.itemsWanted.forEach(i -> {
		 i.setCount(0);
	 });

	 if(hasBeenInit) {
		 this.children.add(ITEMS);
	 }
	 ResourceLocation MULTIPLE_CHOICE = new ResourceLocation(Main.MODID, "textures/gui/quest_choice_gui.png");

	SpriteTemplate temp1 = new SpriteTemplate(MULTIPLE_CHOICE, 0, 125, 2);
	SpriteTemplate temp2 = new SpriteTemplate(MULTIPLE_CHOICE, 0, 138, 2);
	if(this.buy != null) {
		this.buy = new AnimatedButton(width/2+90, (int)(originy + (300 / 1.5)), 26, 13, "", new Button.IPressable() {
			
			@Override
			public void onPress(Button but) {
				System.out.println("button works.");
			
				PacketHandler.INSTANCE.sendToServer(new ActivateComputerPacket(((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted));
				    Minecraft.getInstance().displayGuiScreen(null);
			}
			
		}, temp1, temp2);
		this.addButton(buy);

	}

	 openingStatement.event = new SequencedString.IRenderEndEvent() {
		
		@Override
		public void onEnd() {
			
			((ComputerScreen)Minecraft.getInstance().currentScreen).children.add(ITEMS);
			((ComputerScreen)Minecraft.getInstance().currentScreen).isShopping = true;
			((ComputerScreen)Minecraft.getInstance().currentScreen).isTalking = false;
			((ComputerScreen)Minecraft.getInstance().currentScreen).buttons.get(0).active = false;
			((ComputerScreen)Minecraft.getInstance().currentScreen).buttons.get(0).visible = false;

			 ResourceLocation MULTIPLE_CHOICE = new ResourceLocation(Main.MODID, "textures/gui/quest_choice_gui.png");

				SpriteTemplate temp1 = new SpriteTemplate(MULTIPLE_CHOICE, 0, 125, 2);
				SpriteTemplate temp2 = new SpriteTemplate(MULTIPLE_CHOICE, 0, 138, 2);
				((ComputerScreen)Minecraft.getInstance().currentScreen).buy = new AnimatedButton(width/2+90, (int)(originy + (300 / 1.5)), 26, 13, "", new Button.IPressable() {
						
						@Override
						public void onPress(Button but) {
							System.out.println("button works.");
						
							PacketHandler.INSTANCE.sendToServer(new ActivateComputerPacket(((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted));
							    Minecraft.getInstance().displayGuiScreen(null);
						}
						
					}, temp1, temp2);
				((ComputerScreen)Minecraft.getInstance().currentScreen).addButton(buy);

		}
			 		
	};
	}
	
	@Override
	public void tick() {
		
		int shopScreenX = (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1);
		int shopScreenY = (int)(originy + (15 / 1.5));

		if(this.startedShopping) {
		
			
			GenericTexturedButton backwardsButton = new GenericTexturedButton(BACKWARD_BUTTON, shopScreenX+15, shopScreenY + 5, 10, 10, "", new Button.IPressable() {
		
		@Override
		public void onPress(Button arg0) {

			PCEntry selected = ((ComputerScreen)Minecraft.getInstance().currentScreen).ITEMS.selectedEntry;
			int selectedIndex = ((ComputerScreen)Minecraft.getInstance().currentScreen).ITEMS.ENTRIES.indexOf(selected);
			
			if(selected == null)
				return;
			
			boolean clause = ((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).count > 0;
			if(clause)
				((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).setCount(((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).count - 1);
		}
	});
	 
	 GenericTexturedButton forwardsButton = new GenericTexturedButton(FORWARD_BUTTON, shopScreenX+45, shopScreenY + 5, 10, 10, "", new Button.IPressable() {
			
		@Override
		public void onPress(Button arg0) {

			PCEntry selected = ((ComputerScreen)Minecraft.getInstance().currentScreen).ITEMS.selectedEntry;
			int selectedIndex = ((ComputerScreen)Minecraft.getInstance().currentScreen).ITEMS.ENTRIES.indexOf(selected);
			
			if(selected == null)
				return;
			
			boolean clause = ((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).count < selected.item.getMaxStackSize();
			boolean clauseCount = ((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).count <  selected.getCount();
			if(clause && clauseCount)
				((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).setCount(((ComputerScreen)Minecraft.getInstance().currentScreen).itemsWanted.get(selectedIndex).count +1);
		}

				
		}
	);

	 this.addButton(backwardsButton);
	 this.addButton(forwardsButton);

	 this.startedShopping = false;
		}
		super.tick();

	}

	
	public int calculatePurchase(List<PCEntry> itemsWanted) {
		int total = 0;
		for(PCEntry entry : itemsWanted) {
			total += (entry.getPrice() * entry.getCount());
		}
		return total;
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {

		if(this.openingStatement.ticksExisted >= this.openingStatement.maxTicks) {
			this.openingStatement.ticksExisted = this.openingStatement.maxTicks + this.openingStatement.delayTicks;
		}

		if(this.openingStatement.ticksExisted > 1 && this.openingStatement.ticksExisted < this.openingStatement.maxTicks) {
			this.openingStatement.ticksExisted = this.openingStatement.maxTicks;
		}
		
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}

	
	public static class PCEntry {
		
		//Entry for any possible item that can be sold on a PC
		ItemStack item;
		float price;
		String name;
		int count;
		List<SequencedString> info = new ArrayList<>();
		public PCEntry(ItemStack item, float price, @Nullable List<String> information, String name,int count) {
			
			this.item = item;
			this.price = price;
			if(information != null) {

				for(String str : information) {
					this.info.add(new SequencedString(str, 100, 100));
				}
				}
			this.name = name;
			this.count = count;
		}
		public PCEntry(ItemStack item, float price,int count) {
			this(item,price,null, item.getDisplayName().getFormattedText(),count);
		}

		
		public ItemStack getItem() {
			return item;
		}

		public float getPrice() {
			return price;
		}

		public String getName() {
			return name;
		}
		
		public int getCount() {
			return count;
			}
		

		public void setCount(int v) {
			
			this.count= v;
			
			
		}
		public List<SequencedString> getInfo() {
			return info;
		}

		
		public CompoundNBT write() {
			CompoundNBT nbt = new CompoundNBT();
			
			nbt.putString("item", item.getItem().delegate.name().toString());
			nbt.putString("name", name);
			nbt.putFloat("price", price);
			nbt.putInt("count", count);
			
			if(this.info != null) {
				nbt.putBoolean("info", true);
				nbt.putInt("infosize", info.size());
				for(int i = 0; i < info.size(); i++) {
					nbt.putString("info" + ":" + String.valueOf(i), info.get(i).string);
				}
			} else {
				nbt.putBoolean("info", false);
			}
			return nbt;
		}
		
		public static PCEntry read(CompoundNBT nbt) {
			
			String name = nbt.getString("name");
			ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(nbt.getString("item"))));
			int count = nbt.getInt("count");
			float price = nbt.getFloat("price");
			List<String> info = null;
			
			if(nbt.getBoolean("info")) {
				int size = nbt.getInt("infosize");
				info = new ArrayList<>();
				for(int i = 0; i < size; i++) {

					info.add(nbt.getString("info:" + String.valueOf(i)));
			}
			
		}
			PCEntry entry = new PCEntry(stack,price,info,name,count);

			return entry;

		}}}

