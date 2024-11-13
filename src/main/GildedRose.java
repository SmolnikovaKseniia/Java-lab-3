package com.gildedrose;

import java.util.List;

class Item {
    String name;
    int sellIn;
    int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
}

interface ItemUpdater {
    void update(Item item);
}

class GildedRose {
    private List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemUpdater updater = ItemUpdaterFactory.getUpdater(item);
            updater.update(item);
        }
    }
}

class ItemUpdaterFactory {
    public static ItemUpdater getUpdater(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrieUpdater();
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassUpdater();
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasUpdater();
            default:
                return new StandardItemUpdater();
        }
    }
}

class AgedBrieUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
        item.sellIn--;
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}

class BackstagePassUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        increaseQuality(item);

        if (item.sellIn < 10) {
            increaseQuality(item);
        }
        if (item.sellIn < 5) {
            increaseQuality(item);
        }

        if (item.sellIn < 0) {
            item.quality = 0;
        }

        item.sellIn--;
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }
}

class SulfurasUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        // Sulfuras doesn't change in quality or sellIn
    }
}

class StandardItemUpdater implements ItemUpdater {
    @Override
    public void update(Item item) {
        decreaseQuality(item);
        if (item.sellIn < 0) {
            decreaseQuality(item);
        }
        item.sellIn--;
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
