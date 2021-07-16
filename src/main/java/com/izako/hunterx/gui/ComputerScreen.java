package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.CGiveItemStackPacket;
import com.izako.hunterx.networking.packets.StatsUpdatePacket;
import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class ComputerScreen extends Screen {

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
	
	public int amountWanted = 0;
	public ComputerScreen() {
		super(new StringTextComponent(""));

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
			int shopScreenY = (int)(originy + (60 / 1.5));

		    Helper.drawIMG(FOREGROUND, (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1), (int)(originy + (60 / 1.5)), 0, 0, (int)(161 * 1.5), (int)(121 * 1.5), 1, 161, 121);
		
			RenderSystem.translated(0, 0, 1);
			this.ITEMS.render(mouseX, mouseY, partialTicks);
			RenderSystem.translated(0, 0, 0);

			

			WyHelper.drawStringWithBorder(font,"Name", shopScreenX+100 - font.getStringWidth("Name") / 2,  shopScreenY+5, Color.WHITE.getRGB());
			WyHelper.drawStringWithBorder(font,"Price", shopScreenX+180 - font.getStringWidth("Name") / 2,  shopScreenY+5, Color.WHITE.getRGB());

			if(this.ITEMS.selectedEntry != null)  {
				WyHelper.drawStringWithBorder(font,String.valueOf(amountWanted), shopScreenX+35 - font.getStringWidth(String.valueOf(amountWanted)) / 2,  shopScreenY+5, Color.BLUE.getRGB());

			}
		

			RenderSystem.popMatrix();
		}

	}

	@Override
	public void init() {
		 originx = this.width / 2 - (330 / 2);
		 originy = this.height / 2 - (240 / 2);

		int shopScreenX = (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1);
		int shopScreenY = (int)(originy + (60 / 1.5));

		MovingButton bartender = new MovingButton((int)(originx + 125),(int)(originy + 120), 70, 118,0,123, FOREGROUND, new Button.IPressable() {
			
			@Override	
			public void onPress(Button p_onPress_1_) {
				((ComputerScreen)Minecraft.getInstance().currentScreen).isTalking = true;
			}
		});
		
		this.ITEMS = new ItemListSlider(minecraft, (int)((160*1.5)-2), 160, (int)((originx) + ((320 / 2) - (160*1.5) / 2)), (int)(originy + 60));
		


		
		this.ITEMS.onInitClick = new ItemListSlider.onInitialClickEntry() {
			
			@Override
			public void onInitClick(PCEntry entry, ItemListSlider slider) {
				
				((ComputerScreen)Minecraft.getInstance().currentScreen).startedShopping=true;
			}
		};
		
		this.ITEMS.onActivate = (PCEntry entry, ItemListSlider slider) -> {
			
			IHunterData data = HunterDataCapability.get(this.minecraft.player);
			
			if(data.getJenny() >= (entry.getPrice() * this.amountWanted) && entry.getCount() >= this.amountWanted ) {
			data.setJenny((int) (data.getJenny() - (entry.getPrice() * this.amountWanted)));
			entry.setCount(entry.getCount() - this.amountWanted);
			ItemStack stack = entry.item.copy();
			stack.setCount(this.amountWanted);
			PacketHandler.INSTANCE.sendToServer(new CGiveItemStackPacket(stack));
			PacketHandler.INSTANCE.sendToServer(new StatsUpdatePacket(data,false));
			this.minecraft.player.inventory.addItemStackToInventory(stack);
			}
		};
	 this.addButton(bartender);
	 this.children.add(ITEMS);

	 openingStatement.event = new SequencedString.IRenderEndEvent() {
		
		@Override
		public void onEnd() {

			((ComputerScreen)Minecraft.getInstance().currentScreen).isShopping = true;
			((ComputerScreen)Minecraft.getInstance().currentScreen).isTalking = false;
			((ComputerScreen)Minecraft.getInstance().currentScreen).buttons.get(0).active = false;
			
			 
			 		}
	};
	}
	
	@Override
	public void tick() {
		
		int shopScreenX = (int)((originx) + ((320 / 2) - (160*1.5) / 2)-1);
		int shopScreenY = (int)(originy + (60 / 1.5));

		if(this.startedShopping) {
		
			GenericTexturedButton backwardsButton = new GenericTexturedButton(BACKWARD_BUTTON, shopScreenX+15, shopScreenY + 5, 10, 10, "", new Button.IPressable() {
		
		@Override
		public void onPress(Button arg0) {

			boolean clause = amountWanted > 0;
			if(clause)
				((ComputerScreen)Minecraft.getInstance().currentScreen).amountWanted--;
		}
	});
	 
	 GenericTexturedButton forwardsButton = new GenericTexturedButton(FORWARD_BUTTON, shopScreenX+45, shopScreenY + 5, 10, 10, "", new Button.IPressable() {
			
		@Override
		public void onPress(Button arg0) {

			boolean clause = ((ComputerScreen)Minecraft.getInstance().currentScreen).amountWanted <((ComputerScreen)Minecraft.getInstance().currentScreen).ITEMS.selectedEntry.getItem().getMaxStackSize();
			if(clause)
				((ComputerScreen)Minecraft.getInstance().currentScreen).amountWanted++;

				
		}
	});

	 this.addButton(backwardsButton);
	 this.addButton(forwardsButton);

	 this.startedShopping = false;
		}
		super.tick();

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

