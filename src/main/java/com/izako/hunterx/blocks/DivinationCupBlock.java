package com.izako.hunterx.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.EnumStats;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class DivinationCupBlock extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	private static final EnumStats[] CONJURER_PAIR = new EnumStats[] {EnumStats.DEFENSE, EnumStats.ATTACK};
	private static final EnumStats[] EMISSION_PAIR = new EnumStats[] {EnumStats.HEALTH, EnumStats.ATTACK};
	private static final EnumStats[] MANIPULATOR_PAIR = new EnumStats[] {EnumStats.DEFENSE, EnumStats.SPEED};
	private static final EnumStats[] TRANSMUTER_PAIR = new EnumStats[] {EnumStats.SPEED, EnumStats.ATTACK};
	private static final EnumStats[] ENHANCER_PAIR = new EnumStats[] {EnumStats.HEALTH, EnumStats.DEFENSE};

	private static final VoxelShape SHAPE = Stream.of(
			VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 0, 6, 8.39782, 0.3, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 0, 6, 8.39782, 0.3, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 0, 6, 8.39782, 0.3, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 0, 6, 8.39782, 0.3, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 0, 6, 8.39782, 0.3, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 0, 7.60218, 10, 0.3, 8.39782), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 0, 7.60218, 10, 0.3, 8.39782), Block.makeCuboidShape(6, 0, 7.60218, 10, 0.3, 8.39782), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
			Block.makeCuboidShape(7.8, 0.2, 7.8, 8.3, 4.3, 8.2),
			Block.makeCuboidShape(7.1, 0.1, 7.2, 9.1, 0.5, 9),
			VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 4.2, 6, 8.39782, 4.5, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 4.2, 6, 8.39782, 4.5, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 4.2, 6, 8.39782, 4.5, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 4.2, 6, 8.39782, 4.5, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.60218, 4.2, 6, 8.39782, 4.5, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 4.2, 7.60218, 10, 4.5, 8.39782), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 4.2, 7.60218, 10, 4.5, 8.39782), Block.makeCuboidShape(6, 4.2, 7.60218, 10, 4.5, 8.39782), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
			VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 5.8, 8.43761, 7.5, 6.3), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 5.8, 8.43761, 7.5, 6.3), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 5.8, 8.43761, 7.5, 6.3), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 5.8, 8.43761, 7.5, 6.3), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 5.8, 8.43761, 7.5, 6.3), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 9.7, 8.43761, 7.5, 10.2), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 9.7, 8.43761, 7.5, 10.2), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 9.7, 8.43761, 7.5, 10.2), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 9.7, 8.43761, 7.5, 10.2), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.56239, 4.5, 9.7, 8.43761, 7.5, 10.2), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5.8, 4.5, 7.56239, 6.3, 7.5, 8.43761), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5.8, 4.5, 7.56239, 6.3, 7.5, 8.43761), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5.8, 4.5, 7.56239, 6.3, 7.5, 8.43761), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 4.5, 7.56239, 10.2, 7.5, 8.43761), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.7, 4.5, 7.56239, 10.2, 7.5, 8.43761), Block.makeCuboidShape(9.7, 4.5, 7.56239, 10.2, 7.5, 8.43761), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
			VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 5.6, 8.47739, 8.8, 6.1), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 5.6, 8.47739, 8.8, 6.1), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 5.6, 8.47739, 8.8, 6.1), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 5.6, 8.47739, 8.8, 6.1), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 5.6, 8.47739, 8.8, 6.1), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 9.9, 8.47739, 8.8, 10.4), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 9.9, 8.47739, 8.8, 10.4), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 9.9, 8.47739, 8.8, 10.4), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 9.9, 8.47739, 8.8, 10.4), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.52261, 7.3, 9.9, 8.47739, 8.8, 10.4), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5.6, 7.3, 7.52261, 6.1, 8.8, 8.47739), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5.6, 7.3, 7.52261, 6.1, 8.8, 8.47739), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5.6, 7.3, 7.52261, 6.1, 8.8, 8.47739), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.9, 7.3, 7.52261, 10.4, 8.8, 8.47739), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.9, 7.3, 7.52261, 10.4, 8.8, 8.47739), Block.makeCuboidShape(9.9, 7.3, 7.52261, 10.4, 8.8, 8.47739), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
			VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6.3, 8, 6.4, 9.7, 8.7, 7), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6.9, 8, 5.9, 9.2, 8.7, 6.5), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6.2, 8, 8.9, 9.7, 8.7, 9.2), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6.5, 8, 9.2, 9.55, 8.7, 9.8), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6.8, 8, 9.8, 9.35, 8.7, 10), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 8, 7, 10, 8.7, 8.9), VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7.6, 8.6, 7.5, 8.8, 8.8, 9.2), Block.makeCuboidShape(7.9, 8.6, 7.1, 8.3, 8.8, 7.7), IBooleanFunction.AND), IBooleanFunction.AND), IBooleanFunction.AND), IBooleanFunction.AND), IBooleanFunction.AND), IBooleanFunction.AND), IBooleanFunction.AND)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	

	public DivinationCupBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult result) {
		
		
		IAbilityData data = AbilityDataCapability.get(player);
		if((data.hasActiveAbility(ModAbilities.REN_ABILITY) || data.hasActiveAbility(ModAbilities.TEN_ABILITY))) {
			if(data.getNenType() != NenType.UNKNOWN)
				return ActionResultType.FAIL;
			List<NenType> rolls = new ArrayList<>();
			NenType affinity = IZAHelper.getAffinity(player.getUniqueID());
			rolls.addAll(Arrays.asList(affinity,affinity,affinity));
			NenType random = NenType.getTypeFromOrdinal(worldIn.getRandom().nextInt(NenType.values().length - 2) + 1);
			rolls.addAll(Arrays.asList(random,random));
			NenType type = getPersonalityType(player);
			if(type == null) {
				type = NenType.getTypeFromOrdinal(worldIn.getRandom().nextInt(NenType.values().length - 2) + 1);

			}
			rolls.addAll(Arrays.asList(type,type,type,type,type));
			
			NenType finalType = rolls.get(worldIn.rand.nextInt(rolls.size()));
			
			data.setNenType(finalType);
			
			if(finalType == NenType.ENHANCER) {
				player.sendMessage(new StringTextComponent("You see the water overflowing.... You are an enhancer.").applyTextStyle(TextFormatting.RED));
			}
			else if(finalType == NenType.CONJURER) {
				player.sendMessage(new StringTextComponent("You notice something forming amidst the water.... You are a conjurer.").applyTextStyle(TextFormatting.RED));
			}
			else if(finalType == NenType.EMITTER) {
				player.sendMessage(new StringTextComponent("You see the color of the water change.... You are an emitter.").applyTextStyle(TextFormatting.RED));
			}
			else if(finalType == NenType.MANIPULATOR) {
				player.sendMessage(new StringTextComponent("The leaf starts moving.... You are a manipulator.").applyTextStyle(TextFormatting.RED));
			}
			else if(finalType == NenType.TRANSMUTER) {
				player.sendMessage(new StringTextComponent("Nothing seems to have happened, you taste the water and its sweet.... You are a transmuter.").applyTextStyle(TextFormatting.RED));
			}
			

			
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, result);
	}
	
	private NenType getPersonalityType(PlayerEntity p) 
	{
		IHunterData hData = HunterDataCapability.get(p);
		EnumStats stat1 = hData.getFirstMaxed();
		EnumStats stat2 = hData.getSecondMaxed();
		
		if(Arrays.asList(CONJURER_PAIR).containsAll(Arrays.asList(stat1,stat2))) {
			return NenType.CONJURER;
		}
		if(Arrays.asList(TRANSMUTER_PAIR).containsAll(Arrays.asList(stat1,stat2))) {
			return NenType.TRANSMUTER;
		}
		if(Arrays.asList(ENHANCER_PAIR).containsAll(Arrays.asList(stat1,stat2))) {
			return NenType.ENHANCER;
		}
		if(Arrays.asList(EMISSION_PAIR).containsAll(Arrays.asList(stat1,stat2))) {
			return NenType.EMITTER;
		}
		if(Arrays.asList(MANIPULATOR_PAIR).containsAll(Arrays.asList(stat1,stat2))) {
			return NenType.MANIPULATOR;
		}

		return null;
	}
}
