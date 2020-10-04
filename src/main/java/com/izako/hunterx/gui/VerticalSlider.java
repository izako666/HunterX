package com.izako.hunterx.gui;

import com.izako.hunterx.Main;
import com.izako.hunterx.izapi.IZAHelper;

import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class VerticalSlider extends Button {

	public static final ResourceLocation SLIDER_ICONS = new ResourceLocation(Main.MODID, "textures/gui/slider_icons.png");
	public double minValue = 0;
	public double value = 0;
	public double maxValue = 10.0;
	public double inSliderLength = 30;
	public VerticalSlider(int x, int y, int width, int height, double min,double max) {
		super(x, y, width, height, "", (b)->{});
		this.minValue = min;
		this.maxValue = max;
	}


	@Override
	protected void onDrag(double mX, double mY, double p_onDrag_5_, double p_onDrag_7_) {
		
		if(mY >= this.y && mY <= (this.y + this.getHeight())) {
			this.value = this.posToValue(mY);
		} else {
			this.onRelease(mX, mY);
		}
		
	}


	private double posToValue(double posY) {
		double val = (((posY - this.y) * (double)((maxValue - minValue))) / (double)(((this.y + this.height) - this.y))) + minValue;
		return val;
	}
	
	private double valueToPos() {
	//	double pos = (((this.value - this.minValue) * (double)((this.height))) / (double)(((this.maxValue) - this.minValue))) + this.y;
		double pos = IZAHelper.fromRangeToRange(minValue, maxValue, this.y + inSliderLength + 1, (this.y
				+ height) + 2,this.value);
		
		return pos;
		
	}
	@Override
	public void renderButton(int mX, int mY, float idfk) {
		IZAHelper.drawIMG(SLIDER_ICONS, this.x, this.y, 0, 0, 8, 2, 1, 8, 2);
		IZAHelper.drawIMG(SLIDER_ICONS, this.x, this.y + 2, 0, 2, 8, this.height, 1, 8, 11);
		IZAHelper.drawIMG(SLIDER_ICONS, this.x, this.y + 2 + this.height, 0, 13, 8, 2, 1, 8, 2);

		IZAHelper.drawIMG(SLIDER_ICONS, this.x + 1, (int) ((int) this.valueToPos() -(inSliderLength)), 0, 16, 6, 1, 2, 6, 1);
		IZAHelper.drawIMG(SLIDER_ICONS, this.x + 1, (int) ((int) (this.valueToPos() - inSliderLength + 1)), 0, 17, 6, (int) inSliderLength, 2, 6, 3);
	    IZAHelper.drawIMG(SLIDER_ICONS, this.x + 1, (int) ((int) this.valueToPos() + 1), 0, 20, 6, 1, 2, 6, 1);

	}

}
