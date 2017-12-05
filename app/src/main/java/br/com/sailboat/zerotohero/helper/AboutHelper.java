package br.com.sailboat.zerotohero.helper;

import android.content.Context;

import java.util.ArrayList;

import br.com.sailboat.canoe.recycler.RecyclerItem;
import br.com.sailboat.canoe.recycler.item.ImageTitleDividerRecyclerItem;
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem;
import br.com.sailboat.canoe.view.about.AboutAdapter;
import br.com.sailboat.zerotohero.BuildConfig;
import br.com.sailboat.zerotohero.R;

public class AboutHelper {

    public static ArrayList<RecyclerItem> getAbout(Context ctx) {
        ArrayList<RecyclerItem> items = new ArrayList<>();
        items.add(getImageWithTitle(ctx));
        items.add(getAppDescription(ctx));
        items.add(getVersion(ctx));
        items.add(getDevelopedBy(ctx));

        return items;
    }

    private static ImageTitleDividerRecyclerItem getImageWithTitle(Context ctx) {
        ImageTitleDividerRecyclerItem img = new ImageTitleDividerRecyclerItem(AboutAdapter.VIEW_TYPE_IMAGE_TITLE);
        img.setImageRes(R.drawable.ic_zero_to_hero_128dp);
        img.setTitle(ctx.getString(R.string.app_full_name));

        return img;
    }

    private static RecyclerItem getAppDescription(Context ctx) {
        LabelValueRecyclerItem item = new LabelValueRecyclerItem(AboutAdapter.VIEW_TYPE_LABEL_VALUE);
        item.setLabel(ctx.getString(R.string.label_description));
        item.setValue(ctx.getString(R.string.app_description));

        return item;
    }

    private static LabelValueRecyclerItem getVersion(Context ctx) {
        LabelValueRecyclerItem version = new LabelValueRecyclerItem(AboutAdapter.VIEW_TYPE_LABEL_VALUE);
        version.setLabel(ctx.getString(R.string.version));
        version.setValue(BuildConfig.VERSION_NAME);

        return version;
    }

    private static LabelValueRecyclerItem getDevelopedBy(Context ctx) {
        LabelValueRecyclerItem developed = new LabelValueRecyclerItem(AboutAdapter.VIEW_TYPE_LABEL_VALUE);
        developed.setLabel(ctx.getString(R.string.developed_by));
        developed.setValue("Brayan Bedritchuk");

        return developed;
    }

}
