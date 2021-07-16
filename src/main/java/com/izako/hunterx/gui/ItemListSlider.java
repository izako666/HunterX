package com.izako.hunterx.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.gui.ComputerScreen.PCEntry;
import com.izako.wypi.WyHelper;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.client.gui.ScrollPanel;

public class ItemListSlider extends ScrollPanel
{

	private static final int ENTRY_HEIGHT = 20;
	public List<PCEntry> ENTRIES = new ArrayList<>();
	public Screen parent;
	public PCEntry selectedEntry;
	public ItemListSlider.onActivateEntry onActivate = (x,y) -> {};
	public ItemListSlider.onInitialClickEntry onInitClick = (x,y) -> {};

	public ItemListSlider(Minecraft mc, int width, int height, int x, int y)
	{
		super(mc,width,height,y,x);
		this.parent = mc.currentScreen;

	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int partialTicks)
	{
		return true;
	}

	@Override
	protected int getContentHeight()
	{
		return (int) ((this.ENTRIES.size() * (ENTRY_HEIGHT * 1.25)) + 2);
	}

	@Override
	protected int getScrollAmount()
	{
		return 10;
	}

	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY)
	{
		for (PCEntry entry : this.ENTRIES)
		{
			int y = relativeY;
			int x = (this.parent.width / 2 - 109) + 40;
			ItemStack stack = entry.info.isEmpty() ? entry.item : new ItemStack(Items.ENCHANTED_BOOK);

			renderItem(stack, this.left , y - 1);
			if (this.selectedEntry != null && this.selectedEntry == entry)
				WyHelper.drawColourOnScreen(Color.WHITE.getRGB(), 100, this.left, y - 4, this.width+18, 24, 1);
			
			this.drawSizedString(entry.getName(), this.left + 100, y + 4, 0.8f, -1);
			this.drawSizedString(entry.getPrice() + "", this.left + 180, y + 4, 0.8f, -1);
			relativeY += ENTRY_HEIGHT * 1.25;
		}
	}

	public PCEntry findStackEntry(final int mouseX, final int mouseY)
	{
		double offset = (mouseY - this.top) + this.scrollDistance;
		boolean isHovered = mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height;

		if (offset <= 0 || !isHovered)
			return null;

		int lineIdx = (int) (offset / (ENTRY_HEIGHT * 1.25));
		if (lineIdx >= this.ENTRIES.size())
			return null;

		PCEntry entry = this.ENTRIES.get(lineIdx);
		if (entry != null && mouseX >= this.left && mouseX <= this.right && mouseY <= this.bottom)
			return entry;

		return null;
	}

	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button)
	{
		PCEntry entry = this.findStackEntry((int) mouseX, (int) mouseY);

		boolean isHovered = mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height;

		if (isHovered && entry != null)
		{
			if(this.selectedEntry == entry) {
				this.onActivateEntry(entry);
			} else {
			this.selectedEntry = entry;
			this.onClickEntry(entry);
			}
		} 

		return super.mouseClicked(mouseX, mouseY, button);
	}

	public void removeEntry(int index)
	{
		this.ENTRIES.remove(index);
	}
	
	
	public void renderItem(ItemStack stack, int posX, int posY)
	{	
		parent.getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(stack, posX, posY);
	}

	public void drawSizedString(String txt, int x, int y, float scale, int color)
	{
		RenderSystem.pushMatrix();
		RenderSystem.translated(x, y, 1);
		RenderSystem.scalef(scale, scale, scale);
			
		if (color == -1)
			color = WyHelper.hexToRGB("#FFFFFF").getRGB();
			
		this.drawCenteredString(txt, 0, 0, color);

		RenderSystem.popMatrix();
	}
	public void drawCenteredString(String txt, int posX, int posY, int color)
	{
		WyHelper.drawStringWithBorder(this.parent.getMinecraft().fontRenderer, txt, posX - this.parent.getMinecraft().fontRenderer.getStringWidth(txt) / 2, posY, color);
	}
	
	
	public void onActivateEntry(PCEntry entry) {

		this.onActivate.onActivate(entry, this);
	}
	public void onClickEntry(PCEntry entry) {

		this.onInitClick.onInitClick(entry, this);
	}


	interface onActivateEntry {
		
		void onActivate(PCEntry entry,ItemListSlider slider);
	}

	interface onInitialClickEntry {
		
		void onInitClick(PCEntry entry,ItemListSlider slider);
	}

}
