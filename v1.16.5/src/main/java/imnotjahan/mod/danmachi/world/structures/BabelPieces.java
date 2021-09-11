package imnotjahan.mod.danmachi.world.structures;

import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.Reference;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class BabelPieces
{
    /*
     * Here is a video on how to save a structure with structure blocks. https://www.youtube.com/watch?v=ylGFb4F4xVk&t=1s
     * Once saved, the structure nbt file is store in that world's save folder within the generated folder inside.
     *
     * Move the nbt file of your structure into data.mod_id.structures folder under src/main/resources in your mod.
     * That's data folder. Not assets. I messed up before and put it in the wrong place lmao. It goes in data folder!
     * Make sure the nbt file name is all lowercase and uses no spaces. That's the naming convention.
     *
     * Here, I have two structure nbt files named run_down_house_left_side.nbt and run_down_house_right_side.nbt
     * and I access them with the following resource locations below.
     */
    private static final ResourceLocation BABEL = new ResourceLocation(Reference.MODID, "babel");

    /*
     * Begins assembling your structure and where the pieces needs to go.
     */
    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList,
                             Random random)
    {
        int x = pos.getX();
        int z = pos.getZ();

        // This is how we factor in rotation for multi-piece structures.
        //
        // I would recommend using the OFFSET map above to have each piece at correct height relative of each other
        // and keep the X and Z equal to 0. And then in rotations, have the centermost piece have a rotation
        // of 0, 0, 0 and then have all other pieces' rotation be based off of the bottommost left corner of
        // that piece (the corner that is smallest in X and Z).
        //
        // Lots of trial and error may be needed to get this right for your structure.
        BlockPos rotationOffset = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffset.offset(x, pos.getY(), z);
        pieceList.add(new BabelPieces.Piece(templateManager, BABEL, blockpos, rotation));
    }

    /*
     * Here's where some voodoo happens. Most of this doesn't need to be touched but you do
     * have to pass in the IStructurePieceType you registered into the super constructors.
     *
     * The method you will most likely want to touch is the handleDataMarker method.
     */
    public static class Piece extends TemplateStructurePiece
    {
        private ResourceLocation resourceLocation;
        private Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(Structures.RDHP, 0);
            this.resourceLocation = resourceLocationIn;
            this.templatePosition = pos;
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(Structures.RDHP, tagCompound);
            this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.get(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        protected void handleDataMarker(String p_186175_1_, BlockPos p_186175_2_, IServerWorld p_186175_3_, Random p_186175_4_, MutableBoundingBox p_186175_5_)
        {

        }
    }
}
