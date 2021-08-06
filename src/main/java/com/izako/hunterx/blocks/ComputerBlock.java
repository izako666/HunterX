package com.izako.hunterx.blocks;

import com.izako.hunterx.data.worlddata.ModWorldData;
import com.izako.hunterx.init.ModItems;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.ActivateComputerPacket;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;

public class ComputerBlock extends Block {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;


	private static final VoxelShape SHAPE_N= VoxelShapes.or(
			Block.makeCuboidShape(0, 0, 4, 16, 13, 16),
			Block.makeCuboidShape(1, 0, 0, 3, 1, 3),
			Block.makeCuboidShape(6, 0, 1, 13, 1, 3)
			);	
	private static final VoxelShape SHAPE_S= VoxelShapes.or(
			Block.makeCuboidShape(0, 0, 0, 16, 13, 12),
			Block.makeCuboidShape(13, 0, 13, 15, 1, 16),
			Block.makeCuboidShape(3, 0, 13, 10, 1, 15)
			);	
	private static final VoxelShape SHAPE_W= VoxelShapes.or(
			Block.makeCuboidShape(0, 0, 0, 12, 13, 16),
			Block.makeCuboidShape(13, 0, 1, 16, 1, 3),
			Block.makeCuboidShape(13, 0, 6, 15, 1, 13)
			);	
	private static final VoxelShape SHAPE_E= VoxelShapes.or(
			Block.makeCuboidShape(4, 0, 0, 16, 13, 16),
			Block.makeCuboidShape(0, 0, 13, 3, 1, 15),
			Block.makeCuboidShape(1, 0, 3, 3, 1, 10)
			);	

	public ComputerBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(FACING)) {
		case DOWN:
			return SHAPE_N;
		case EAST:
			return SHAPE_E;
		case NORTH:
			return SHAPE_S;
		case SOUTH:
			return SHAPE_N;
		case UP:
			return SHAPE_N;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
			}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
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
		
		if(!worldIn.isRemote()) {
			ModWorldData data = ModWorldData.get((ServerWorld) worldIn);
			boolean isHunter = player.inventory.hasItemStack(new ItemStack(ModItems.HUNTER_LICENSE));
			PacketHandler.INSTANCE.sendTo(new ActivateComputerPacket(data.getNormalStock(),data.getHunterStock(), isHunter), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, result);
	}
	
}
