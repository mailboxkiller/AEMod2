package com.Sandvoxel.appeng.tileEntiy;

import com.Sandvoxel.appeng.AESaveData.AEDriveCache;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koval on 4/25/2016.
 */
public class AEDriveEntiy extends TileEntity {

    private List<AEDriveCache> data = new ArrayList<AEDriveCache>();

    public void adddata ( ItemStack dart){
        NBTTagCompound nbt = (NBTTagCompound) dart.getTagCompound().getTag("bool");
        boolean test = nbt.getBoolean("test");
        data.add(new AEDriveCache(test));
    }
    public AEDriveCache getEntry(int i)
    {
        if(i < data.size())
        {
            return data.get(i);
        }
        return null;
    }
    public void deleteEntry(int i)
    {
        if(i < data.size())
        {
            data.remove(i);
        }
    }
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        data = new ArrayList<AEDriveCache>();

        NBTTagList entryList = (NBTTagList) compound.getTag("teleports");
        for(int i = 0; i < entryList.tagCount(); i++)
        {
            NBTTagCompound entryCompound = entryList.getCompoundTagAt(i);
            AEDriveCache entry = AEDriveCache.readEntryFromNBT(entryCompound);
            data.add(entry);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        NBTTagList entryList = new NBTTagList();
        for(AEDriveCache entry : data)
        {
            NBTTagCompound entryCompound = new NBTTagCompound();
            entry.writeEntryToNBT(entryCompound);
            entryList.appendTag(entryCompound);
        }

        compound.setTag("teleports", entryList);
    }



}

